package br.usjt.arqdesis.cco2anpi_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import br.com.cco2anpi.models.TypeEnum;
import br.com.cco2anpi.models.User;

/**
 * Created by Giovanni on 21/09/2017.
 * Class dedicated to execute admin tasks commanded by employer
 */

public class ManagerActivity extends AppCompatActivity {

    private LinearLayout limitedLayout;
    private LinearLayout layoutSyndic;
    private LinearLayout layoutEmployee;
    private LinearLayout layoutClerck;
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
        layoutSyndic = (LinearLayout) getLayoutInflater().inflate(R.layout.syndic_options, null);
        layoutEmployee  = (LinearLayout) getLayoutInflater().inflate(R.layout.employee_options, null);
        layoutClerck = (LinearLayout) getLayoutInflater().inflate(R.layout.clerk_options, null);
        btnFuncionario = (Button) findViewById(R.id.btnFuncionario);
        btnEmpresa = (Button) findViewById(R.id.btnEmpresa);
        btnArCondicionado = (Button) findViewById(R.id.btnArCondicionado);
        context = this.getApplicationContext();
        user = new User( new Gson().fromJson(getIntent().getStringExtra("user"), User.class));

        TypeEnum typeUser = TypeEnum.getValue(this.user.getType());
        if (typeUser == TypeEnum.SYNDIC) {
            limitedLayout.addView(layoutSyndic);
        } else if (typeUser == TypeEnum.CLERK) {
            limitedLayout.addView(layoutClerck);
        } else if (typeUser == TypeEnum.COMPANY) {
            limitedLayout.addView(layoutEmployee);
        }
    }

}