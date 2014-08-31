package org.dashee.venus;

import android.test.AndroidTestCase;

import static org.dashee.venus.VenusUtils.isValidEmail;

/**
 * Test our util class
 */
public class VenusUtilsTest
    extends AndroidTestCase
{
    /**
     * Test to see valid email address pass
     */
    public void testValidEmailAddress()
    {
        assertTrue(isValidEmail("shahmirj@gmail.com"));
    }

    /**
     * Test to see invalid email addresses return false
     */
    public void testInvalidEmailAddress()
    {
        assertFalse(isValidEmail("hello"));
        assertFalse(isValidEmail("tonight.com"));
        assertFalse(isValidEmail("@shahmirj.com"));
        assertFalse(isValidEmail("hello@shahmirj"));
    }
}