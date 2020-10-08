package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.xwaydesigns.morbamosquetrust.Adapters.QuranRecitationAdapter;
import com.xwaydesigns.morbamosquetrust.Databases.DatabaseAccess;
import com.xwaydesigns.morbamosquetrust.Model.QuranRecitation;

import java.util.ArrayList;
import java.util.List;

public class QuranRecitationActivity extends AppCompatActivity {

    RecyclerView aayat_list;
    TextView surah_no_text,surah_heading_text,aayat_count_text;
    String sno,surah_no,surah_name,aayah_no,aayah_arabic,aayah_eng;
    String received_surah_no;
    List<QuranRecitation> data;
    RecyclerView.LayoutManager manager;
    Cursor c = null;
    private SQLiteDatabase db;
    private String received_surah_name;
    private String received_aayah_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_recitation);
        aayat_list = findViewById(R.id.aayat_list);
        surah_no_text = findViewById(R.id.surah_number);
        surah_heading_text = findViewById(R.id.surah_heading);
        aayat_count_text = findViewById(R.id.aayat_count);
        manager = new LinearLayoutManager(this);
        aayat_list.setLayoutManager(manager);
        aayat_list.setHasFixedSize(false);
        data = new ArrayList<>();

        Intent intent = getIntent();
        received_surah_no = intent.getStringExtra("surah_no");
        received_surah_name = intent.getStringExtra("surah_name");
        received_aayah_count = intent.getStringExtra("aayah_count");

        surah_no_text.setText(received_surah_no);
        surah_heading_text.setText(received_surah_name);
        aayat_count_text.setText(received_aayah_count);


        DatabaseAccess db_access = DatabaseAccess.getInstance(getApplicationContext());
        db = db_access.open_Aayat_Database();                                                                    //  Size LIKE "+"'"+size+"'"+"
        c = db.rawQuery("Select A.*,B.aayah_eng from quran_text as A Inner Join en_hilali as B on A.surah_no = B.surah_no AND A.aayah_no = B.aayah_no where A.surah_no LIKE "+"'"+received_surah_no+"'"+" GROUP BY  A.sno",new String[]{});
        data = new ArrayList<>();
        while(c.moveToNext())
        {
            sno = c.getString(0);
            surah_no = c.getString(1);
            surah_name = c.getString(2);
            aayah_no = c.getString(3);
            aayah_arabic = c.getString(4);
            aayah_eng = c.getString(5);
            QuranRecitation obj = new QuranRecitation(sno,surah_no,surah_name,aayah_no,aayah_arabic,aayah_eng);
            data.add(obj);
        }
      // db.close();
      //  db_access.closeDatabase();
        QuranRecitationAdapter adapter = new QuranRecitationAdapter(this,data);
        aayat_list.setAdapter(adapter);
        db.close();
        db_access.closeDatabase();

    }
}