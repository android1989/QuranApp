package com.xwaydesigns.morbamosquetrust.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xwaydesigns.morbamosquetrust.Model.QuranRecitation;
import com.xwaydesigns.morbamosquetrust.R;

import java.util.List;

public class QuranRecitationAdapter extends RecyclerView.Adapter<QuranRecitationAdapter.QuranRecitationViewHolder>
{
    List<QuranRecitation> data;
    Context ctx;

    public QuranRecitationAdapter(Context ctx, List<QuranRecitation> data)
    {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public QuranRecitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aayat_single_row, parent, false);
        return new QuranRecitationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuranRecitationViewHolder holder, int position)
    {
        QuranRecitation obj = data.get(position);

        holder.arbi_aayat.setText(obj.getAayah_arabic()+"("+obj.getAayah_no()+")");
        holder.english_aayat.setText(obj.getAayah_eng());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class QuranRecitationViewHolder extends RecyclerView.ViewHolder
    {
        TextView arbi_aayat;
        TextView english_aayat;
        View mview;
        public QuranRecitationViewHolder(@NonNull View itemView) {
            super(itemView);
            mview =itemView;
            arbi_aayat = mview.findViewById(R.id.arbi_text);
            english_aayat = mview.findViewById(R.id.eng_text);
        }
    }
}
