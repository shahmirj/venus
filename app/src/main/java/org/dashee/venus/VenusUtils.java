package org.dashee.venus;

/**
 * List of common function used throughout our system
 */
public class VenusUtils
{
    /**
     * Validate a given email address
     *
     * @param email the email to validate
     *
     * @return True if it is a valid email address
     */
    public static boolean isValidEmail(String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
