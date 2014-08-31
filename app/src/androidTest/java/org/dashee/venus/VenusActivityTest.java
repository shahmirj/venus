package org.dashee.venus;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Test our Activity Class
 */
public class VenusActivityTest
    extends ActivityInstrumentationTestCase2<Venus>
{
    /**
     * Activity instance useful for testing
     */
    private Venus venus;

    /**
     * Need this to construct the activity
     */
    public VenusActivityTest()
    {
        super(Venus.class);
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
        this.venus = getActivity();
    }

    /**
     *
     * Ensure that default values are good
    public void testDefaults()
    {
    }
     */

    /**
     * Test clicking the button does something
     */
    public void testSignIn()
    {
        final TextView email = (TextView) this.venus.findViewById(R.id.email);
        final TextView password = (TextView) this.venus.findViewById(R.id.password);
        Button signIn = (Button) this.venus.findViewById(R.id.signin);

        TextUtils.setText(this.venus, email, "shahmirj@gmail.com");
        TextUtils.setText(this.venus, password, "helloworld");

        // Check signing in works
        assertEquals("Sign In", signIn.getText());
        TouchUtils.clickView(this, signIn);
        assertEquals("Connecting...", signIn.getText());
    }

    /**
     * Make sure that when sign in is clicked without any information
     * then it shows an error message
     */
    public void testSignInShowsErrorWithInvalidValues()
    {
        Button signIn = (Button) this.venus.findViewById(R.id.signin);
        EditText email = (EditText) this.venus.findViewById(R.id.email);
        EditText password = (EditText) this.venus.findViewById(R.id.password);
        TextView errorString = (TextView) this.venus.findViewById(R.id.errorstring);

        // Ensure that the original state is set to GONE
        assertTrue(errorString.getVisibility() == View.GONE);

        // Click the sign in button and the error string should be visible and the
        // error string should be that we are missing the user
        TouchUtils.clickView(this, signIn);
        assertTrue(errorString.getVisibility() == View.VISIBLE);
        assertEquals(this.venus.getString(R.string.error_empty_email), errorString.getText());

        // Ensure that the empty password error is shown when email is set but password
        // is empty
        TextUtils.setText(this.venus, email, "shahmirj@gmail.com");
        TouchUtils.clickView(this, signIn);
        assertTrue(errorString.getVisibility() == View.VISIBLE);
        assertEquals(this.venus.getString(R.string.error_empty_password), errorString.getText());

        TextUtils.setText(this.venus, password, "helloword");
        TouchUtils.clickView(this, signIn);
        assertTrue(errorString.getVisibility() == View.GONE);
    }
}
