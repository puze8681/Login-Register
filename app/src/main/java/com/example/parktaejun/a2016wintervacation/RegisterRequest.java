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

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge + "");
    }

    @Override
    public  Map<String, String> getParams() {
        return parameters;
    }
}
