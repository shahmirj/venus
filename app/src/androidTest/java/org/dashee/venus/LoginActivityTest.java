package org.dashee.venus;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Test our Activity Class
 */
public class LoginActivityTest
    extends ActivityInstrumentationTestCase2<LoginActivity>
{
    /**
     * Activity instance useful for testing
     */
    private LoginActivity loginActivity;

    /**
     * Need this to construct the activity
     */
    public LoginActivityTest ()
    {
        super(LoginActivity.class);
    }

    /**
     * Set our venus activity and other factors for testing
     *
     * @throws Exception
     */
    @Override
    protected void setUp () throws Exception
    {
        super.setUp();
        setActivityInitialTouchMode(false);
        this.loginActivity = getActivity();
    }

    /**
     * Test clicking the button does something
     */
    public void testSignIn()
    {
        final TextView email = (TextView) this.loginActivity.findViewById(R.id.email);
        final TextView password = (TextView) this.loginActivity.findViewById(R.id.password);
        Button signIn = (Button) this.loginActivity.findViewById(R.id.signin);

        TextUtils.setText(this.loginActivity, email, "shahmirj@gmail.com");
        TextUtils.setText(this.loginActivity, password, "helloworld");

        // Check signing in works
        assertEquals("Sign In", signIn.getText());
        TouchUtils.clickView(this, signIn);
        assertEquals("Connecting...", signIn.getText());
    }

    /**
     * Make sure that when sign in is clicked without any information
     * then it shows an error message
     */
    public void testSignInShowsCorrectErrorWhenInvalidValues()
    {
        Button signIn = (Button) this.loginActivity.findViewById(R.id.signin);
        EditText email = (EditText) this.loginActivity.findViewById(R.id.email);
        EditText password = (EditText) this.loginActivity.findViewById(R.id.password);

        // Ensure that empty email is spotted
        TouchUtils.clickView(this, signIn);
        assertEquals(this.loginActivity.getString(R.string.error_empty_email), email.getError());

        // Ensure that email is trimmed and checked for empty
        TextUtils.setText(this.loginActivity, email, "  ");
        TouchUtils.clickView(this, signIn);
        assertEquals(this.loginActivity.getString(R.string.error_empty_email), email.getError());

        // Invalid Email type
        TextUtils.setText(this.loginActivity, email, "gmail.com");
        TouchUtils.clickView(this, signIn);
        assertEquals(this.loginActivity.getString(R.string.error_invalid_email), email.getError());

        // Ensure Password is not left empty
        TextUtils.setText(this.loginActivity, email, "shahmirj@gmail.com");
        TouchUtils.clickView(this, signIn);
        assertEquals(this.loginActivity.getString(R.string.error_empty_password), password.getError());
    }

    /**
     * Test to see when the user clicks forget password the correct activity
     * is loaded
     */
    public void testForgotPasswordLoadsActivity()
    {
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(ForgotPasswordActivity.class.getName(), null, false);

        TextView forgetpassword = (TextView) this.loginActivity.findViewById(R.id.forgotpassword);
        TouchUtils.clickView(this, forgetpassword);

        ForgotPasswordActivity forgotPasswordActivity
                = (ForgotPasswordActivity) getInstrumentation()
                .waitForMonitorWithTimeout(monitor, 10000);

        assertNotNull(forgotPasswordActivity);
        forgotPasswordActivity.finish();
    }
}
