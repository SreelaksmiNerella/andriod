package com.example.snerella.audio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    Button play, stop, record;
    private MediaRecorder myAudioRecorder;
    private  String outputFile=null;
    private EditText title;
    Button button;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    List<String> list;
    String item=null;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Alarm");
//        try{
//            if(dir.mkdir()) {
//                System.out.println("Directory created");
//            } else {
//                System.out.println("Directory is not created");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        record = (Button) findViewById(R.id.record);
        button = (Button) findViewById(R.id.title);
        spinner=(Spinner)findViewById(R.id.spinner);
        //final String outputFile;

        list = new ArrayList<String>();


        stop.setEnabled(false);
        play.setEnabled(false);

        //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Alarm/"+item+".3gp";






        File Root = Environment.getExternalStorageDirectory();
        File alarm_dir = new File(Root, "/Alarm");
        for (File f : alarm_dir.listFiles()) {
            if (f.isFile()) {
                String file_name = f.getName();
                list.add(file_name);
            }
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = (EditText) findViewById(R.id.audio);
                Toast toast = new Toast(getApplicationContext());

                //toast.makeText(MainActivity.this, title.getText(), toast.LENGTH_SHORT).show();
                list.add(title.getText().toString());
                adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                String selectedItem = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                title.setText("");
                // toast.makeText(MainActivity.this, selectedItem, toast.LENGTH_SHORT).show();

            }
        });


                record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            myAudioRecorder = new MediaRecorder();
                            myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                            myAudioRecorder.setOutputFile(outputFile);
                            myAudioRecorder.prepare();
                            myAudioRecorder.start();
                            Toast.makeText(getApplicationContext(), outputFile, Toast.LENGTH_LONG).show();

                        } catch (IllegalStateException e) {

                            e.printStackTrace();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }

                        record.setEnabled(false);
                        stop.setEnabled(true);

                       // Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
                    }
                });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;

                stop.setEnabled(false);
                play.setEnabled(true);
                record.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio recorded......", Toast.LENGTH_LONG).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                 item = parent.getItemAtPosition(pos).toString();
               outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Alarm/"+item+".3gp";
               // Toast.makeText(getApplicationContext(), item , Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), outputFile , Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                MediaPlayer m = new MediaPlayer();

                try {
                    m.setDataSource(outputFile);
                    //System.out.println(outputFile);
                    Toast.makeText(getApplicationContext(), outputFile, Toast.LENGTH_LONG).show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    m.prepare();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                m.start();
                Toast.makeText(getApplicationContext(), "Playing audio now.....", Toast.LENGTH_LONG).show();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.snerella.audio/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.snerella.audio/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}