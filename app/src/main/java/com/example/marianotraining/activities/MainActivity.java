package com.example.marianotraining.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.marianotraining.R;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {

	private LoginButton loginBtn;

	private UiLifecycleHelper uiHelper;

	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);
        String app = getApplicationContext().getApplicationInfo().dataDir;

		setContentView(R.layout.activity_main);

		loginBtn = (LoginButton) findViewById(R.id.authButton);
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
                    if(user.getId().equals("10205887979742767")){
                        Intent postLogin = new Intent(MainActivity.this, AdministratorActivity.class);
                        postLogin.putExtra("user_id","10205887979742767");
                        MainActivity.this.startActivity(postLogin);
                    }else{
                        Intent postLogin = new Intent(MainActivity.this, NormalUserActivity.class);
					    MainActivity.this.startActivity(postLogin);
                    }
                } else {
//                    Intent postLogin = new Intent(MainActivity.this, AdministratorActivity.class);
//                    MainActivity.this.startActivity(postLogin);
				}
			}
		});
	}

	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				Log.d("FacebookSampleActivity", "Facebook session opened");
			} else if (state.isClosed()) {
				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};

	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}

	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
					this, PERMISSIONS));
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}

}

