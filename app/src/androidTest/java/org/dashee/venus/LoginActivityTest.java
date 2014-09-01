package org.dashee.venus;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.dashee.venus.fragment.ForgotPasswordFragment;
import org.dashee.venus.fragment.LoginFragment;

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
     * Ensure that the defaults are set as expected
     */
    public void testDefaults()
    {
        Fragment current = this.loginActivity.getSupportFragmentManager()
            .findFragmentById(R.id.fragment_login_container);

        assertTrue(current instanceof LoginFragment);
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
    public void testForgotPasswordLoadFragment()
    {
        TextView forgetpassword = (TextView) this.loginActivity.findViewById(R.id.forgotpassword);
        TouchUtils.clickView(this, forgetpassword);

        Fragment current = this.loginActivity.getSupportFragmentManager()
            .findFragmentById(R.id.fragment_login_container);

        assertTrue(current instanceof ForgotPasswordFragment);
    }

    /**
     * Helper to click the forgot password function
     */
    private void clickForgotPassword()
    {
        TextView forgotPassword = (TextView) this.loginActivity.findViewById
                (R.id.forgotpassword);
        TouchUtils.clickView(this, forgotPassword);
    }

    /**
     * Make sure that when sign in is clicked without any information
     * then it shows an error message
     */
    public void testSendEmailShowsCorrectErrorWhenInvalidValues()
    {
        clickForgotPassword();

        EditText email = (EditText) this.loginActivity.findViewById(R.id.email);
        Button sendEmail = (Button) this.loginActivity.findViewById(R.id.sendEmail);

        // Ensure that empty email is spotted
        TouchUtils.clickView(this, sendEmail);
        assertEquals(
                this.loginActivity.getString(R.string.error_empty_email),
                email.getError()
        );

        // Ensure that email is trimmed and checked for empty
        TextUtils.setText(this.loginActivity, email, "  ");
        TouchUtils.clickView(this, sendEmail);
        assertEquals(
                this.loginActivity.getString(R.string.error_empty_email),
                email.getError()
        );

        // Invalid Email type
        TextUtils.setText(this.loginActivity, email, "gmail.com");
        TouchUtils.clickView(this, sendEmail);
        assertEquals(
                this.loginActivity
                        .getString(R.string.error_invalid_email),
                email.getError()
        );
    }

    /**
     * Test clicking the button does something
     */
    public void testSendEmail()
    {
        clickForgotPassword();

        final TextView email = (TextView) this.loginActivity.findViewById(R.id.email);
        Button sendEmail = (Button) this.loginActivity.findViewById(R.id.sendEmail);

        TextUtils.setText(
                this.loginActivity,
                email,
                "shahmirj@gmail.com"
        );
        TouchUtils.clickView(this, sendEmail);

        Fragment current = this.loginActivity.getSupportFragmentManager()
            .findFragmentById(R.id.fragment_login_container);

        assertTrue(current instanceof LoginFragment);


    }
}
