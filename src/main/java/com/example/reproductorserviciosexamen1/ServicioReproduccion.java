package com.example.reproductorserviciosexamen1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServicioReproduccion extends Service {
    MediaPlayer mp;
    MediaPlayer vectormp [] = new MediaPlayer[3];
    Button play_pause, btn_repetir;
    MainActivity b;
    public static int repetir = 2, posicion =0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate(){
        super.onCreate();
      b = new MainActivity();
        vectormp[0] = MediaPlayer.create(this, R.raw.race);
        vectormp[1] = MediaPlayer.create(this, R.raw.sound);
        vectormp[2] = MediaPlayer.create(this, R.raw.tea);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (b.opcion==1){

            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].pause();
//            b.play_pause.setBackgroundResource(R.drawable.reproducir);
                Toast.makeText(this,"Pausa", Toast.LENGTH_SHORT).show();
            }
            else{

                vectormp[posicion].start();
                //b.play_pause.setBackgroundResource(R.drawable.pausa);
                Toast.makeText(this,"Play", Toast.LENGTH_SHORT).show();
            }

        }else if(b.opcion==2){
            if(vectormp[posicion] != null){
                vectormp[posicion].stop();

                vectormp[0] = MediaPlayer.create(this, R.raw.race);
                vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                vectormp[2] = MediaPlayer.create(this, R.raw.tea);
                posicion = 0;


                Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
            }
        }else if(b.opcion==3){
            if(repetir == 1){
                btn_repetir.setBackgroundResource(R.drawable.no_repetir);
                Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
                vectormp[posicion].setLooping(false);
                repetir = 2;
            } else {
                btn_repetir.setBackgroundResource(R.drawable.repetir);
                Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
                vectormp[posicion].setLooping(true);
                repetir = 1;
            }
        }else if(b.opcion==4){
            if(posicion < vectormp.length -1){

                if(vectormp[posicion].isPlaying()){
                    vectormp[posicion].stop();
                    posicion++;
                    vectormp[posicion].start();



                } else {
                    posicion++;


                }

            } else {
                Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
            }
        } else if (b.opcion == 5) {

            if(posicion >= 1){

                if(vectormp[posicion].isPlaying()){
                    vectormp[posicion].stop();
                    vectormp[0] = MediaPlayer.create(this, R.raw.race);
                    vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                    vectormp[2] = MediaPlayer.create(this, R.raw.tea);
                    posicion--;


                    vectormp[posicion].start();

                } else {
                    posicion--;


                }

            } else {
                Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
            }

        }


        return super.onStartCommand(intent, flags, startId);
    }
}
