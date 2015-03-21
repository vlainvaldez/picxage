package com.serverus.picxage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


/*
* This class is created only to show log and toast for testing purposes
*/

public class L  {

    public static void m(String message){
        Log.d("aoi", message);
    }

    public static void t(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
