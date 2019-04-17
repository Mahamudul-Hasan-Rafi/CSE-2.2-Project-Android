package com.example.mylenovo.filemanager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountCreate extends AppCompatActivity {

    private Button button;
    private TextView text;
    private long backpressedTime;

    //private EditText email;
    private EditText username;
    private EditText password;
    private EditText confirm;
    private ProgressBar progressBar;


    private String uname, upass, uemail;
    String read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);
        setupUIView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result=validate();

              if(result) {
                  /*mAuth.createUserWithEmailAndPassword(uemail, upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()) {
                              Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();

                              database = FirebaseDatabase.getInstance().getReference(mAuth.getUid());

                              User user = new User(uemail, upass);
                              database.setValue(user);
                              Toast.makeText(getApplicationContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                              finish();
                              startActivity(new Intent(AccountCreate.this, MainActivity.class));
                          } else {
                              Toast.makeText(getApplicationContext(), "Account create failed", Toast.LENGTH_SHORT).show();
                          }
                      }
                  });*/

                  writeToFile(upass, uname);

                  Toast.makeText(getApplicationContext(), "Password saved", Toast.LENGTH_SHORT).show();
                  finish();
                  startActivity(new Intent(AccountCreate.this, MainActivity.class));
              }


            }
        });
    }

    protected void setupUIView()
    {
        //email = (EditText) findViewById(R.id.editText3);
        username = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        confirm = (EditText) findViewById(R.id.editText5);

        button = (Button) findViewById(R.id.button);

        //progressBar = (ProgressBar)findViewById(R.id.prgs_bar);
    }

    /*public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
    boolean validate()
    {
        boolean result = false;

        //uemail = email.getText().toString();
        uname = username.getText().toString();
        upass = password.getText().toString();
        String uconf = confirm.getText().toString();

        if(uname.isEmpty())
        {
            username.setError("Enter username");
            username.requestFocus();
            return result;
        }
        if(upass.isEmpty())
        {
            password.setError("Enter password");
            password.requestFocus();
            return result;
        }

        else if(uconf.isEmpty())
        {
            confirm.setError("Confirm Password");
            confirm.requestFocus();
            return result;
        }

        else if(upass.length()<4)
        {
            password.setError("Length of password must be minimum 4");
            password.requestFocus();
            return result;
        }

        else if(!uconf.equals(upass))
        {
            confirm.setError("Wrong Confirmation");
            confirm.requestFocus();
            return result;
        }

        else
        {
            result = true;
            //progressBar.setVisibility(View.VISIBLE);
            return result;
        }
    }

    void  writeToFile(String uPass, String uname)
    {
        try {
            FileOutputStream f_out = openFileOutput("User.txt", Context.MODE_PRIVATE);
            FileOutputStream f_ot = openFileOutput("User2.txt", Context.MODE_PRIVATE);

            f_out.write(uPass.getBytes());
            f_ot.write(uname.getBytes());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onBackPressed()
    {

    }

}


