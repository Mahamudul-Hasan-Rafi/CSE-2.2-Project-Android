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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
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
    private FirebaseAuth mAuth;

    private String passwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button2);
        text = (TextView)findViewById(R.id.textView2);
        reset = (TextView)findViewById(R.id.textView3);
        userName = (EditText)findViewById(R.id.editText7);
        userPassword = (EditText)findViewById((R.id.editText9));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, Password_reset.class));
            }
        });
    }
    private void validate()
    {
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        /*mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Log in successful", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Log in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        readFromFile(name, password);

        if(password.equals(passwd))
        {
            Toast.makeText(getApplicationContext(), "Log in successful", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);
        }
        /*else if(passwd==null)
        {
            Toast.makeText(getApplicationContext(), "Not registered yet", Toast.LENGTH_SHORT).show();
        }*/
        else
        {
            Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
        }

    }

    public void readFromFile(String uName, String uPassword)
    {
        try {
            FileInputStream f_read = openFileInput(uName+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(f_read);
            BufferedReader bufferedReader = new BufferedReader((inputStreamReader));

            passwd = bufferedReader.readLine();
        }
        catch (FileNotFoundException e) {

            Toast.makeText(getApplicationContext(), "No user in this name registered", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openActivity2()
    {
        finish();
        Intent intent = new Intent(this, AccountCreate.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {


        //backpressedTime = System.currentTimeMillis();

        if(backpressedTime+2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else
        {
            Toast toast = Toast.makeText(this,"Press again to exit", Toast.LENGTH_SHORT);
            toast.show();
        }
        backpressedTime = System.currentTimeMillis();
    }
}
