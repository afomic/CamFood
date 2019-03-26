package afomic.com.camfood.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.ui.home.HomeActivity;

public class NotificationHelper {
    public static void sendNotificationMessage(Context context, String message) {
        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "Camfood Notification ";

        Common.createNotificationChannel(mNotificationManager,
                NOTIFICATION_CHANNEL_ID, "Camfood",
                context.getResources().getString(R.string.camfood_channel_description));

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(context.getResources().getColor(R.color.colorPrimaryDark))
                        .setContentTitle("CamFood")
                        .setContentText(message);
        mBuilder.setPriority(Notification.PRIORITY_MAX).setAutoCancel(true);

        int requestID = 0;

        Intent dismissIntent = new Intent();
        dismissIntent.setAction(Constants.DISMISS);
        dismissIntent.setClass(context, HomeActivity.class);
        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(context, requestID,
                dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.addAction(android.R.color.transparent, context.getString(android.R.string.ok), dismissPendingIntent);

        mNotificationManager.notify(requestID, mBuilder.build());
    }
}
