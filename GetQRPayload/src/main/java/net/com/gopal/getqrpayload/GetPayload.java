package net.com.gopal.getqrpayload;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import net.com.gopal.getqrpayload.api.Constans;
import net.com.gopal.getqrpayload.api.GetDymanicQr;
import net.com.gopal.getqrpayload.api.LoginAsyncTask;
import net.com.gopal.getqrpayload.utlis.UtilsQRCode;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class GetPayload {

    public static String Payload(Context context, String amount, Dialog dialog) {
        final String[] payloadData = {""};
        new LoginAsyncTask(context, new LoginAsyncTask.Response() {
            @Override
            public void onSuccess() {
                new GetDymanicQr(context, Constans.MERCHANT_DYANAMIC_QR, amount, new GetDymanicQr.Response() {
                    @Override
                    public void onSuccess(String payload) {
                        payloadData[0] = payload;
                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onProcess() {
                        dialog.show();
                    }
                }).execute();
            }

            @Override
            public void onFail() {
                Toast.makeText(context, "Enable to do transaction", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProcess() {

            }
        }, Constans.LOGIN, reqPrams()).execute();
        return payloadData[0];
    }

    private static JSONObject reqPrams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("grant_type", "password");
            jsonObject.put("username", "bonfire");
            jsonObject.put("password", "bonfire@123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static JSONObject reqParamsForDymanicQr(String amount) {
        JSONObject jsonObject = new JSONObject();
        try {
            Date c = Calendar.getInstance().getTime();
            Random random = new Random();
            int x = random.nextInt(50);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String formattedDate = df.format(c);
            jsonObject.put("merchantId", "");
            jsonObject.put("termId", "");
            jsonObject.put("payType", "UPI");
            jsonObject.put("invoiceNo", formattedDate + df);
            jsonObject.put("amount", amount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
