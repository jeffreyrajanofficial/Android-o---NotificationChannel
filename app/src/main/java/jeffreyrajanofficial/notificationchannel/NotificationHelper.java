package jeffreyrajanofficial.notificationchannel;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;

/**
 * Created by jeffreyrajan on 15/08/17.
 */

public class NotificationHelper extends ContextWrapper {

    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "jeffreyrajanofficial.notificationchannel.android";
    public static final String IOS_CHANNEL_ID = "jeffreyrajanofficial.notificationchannel.ios";
    public static final String ANDROID_CHANNEL_NAME = "android";
    public static final String IOS_CHANNEL_NAME = "ios";


    public NotificationHelper(Context base) {
        super(base);

        //Create a channel
        createChannel();
    }

    private void createChannel() {

        //Create a notification channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

        //Enable notification alert light
        androidChannel.enableLights(true);

        //Enable notification vibration
        androidChannel.enableVibration(true);

        //set notification alert light color
        androidChannel.setLightColor(Color.CYAN);

        //Set whether notifications posted to this channel appears on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(androidChannel);

        NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
                IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

        //IMPORTANCE_MAX - unused
        //IMPORTANCE_HIGH - shows everywhere, makes noise and peeks
        //IMPORTANCE_DEFAULT - shows everywhere, makes noise, but does not visually intrude
        //IMPORTANCE_LOW - shows everywhere, but is not intrusive
        //IMPORTANCE_MIN - only shows in the shade, below the fold
        //IMPORTANCE_NONE - a notification with no importance, does not show in the shade

        iosChannel.enableLights(true);
        iosChannel.enableVibration(true);
        iosChannel.setLightColor(Color.GREEN);
        iosChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(iosChannel);
    }

    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    //Creating a notification builder for channel 1
    public Notification.Builder getAndroidNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    //Creating a notification builder for channel 1
    public Notification.Builder getIosNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), IOS_CHANNEL_ID)
                .setContentText(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }


    //Send a notification
    public void notify(int id, Notification.Builder notification) {
        getManager().notify(id, notification.build());
    }

    public void goToAndroidChannelSettings() {
        goToNotificationSettings(ANDROID_CHANNEL_ID);
    }

    public void goToIosChannelSettings() {
        goToNotificationSettings(IOS_CHANNEL_ID);
    }

    private void goToNotificationSettings(String channel) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        startActivity(intent);
    }
}
