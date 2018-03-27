package com.github.example.servicecodechallenge;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


public class ChronoService extends Service {
    private static final String TAG = "ChronoService =>";
    private IBinder mBinder = new LocalBinder();
    public static final String CHRONO_SERVICE_COUNTER_FINISH = "CHRONO_SERVICE_COUNTER_FINISH";
    public static final String CHRONO_SERVICE_COUNTER_UPDATE = "CHRONO_SERVICE_COUNTER_UPDATE";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    public class LocalBinder extends Binder{
        ChronoService getService(){
            return ChronoService.this;
        }
    }

    public void startChrono(final int seconds) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < seconds; i++) {
                    sendMessage(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: startChrono " + i + "/" + seconds);
                }
                sendMessage(-1);
            }
        }).start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    private void sendMessage(int count) {
        Intent intent = new Intent();
        if (count == -1 ) {
            intent.setAction(CHRONO_SERVICE_COUNTER_FINISH);
        } else {
            intent.setAction(CHRONO_SERVICE_COUNTER_UPDATE);
            intent.putExtra("count", String.valueOf(count));
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }
}
