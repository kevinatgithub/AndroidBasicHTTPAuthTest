package dev.kevin.app.httpbasicaccessauthenticationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetTest getTest = new GetTest(new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.Listener<ApiResponse>() {
            @Override
            public void onResponse(ApiResponse response) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        },this);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getTest);

    }
}
