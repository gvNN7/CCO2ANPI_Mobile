package br.usjt.arqdesis.cco2anpi_mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.cco2anpi.clients.UserClient;
import br.com.cco2anpi.models.User;


import java.util.ArrayList;

/**
 * Created by Giovanni on 17/09/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (EditText) findViewById(R.id.login_user);
        txtPassword = (EditText) findViewById(R.id.password_user);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                UserClient userClient = new UserClient("http://192.168.0.1:8080/Services");
                User user = new User();
                user.setUsername(txtUsername.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                try {
                    user = userClient.authentication(user).getBody().getResponse();
                    if (user != null) {
                        Log.i("loginTest", "passou");
                    } else {
                        Log.i("loginTest", "nulo");
                    }
                } catch (Exception ex) {
                    Log.e("loginException", "error: " + ex.getMessage());

                }
            }
        });
    }

}
