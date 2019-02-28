package dev.kevin.app.httpbasicaccessauthenticationtest;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GetTest extends Request<ApiResponse> {

    private Response.Listener<ApiResponse> mListener;
    private Gson gson;
    private Context context;

    public GetTest(Response.ErrorListener errorListener, Response.Listener<ApiResponse> listener, Context _context){
        super(Method.GET, "http://10.100.100.12/BasicHttpAuth/public/api/test", errorListener);
        gson = new Gson();
        context = _context;
        mListener = listener;
    }


    @Override
    protected Response<ApiResponse> parseNetworkResponse(NetworkResponse response) {
        String jsonString = new String(response.data);
        return Response.success(gson.fromJson(jsonString,ApiResponse.class),getCacheEntry());
    }

    @Override
    protected void deliverResponse(ApiResponse response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        String credentials = "test@test.net:test";
        String auth = "Basic "
                + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", auth);
        return headers;
    }
}
