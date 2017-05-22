package com.example.linyuming.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    private EditText user;
    private EditText password;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember){
            String account = pref.getString("account","");
            String password1 = pref.getString("password","");
            user.setText(account);
            password.setText(password1);
            checkBox.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = user.getText().toString();
                String passwordEdit = password.getText().toString();
                if (account.equals("admin")&&passwordEdit.equals("123456")){
                    editor = pref.edit();
                    if(checkBox.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",passwordEdit);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"account or password is invalid ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

