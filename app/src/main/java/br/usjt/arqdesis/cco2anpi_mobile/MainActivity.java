package br.usjt.arqdesis.cco2anpi_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText pwd;
    public static final String LOGIN = "br.usjt.arqdesis.cliente.login";
    public static final String PASS = "br.usjt.arqdesis.cliente.pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText)findViewById(R.id.login_user);
        pwd = (EditText)findViewById(R.id.password_user);
    }

    public void efetuaLogin(View view){
        Intent intent = new Intent(this, Login.class);
        String lo = login.getText().toString();
        String pa = pwd.getText().toString();
        intent.putExtra(LOGIN,lo);
        intent.putExtra(PASS,pa);
        startActivity(intent);
    }
}
