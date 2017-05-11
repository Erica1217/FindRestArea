package com.example.dsm2015.findRestArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dsm2015 on 2017-04-29.
 */

public class DataParser {
    /* Url로부터 문자열을 가져옴 */
    public String getDataFromUrl(final String url) {
        URLConnection connection;
        InputStream in = null;
        try {
            connection = (new URL(url)).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            in = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();

        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder html = new StringBuilder();
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                html.append(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }

    /* 문자열을 정제한다 */
    public String refineData() {

    }

    /* 데이터를 DB에 넣는다 */
    public boolean putData(DbManager dbManager) {

    }
}
