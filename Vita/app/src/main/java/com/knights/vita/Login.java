package com.knights.vita;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userName,passWord;
    Button login;
    int userid;

    String[] user = {"user1","user2","user3"};
    String[] pass = {"pass1","pass2","pass3"};

    int flag = 0;
    String u,p;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button);

        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u = userName.getText().toString();
                p = passWord.getText().toString();


                if(u != "" && p != "") {
                    flag = 0;
                    for (int i = 0; i < user.length; i++) {
                        if(u.equals(user[i])){
                            flag = 1;
                            if(p.equals(pass[i])){

                                switch (u) {
                                    case "user1" : userid = 1;
                                                    break;

                                    case "user2" : userid = 2;
                                                    break;

                                    case "user3" : userid = 3;
                                                     break;

                                    case "user4" : userid = 4;
                                                     break;

                                    case "user5" : userid = 5;
                                                    break;
                                }

                                editor.putInt("userId", userid);
                                editor.commit();

                                Intent objectIntent = new Intent(Login.this,Home.class);
                                startActivity(objectIntent);
                                break;
                            }else{
                                Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }
                    if(flag == 0){
                        Toast.makeText(Login.this, "Invalid username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.commit();

    }
}

