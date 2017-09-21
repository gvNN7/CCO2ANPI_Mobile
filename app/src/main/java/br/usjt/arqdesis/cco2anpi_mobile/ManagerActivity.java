package br.usjt.arqdesis.cco2anpi_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Giovanni on 21/09/2017.
 * Class dedicated to execute admin tasks commanded by employer
 */

public class ManagerActivity extends AppCompatActivity {

    private Button btnFuncionario;
    private Button btnEmpresa;
    private Button btnArCondicionado;
    private ProgressDialog dialog;
    private Context context;

    /**
     * Method used when activity start
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_options);
        btnFuncionario = (Button) findViewById(R.id.btnFuncionario);
        btnEmpresa = (Button) findViewById(R.id.btnEmpresa);
        btnArCondicionado = (Button) findViewById(R.id.btnArCondicionado);
        context = this.getApplicationContext();

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
}