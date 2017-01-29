package com.example.parktaejun.a2016wintervacation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

import static java.lang.Boolean.getBoolean;

/**
 * Created by parktaejun on 2017. 1. 29..
 */

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;
    private Activity parentActivity;
    private List<User> saveList;

    public UserListAdapter(Context context, List<User> userList, Activity parentActivity, List<User> saveList){
        this.context = context;
        this.userList = userList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.user, null);
        final TextView user_id = (TextView) v.findViewById(R.id.user_id);
        TextView user_password = (TextView) v.findViewById(R.id.user_password);
        TextView user_name = (TextView) v.findViewById(R.id.user_name);
        TextView user_age = (TextView) v.findViewById(R.id.user_age);

        user_id.setText(userList.get(i).getUserID());
        user_password.setText(userList.get(i).getUserPassword());
        user_name.setText(userList.get(i).getUserName());
        user_age.setText(userList.get(i).getUserAge());

        v.setTag(userList.get(i).getUserID());

        Button deleteButton = (Button) v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");
                            if(success == "200"){
                                userList.remove(i);
                                for(int i = 0; i < saveList.size(); i++){
                                    if(saveList.get(i).getUserID().equals(user_id.getText().toString())){
                                        saveList.remove(i);
                                        break;
                                    }
                                }
                                notifyDataSetChanged();
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(user_id.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });
        return v;
    }
}
