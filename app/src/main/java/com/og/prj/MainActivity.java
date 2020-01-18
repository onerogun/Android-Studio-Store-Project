package com.og.prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private HandleDB database;
    private String pass = "user";
    private String mail = "user@x.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*    sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            String emailInPref = sharedPreferences.getString("username", "");
            String passwordInPref = sharedPreferences.getString("password", "");

            if(emailInPref.equals(mail) && passwordInPref.equals(pass)) {
                Intent intent = new Intent(this, NavigationDraw.class);
                startActivity(intent);
            }
        }*/

        database = new HandleDB(this,null,null,1);
    }

    public void onSubmitButton(View view) {

        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);

        String enteredEmail = String.valueOf(email.getText());
        String enteredPassword = String.valueOf(password.getText());

        if(!enteredEmail.equals("") && !enteredPassword.equals("")) {
            User user = new User(enteredEmail, enteredPassword);
            if (user != null) {
                if (database.userExists(user)) {
                    Intent intent = new Intent(this, NavigationDraw.class);
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        else {
            Toast toast = Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG);
            toast.show();
        }


     /*   if (enteredEmail.equals(mail) && enteredPassword.equals(pass)) {


            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", enteredEmail);
            editor.putString("password", enteredPassword);
            editor.apply();



            Intent intent = new Intent(this, NavigationDraw.class);
            startActivity(intent);
        } else if(!enteredEmail.equals(mail)){
            Toast toast = Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG);
            toast.show();
        }*/
    }

    public void onRegisterButton(View view) {
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);

        String mailtoadd = String.valueOf(email.getText());
        String passwrdtoadd = String.valueOf(password.getText());

        User user = new User(mailtoadd,passwrdtoadd);
        database.addUser(user);

    }
}
