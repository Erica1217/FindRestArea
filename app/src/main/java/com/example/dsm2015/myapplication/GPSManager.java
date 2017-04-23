package com.example.dsm2015.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

/**
 * Created by dsm2015 on 2017-04-06.
 */

public class GPSManager implements  LocationListener{
    private static final int ACCESS_FINE_LOCATION = 1;
    private static final int ACCESS_COARSE_LOCATION = 2;

    // Location 제공자에서 정보를 얻어오기(GPS)
    // 1. Location을 사용하기 위한 권한을 얻어와야한다 AndroidManifest.xml
    //     ACCESS_FINE_LOCATION : NETWORK_PROVIDER, GPS_PROVIDER
    //     ACCESS_COARSE_LOCATION : NETWORK_PROVIDER
    // 2. LocationManager 를 통해서 원하는 제공자의 리스너 등록
    // 3. GPS 는 에뮬레이터에서는 기본적으로 동작하지 않는다
    // 4. 실내에서는 GPS_PROVIDER 를 요청해도 응답이 없다.  특별한 처리를 안하면 아무리 시간이 지나도
    //    응답이 없다.
    //    해결방법은
    //     ① 타이머를 설정하여 GPS_PROVIDER 에서 일정시간 응답이 없는 경우 NETWORK_PROVIDER로 전환
    //     ② 혹은, 둘다 한꺼번헤 호출하여 들어오는 값을 사용하는 방식.

    // The minimum distance to change Updates in meters, The minimum time between updates in milliseconds
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    static Context mContext;
    static GPSManager instance = null;

    /* LocationManager 객체를 얻음*/
    final LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

    /* 싱글톤 */
    private GPSManager() {
    }

    static public GPSManager getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new GPSManager();
        }
        Log.d("getInstace", "되랴 얍");
        return instance;
    }

    /* GPS신호 업데이트 */
    public void GPSUpdate() {
        checkPermission();

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                MIN_TIME_BW_UPDATES, // 통지사이의 최소 시간간격 (miliSecond)
                MIN_DISTANCE_CHANGE_FOR_UPDATES, // 통지사이의 최소 변경거리 (m)
                this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                MIN_TIME_BW_UPDATES, // 통지사이의 최소 시간간격 (miliSecond)
                MIN_DISTANCE_CHANGE_FOR_UPDATES, // 통지사이의 최소 변경거리 (m)
                this);

    }

    public void removeUpdate() {
        locationManager.removeUpdates(this);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
    }

    @Override
    public void onLocationChanged(Location location) {
        //여기서 위치값이 갱신되면 이벤트가 발생한다.
        //값은 Location 형태로 리턴되며 좌표 출력   방법은 다음과 같다.

        Log.d("test", "onLocationChanged, location:" + location);
        double longitude = location.getLongitude(); //경도
        double latitude = location.getLatitude();   //위도
        double altitude = location.getAltitude();   //고도
        float accuracy = location.getAccuracy();    //정확도
        String provider = location.getProvider();   //위치제공자
        //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
        //Network 위치제공자에 의한 위치변화
        //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.

        Log.d("GPSManager", "onLocationChanged, 경도:" + String.valueOf(longitude) + "\n"
                + "위도 : " + String.valueOf(latitude) + "\n"
        );
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void checkPermission() {
        Log.d("ACEESS_FINE_LOCATION",String.valueOf(ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)));
        if((ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED )
                || (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED )) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 사용자가 임의로 권한을 취소시킨 경우
                // 권한 재요청
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},ACCESS_FINE_LOCATION);
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_COARSE_LOCATION);
            } else {
                // 권한 요청 (최초 요청)
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},ACCESS_FINE_LOCATION);
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_COARSE_LOCATION);
            }
        }
    }




}
