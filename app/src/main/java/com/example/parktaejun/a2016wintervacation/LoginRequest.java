package com.example.parktaejun.a2016wintervacation;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parktaejun on 2017. 1. 29..
 */

public class LoginRequest extends StringRequest{
    final static private String URL = "http://iwin247.net:8681/auth/login";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
    }

    @Override
    public  Map<String, String> getParams() {
        return parameters;
    }
}
