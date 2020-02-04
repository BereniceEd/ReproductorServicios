package com.example.reproductorserviciosexamen1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import javax.xml.transform.Source;

public class MainActivity extends AppCompatActivity {
public Button play_pause, btn_repetir;
ImageView iv;
public int repetir = 2, posicion =0;
public static int opcion;
    private static  final String CHANNEL_ID = "com.example.reproductorserviciosexamen1.channelId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
opcion =0;
        play_pause = (Button)findViewById(R.id.btn_play);
        btn_repetir = (Button)findViewById(R.id.btn_nose);
        iv = (ImageView)findViewById(R.id.imageView);

    }
     Intent iS;
    public void PlayPause(View view){
        opcion =1;
iS = new Intent(this, ServicioReproduccion.class);//Intent para el servicio
        Intent noti = new Intent(this.getApplicationContext(),MainActivity.class);//Intent para la notificaion
        startService(iS);//Se inicia el servicio
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(noti);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.reproducir)
                .setContentText("Reproductor")
                .setContentText("Reproducidno")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(01,builder.build());
        //a ver que sera

    }

    public void pause (View view){
opcion=2;
        startService(iS);
        iv.setImageResource(R.drawable.portada1);
    }

    public void repetir(View view){
        opcion=3;
        startService(iS);

    }

    public void sig(View view){
        opcion =4;
        startService(iS);
    }
public void ant(View view){
        opcion=5;
    startService(iS);
}

}
