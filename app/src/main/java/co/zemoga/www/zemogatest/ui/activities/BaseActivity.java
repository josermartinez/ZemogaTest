package co.zemoga.www.zemogatest.ui.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import co.zemoga.www.zemogatest.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void addFragment(Fragment fragment) {
        addFragment(fragment, R.id.container);
    }

    public void addFragment(Fragment fragment, @IdRes int containerId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        replaceFragment(fragment, R.id.container, addToBackStack);
    }

    public void replaceFragment(Fragment fragment, @IdRes int containerId, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.setCustomAnimations(
                    R.anim.slide_in_right, R.anim.slide_out_left,
                    R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.addToBackStack(null);
        }
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

}
