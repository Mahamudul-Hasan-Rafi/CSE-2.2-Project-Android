package com.example.mylenovo.filemanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView text;
    private TextView reset;
    private long backpressedTime;
    private EditText userName;
    private  EditText userPassword;


    private String passwd;
    private  String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        str = readFromFile();

        //if(str==null)
        //{
           setContentView(R.layout.activity_main02);


            button = (Button)findViewById(R.id.button2);
            //text = (TextView)findViewById(R.id.textView2);
            text = (TextView)findViewById(R.id.textView3);
            //userName = (EditText)findViewById(R.id.editText7);
            userPassword = (EditText)findViewById((R.id.editText9));

            if(str==null)
            {
                text.setText("Not set yet?");

                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(), AccountCreate.class);
                        startActivity(intent);
                    }
                });
            }

            else
            {
                text.setText("Forget password");

                //0Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(new Intent(MainActivity.this, Password_reset.class));
                    }
                });
            }

        //}
        //else
        //{
            //setContentView(R.layout.activity_main001);


            /*button = (Button)findViewById(R.id.button2);
            //text = (TextView)findViewById(R.id.textView2);
            reset = (TextView)findViewById(R.id.textView3);
            //userName = (EditText)findViewById(R.id.editText7);
            userPassword = (EditText)findViewById((R.id.editText9));*/

        //}


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
            }
        });




    }
    private void validate() {
        // String name = userName.getText().toString();
        String password = userPassword.getText().toString();

        if (str == null)
            Toast.makeText(this, "Set Password", Toast.LENGTH_SHORT).show();
        else {
            if (str.equals(password)) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public String readFromFile()
    {

        //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

        String strr=null;
        try {
            FileInputStream fin = openFileInput("User.txt");
            InputStreamReader ipr = new InputStreamReader(fin);
            BufferedReader bfr = new BufferedReader(ipr);

            String line;
            StringBuffer stbr = new StringBuffer();

            while ((line = bfr.readLine()) != null) {
                stbr.append(line);
            }
            strr = stbr.toString();
            //Toast.makeText(getApplicationContext(), strr, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strr;
    }



}
