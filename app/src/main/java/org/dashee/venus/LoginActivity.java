package org.dashee.venus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity
        extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    /**
     * This function will ensure all values are good and return a true
     * if they are otherwise it will set the errorString and return false
     *
     * @return true if all fields are valid
     */
    private boolean isValidBeforeSigningIn ()
    {
        TextView email = (TextView) this.findViewById(R.id.email);
        TextView password = (TextView) this.findViewById(R.id.password);

        String emailString = email.getText().toString().trim();

        if (emailString.length() == 0){
            email.setError(getString(R.string.error_empty_email));
            return false;
        }

        if (!VenusUtils.isValidEmail(emailString)){
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }

        if (password.getText().length() == 0) {
            password.setError(getString(R.string.error_empty_password));
            return false;
        }

        return true;
    }

    /**
     * When we click sign in, go to our server and log the user In.
     *
     * Ensure that if it is not valid, then the error string is set
     * and the signing in is canceled
     *
     * @param view The view used for click
     */
    public void signIn(View view)
    {
        if (!this.isValidBeforeSigningIn())
            return;

        Button signIn = (Button) findViewById(R.id.signin);
        signIn.setText(R.string.connecting);
    }

    /**
     * When the user clicks on forgotPassword we create a new activity and
     * send the user to this location
     *
     * @param view The view used for click
     */
    public void forgotPassword(View view)
    {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}
