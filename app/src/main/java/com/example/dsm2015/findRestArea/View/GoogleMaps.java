package com.example.dsm2015.findRestArea.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by dsm2015 on 2017-05-16.
 */

public class GoogleMaps implements LocationListener,OnMapReadyCallback {
    private GoogleMap map;

    @Override
    public void onLocationChanged(Location location) {
        //디바이스의 위치정보가 변경되었을 때 실행되는 이벤트
        Log.d("GoogleMaps","onLocationChanged");
        if(map==null){
            return;
        }

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude())));
        Marker marker;
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).visible(true);
        marker = map.addMarker(markerOptions);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //활성/비활성 상태가 변경되었을 때 실행되는 이벤트
    }

    @Override
    public void onProviderEnabled(String provider) {
        //활성화 되었을 때 실행되는 이벤트
    }

    @Override
    public void onProviderDisabled(String provider) {
        //비활성화 되었을 때 실행되는 이벤트
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }
}
