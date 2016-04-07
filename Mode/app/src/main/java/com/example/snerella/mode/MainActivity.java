package com.example.snerella.mode;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends Activity {
    Button mode,ring,silent;
    private AudioManager myAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ring=(Button)findViewById(R.id.normal);
        mode=(Button)findViewById(R.id.mode);
        silent=(Button)findViewById(R.id.silent);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(MainActivity.this,"Now in Ringing Mode",Toast.LENGTH_LONG).show();
            }
        });

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(MainActivity.this,"Now in Silent Mode",Toast.LENGTH_LONG).show();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mod=myAudioManager.getRingerMode();

                if(mod==AudioManager.RINGER_MODE_NORMAL){
                    Toast.makeText(MainActivity.this,"Now in Ringing Mode",Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(MainActivity.this,"Now in Silent Mode",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}