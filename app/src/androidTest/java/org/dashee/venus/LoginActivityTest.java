package org.dashee.venus;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.dashee.venus.fragment.ForgotPasswordFragment;
import org.dashee.venus.fragment.LoginFragment;
import org.dashee.venus.fragment.RegisterFragment;

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
    protected void setUp() throws Exception
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
        final TextView email = (TextView) this.loginActivity
            .findViewById(R.id.email);
        final TextView password = (TextView) this.loginActivity
            .findViewById(R.id.password);
        Button signIn = (Button) this.loginActivity.findViewById(R.id.signin);

        TextUtils.setText(this.loginActivity, email, "shahmirj@gmail.com");
        TextUtils.setText(this.loginActivity, password, "helloworld");

        // Check signing in works
        assertEquals(
            this.loginActivity.getString(R.string.signin),
            signIn.getText()
        );
        TouchUtils.clickView(this, signIn);
        assertEquals(
            this.loginActivity.getString(R.string.connecting),
            signIn.getText()
        );
    }

    /**
     * Make sure that when sign in is clicked without any information
     * then it shows an error message
     */
    public void testSignInShowsCorrectErrorWhenInvalidValues()
    {
        Button signIn = (Button) this.loginActivity.findViewById(R.id.signin);
        EditText email = (EditText) this.loginActivity.findViewById(R.id.email);
        EditText password = (EditText) this.loginActivity
            .findViewById(R.id.password);

        // Ensure that empty email is spotted
        TouchUtils.clickView(this, signIn);
        assertEquals(
            this.loginActivity.getString(R.string.error_empty_email),
            email.getError()
        );

        // Ensure that email is trimmed and checked for empty
        TextUtils.setText(this.loginActivity, email, "  ");
        TouchUtils.clickView(this, signIn);
        assertEquals(
            this.loginActivity.getString(R.string.error_empty_email),
            email.getError()
        );

        // Invalid Email type
        TextUtils.setText(this.loginActivity, email, "gmail.com");
        TouchUtils.clickView(this, signIn);
        assertEquals(
            this.loginActivity.getString(R.string.error_invalid_email),
            email.getError()
        );

        // Ensure Password is not left empty
        TextUtils.setText(this.loginActivity, email, "shahmirj@gmail.com");
        TouchUtils.clickView(this, signIn);
        assertEquals(
            this.loginActivity.getString(R.string.error_empty_password),
            password.getError()
        );
    }

    /**
     * Test to see when the user clicks forget password the correct activity
     * is loaded
     */
    public void testForgotPasswordLoadFragment()
    {
        TextView forgetpassword = (TextView) this.loginActivity
            .findViewById(R.id.forgotpassword);
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
     * Helper to click the forgot password function
     */
    private void clickRegister()
    {
        TextView register = (TextView) this.loginActivity.findViewById
            (R.id.register);
        TouchUtils.clickView(this, register);
    }

    /**
     * Make sure that when sign in is clicked without any information
     * then it shows an error message
     */
    public void testSendEmailShowsCorrectErrorWhenInvalidValues()
    {
        clickForgotPassword();

        EditText email = (EditText) this.loginActivity
            .findViewById(R.id.email);
        Button sendEmail = (Button) this.loginActivity
            .findViewById(R.id.send_password);

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

        final TextView email = (TextView) this.loginActivity
            .findViewById(R.id.email);
        Button sendEmail = (Button) this.loginActivity
            .findViewById(R.id.send_password);

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

    /**
     * Test clicking the load fragment button changes the correct fragment
     */
    public void testRegisterLoadFragment()
    {
        TextView register = (TextView) this.loginActivity
            .findViewById(R.id.register);
        TouchUtils.clickView(this, register);

        Fragment current = this.loginActivity.getSupportFragmentManager()
            .findFragmentById(R.id.fragment_login_container);

        assertTrue(current instanceof RegisterFragment);
    }

    /**
     * Test register fails when invalid values are sent. And cascade them
     * down till we check all combinations
     */
    public void testRegisterInvalidValues()
    {
        clickRegister();

        EditText name = (EditText) this.loginActivity
            .findViewById(R.id.name);
        EditText email = (EditText) this.loginActivity
            .findViewById(R.id.email);
        EditText password = (EditText) this.loginActivity
            .findViewById(R.id.password);
        EditText password_confirm = (EditText) this.loginActivity
            .findViewById(R.id.password_confirm);
        Button register = (Button) this.loginActivity
            .findViewById(R.id.register);
        CheckBox terms = (CheckBox) this.loginActivity
            .findViewById(R.id.confirm_terms);

        // Fail the fact there is no name
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_empty_name),
            name.getError()
        );

        // Fail the fact there is no email
        TextUtils.setText(this.loginActivity, name, "Shahmir Javaid");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_empty_email),
            email.getError()
        );

        // Fail the fact the email is invalid
        TextUtils.setText(this.loginActivity, email, "shahmirj");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_invalid_email),
            email.getError()
        );

        // Fail the fact the password is empty
        TextUtils.setText(this.loginActivity, email, "shahmirj@gmail.com");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_empty_password),
            password.getError()
        );

        // Fail the fact the password is invalid
        TextUtils.setText(this.loginActivity, password, "1234");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_small_password),
            password.getError()
        );

        // Fail the fact that password_confirm is empty
        TextUtils.setText(this.loginActivity, password, "12345");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_empty_password),
            password_confirm.getError()
        );

        // Fail the fact that password_confirm does not match
        TextUtils.setText(this.loginActivity, password_confirm, "1234");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_match_password),
            password_confirm.getError()
        );

        // Fail the fact, the user did not agree any terms
        TextUtils.setText(this.loginActivity, password_confirm, "12345");
        TouchUtils.clickView(this, register);
        assertEquals(
            this.loginActivity
                .getString(R.string.error_not_agreed_terms),
            terms.getError()
        );
    }

    /**
     * Ensure that sending correct values to register actually registers the
     * user
     */
    public void testCorrectValuesRegistersTheUser()
    {
        clickRegister();

        LinearLayout layout_registering = (LinearLayout)this.loginActivity
            .findViewById(R.id.layout_registering);
        LinearLayout layout_register = (LinearLayout) this.loginActivity
            .findViewById(R.id.layout_register);

        // Test the initial state
        assertSame(View.VISIBLE, layout_register.getVisibility());
        assertSame(View.GONE, layout_registering.getVisibility());

        // Initialize the view fields
        EditText name = (EditText) this.loginActivity
            .findViewById(R.id.name);
        EditText email = (EditText) this.loginActivity
            .findViewById(R.id.email);
        EditText password = (EditText) this.loginActivity
            .findViewById(R.id.password);
        EditText password_confirm = (EditText) this.loginActivity
            .findViewById(R.id.password_confirm);
        CheckBox terms = (CheckBox) this.loginActivity
            .findViewById(R.id.confirm_terms);
        Button register = (Button) this.loginActivity
            .findViewById(R.id.register);
        TextView registering = (TextView) this.loginActivity
            .findViewById(R.id.registering);

        // Set the required information
        TextUtils.setText(this.loginActivity, name, "Shahmir Javaid");
        TextUtils.setText(this.loginActivity, email, "shahmir@gmail.com");
        TextUtils.setText(this.loginActivity, password, "12345");
        TextUtils.setText(this.loginActivity, password_confirm, "12345");
        TouchUtils.clickView(this, terms);

        // Click register
        TouchUtils.clickView(this, register);

        // The register should switch to loading
        assertEquals(
            this.loginActivity.getString(R.string.registering) + " " +
                name.getText().toString(),
            registering.getText().toString()
        );
        assertSame(View.GONE, layout_register.getVisibility());
        assertSame(View.VISIBLE, layout_registering.getVisibility());

        // Assert the correct fragment
        //assertTrue(current instanceof LoginFragment);
    }
}
