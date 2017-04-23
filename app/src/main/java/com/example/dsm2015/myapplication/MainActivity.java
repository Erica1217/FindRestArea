package com.example.dsm2015.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int ACCESS_FINE_LOCATION = 1;
    private static final int ACCESS_COARSE_LOCATION = 2;
    TextView textViewX;
    TextView textViewY;
    Button button;
    GPSManager gpsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Main", "onCreate");

        textViewX = (TextView) findViewById(R.id.x);
        textViewY = (TextView) findViewById(R.id.y);
        button = (Button) findViewById(R.id.button);

        setTextViewXY("위치정보 미수신중");
        gpsManager = GPSManager.getInstance(this);

        setTextViewXY("수신중");
        gpsManager.GPSUpdate();
    }

//        // LocationManager 객체를 얻어온다
//        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//                    setTextViewXY("수신중..");
//                        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
//                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
//                                100, // 통지사이의 최소 시간간격 (miliSecond)
//                                1, // 통지사이의 최소 변경거리 (m)
//                                mLocationListener);
//                        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
//                                100, // 통지사이의 최소 시간간격 (miliSecond)
//                                1, // 통지사이의 최소 변경거리 (m)
//                                mLocationListener);
//                }catch(SecurityException ex){
//                    Log.e("location updates error",ex.toString());
//
//                }
//            }
//        });
//    }
//
//    private final LocationListener mLocationListener = new LocationListener() {
//        public void onLocationChanged(Location location) {
//            //여기서 위치값이 갱신되면 이벤트가 발생한다.
//            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.
//
//            Log.d("test", "onLocationChanged, location:" + location);
//            double longitude = location.getLongitude(); //경도
//            double latitude = location.getLatitude();   //위도
//            double altitude = location.getAltitude();   //고도
//            float accuracy = location.getAccuracy();    //정확도
//            String provider = location.getProvider();   //위치제공자
//            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
//            //Network 위치제공자에 의한 위치변화
//            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
//            textViewX.setText("위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude
//                    + "\n고도 : " + altitude + "\n정확도 : "  + accuracy);
//        }
//        public void onProviderDisabled(String provider) {
//            // Disabled시
//            Log.d("test", "onProviderDisabled, provider:" + provider);
//        }
//
//        public void onProviderEnabled(String provider) {
//            // Enabled시
//            Log.d("test", "onProviderEnabled, provider:" + provider);
//        }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            // 변경시
//            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
//        }
//    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ACCESS_FINE_LOCATION: {
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.CAMERA)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //permission 성공
                        }
                    } else {
                        //실패
                    }
                }
            }
            break;
            case ACCESS_COARSE_LOCATION:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //permission 성공
                        } else {
                            //실패
                        }
                    }
                }
                break;
        }
    }


    private void setTextViewXY(String str) {
        textViewX.setText(str);
        textViewY.setText(str);
    }
}