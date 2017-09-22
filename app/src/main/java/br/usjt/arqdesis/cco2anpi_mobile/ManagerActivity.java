package br.usjt.arqdesis.cco2anpi_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import br.com.cco2anpi.clients.UserClient;
import br.com.cco2anpi.models.TypeEnum;
import br.com.cco2anpi.models.User;

/**
 * Created by Giovanni on 21/09/2017.
 * Class dedicated to execute admin tasks commanded by employer
 */

public class ManagerActivity extends AppCompatActivity {

    private LinearLayout limitedLayout;
    private Button btnFuncionario;
    private Button btnEmpresa;
    private Button btnArCondicionado;
    private ProgressDialog dialog;
    private User user;
    private Context context;

    /**
     * Method used when activity start
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_options);
        limitedLayout = (LinearLayout) findViewById(R.id.linearLayoutFullOptions);
        btnFuncionario = (Button) findViewById(R.id.btnFuncionario);
        btnEmpresa = (Button) findViewById(R.id.btnEmpresa);
        btnArCondicionado = (Button) findViewById(R.id.btnArCondicionado);
        context = this.getApplicationContext();
        user = new User();
        user.setUserId(Integer.parseInt(getIntent().getStringExtra("userID")));
        final UserClient userClient = new UserClient("http://10.128.125.6:8080/Services");

        class GetUser extends AsyncTask<User, Void, User> {

            @Override
            protected User doInBackground(User... users) {
                return userClient.getUser(users[0]).getBody().getResponse();
            }

            protected void onPostExecute(User user) { configureUser(user); };
        }

        /**
         * Set action of button Funcionários
         */
        btnFuncionario.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                //Action to be implemented
                // execute dialog while authenticating
                dialog = ProgressDialog.show(ManagerActivity.this, "",
                        "Loading. Please wait...", true);
                dialog.show();
            }
        });

        /**
         * Set action of button Empresas
         */
        btnEmpresa.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                //Action to be implemented
                dialog = ProgressDialog.show(ManagerActivity.this, "",
                        "Loading. Please wait...", true);
                dialog.show();
            }
        });

        /**
         * Set action of button Ar Condicionado
         */
        btnArCondicionado.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                //Action to be implemented
                dialog = ProgressDialog.show(ManagerActivity.this, "",
                        "Loading. Please wait...", true);
                dialog.show();
            }
        });
    }

    public void configureUser(User user){
        this.user = user;
        TypeEnum typeUser = TypeEnum.getValue(this.user.getType());
        if(typeUser == TypeEnum.SYNDIC) {
            //limitedLayout.addView((LinearLayout) findViewById(R.layout.syndic_options));
        } else if(typeUser == TypeEnum.CLERK) {

        } else if(typeUser == TypeEnum.COMPANY) {

        }
    }
}