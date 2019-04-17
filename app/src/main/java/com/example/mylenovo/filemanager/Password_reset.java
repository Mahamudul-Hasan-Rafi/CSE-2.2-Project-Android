package com.example.mylenovo.filemanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Password_reset extends AppCompatActivity {

    private EditText ans;
    private Button reset;
    String st, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        //setUIView();
        st = readFromFile();
        Toast.makeText(this, st, Toast.LENGTH_SHORT);


        ans = (EditText)findViewById(R.id.ans);
        reset = (Button)findViewById(R.id.reset);

        name = ans.getText().toString();


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    private void validate() {
        // String name = userName.getText().toString();
        name = ans.getText().toString();

        if (st == null)
            Toast.makeText(this, "No ans", Toast.LENGTH_SHORT).show();
        else {
            if (st.equals(name)) {
                finish();
                Intent intent = new Intent(getApplicationContext(), AccountCreate.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Incorrect answer", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public String readFromFile()
    {

        //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

        String strr=null;
        try {
            FileInputStream fin = openFileInput("User2.txt");
            InputStreamReader ipr = new InputStreamReader(fin);
            BufferedReader bfr = new BufferedReader(ipr);

            String line;
            StringBuffer stbr = new StringBuffer();

            while ((line = bfr.readLine()) != null) {
                stbr.append(line);
            }
            strr = stbr.toString();
            Toast.makeText(getApplicationContext(), strr, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strr;
    }

    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
