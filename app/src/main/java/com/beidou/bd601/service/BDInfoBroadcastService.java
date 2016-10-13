package com.beidou.bd601.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by wangkuan on 2016/10/10.
 * 获取各种北斗信息，向系统进行广播，例如北斗信号强度，北斗时间，北斗卡号，北斗
 */
public class BDInfoBroadcastService extends Service {

    private static final String TAG="Test";

    class BroadcastThread extends Thread{
        public void run(){
            while(true){
                //发送广播
                try {
                    Thread.sleep(1000);
                    Intent intent = new Intent();

                    intent.putExtra("data", getShowData());
                    intent.setAction("testbroadcast");
                    sendBroadcast(intent);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    float [] getShowData (){
        float [] data = new float[12];
        for(int i=0;i<12;i++){
            data[i]=(float) Math.random()*50;
        }
        return  data;
    }

    @Override
    //Service时被调用
    public void onCreate()
    {
        Log.i(TAG, "Service onCreate--->");
        //启动广播线程
        BroadcastThread thread = new BroadcastThread();
        thread.start();
        super.onCreate();
    }

    @Override
    //当调用者使用startService()方法启动Service时，该方法被调用
    public void onStart(Intent intent, int startId)
    {
        Log.i(TAG, "Service onStart--->");
        super.onStart(intent, startId);
    }

    @Override
    //当Service不在使用时调用
    public void onDestroy()
    {
        Log.i(TAG, "Service onDestroy--->");
        super.onDestroy();
    }

    @Override
    //当使用startService()方法启动Service时，方法体内只需写return null
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}
