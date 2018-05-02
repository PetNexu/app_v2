package com.nexu.projet.miashs.nexu;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NotificationHomeActivity extends AppCompatActivity{

        private Button addNotificationBtn;
        private Button deleteNotificationBtn;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.notification);

            addNotificationBtn = (Button) findViewById(R.id.add_notification);
            addNotificationBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Toast.makeText(getBaseContext(), "Ajout d'une notification", Toast.LENGTH_SHORT).show();
                }
            });

            deleteNotificationBtn = (Button) findViewById(R.id.delete_notification);
            deleteNotificationBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Toast.makeText(getBaseContext(), "Suppression d'une notification", Toast.LENGTH_SHORT).show();
                }
            });
        }

      /*private final void createNotification(){
        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, TutoNotificationHomeActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                REQUEST_CODE, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setTicker(notificationTitle)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_desc))
                .setContentIntent(pendingIntent);

        mNotification.notify(NOTIFICATION_ID, builder.build());
    }*/
}
