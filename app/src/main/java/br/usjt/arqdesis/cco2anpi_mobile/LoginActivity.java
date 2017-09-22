package br.usjt.arqdesis.cco2anpi_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cco2anpi.clients.UserClient;
import br.com.cco2anpi.models.User;
import br.com.cco2anpi.tools.Crypto;

/**
 * Created by Giovanni on 17/09/2017.
 * Activity dedicated to control login activities
 */

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private ProgressDialog dialog;
    private Context context;

    /**
     * Method used when activity start
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (EditText) findViewById(R.id.login_user);
        txtPassword = (EditText) findViewById(R.id.password_user);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        context = this.getApplicationContext();

        /**
         * Set action of button
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /**
                 * This is a private class created to realize async tasks,
                 * in this case, we realize the authentication functionality:
                 * We receive 2 Strings (username and password respectively)
                 * In background we execute the function of auth
                 * and post execution (we need to execute another public function
                 * to have access and continue with the project, so we execute authentication function)
                 */
                class Authentication extends AsyncTask<String, Void, User> {

                    @Override
                    protected User doInBackground(String... strings) {
                        UserClient userClient = new UserClient("http://10.128.125.6:8080/Services");
                        User user = new User();
                        try {
                            user.setUsername(strings[0]);
                            user.setSalt(Crypto.generateRandomSalt());
                            user.setPassword(Crypto.encrypt(strings[1], user.getSalt()));
                        } catch (Exception ex) {
                            Log.e("AuthenticationException", ex.getMessage());
                        }
                        return userClient.authentication(user).getBody().getResponse();
                    }

                    protected void onPostExecute(User user) {
                        authenticate(user);
                    }
                }
                // execute dialog while authenticating
                dialog = ProgressDialog.show(LoginActivity.this, "",
                        "Loading. Please wait...", true);
                dialog.show();
                // Execute async task
                new Authentication().execute(txtUsername.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    /**
     * This is just a function to authenticate if the user is null or not
     *
     * @param user user filled
     * @return User authenticated
     */
    public User authenticate(User user) {
        Snackbar snackbar;
        //dismiss dialog while authentication over
        dialog.dismiss();
        if (user.getUserId() != null) {

            //create intent, fill extra with user object, and go to another activity
            Intent intent = new Intent(this, ManagerActivity.class);
            intent.putExtra("userID", user.getUserId());
            startActivity(intent);
            finish();
        } else {
            snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout),
                    "Username or password invalid!",
                    Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        return user;
    }

}
