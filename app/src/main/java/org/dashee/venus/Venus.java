package org.dashee.venus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Venus
    extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.venus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
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
        TextView errorString = (TextView) this.findViewById(R.id.errorstring);

        // Set the visibility
        errorString.setVisibility(View.VISIBLE);

        if (email.getText().length() == 0){
            errorString.setText(R.string.error_empty_email);
            return false;
        }

        if (password.getText().length() == 0) {
            errorString.setText(R.string.error_empty_password);
            return false;
        }

        errorString.setVisibility(View.GONE);
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
}