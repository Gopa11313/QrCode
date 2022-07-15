package net.com.gopal.getqrpayload;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkAPI {
    public static String getFromurl(String url,String token) {
        String result = "";
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .writeTimeout(180, TimeUnit.SECONDS)
                    .readTimeout(180, TimeUnit.SECONDS)
                    .build();
            Request request;

            if (token != null) {
                request = new Request.Builder()
                        .addHeader("Authorization", token)
                        .url(url)
                        .build();
                System.out.println(request);
            } else {
                request = new Request.Builder()
                        .url(url)
                        .build();
            }

            Response response = okHttpClient.newCall(request).execute();

            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        return result;
    }

    public static String getRegisterData(String url) {
        String result = "";
        try {
            Log.d("dinesh", "getFromurl: " + url);
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();

        }

        return result;
    }

    public static String sendHTTPData(String urlpath, JSONObject json,String token) throws Exception {


        String TAG = "gopal";
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(JSON, json.toString());

        System.out.println("url" + urlpath);
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(urlpath)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }
    public static String sendHTTPData(String urlpath, JSONObject json) throws Exception {
        String TAG = "dinesh";
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url(urlpath)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
