package org.dashee.venus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.dashee.venus.fragment.ForgotPasswordFragment;
import org.dashee.venus.fragment.LoginFragment;
import org.dashee.venus.fragment.RegisterFragment;

/**
 * This activity facilitates the fragment_login in of the user. It prevents
 * the user from going further without logging in
 */
public class LoginActivity
    extends FragmentActivity
{
    /**
     * Create our activity and start with the login fragment
     *
     * @param savedInstanceState The last instance
     */
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
     * Helper function to change the current fragment
     *
     * @param fragment The fragment to change to
     */
    private void changeFragmentTo(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction()
            .replace(
                R.id.fragment_login_container,
                fragment
            ).addToBackStack(null).commit();
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
     * When the user clicks on forgotPassword we create a new activity and
     * send the user to this location
     *
     * @param view The view used for click
     */
    public void gotoForgotPassword(View view)
    {
        changeFragmentTo(ForgotPasswordFragment.makeFragment());
    }

    /**
     * Send the email to the user when all is well
     *
     * @param view The current view
     */
    public void forgotPassword(View view)
    {
        if (!isValidBeforeSendingEmail())
            return;

        Toast.makeText(
            this,
            R.string.forgotpassword_email_sent,
            Toast.LENGTH_SHORT
        ).show();

        changeFragmentTo(LoginFragment.makeFragment());
    }

    /**
     * When the register button is pressed do something
     *
     * @param view The view which it was pressed from
     */
    public void gotoRegister(View view)
    {
        changeFragmentTo(RegisterFragment.makeFragment());
    }

    /**
     * Check to see if the registeration process is valid
     *
     * @return true if all fields are valid
     */
    public boolean isValidBeforeRegistering()
    {
        EditText name = (EditText) this.findViewById(R.id.name);
        EditText email = (EditText) this.findViewById(R.id.email);
        EditText password = (EditText) this.findViewById(R.id.password);
        EditText password_confirm
            = (EditText) this.findViewById(R.id.password_confirm);
        CheckBox terms = (CheckBox) this.findViewById(R.id.confirm_terms);

        // The name cannot be empty
        if (name.getText().toString().isEmpty()) {
            name.setError(getString(R.string.error_empty_name));
            return false;
        }

        // The email cannot be empty
        if (email.getText().toString().isEmpty()) {
            email.setError(getString(R.string.error_empty_email));
            return false;
        }

        // Ensure the validity of the email
        if (!VenusUtils.isValidEmail(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }

        // The password cannot be empty
        if (password.getText().toString().isEmpty()) {
            password.setError(getString(R.string.error_empty_password));
            return false;
        }

        // The password minimal length
        if (password.getText().toString().trim().length() <= 4) {
            password.setError(getString(R.string.error_small_password));
            return false;
        }

        // The confirm password is left empty
        if (password_confirm.getText().toString().isEmpty()) {
            password_confirm.setError(getString(R.string.error_empty_password));
            return false;
        }

        // Make sure the passwords match
        if (
            !password_confirm.getText().toString().contentEquals(
                password.getText().toString())
        ) {
            password_confirm.setError(getString(R.string.error_match_password));
            return false;
        }

        if (!terms.isChecked()) {
            terms.setError(getString(R.string.error_not_agreed_terms));
            return false;
        }

        return true;
    }

    /**
     * Send the email to the user when all is well
     *
     * @param view The current view
     */
    public void register(View view)
    {
        if (!isValidBeforeRegistering())
            return;

        EditText name = (EditText) this.findViewById(R.id.name);
        TextView registering = (TextView) this.findViewById(R.id.registering);
        registering.append(" " + name.getText());

        LinearLayout layout_register
            = (LinearLayout) this.findViewById(R.id.layout_register);
        LinearLayout layout_registering
            = (LinearLayout) this.findViewById(R.id.layout_registering);

        layout_register.setVisibility(View.GONE);
        layout_registering.setVisibility(View.VISIBLE);

        Toast.makeText(
            this,
            R.string.registered,
            Toast.LENGTH_LONG
        ).show();

        changeFragmentTo(LoginFragment.makeFragment());
    }
}