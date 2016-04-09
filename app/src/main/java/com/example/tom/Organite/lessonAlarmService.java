package com.example.tom.Organite;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Tom on 09/04/2016.
 */
public class lessonAlarmService extends Service {
    @Override

    public void onCreate() {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();

    }



    @Override

    public IBinder onBind(Intent intent) {

// TODO Auto-generated method stub

        return null;

    }



    @Override

    public void onDestroy() {

// TODO Auto-generated method stub

        super.onDestroy();

        Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();

    }



    @Override

    public void onStart(Intent intent, int startId) {

// TODO Auto-generated method stub

        super.onStart(intent, startId);
        String subj = intent.getStringExtra("Subject");
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_stat_name);
        mBuilder.setContentTitle("Reminder of your " + subj + " lesson starting now");
        mBuilder.build();
        Toast.makeText(this, "Notification built", Toast.LENGTH_SHORT);
        NotificationManager nMag = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        nMag.notify(999, mBuilder.build());

    }



    @Override

    public boolean onUnbind(Intent intent) {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);

    }



}
