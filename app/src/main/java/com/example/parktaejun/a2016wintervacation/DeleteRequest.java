package com.example.parktaejun.a2016wintervacation;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parktaejun on 2017. 1. 29..
 */

public class DeleteRequest extends StringRequest {

    final static private String URL = "http://iwin247.kr:8681/users/delete";
    private Map<String, String> parameters;

    public DeleteRequest(String user_id, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
    }

    @Override
    public Map <String, String> getParams() {
        return parameters;
    }
}
