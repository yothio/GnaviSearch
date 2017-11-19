package yothio.gnavisearch.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import okhttp3.internal.Util;
import yothio.gnavisearch.R;
import yothio.gnavisearch.util.Const;
import yothio.gnavisearch.util.PermissionUtil;

import static yothio.gnavisearch.util.Const.PERMISSIONS;

/**
 * Created by yocchi on 2017/11/20.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // タイトルを非表示にします。
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // splash.xmlをViewに指定します。
        setContentView(R.layout.splash_activity);
        Handler hdl = new Handler();
        // 500ms遅延させてsplashHandlerを実行します。
        hdl.postDelayed(new splashHandler(), 500);
    }

    class splashHandler implements Runnable {
        public void run() {
            // 権限がある場合は、そのまま通常処理を行う
            Intent intent = new Intent(getApplication(), MainActivity.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PermissionUtil.hasSelfPermissions(SplashActivity.this, PERMISSIONS)) {
                    startActivity(intent);
                    // SplashActivityを終了させます。
                    SplashActivity.this.finish();
                } else {
                    // 権限がない場合は、パーミッション確認アラートを表示する
                    requestPermissions(PERMISSIONS, Const.PERMISSION_REQUEST_CODE);
                }
            } else {
                startActivity(intent);
                // SplashActivityを終了させます。
                SplashActivity.this.finish();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // アラート表示中に画面回転すると length ０でコールバックされるのでガードする
        if (grantResults.length > 0 && (requestCode == Const.PERMISSION_REQUEST_CODE)) {
            if (!PermissionUtil.checkGrantResults(grantResults)) {
//                今後確認しないにチェックが入っているか確認
                if (!PermissionUtil.shouldShowRequestPermissionRationale(this, permissions, grantResults)) {
//                    今後確認しないにチェックが入っている場合
                    PermissionUtil.showAlertDialog(getSupportFragmentManager());
                }else{
                    // 権限がない場合は、パーミッション確認アラートを表示する
                    requestPermissions(PERMISSIONS, Const.PERMISSION_REQUEST_CODE);
                }
            } else {
                    // 権限が取れた場合は通常の処理を行う
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                // SplashActivityを終了させます。
                SplashActivity.this.finish();

            }
        }
    }
}