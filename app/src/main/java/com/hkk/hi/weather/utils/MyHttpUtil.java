package com.hkk.hi.weather.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hi on 2016/8/3.
 */
public  class  MyHttpUtil {

    URL url = null;
    public String getData(URL requestUrl){
        String result = "";
        InputStream in = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setReadTimeout(8000);
             in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            if ((line = reader.readLine()) != null){
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
