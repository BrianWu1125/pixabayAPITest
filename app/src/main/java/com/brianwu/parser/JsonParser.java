package com.brianwu.parser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
public class JsonParser {

	private Gson gson = new Gson();
	private com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();

	public <T> T parser(String paramString, Class<T> paramClass) {
		try{
		    JsonObject localJsonObject = (JsonObject)jsonParser.parse(paramString);
	
		    return gson.fromJson(localJsonObject, paramClass);
		}catch(Exception e){
			return null;
		}
	}


}
