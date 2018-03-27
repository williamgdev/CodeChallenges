package com.github.example.servicecodechallenge;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity =>";
    private TextView txtView;
    private ChronoService boundService;
    private ChronoService.LocalBinder binder;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.textView);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ChronoService.CHRONO_SERVICE_COUNTER_UPDATE);
        intentFilter.addAction(ChronoService.CHRONO_SERVICE_COUNTER_FINISH);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (ChronoService.CHRONO_SERVICE_COUNTER_UPDATE.equals(intent.getAction())) {
                    updateUI(intent.getStringExtra("count"));
                } else if (ChronoService.CHRONO_SERVICE_COUNTER_FINISH.equals(intent.getAction())) {
                    unbindService(chronoOnConnection);
                }
            }
        }, intentFilter);

        bindService(new Intent(this, ChronoService.class), chronoOnConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection chronoOnConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (binder == null) {
                binder = (ChronoService.LocalBinder) service;
                boundService = binder.getService();
            }
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    private void updateUI(String s) {
        Log.d(TAG, "updateUI: ");
        txtView.setText(s);
    }

    public void startChrono(View view) {
        boundService.startChrono(10);
    }
}
