package com.example.abyte.umg;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Php extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;

    private TextView mTextView;
    private String mJSONURLString = "http://192.168.0.106:8080/Empezando/sales/hola/perdimos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csharp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = getApplicationContext();
        mActivity = Php.this;
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mTextView = (TextView) findViewById(R.id.tv);
        imprimir();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void imprimir(){
        mTextView.setText("");


        RequestQueue requestQueue = Volley.newRequestQueue(mContext);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{

                            for(int i=0;i<response.length();i++){

                                JSONObject gers = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String aProductID = gers.getString("aProductID");
                                String bProductName = gers.getString("bProductName");
                                String cStock = gers.getString("cStock");
                                String dQuantitySold = gers.getString("dQuantitySold");
                                String eLastSoldDate = gers.getString("eLastSoldDate");
                                //ultima fecha de venta
                                String fBestCustomer = gers.getString("fBestCustomer");
                                //mejor cliente
                                //String aProductoID = student.getString("aProductoID");
                                //String bProductoName = student.getString("bProductoName");
                                // String cStock = student.getString("cStock");
                                //String dQuantitySold = student.getString("dQuantitySold");
                                //String fLastSoldDate = student.getString("fLastSoldDate");
                                //String gBestCustomer = student.getString("gBestCustomer");
                                //String age = student.getString("age");


                                mTextView.append("ID: "+  aProductID  +"  NOMBRE:  " + bProductName + "  STOCK:  "+ cStock + "  CANTIDAD VENDIDA:   "+ dQuantitySold + "  ULTIMA FECHA DE VENTA  " + eLastSoldDate +
                                        "  MEJOR CLIENTE:  "+ fBestCustomer );
                                mTextView.append("\n\n");

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                        Snackbar.make(
                                mCLayout,
                                "Error...",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                }
        );


        requestQueue.add(jsonArrayRequest);
    }
};

