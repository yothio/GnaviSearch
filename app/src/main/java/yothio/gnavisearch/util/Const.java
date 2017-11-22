package yothio.gnavisearch.util;

import android.Manifest;

/**
 * Created by yocchi on 2017/11/15.
 */

public class Const {
    final public static String INTENT_KEY = "intent_key";
    final public static String API_BASE_URL = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";

    final public static String GPS_PERMISSIONS[] = {Manifest.permission.ACCESS_FINE_LOCATION};
    final public static int GPS_REQUEST_CODE = 123321;
}
