package com.brianwu.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by user on 2016/11/23.
 */
public class HttpRequest {


    public static String doGet(String urlStr ) {
        URLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL(urlStr);
            if(urlStr.contains(BaseAPIRequest.HTTPS)){
               urlConnection = (HttpsURLConnection) url.openConnection();
                ((HttpsURLConnection)urlConnection).setRequestMethod("GET");
            }else{
                urlConnection = (HttpURLConnection) url.openConnection();
                ((HttpURLConnection)urlConnection).setRequestMethod("GET");
            }
            HttpURLConnection.setFollowRedirects(true);
            InputStream is = null;
            String encoding = urlConnection.getContentEncoding();
            urlConnection.connect();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            int responseCode = httpConnection.getResponseCode();
            /* redirect URL */
            if ((responseCode == 302 || responseCode == 301)) {
                return doGet(httpConnection.getHeaderField("Location"));
            }
            if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
                is = new GZIPInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                  int tmplength = 2048;
                byte[] tmpfile = new byte[tmplength];
                int read = 0;
                while ((read = is.read(tmpfile)) != -1) {
                    bos.write(tmpfile, 0, read);
                }
                bos.close();
                is = null;
                result = new String(bos.toByteArray());

            } else {
                is = new BufferedInputStream(urlConnection.getInputStream());
                byte[] contents = new byte[1024];

                int bytesRead = 0;
                while ((bytesRead = is.read(contents)) != -1) {
                    result += new String(contents, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                if(urlStr.contains(BaseAPIRequest.HTTPS)){
                    ((HttpsURLConnection)urlConnection).disconnect();
                }else{
                    ((HttpURLConnection)urlConnection).disconnect();
                }
            }
        }
        return result;
    }
}