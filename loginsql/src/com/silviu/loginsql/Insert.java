package com.silviu.loginsql;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends Activity implements OnClickListener  {
	
	JSONParser jsonParser = new JSONParser(); 
	
	private EditText txtnewuser; 
	private EditText txtpassword; 
	private Button btnsavenew; 
	private int success;

	private static String url_insert_new = "http://10.0.2.2/accesphp/insertnew.php"; 

	private static final String TAG_SUCCESS = "Success"; 
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_insert); 
 
		txtnewuser = (EditText) findViewById(R.id.newusername); 
		txtpassword = (EditText) findViewById(R.id.newpassword); 
		btnsavenew = (Button) findViewById(R.id.insertbtn); 
		btnsavenew.setOnClickListener(this); 
		} 
	@Override 
	public void onClick(View v) 
	{ 
		if (v.getId()==R.id.insertbtn){ 

			new InsertNewUser().execute(); 
				Toast.makeText(getApplicationContext(), "New user inserted.", Toast.LENGTH_LONG).show(); 
 
			} 
		} 
	
	class InsertNewUser extends AsyncTask<String, String, String> { 

		String username = txtnewuser.getText().toString(); 
		String password = txtpassword.getText().toString(); 

		protected String doInBackground(String... args) { 
 
			List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			params.add(new BasicNameValuePair("username", username)); 
			params.add(new BasicNameValuePair("password", password)); 
			

			JSONObject json = jsonParser.makeHttpRequest(url_insert_new, "GET", params); 

			Log.d("Insert New Item Response", json.toString()); 

			try { 
				success = json.getInt(TAG_SUCCESS); 
				if (success == 1) { 

					} else {
					
						} 
				} catch (JSONException e) { e.printStackTrace(); } 
			
			return null; 
			} 
		
		} 
}


