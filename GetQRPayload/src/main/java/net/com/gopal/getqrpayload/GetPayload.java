package net.com.gopal.getqrpayload;

import android.content.Context;
import android.graphics.Bitmap;

import net.com.gopal.getqrpayload.utlis.UtilsQRCode;

import java.util.Map;

public class GetPayload {


    public static Bitmap Payload(Context context, String amount) {
        Map<String, String> map = null;
        map = UtilsQRCode.getUPQRCPreference(context);
        return UtilsQRCode.generateQR(UtilsQRCode.updateTagLenghtValue(amount, map));
    }
}
