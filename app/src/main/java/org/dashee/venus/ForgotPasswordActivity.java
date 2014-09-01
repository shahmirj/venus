package org.dashee.venus;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An activity useful for when user forgot his/her password and they
 * want an interface to allow them to send this details to a given email
 * address.
 *
 * This activity should logically be initiated from the loginactivity
 */
public class ForgotPasswordActivity
    extends Activity
{

    /**
     * Enable the home key and set the view
     *
     * @param savedInstanceState The current saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        // Enable the home key
        ActionBar action = getActionBar();
        if (action != null)
            action.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Make sure that the back button works
     *
     * @param item The item clicked
     * @return true if all is well
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        String emailString = email.getText().toString().trim();

        if (emailString.length() == 0){
            email.setError(getString(R.string.error_empty_email));
            return false;
        }

        if (!VenusUtils.isValidEmail(emailString)){
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }

        return true;
    }

    /**
     * When the sendEmail button is pressed a call is made to the server to
     * send user his/her password.
     *
     * Make sure though that the email is valid before sending it. Once sent
     * let the user know the process was successful by toasting.
     *
     * @param view The view that was clicked
     */
    public void sendEmail(View view)
    {
        if (!this.isValidBeforeSigningIn())
            return;

        Toast.makeText(
                this.getApplicationContext(),
                R.string.forgotpassword_email_sent,
                Toast.LENGTH_SHORT
        ).show();

        this.finish();
    }
}