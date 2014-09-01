package org.dashee.venus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.dashee.venus.fragment.ForgotPasswordFragment;
import org.dashee.venus.fragment.LoginFragment;

/**
 * This activity facilitates the fragment_login in of the user. It prevents
 * the user from going further without logging in
 */
public class LoginActivity
    extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Restoring the instance so don't commit a new fragment
        if (savedInstanceState != null)
            return;

        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_login_container, LoginFragment.makeFragment())
            .commit();
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
     * Ensure that all information is valid before sending the email
     *
     * @return A boolean representing the state
     */
    private boolean isValidBeforeSendingEmail()
    {
        TextView email = (TextView) this.findViewById(R.id.email);

        String emailString = email.getText().toString().trim();

        if (emailString.length() == 0){
            email.setError(getString(R.string.error_empty_email));
            return false;
        }

        if (!VenusUtils.isValidEmail(emailString)) {
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }

        return true;
    }

    /**
     * Send the email to the user when all is well
     *
     * @param view The current view
     */
    public void sendEmail(View view)
    {
        if (!isValidBeforeSendingEmail())
            return;

        Toast.makeText(this, R.string.forgotpassword_email_sent,
                Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().beginTransaction()
           .replace(
                   R.id.fragment_login_container,
                   LoginFragment.makeFragment()
           ).addToBackStack(null).commit();
    }

    /**
     * When the user clicks on forgotPassword we create a new activity and
     * send the user to this location
     *
     * @param view The view used for click
     */
    public void forgotPassword(View view)
    {
        getSupportFragmentManager().beginTransaction()
            .replace(
                R.id.fragment_login_container,
                ForgotPasswordFragment.makeFragment()
            ).addToBackStack(null).commit();
    }
}
