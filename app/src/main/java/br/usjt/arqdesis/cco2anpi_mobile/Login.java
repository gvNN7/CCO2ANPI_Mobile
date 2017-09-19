package br.usjt.arqdesis.cco2anpi_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;

/**
 * Created by Giovanni on 17/09/2017.
 */

public class Login extends AppCompatActivity {
    Activity ativ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ativ = this;
        Intent intent = getIntent();
        String log = intent.getStringExtra(MainActivity.LOGIN);
        String pwd = intent.getStringExtra(MainActivity.PASS);
        buscaUsuario(log,pwd);
        senhas();
        usuarios();

    }

    public String buscaUsuario(String login, String pass) {
        String pos;
        for(int i=0; i<3; i++)
        {
            if(login == usuarios().get(i))
            {
                pos = usuarios().get(i);
            }
        }
        int index = 2;

        for(int i = 0; i<3; i++)
        {
            if(senhas().get(index).equals(pass))
            {
                return ("Acesso concedido");
            }
            else
            {
                return("Acesso negado");
            }
        }
        return("Não foi possivel efetuar o login");
    }

    public ArrayList<String> usuarios() {
        ArrayList<String> users = new ArrayList<>();
        users.add("atendente");
        users.add("sindico");
        users.add("funcionario");
        return users;
    }

    public ArrayList<String> senhas(){
        ArrayList<String> password = new ArrayList<>();
        password.add("a123");
        password.add("s123");
        password.add("f123");
        return password;
    }
}
