package com.xwaydesigns.morbamosquetrust.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.HashMap;

public class DatabaseAccess
{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess minstance;
    Cursor c = null;
    Context ctx;
    private String prayers;
    String date,fazr,sunrise,dhuhr,asr,maghrib,isha;


    //making private so that outside the class no object can be created
    private DatabaseAccess(Context context)
    {
        this.openHelper = new DatabaseOpenHelper(context);
        ctx = context;
    }

    //to return single instance of class
    public static DatabaseAccess getInstance(Context context)
    {
        if(minstance == null)
        {
            minstance = new DatabaseAccess(context);
        }
        return minstance;
    }

    //to open the database
    public void openDatabase()
    {
        this.db = openHelper.getWritableDatabase();
    }

    public SQLiteDatabase open_Aayat_Database()
    {
        this.db = openHelper.getWritableDatabase();
        return db;
    }

    public void closeDatabase()
    {
        if(db!=null)
        {
            db.close();
        }
    }

    //now write the method for query and return the result from database
    public HashMap<String,String> getPrayers()
    {
        HashMap<String,String> user = new HashMap<String,String>();
        c = db.rawQuery("Select * from prayer_times where strftime('%m %d',dates) = strftime('%m %d','now')",new String[]{});
        //select A.*,B.aayah_eng from quran_text as A Inner Join en_hilali as B on A.surah_no = B.surah_no where A.surah_no = 2;
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext())
        {
             date = c.getString(0);
             fazr = c.getString(1);
             sunrise = c.getString(2);
             dhuhr = c.getString(3);
             asr = c.getString(4);
             maghrib = c.getString(5);
             isha = c.getString(6);

            user.put("date",date);
            user.put("fazr",fazr);
            user.put("sunrise",sunrise);
            user.put("dhuhr",dhuhr);
            user.put("asr",asr);
            user.put("maghrib",maghrib);
            user.put("isha",isha);
          //  buffer.append(","+ date + "," + fazr + "," + sunrise +","+ dhuhr +","+ asr +","+ maghrib + "," + isha );
        }

        return user;
    }
}
