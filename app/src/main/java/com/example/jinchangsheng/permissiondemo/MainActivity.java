package com.example.jinchangsheng.permissiondemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by jinchangsheng on 16/6/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Context context;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==PermissionUtils.REQUEST_CAMERA){
                if (msg.arg1==PermissionUtils.success){
                    //执行拍照代码takePicture();
                }else if (msg.arg1==PermissionUtils.failed){
                    Toast.makeText(MainActivity.this, getString(R.string.permission_camera), Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                PermissionUtils.checkCamera(context,handler);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtils.REQUEST_CAMERA:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PermissionUtils.show(handler, PermissionUtils.REQUEST_CAMERA, PermissionUtils.success);
                } else {
                    PermissionUtils.show(handler, PermissionUtils.REQUEST_CAMERA, PermissionUtils.failed);
                }
                break;

        }
    }

}
