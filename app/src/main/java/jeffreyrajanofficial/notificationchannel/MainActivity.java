package jeffreyrajanofficial.notificationchannel;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHelper = new NotificationHelper(MainActivity.this);

        final EditText androidTitle = (EditText) findViewById(R.id.et_android_title);
        final EditText androidBody = (EditText) findViewById(R.id.et_android_body);
        Button sendAndroid = (Button) findViewById(R.id.btn_send_android);

        sendAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = androidTitle.getText().toString().trim();
                String body = androidBody.getText().toString().trim();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    Notification.Builder notificationBuilder = notificationHelper.getAndroidNotification(
                            title, body
                    );

                    notificationHelper.notify(101, notificationBuilder);
                }
            }
        });

        Button sendIosChannelNotification = (Button) findViewById(R.id.btn_send_ios);
        sendIosChannelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = ((EditText) findViewById(R.id.et_ios_title)).toString().trim();
                String body = ((EditText) findViewById(R.id.et_ios_body)).toString().trim();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    Notification.Builder notificationBuilder = notificationHelper.getIosNotification(
                            title, body
                    );

                    notificationHelper.notify(102, notificationBuilder);
                }
            }
        });

        Button androidSettings = (Button) findViewById(R.id.btn_android_channel_settings);
        androidSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationHelper.goToAndroidChannelSettings();
            }
        });

        Button iosSettings = (Button) findViewById(R.id.btn_ios_channel_settings);
        iosSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationHelper.goToIosChannelSettings();
            }
        });
    }

}
