package com.example.parktaejun.a2016wintervacation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public final static String LOG_TAG = "LOG_TAG";

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
        String user_id = intent.getStringExtra("user_id");
        String user_password = intent.getStringExtra("user_password");
        String user_name = intent.getStringExtra("user_name");
        String user_age = intent.getStringExtra("user_age");
        String message = intent.getStringExtra("user_name") + "님";

        idText.setText("ID : " + user_id);
        passwordText.setText("PW : " + user_password);
        nameText.setText("NAME : " + user_name);
        ageText.setText("AGE : " + user_age);
        welcomeMessage.setText(message);

        if(!user_id.equals("admin"))
        {
            managementButton.setVisibility(View.GONE);
        }

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"1");
                new BackgroundTask().execute();
                Log.d(LOG_TAG,"2");
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {

        String target;
        @Override
        protected void onPreExecute() {
            target = "http://iwin247.kr:8681/users/";
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(LOG_TAG,"10");
            try{
                Log.d(LOG_TAG,"6");
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                Log.d(LOG_TAG,"7");
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d(LOG_TAG,"8");
                return stringBuilder.toString().trim();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            Log.d(LOG_TAG,"9");
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
            Log.d(LOG_TAG,"3");
        }

        @Override
        public void onPostExecute(String result){
            Intent managementIntent = new Intent(MainActivity.this, ManagementActivity.class);
            managementIntent.putExtra("userList", result);
            Log.d(LOG_TAG,"4");
            MainActivity.this.startActivity(managementIntent);
            Log.d(LOG_TAG,"5");
        }
    }
}
