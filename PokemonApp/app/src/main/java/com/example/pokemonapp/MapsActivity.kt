    package com.example.pokemonapp

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.pokemonapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.lang.Exception
import kotlin.concurrent.thread

    class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
        LoadPokemon()
    }

    var ACCESSLOCATION = 123
    fun checkPermission(){
        if(Build.VERSION.SDK_INT >=23){
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),ACCESSLOCATION)
                return
            }
        }
        GetUserLocation()
    }

    fun GetUserLocation(){
        Toast.makeText(this,"User Location access on",Toast.LENGTH_LONG).show()
        var myLocation= MyLocationListener()
        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)
        var mythread = myThread()
        mythread.start()
    }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            when(requestCode){
                ACCESSLOCATION->{
                    if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        GetUserLocation()
                    }else{
                        Toast.makeText(this,"User can`t access to your location",Toast.LENGTH_LONG).show()
                    }
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }
        var location:Location?=null
        inner class MyLocationListener:LocationListener {

            constructor(){
                location= Location("start")
                location!!.longitude=0.0
                location!!.longitude=0.0
            }
            override fun onLocationChanged(p0: Location) {
                location=p0
            }
        }

        var oldLocation:Location?=null
        inner class myThread:Thread{
            constructor():super(){
                oldLocation= Location("start")
                oldLocation!!.longitude=0.0
                oldLocation!!.longitude=0.0
            }

            override fun run(){
                while(true){
                    try {

                        if(oldLocation!!.distanceTo(location)==0f){
                            continue
                        }
                        oldLocation=location
                        runOnUiThread {
                            mMap!!.clear()
                            val sydney = LatLng(location!!.latitude,location!!.longitude)
                            mMap.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("Me")
                                .snippet("Here my location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17f))

                            for(i in 0..listPokemon.size-1){
                                var newPokemon=listPokemon[i]
                                if(newPokemon.IsCatch==false){
                                    val pokemonLct = LatLng(newPokemon.location!!.latitude,newPokemon.location!!.longitude)
                                    mMap.addMarker(MarkerOptions()
                                        .position(pokemonLct)
                                        .title(newPokemon.name!!)
                                        .snippet(newPokemon.des!!+", Power:"+newPokemon.power!!)
                                        .icon(BitmapDescriptorFactory.fromResource(newPokemon.image!!)))

                                    if(location!!.distanceTo(newPokemon.location)<10){
                                        newPokemon.IsCatch=true
                                        listPokemon[i]=newPokemon
                                        playerPower+=newPokemon.power!!
                                        Toast.makeText(applicationContext,
                                            "You catch new pokemon your new power is " + playerPower,
                                            Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                        Thread.sleep(1000)
                    }catch (ex:Exception){}
                }
            }
        }

        var playerPower = 0.0
        var listPokemon=ArrayList<Pokemon>()

        fun LoadPokemon(){
            listPokemon.add(Pokemon(R.drawable.charmander,
                "Charmander","Charmander from Japan",55.0,15.9870,108.2154)
            )
            listPokemon.add(Pokemon(R.drawable.bulbasaur,
                "Bulbasaur","Bulbasaur from USA",90.5,15.9852,108.2163))
            listPokemon.add(Pokemon(R.drawable.squirtle,
                "Squirtle","Squirtle from Iraq",33.5,15.9854,108.2139))
        }
    }