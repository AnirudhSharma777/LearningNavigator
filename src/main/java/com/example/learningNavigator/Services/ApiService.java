package com.example.learningNavigator.Services;

import com.example.learningNavigator.Exceptions.NotAIntegerException;

public interface ApiService {
    String callExternalApi(String url,String number) throws NotAIntegerException;  
}
