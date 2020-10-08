package com.xwaydesigns.morbamosquetrust.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.xwaydesigns.morbamosquetrust.ExtraClasses.SessionManager;
import com.xwaydesigns.morbamosquetrust.Model.QuranRecitation;
import com.xwaydesigns.morbamosquetrust.Model.Surah;
import com.xwaydesigns.morbamosquetrust.QuranRecitationActivity;
import com.xwaydesigns.morbamosquetrust.R;
import com.xwaydesigns.morbamosquetrust.SelectMasjidActivity;
import com.xwaydesigns.morbamosquetrust.SurahActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder>
{
    List<Surah> data;
    Context ctx;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();

    public SurahAdapter(Context ctx, List<Surah> data)
    {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_surah_row, parent, false);
        return new SurahViewHolder(view,ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position)
    {
        Surah obj = data.get(position);
        holder.surah_no.setText(obj.getSurah_no());
        holder.surah_name.setText(obj.getSurah_name());
        holder.eng.setText(obj.getEng());
        holder.aayah_count.setText(obj.getAayah_count());

        list.add(position,obj.getSurah_no());
        holder.holder_list = (ArrayList<String>) list.clone();

        list1.add(position,obj.getSurah_name());
        holder.holder_list1 = (ArrayList<String>) list1.clone();

        list2.add(position,obj.getAayah_count());
        holder.holder_list2 = (ArrayList<String>) list2.clone();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SurahViewHolder extends RecyclerView.ViewHolder
    {
        TextView surah_no;
        TextView surah_name;
        TextView eng;
        TextView aayah_count;
        View mview;
        Context ctx;
        ArrayList<String> holder_list = new ArrayList<>();
        ArrayList<String> holder_list1 = new ArrayList<>();
        ArrayList<String> holder_list2 = new ArrayList<>();

        //----------------------------------------------------------\\
         String audio_url;
         static MediaPlayer mediaPlayer;
        static Handler handler = new Handler();
         View audio_view;
         TextView surah_heading;
         static ImageView play_pause;
        static TextView current_time_text;
         TextView total_duration_text;
         static SeekBar play_bar;
          SessionManager manager;

        //------------------------------------------------------------------\\

        public SurahViewHolder(@NonNull View itemView,final Context ctx) {
            super(itemView);
            mview = itemView;
            this.ctx = ctx;
            surah_no = mview.findViewById(R.id.surah_no);
            surah_name = mview.findViewById(R.id.arabic_surah);
            eng = mview.findViewById(R.id.english_surah);
            aayah_count = mview.findViewById(R.id.aayah_count);

            mview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, QuranRecitationActivity.class);
                    intent.putExtra("surah_no", holder_list.get(getAdapterPosition()));
                    intent.putExtra("surah_name", holder_list1.get(getAdapterPosition()));
                    intent.putExtra("aayah_count", holder_list2.get(getAdapterPosition()));
                    ctx.startActivity(intent);
                }
            });

        }//surahViewholder constructor ends

    }//SurahViewHolder class end

}//SurahAdapter ends

