package org.dashee.venus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dashee.venus.R;

/**
 * The fragment that holds our login interaction
 */
public class LoginFragment
    extends Fragment
{
    /**
     * Emptry construction for all
     */
    public LoginFragment() {}

    /**
     * When the fragment is created
     *
     * @param savedInstanceState The saved instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    /**
     * When the view is created this is ran
     *
     * @param inflater The inflater
     * @param container The container
     * @param savedInstanceState The saved instance
     *
     * @return The created view
     */
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment makeFragment()
    {
        return new LoginFragment();
    }
}
