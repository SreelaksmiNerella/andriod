package com.example.snerella.calc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        Button first;
        Button second;
        Button three;
        Button add;
        Button sub;
        Button mul;
        Button div;
        int i=0;
        TextView result;
        Double[] num=new Double[2];
        double sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        first = (Button)findViewById(R.id.b1);
        second = (Button)findViewById(R.id.b2);
        three=(Button)findViewById(R.id.b3);
        add = (Button)findViewById(R.id.plus);
        sub=(Button)findViewById(R.id.minus);
        div=(Button)findViewById(R.id.div);
        mul=(Button)findViewById(R.id.mul);


        result=(TextView)findViewById(R.id.res);

        first.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num[0]=Double.parseDouble(first.getText().toString());
                //i++;
            }
        });
        second.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num[0]= Double.parseDouble(second.getText().toString());
               // i++;
            }
        });
        three.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num[0]=Double.parseDouble(three.getText().toString());
               // i++;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                sum+=num[0];
                num[0]=0.0;
                //i=0;
                result.setText(Double.toString(sum));
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                    sum-=num[0];
                    num[0]=0.0;

                result.setText(Double.toString(sum));
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                    sum *= num[0];
                    num[0]=0.0;

                result.setText(Double.toString(sum));
            }
        });

        div.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                    sum /= num[0];
                num[0]=0.0;

                result.setText(Double.toString(sum));
            }
        });






    }


}
