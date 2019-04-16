package com.avrethem.blankproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyDeviceInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with something fun", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        printInfo();
        // super.reportFullyDrawn();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void printInfo() {

        // You may want to take a look at those pages : http://developer.android.com/reference/android/os/Build.html and http://developer.android.com/reference/java/lang/System.html (the getProperty() method might do the job).

        String deviceInfo = "device: Device {";
        deviceInfo += "approach:native,";
        deviceInfo += "model:" + Build.MANUFACTURER + ",";
        deviceInfo += "model:" + android.os.Build.MODEL + ",";
        deviceInfo += "platform:android,";
        // Always ask for permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && this.checkPermission
                (this.PHONE_STATE_PERMISSION, this)) {
            deviceInfo += "serial:" + Build.getSerial() + ",";

        } else {
            deviceInfo += "serial:"+ Build.SERIAL + ",";
        }
        deviceInfo += "android-api:" + android.os.Build.VERSION.SDK_INT + ",";
        deviceInfo += "product:" + android.os.Build.PRODUCT + ",";
        deviceInfo += "isVirtual:" + isEmulator() + ",";


        deviceInfo += "device:" + android.os.Build.DEVICE + ",";
        deviceInfo += "product:" + android.os.Build.PRODUCT + ",";
        deviceInfo += "bootloader:" + android.os.Build.BOOTLOADER + ",";
        deviceInfo += "os:" + System.getProperty("os.version") + ",";
        deviceInfo += "bootloader:" + android.os.Build.ID + ",";


        Log.d(TAG, deviceInfo);
    }


    public static String isEmulator() {
        if (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT))
            return "true";
        else
            return "false";
    }


    public static final String PHONE_STATE_PERMISSION =
            Manifest.permission.READ_PHONE_STATE;

    public static boolean checkPermission(String permission, Activity activity) {
        return ContextCompat.checkSelfPermission(activity, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }


}
