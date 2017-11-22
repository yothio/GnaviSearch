package yothio.gnavisearch.network;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by yocchi on 2017/11/22.
 */

//現在地取得関連のクラス
public class GpsManager {

    private LocationManager locationManager;
    private Context context;
    private LocationListener locationListener;


    public GpsManager(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.context = context;
    }

    /**
     * 現在地取得メソッド
     * アクティビティから呼ばれるメソッド
     * @param callback  取得した現在地を使用する処理を記述
     */
    public void getLocation(Callback callback) {
        if(gpsIsEnabled()) {
            createLocationListener(callback::callback);
            gpsStart();
        }

    }

    /**
     * 位置情報が有効になっているか確認する
     * 向こうの場合は有効にするように促す
     * @return  位置情報が有効・無効
     */
    private boolean gpsIsEnabled() {
        boolean isEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(isEnabled){
            return true;
        }else{
            new AlertDialog.Builder(context).setMessage("位置情報がオフになっています。").setNeutralButton("有効にする", (dialogInterface, i) -> {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }).show();
            return false;
        }
    }

    /**
     * 現在地取得開始メソッド
     * ロケーションマネージャにリスナをセットする
     */
    private void gpsStart() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, locationListener);

    }
    private void gpsStop(){
        locationManager.removeUpdates(locationListener);
    }

    /**
     * 現在地変更時に通知を受けるリスナーを作成する
     * @param callback  画面で行う処理
     */
    private void createLocationListener(Callback callback){
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                callback.callback(location);
                gpsStop();
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {
            }
            @Override
            public void onProviderDisabled(String s) {
            }
        };
    }



//    コールバック
    public interface Callback{
        void callback(Location location);
    }

}
