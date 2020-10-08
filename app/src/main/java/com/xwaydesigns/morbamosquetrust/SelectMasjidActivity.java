package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xwaydesigns.morbamosquetrust.ExtraClasses.Constants;
import com.xwaydesigns.morbamosquetrust.ExtraClasses.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class SelectMasjidActivity extends AppCompatActivity {

    Spinner select;
    Button submit;
    private String fetch_masjid_server_url = Constants.BASE_URL+"android/fetch_masjid.php";
    private String masjid_id;
    private String masjid_name;
    private String[] masjid_id_array;
    private String[] masjid_name_array;
    private List<String> masjid_id_list = new ArrayList<>();
    private List<String> masjid_name_list = new ArrayList<>();
    private ProgressDialog dialog;
    private SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_masjid);

        dialog = new ProgressDialog(SelectMasjidActivity.this);
        dialog.setTitle("Loading, Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        manager = new SessionManager(SelectMasjidActivity.this);
        select = findViewById(R.id.select_masjid_spinner);
        submit = findViewById(R.id.submit);

        if(manager.connectivity()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   //do nothing
                }
            }, TIME_OUT);
        }else if(!manager.connectivity()){
            dialog.dismiss();
            Toast.makeText(SelectMasjidActivity.this,"No internet Connectivity", Toast.LENGTH_SHORT).show();
        }

        //-------------------------------------------------------------------------------------------------\\
        StringRequest request = new StringRequest(Request.Method.POST,fetch_masjid_server_url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                          // Toast.makeText(SelectMasjidActivity.this, "Response:"+ response, Toast.LENGTH_SHORT).show();
                            JSONArray result = new JSONArray(response);
                            JSONObject object = null ;
                            masjid_id_array = new String[result.length()];
                            masjid_name_array = new String[result.length()];

                            for(int i = 0; i< result.length() ; i++)
                            {
                                object = result.getJSONObject(i);
                                masjid_id_array[i] = object.getString("masjid_id");
                                masjid_name_array[i] = object.getString("masjid_name");
                            }

                            for(int i = 0; i< masjid_id_array.length; i++)
                            {
                                masjid_id_list.add(masjid_id_array[i]);
                                masjid_name_list.add(masjid_name_array[i]);
                            }
                            if(masjid_id_array.length != 0)
                            {
                                select.setAdapter(new ArrayAdapter<String>(SelectMasjidActivity.this, android.R.layout.simple_spinner_dropdown_item, masjid_name_list));
                                dialog.dismiss();
                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(SelectMasjidActivity.this, "No Data From Server ", Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch (JSONException e)
                        {
                            dialog.dismiss();
                            e.printStackTrace();
                            Log.e("fetch JSONException ", String.valueOf(e));
                           // e.printStackTrace();
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
                params.put("fetch","masjid");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SelectMasjidActivity.this);
        requestQueue.add(request);
        //-------------------------------------------------------------------------------------------------\\

        //----------------------------------------------------------------------------------------//
        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                masjid_id = masjid_id_list.get(i);
                masjid_name = masjid_name_list.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        //---------------------------------------------------------------------------------------------\\


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(select.getSelectedItemPosition() != 0)
                {
                    manager.SelectionSuccessful(masjid_id, masjid_name);
                    Intent start = new Intent(SelectMasjidActivity.this, MainActivity.class);
                    start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(start);
                    finish();
                }
                else
                {
                    Toast.makeText(SelectMasjidActivity.this, "Please Select the Masjid From the list", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void displayExceptionMessage1(String msg) {
        Toast.makeText(SelectMasjidActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
    }
    public void displayExceptionMessage2(String msg) {
        Toast.makeText(SelectMasjidActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
    }
}