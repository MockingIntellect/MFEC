package mfec.example.danupol.githubcontact;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mfec.example.danupol.githubcontact.ContactDetail.ContactDetailFragment;
import mfec.example.danupol.githubcontact.ContactList.ContactListFragment;

public class MainActivity extends AppCompatActivity implements ContactListFragment.OnFragmentInteractionListener {

    private final String MAIN_STACK = "MAIN_STACK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactListFragment contactListFragment = ContactListFragment.newInstance(this);
        replaceFragment(contactListFragment);
    }

    @Override
    public void OnPressUser(String username) {
        ContactDetailFragment fragment = ContactDetailFragment.newInstance(username);
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(MAIN_STACK)
                .replace(R.id.main_frame, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int fragCount = getSupportFragmentManager().getBackStackEntryCount();
        if(fragCount < 2) {
            super.onBackPressed();
            return;
        }

        getSupportFragmentManager().popBackStackImmediate();
    }
}
