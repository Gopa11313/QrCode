package net.com.gopal.getqrpayload.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import net.com.gopal.getqrpayload.NetworkAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {
    Context context;
    Response response;
    String url;
    JSONObject jsonObject;

    public LoginAsyncTask(Context context, Response response, String url, JSONObject jsonObject) {
        this.context = context;
        this.response = response;
        this.jsonObject = jsonObject;
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response = null;
        try {
            response = NetworkAPI.sendHTTPData(url);
            Log.d("Gopal", "response : " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String token = jsonObject.getString("access_token");
            token = "bearer " + token;
            response.onSuccess(token);


        } catch (Exception e) {
            response.onFail();
            e.printStackTrace();
        }
    }

    public interface Response {
        void onSuccess(String token);

        void onFail();

        void onProcess();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        response.onProcess();
    }
}
