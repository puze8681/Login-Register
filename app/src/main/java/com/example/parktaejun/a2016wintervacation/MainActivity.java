package com.example.parktaejun.a2016wintervacation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.user_id);
        TextView passwordText = (TextView) findViewById(R.id.user_password);
        TextView nameText = (TextView) findViewById(R.id.user_name);
        TextView ageText = (TextView) findViewById(R.id.user_age);
        TextView welcomeMessage = (TextView)findViewById(R.id.welcomeMessage);

        Button managementButton = (Button)findViewById(R.id.managementButton);


        Intent intent = getIntent();
        String user_id = "ID : " + intent.getStringExtra("user_id");
        String user_password = "PW : " + intent.getStringExtra("user_password");
        String user_name = "NAME : " + intent.getStringExtra("user_name");
        String user_age = "AGE : " + intent.getStringExtra("user_age");
        String message = "환영합니다. " + intent.getStringExtra("user_name") + "님";

        idText.setText(user_id);
        passwordText.setText(user_password);
        nameText.setText(user_name);
        ageText.setText(user_age);
        welcomeMessage.setText(message);

        if(!user_id.equals("admin"))
        {
            managementButton.setVisibility(View.GONE);
        }

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTast().execute();
            }
        });
    }

    class BackgroundTast extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://iwin247.kr:8681/users/";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent managementIntent = new Intent(MainActivity.this, ManagementActivity.class);
            managementIntent.putExtra("userList", result);
            MainActivity.this.startActivity(managementIntent);
        }
    }
}