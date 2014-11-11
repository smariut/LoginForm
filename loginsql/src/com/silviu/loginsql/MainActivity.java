package com.silviu.loginsql;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    
	Button b;
    EditText et,pass;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        b = (Button)findViewById(R.id.loginbtn);  
        et = (EditText)findViewById(R.id.username);
        pass= (EditText)findViewById(R.id.password);
                 
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                 new Thread(new Runnable() {
                        public void run() {
                            login();                          
                        }
                      }).start();               
            }
        });
    }
    void login(){
        try{            
              
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://10.0.2.2/accesphp/checkpass.php"); // folderul unde se afla php-urile
            
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username",et.getText().toString().trim()));  
            nameValuePairs.add(new BasicNameValuePair("password",pass.getText().toString().trim())); 
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            
            response=httpclient.execute(httppost);
            
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
             
            if(response.equalsIgnoreCase("User Found")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                    }
                });
                            
            }else{
 
            	startActivity(new Intent(MainActivity.this, Insert.class));             
            }
             
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }  
    
    
}
