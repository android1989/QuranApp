package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.xwaydesigns.morbamosquetrust.Adapters.QuranRecitationAdapter;
import com.xwaydesigns.morbamosquetrust.Adapters.SurahAdapter;
import com.xwaydesigns.morbamosquetrust.Databases.DatabaseAccess;
import com.xwaydesigns.morbamosquetrust.Model.QuranRecitation;
import com.xwaydesigns.morbamosquetrust.Model.Surah;

import java.util.ArrayList;
import java.util.List;

public class SurahActivity extends AppCompatActivity {

    private RecyclerView surah_list;
    RecyclerView.LayoutManager manager;
    List<Surah> data;
    String surah_no,surah_name,eng,aayah_count ;
    Cursor c = null;
    private SQLiteDatabase db;
    SurahAdapter.SurahViewHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        surah_list = findViewById(R.id.surah_list);
        manager = new LinearLayoutManager(this);
        surah_list.setLayoutManager(manager);
        surah_list.setHasFixedSize(true);
        data = new ArrayList<>();

        DatabaseAccess db_access = DatabaseAccess.getInstance(getApplicationContext());
        db = db_access.open_Aayat_Database();
        c = db.rawQuery("SELECT * FROM quran_surah ",new String[]{});
        data = new ArrayList<>();
        while(c.moveToNext())
        {
            surah_no = c.getString(0);
            surah_name = c.getString(1);
            eng = c.getString(2);
            aayah_count = c.getString(3);
            Surah obj = new Surah(surah_no,surah_name,eng,aayah_count);
            data.add(obj);
        }

        SurahAdapter adapter = new SurahAdapter(SurahActivity.this,data);
        surah_list.setAdapter(adapter);
        db.close();
        db_access.closeDatabase();
    }

}