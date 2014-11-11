package com.silviu.loginsql;

 
import java.io.IOException; 
import java.io.InputStream; 
//import java.io.InputStreamReader; 
import java.io.UnsupportedEncodingException; 
import java.util.List; 
import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.client.utils.URLEncodedUtils; 
import org.apache.http.impl.client.DefaultHttpClient; 
//import org.json.JSONException; 
import org.json.JSONObject; 

public class JSONParser { 
	static InputStream is = null; 
	static JSONObject jObj = null; 
	static String json = ""; 

	public JSONParser() { 
		
	} 

	public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) 
	{ 

		try { 
			if(method == "POST"){ 

				DefaultHttpClient httpClient = new DefaultHttpClient(); 
				HttpPost httpPost = new HttpPost(url); 
				httpPost.setEntity(new UrlEncodedFormEntity(params)); 
				HttpResponse httpResponse = httpClient.execute(httpPost); 
				HttpEntity httpEntity = httpResponse.getEntity(); 
				is = httpEntity.getContent(); 
				}
			else if	(method == "GET"){ 
				DefaultHttpClient httpClient = new DefaultHttpClient(); 
				String paramString = URLEncodedUtils.format(params, "utf-8"); 
				url += "?" + paramString; 
				HttpGet httpGet = new HttpGet(url); 
				
				HttpResponse httpResponse = httpClient.execute(httpGet); 
				HttpEntity httpEntity = httpResponse.getEntity(); 
				is = httpEntity.getContent(); 
				} 
			} catch (UnsupportedEncodingException e) { 
				e.printStackTrace(); 
				} catch (ClientProtocolException e) 
				{ e.printStackTrace(); 
				} catch (IOException e) 
				{ e.printStackTrace(); 
				} 

		return jObj; 
		} 
}
