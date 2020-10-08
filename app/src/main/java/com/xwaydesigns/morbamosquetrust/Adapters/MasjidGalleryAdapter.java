package com.xwaydesigns.morbamosquetrust.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.xwaydesigns.morbamosquetrust.ExtraClasses.Constants;
import com.xwaydesigns.morbamosquetrust.Model.MasjidGallery;
import com.xwaydesigns.morbamosquetrust.R;

import java.util.List;

public class MasjidGalleryAdapter extends RecyclerView.Adapter<MasjidGalleryAdapter.MasjidGalleryViewholder>
{

    List<MasjidGallery> data;
    Context ctx;
    String masjid_id;
     String image_url;

    public MasjidGalleryAdapter(Context ctx, List<MasjidGallery> data,String masjid_id)
    {
        this.data = data;
        this.ctx = ctx;
        this.masjid_id = masjid_id;
    }

    @NonNull
    @Override
    public MasjidGalleryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_gallery_row, parent, false);
        return new MasjidGalleryViewholder(view,ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull MasjidGalleryViewholder holder, int position)
    {
        MasjidGallery obj = data.get(position);
       // Toast.makeText(ctx,"masjid_id:"+masjid_id,Toast.LENGTH_LONG).show();
        image_url = Constants.BASE_URL+"morba/images/"+masjid_id+"/"+obj.getPhoto();
       // Toast.makeText(ctx,"image_url:"+image_url,Toast.LENGTH_LONG).show();
        Picasso.get().load(image_url).resize(150,150).centerCrop().placeholder(R.drawable.default_masjid_image)
                .error(R.drawable.default_masjid_image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MasjidGalleryViewholder extends RecyclerView.ViewHolder
    {
        ImageView image;
        Context ctx;
        // ArrayList<Integer> holder_list = new ArrayList<>();
        public MasjidGalleryViewholder(@NonNull View itemView, final Context ctx)
        {
            super(itemView);
            image =  itemView.findViewById(R.id.masjid_image);
            this.ctx = ctx;

        }
    }
}
