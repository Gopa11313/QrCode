package net.com.gopal.getqrpayload;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
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

    public static String sendHTTPData(String urlpath) throws Exception {


        String TAG = "gopal";
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();

        FormBody.Builder body = new FormBody.Builder()
                .addEncoded("grant_type","password")
                .addEncoded("username","bonfire")
                .addEncoded("password","bonfire@123");
        System.out.println("url" + urlpath);

        Request request = new Request.Builder()
                .url(urlpath)
                .post(body.build())
                .build();

//        Request.Builder request = new Request.Builder()
//                .addHeader("Authorization", token)
//                .url(urlpath)
//                .post(body)
//                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }
    public static String sendHTTPData(String urlpath, String merchID,String termID,String amount,String token) throws Exception {
        String TAG = "dinesh";
        MediaType JSON
                = MediaType.parse("multipart/form-data");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();
        Date c = Calendar.getInstance().getTime();
        Random random = new Random();
        int x = random.nextInt(50);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String formattedDate = df.format(c);
        FormBody.Builder body = new FormBody.Builder()
                .addEncoded("merchantId", merchID)
                .addEncoded("termId", termID)
                .addEncoded("payType", "UPI")
                .addEncoded("invoiceNo", formattedDate + x)
                .addEncoded("amount", amount);
        Request request = new Request.Builder()
                .url(urlpath)
                .post(body.build())
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
