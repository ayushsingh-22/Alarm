package com.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alarm.Broadcast;
import com.example.alarm.R;

public class MainActivity extends AppCompatActivity {
    static final int ALARM_SERVICE_CODE = 100;
    EditText edText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        edText = findViewById(R.id.edtext);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    int time = Integer.parseInt(edText.getText().toString());
                    long triggerTime = System.currentTimeMillis() + (time * 1000L);

                    Intent intent = new Intent(MainActivity.this, Broadcast.class);

                    PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, ALARM_SERVICE_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                    alarm.set(AlarmManager.RTC_WAKEUP, triggerTime, pi);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Handle the case where the input cannot be parsed as an integer
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle other exceptions
                }
            }
        });
    }
}
