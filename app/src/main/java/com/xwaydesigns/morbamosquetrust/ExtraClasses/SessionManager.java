package com.xwaydesigns.morbamosquetrust.ExtraClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.HashMap;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class SessionManager
{
    public Context ctx;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public static final String pref_name = "mydata";
    public int PRIVATE_MODE = 0;
    public static final String MASJID_ID = "masjid_id";
    public static final String MASJID_NAME = "masjid_name";
    public static final String Default_Value = "DEFAULT";

    public SessionManager(Context context)
    {
        ctx = context;
        pref = ctx.getSharedPreferences(pref_name,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void SelectionSuccessful(String masjid_id,String masjid_name)
    {
        editor.putString(MASJID_ID,masjid_id);
        editor.putString(MASJID_NAME,masjid_name);
        editor.apply();
    }

    public HashMap<String,String> getMasjidData()
    {
        HashMap<String,String> user = new HashMap<String,String>();
        user.put("masjid_id",pref.getString(MASJID_ID,Default_Value));
        user.put("masjid_name",pref.getString(MASJID_NAME,Default_Value));
        return user;
    }


    public boolean connectivity(){
        boolean wifi = false;
        boolean mobiledata = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos){
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    wifi = true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    mobiledata = true;
        }
        return mobiledata||wifi;
    }


}
