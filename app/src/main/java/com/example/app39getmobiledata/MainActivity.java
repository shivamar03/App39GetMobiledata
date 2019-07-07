package com.example.app39getmobiledata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
Spinner sp;
InputStream is;
String abc;
String h1="",h2="",h3="",h4="",h5="",h6="",h7="";
List<String> list;
ArrayAdapter<String> ad;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=(Spinner) findViewById(R.id.spinner1);
        list = new ArrayList<String>();
        list.add("Select User");
        new create().execute();
        
    }
    public class create extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try{
				HttpClient obj1=new DefaultHttpClient();
				HttpPost obj2=new HttpPost("http://192.168.1.106/webservices/mobile2.php");
				HttpResponse obj3=obj1.execute(obj2);
				HttpEntity obj4=obj3.getEntity();
				is=obj4.getContent();
			}
			catch (Exception e) {
				// TODO: handle exception
				Log.e("Error in first module",e.toString());
				
			}
			try{
				BufferedReader obj5=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb=new StringBuilder();
				String line=null;
				while((line=obj5.readLine())!=null){
					sb.append(line+"\n");
					
				}
				is.close();
				abc=sb.toString();
			}
			catch (Exception e) {
				// TODO: handle exception
				Log.e("Error in Second module",e.toString());
				
			}
			try{
				JSONArray ja=new JSONArray(abc);
				for(int i=0;i<ja.length();i++){
					JSONObject jsonobject=ja.getJSONObject(i);
					h1=h1+jsonobject.getString("mobileid");
					h2=h2+jsonobject.getString("userid");
					h3=h3+jsonobject.getString("latnetwork");
					h4=h4+jsonobject.getString("longnetwork");
					h5=h5+jsonobject.getString("latgps");
					h6=h6+jsonobject.getString("longgps");
					h7=h7+jsonobject.getString("bluetoothid");
					list.add(h2);
				
				}
				
			}
			catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getApplicationContext(), "Exception", 1).show();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ad=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,list);
			
			sp.setAdapter(ad);
			
			sp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Object ob=sp.getItemAtPosition(arg2);
					if(!ob.equals("Select User"))
					{
					Intent i=new Intent(MainActivity.this,SecondActivity.class);
					i.putExtra("key1",ob.toString());
					startActivity(i);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Select any user", 1).show();
				}
			});
		
		}
    	
    }
}
