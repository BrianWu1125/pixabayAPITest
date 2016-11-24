package com.brianwu.network;

import com.brianwu.bean.HitBean;
import com.brianwu.bean.Pixabay;
import com.brianwu.parser.JsonParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by user on 2016/11/23.
 */
public class APIRequst extends  BaseAPIRequest {
    private static APIRequst ourInstance = new APIRequst();
    protected JsonParser jsonParser = new JsonParser();
    public static APIRequst getInstance() {
        return ourInstance;
    }

    private APIRequst() {
    }

    /****
     *
     *    EX : https://pixabay.com/api/?key=3821590-149cc25e6355e717b04f802b3&q=cat&image_type=photo&pretty=true&per_page=50
     * @param keyword
     * @return
     */
    public Pixabay getPixbayData(String keyword){
        String requestUrl = null;
        try {
            requestUrl = HTTPS + DNS + API + "?key="+KEY +"&q="+ URLEncoder.encode(keyword, "UTF-8") + DEFAULT_TYPE + "&per_page=100";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String  source = HttpRequest.doGet(requestUrl);
        if(!source.equals("")){
            return  jsonParser.parser(source  ,Pixabay.class);
        }
        return null;
    }
}
