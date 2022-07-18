package net.com.gopal.getqrpayload.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import net.com.gopal.getqrpayload.NetworkAPI;

import org.json.JSONObject;

public class GetDymanicQr extends AsyncTask<Void, Void, String> {
    Context context;
    String url;
    String amount;
    Response response;
    String token;

    public GetDymanicQr(Context context, String url, String amount,String token, Response response) {
        this.context = context;
        this.url = url;
        this.amount = amount;
        this.response = response;
        this.token=token;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response = null;
        try {
            response = NetworkAPI.sendHTTPData(url, "000072497701524", "Q0006998", amount, token);
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
            String payload = jsonObject.getString("Payload");
            String message = jsonObject.getString("message");
            response.onSuccess(payload);

        } catch (Exception e) {
            response.onFail();
            e.printStackTrace();
        }
    }

    public interface Response {
        void onSuccess(String payload);

        void onFail();

        void onProcess();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        response.onProcess();
    }
}
