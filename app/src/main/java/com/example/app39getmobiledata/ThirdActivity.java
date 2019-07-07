package com.example.app39getmobiledata;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends Activity{
TextView tv1,tv2,tv3,tv4;
Bundle b;
String s1,s2,s3,s4,s5,s6;
Geocoder geocoder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		tv1=(TextView) findViewById(R.id.textView2);
		tv2=(TextView) findViewById(R.id.textView4);
		b=getIntent().getExtras();
		s1=b.getString("key1");
		s2=b.getString("key2");
		s3=b.getString("key3");
		s4=b.getString("key4");
		s5=getAddress(Double.parseDouble(s1),Double.parseDouble(s2));
		s6=getAddress(Double.parseDouble(s3),Double.parseDouble(s4));
		tv1.setText(s5);
		tv2.setText(s6);
	}
	private String getAddress(double parseDouble, double parseDouble2) {
		// TODO Auto-generated method stub
		geocoder = new Geocoder(getApplicationContext(),Locale.ENGLISH);
		String ret="";
		try{
			List<Address> addresses=geocoder.getFromLocation(parseDouble, parseDouble2, 1);
			if(addresses!=null){
				Address returnaddress=addresses.get(0);
				StringBuilder streeturnaddress=new StringBuilder("Address:\n");
				for(int i=0;i<returnaddress.getMaxAddressLineIndex();i++){
					streeturnaddress.append(returnaddress.getAddressLine(i)).append("\n");
				}
				ret=streeturnaddress.toString();
			}
			else{
				
				ret="no address found";
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ret="can't get address";
		}
		return ret;
		
	}

}
