package org.dashee.venus;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
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
        Button signIn = (Button) this.venus.findViewById(R.id.signin);

        assertEquals("Sign In", signIn.getText());
        TouchUtils.clickView(this, signIn);
        assertEquals("Connecting...", signIn.getText());
    }

}
