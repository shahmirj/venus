package org.dashee.venus;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Test our Activity Class
 */
public class ForgotPasswordActivityTest
    extends ActivityInstrumentationTestCase2<ForgotPasswordActivity>
{
    /**
     * Activity instance useful for testing
     */
    private ForgotPasswordActivity forgotPasswordActivity;

    /**
     * Need this to construct the activity
     */
    public ForgotPasswordActivityTest ()
    {
        super(ForgotPasswordActivity.class);
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

        this.forgotPasswordActivity = getActivity();
    }

    /**
     * Test clicking the button does something
     */
    public void testSendEmail()
    {
        final TextView email = (TextView) this.forgotPasswordActivity.findViewById(R.id.email);
        Button sendEmail = (Button) this.forgotPasswordActivity.findViewById(R.id.sendEmail);

        TextUtils.setText(this.forgotPasswordActivity, email, "shahmirj@gmail.com");

        // Check signing in works
        assertFalse(this.forgotPasswordActivity.isFinishing());
        TouchUtils.clickView(this, sendEmail);
        assertTrue(this.forgotPasswordActivity.isFinishing());
    }

    /**
     * Make sure that when sign in is clicked without any information
     * then it shows an error message
     */
    public void testSendEmailShowsCorrectErrorWhenInvalidValues()
    {
        EditText email = (EditText) this.forgotPasswordActivity.findViewById(R.id.email);
        Button sendEmail = (Button) this.forgotPasswordActivity.findViewById(R.id.sendEmail);

        // Ensure that empty email is spotted
        TouchUtils.clickView(this, sendEmail);
        assertEquals(
                this.forgotPasswordActivity.getString(R.string.error_empty_email),
                email.getError()
        );

        // Ensure that email is trimmed and checked for empty
        TextUtils.setText(this.forgotPasswordActivity, email, "  ");
        TouchUtils.clickView(this, sendEmail);
        assertEquals(this.forgotPasswordActivity.getString(R.string.error_empty_email), email.getError());

        // Invalid Email type
        TextUtils.setText(this.forgotPasswordActivity, email, "gmail.com");
        TouchUtils.clickView(this, sendEmail);
        assertEquals(this.forgotPasswordActivity.getString(R.string.error_invalid_email), email.getError());
    }
}
