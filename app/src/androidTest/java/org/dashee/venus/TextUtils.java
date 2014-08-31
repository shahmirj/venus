package org.dashee.venus;

import android.app.Activity;
import android.widget.TextView;

/**
 * Provide a Text utility where a view text can be changed
 * by running inside a thread
 */
public class TextUtils
{
    public static void setText(Activity activity, final TextView view, final String text)
    {
        activity.runOnUiThread(
                new Runnable()
                {
                    @Override
                    public void run ()
                    {
                        view.setText(text);
                    }
                }
        );
    }
}
