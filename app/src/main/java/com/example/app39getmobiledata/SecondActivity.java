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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity{
Bundle b;
String s1,abc,h1="",h2="",h3="",h4="",h5="",h6="",h7="";
InputStream is;
TextView tv,tv1,tv2,tv3,tv4,tv5,tv6;
List<String> list ;
Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		list=new ArrayList<String>();
		b=getIntent().getExtras();
		s1=b.getString("key1");
		btn=(Button) findViewById(R.id.button1);
		tv=(TextView) findViewById(R.id.textView1);
		tv1=(TextView) findViewById(R.id.textView8);
		tv2=(TextView) findViewById(R.id.textView9);
		tv3=(TextView) findViewById(R.id.textView10);
		tv4=(TextView) findViewById(R.id.textView11);
		tv5=(TextView) findViewById(R.id.textView12);
		tv6=(TextView) findViewById(R.id.textView13);
		tv.setText(s1);
		new create().execute();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(SecondActivity.this,ThirdActivity.class);
				i.putExtra("key1",h3);
				i.putExtra("key2",h4);
				i.putExtra("key3",h5);
				i.putExtra("key4",h6);
				startActivity(i);
			}
		});
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
					if(h2.equals(s1)){
						break;
					}
					
				}
				tv1.setText(h1);
				tv2.setText(h3);
				tv3.setText(h4);
				tv4.setText(h5);
				tv5.setText(h6);
				tv6.setText(h7);
				
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
		}
		
	}
	
}
