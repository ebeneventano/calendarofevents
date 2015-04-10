package com.example.marianotraining.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.marianotraining.R;

public class NormalUserActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal_user);

        Toast.makeText(getApplicationContext(), "Normal user",Toast.LENGTH_SHORT );
    }

}
