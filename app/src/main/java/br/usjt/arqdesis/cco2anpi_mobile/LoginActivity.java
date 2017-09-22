package br.usjt.arqdesis.cco2anpi_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import br.com.cco2anpi.clients.UserClient;
import br.com.cco2anpi.models.User;
import br.com.cco2anpi.tools.Crypto;

/**
 * Created by Giovanni on 17/09/2017.
 * Activity dedicated to control login activities
 */

public class LoginActivity extends AppCompatActivity {

    public static final String CONNECTIONS = "connections";
    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;
    private Button btnLogin;
    private Context context;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;

    /**
     * Method used when activity start
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_login);
        txtUsername = (TextInputEditText) findViewById(R.id.login_user);
        txtPassword = (TextInputEditText) findViewById(R.id.password_user);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        progressBar = (ProgressBar) findViewById(R.id.progress_view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        context = this.getApplicationContext();

        SharedPreferences settings = getSharedPreferences(CONNECTIONS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("services", getString(R.string.services));
        editor.commit();

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
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                        coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.blackTransparent, getApplicationContext().getTheme()));
                    }

                    @Override
                    protected User doInBackground(String... strings) {
                        UserClient userClient = new UserClient(getSharedPreferences(CONNECTIONS, 0).getString("services", ""));
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
                        progressBar.setVisibility(ProgressBar.GONE);
                        coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.white, getApplicationContext().getTheme()));
                    }
                }
                // execute dialog while authenticating
                setProgressBarIndeterminateVisibility(true);
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
        setProgressBarIndeterminateVisibility(false);
        if (user.getUserId() != null) {

            //create intent, fill extra with user object, and go to another activity
            Intent intent = new Intent(this, ManagerActivity.class);
            intent.putExtra("user", new Gson().toJson(user));
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
