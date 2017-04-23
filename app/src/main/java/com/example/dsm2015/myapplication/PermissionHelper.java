package com.example.dsm2015.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dsm2015 on 2017-04-16.
 */

public class PermissionHelper extends AppCompatActivity{
    private static final int ACCESS_FINE_LOCATION = 1;
    private static final int ACCESS_COARSE_LOCATION = 2;

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
}
