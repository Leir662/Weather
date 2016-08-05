package com.hkk.hi.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hkk.hi.weather.utils.XmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hi on 2016/8/4.
 */
public class SearchActivity extends Activity {
    private EditText search_et;
    private ImageView city_search_iv;
    private Map<String, String> cityMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Log.d("MainActivity", "gaafggg");
        initData();
        search_et = (EditText)findViewById(R.id.search_et);
        city_search_iv = (ImageView) findViewById(R.id.city_search_iv);
        MyClickListener listener = new MyClickListener();
        city_search_iv.setOnClickListener(listener);
    }

    private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String cityName = search_et.getText().toString();
            if (TextUtils.isEmpty(cityName)){
                finish();
            }else {
                String cityCode = cityMap.get(cityName);
                if (TextUtils.isEmpty(cityCode)){
                    Toast.makeText(getApplicationContext(),"城市输入不合法，请重新输入",Toast.LENGTH_SHORT).show();
                }else{
                    int code = Integer.parseInt(cityCode);
                    Intent intent = new Intent();
                    intent.putExtra("CODE",code);
                    setResult(200,intent);
                    finish();
                }
            }
        }
    }
    private void initData() {
        try {
            InputStream is = getAssets().open("city_code.xml");
            cityMap = XmlParser.parseCity(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
