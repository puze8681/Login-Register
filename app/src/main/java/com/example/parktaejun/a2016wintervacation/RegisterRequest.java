package com.example.parktaejun.a2016wintervacation;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parktaejun on 2017. 1. 29..
 */

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://iwin247.kr:8681/auth/register";
    private Map<String, String> parameters;

    public RegisterRequest(String user_id, String user_password, String user_name, int user_age, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
        parameters.put("user_password", user_password);
        parameters.put("user_name", user_name);
        parameters.put("user_age", user_age + "");
    }

    @Override
    public  Map<String, String> getParams() {
        return parameters;
    }
}
