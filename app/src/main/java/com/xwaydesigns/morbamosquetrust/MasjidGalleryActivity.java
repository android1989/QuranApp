package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xwaydesigns.morbamosquetrust.Adapters.MasjidGalleryAdapter;
import com.xwaydesigns.morbamosquetrust.ExtraClasses.Constants;
import com.xwaydesigns.morbamosquetrust.ExtraClasses.SessionManager;
import com.xwaydesigns.morbamosquetrust.Model.MasjidGallery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class MasjidGalleryActivity extends AppCompatActivity {

    private RecyclerView gallery_list;
    RecyclerView.LayoutManager manager;
    MasjidGalleryAdapter adapter;
    private List<MasjidGallery> data;
    private String fetch_masjid_gallery_server_url = Constants.BASE_URL+"android/fetch_masjid_gallery.php";
    private ProgressDialog dialog;
    private SessionManager session_manager;
    private String photo;
    private String masjid_id;
    private HashMap<String, String> user_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masjid_gallery);

        dialog = new ProgressDialog(MasjidGalleryActivity.this);
        dialog.setTitle("Loading, Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        session_manager = new SessionManager(MasjidGalleryActivity.this);
        user_data = session_manager.getMasjidData();
        masjid_id = user_data.get("masjid_id");

        gallery_list = findViewById(R.id.gallery_list);
        manager = new GridLayoutManager(this,2);
        gallery_list.setLayoutManager(manager);
        gallery_list.setHasFixedSize(true);
        data = new ArrayList<>();

        if(session_manager.connectivity()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                  //do nothing
                }
            }, TIME_OUT);
        }else if(!session_manager.connectivity()){
            dialog.dismiss();
            Toast.makeText(MasjidGalleryActivity.this,"No internet Connectivity", Toast.LENGTH_SHORT).show();
        }

        //-------------------------------------------------------------------------------------------------\\
        StringRequest request = new StringRequest(Request.Method.POST,fetch_masjid_gallery_server_url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                          //  Toast.makeText(MasjidGalleryActivity.this, "Response:"+ response, Toast.LENGTH_SHORT).show();
                            JSONArray result = new JSONArray(response);
                            JSONObject object = null ;

                            for(int i = 0; i< result.length() ; i++)
                            {
                                object = result.getJSONObject(i);
                                photo = object.getString("photo");

                                MasjidGallery obj = new MasjidGallery(photo);
                                data.add(obj);
                            }

                                MasjidGalleryAdapter adapter = new MasjidGalleryAdapter(MasjidGalleryActivity.this, data, masjid_id);
                                gallery_list.setAdapter(adapter);
                                dialog.dismiss();

                        }
                        catch (JSONException e)
                        {
                            dialog.dismiss();
                            e.printStackTrace();
                            Log.e("fetch JSONException ", String.valueOf(e));
                            e.printStackTrace();
                            displayExceptionMessage1(e.getMessage());
                        }

                    }//onResponse End
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        dialog.dismiss();
                        Log.e("fetch Volley error", String.valueOf(error));
                        error.printStackTrace();
                        displayExceptionMessage2(error.getMessage());
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String, String>();
                params.put("fetch","gallery");
                params.put("masjid_id",masjid_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MasjidGalleryActivity.this);
        requestQueue.add(request);
        //-------------------------------------------------------------------------------------------------\\

    }

    public void displayExceptionMessage1(String msg) {
        Toast.makeText(MasjidGalleryActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
    }
    public void displayExceptionMessage2(String msg) {
        Toast.makeText(MasjidGalleryActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
    }
}