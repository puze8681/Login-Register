package com.example.parktaejun.a2016wintervacation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    public final static String LOG_TAG = "LOG_TAG";

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;
    private List<User> saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        Intent intent = getIntent();

        listView = (ListView)findViewById(R.id.listView);
        userList = new ArrayList<User>();
        saveList = new ArrayList<User>();
        adapter = new UserListAdapter(getApplicationContext(), userList, this, saveList);
        listView.setAdapter(adapter);

        try{
            Log.d(LOG_TAG,"a");
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            Log.d(LOG_TAG,"b");
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            Log.d(LOG_TAG,"c");
            int count = 0;
            Log.d(LOG_TAG,"d");
            String user_id, user_password, user_name, user_age;
            Log.d(LOG_TAG,"e");
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                user_id = object.getString("user_id");
                user_password = object.getString("user_password");
                user_name = object.getString("user_name");
                user_age = object.getString("user_age");
                User user = new User(user_id, user_password, user_name, user_age);
                if(!user_id.equals("admin")) {
                    userList.add(user);
                    saveList.add(user);
                }
                count++;
            }
            Log.d(LOG_TAG,"f");

        }
        catch (Exception e){
            e.printStackTrace();
        }

        EditText search = (EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void searchUser(String search){
        userList.clear();
        for(int i = 0; i < saveList.size(); i++){
            if(saveList.get(i).getUserID().contains(search))
            {
                userList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
