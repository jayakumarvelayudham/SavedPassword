package com.tth.savedpassword;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


private static final String SPF_NAME = "vidslogin"; //  <--- Add this
private static final String USERNAME = "username";  //  <--- To save username
private static final String PASSWORD = "password";  //  <--- To save password
        CheckBox chkRememberMe; //      <--- You even not taken CheckBox, SILLY WORKER
        EditText etUserName, etPassword;
        Button login;
        //      <--- You even not taken CheckBox,     SILLY WORKER

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    chkRememberMe = (CheckBox) findViewById(R.id.chevk);  //      <---  Instantiate CheckBox...
    etUserName = (EditText) findViewById(R.id.eed1); //  <---  Instantiate     EditText...
    etPassword = (EditText) findViewById(R.id.edd12); //  <---  Instantiate EditText...
     login = (Button) findViewById(R.id.btn);


    //  ADD THIS  TO  READ  SAVED  username & password  NEXT-TIME OPENING Application
    SharedPreferences loginPreferences = getSharedPreferences(SPF_NAME,
            Context.MODE_PRIVATE);
    etUserName.setText(loginPreferences.getString(USERNAME, ""));
    etPassword.setText(loginPreferences.getString(PASSWORD, ""));


//    register.setOnClickListener(new View.OnClickListener() {
//
//        public void onClick(View v) {
//            // Switching to Register screen
//            Intent i = new Intent(getApplicationContext(), home.class);
//            startActivity(i);
//        }
//    });
//
//

    login.setOnClickListener(new View.OnClickListener() {

        public void onClick(View arg0) {
            loginAction();

        }
    });


}

    private void loginAction() {

        if (etUserName!= null)
        {
            //   ADD  to save  and  read next time
            String strUserName = etUserName.getText().toString().trim();
            String strPassword = etPassword.getText().toString().trim();
            if (null == strUserName || strUserName.length() == 0) {
                //  showToast("Enter Your Name");
                etUserName.requestFocus();
            } else if (null == strPassword || strPassword.length() == 0) {
                //      showToast("Enter Your Password");
                etPassword.requestFocus();
            } else {
                if (chkRememberMe.isChecked()) {
                    SharedPreferences loginPreferences = getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
                    loginPreferences.edit().putString(USERNAME, strUserName).putString(PASSWORD, strPassword).commit();
                } else {
                    SharedPreferences loginPreferences = getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
                    loginPreferences.edit().clear().commit();
                }


                Intent intent = new Intent(MainActivity.this, HomePage.class);
//                intent.putExtra("username", userName.getText().toString());
                startActivity(intent);
            }
        }
    }
}