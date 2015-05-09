package com.adambednarek.flickr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.adambednarek.flickr.R;

/**
 * Abstract Base Activity that has a Container for Fragments with a ToolBar
 */
public abstract class BaseToolbarContainerActivity extends AppCompatActivity {

  /**
   * Main Container Fragment Tag
   */
  private static final String FRAGMENT_TAG = "MAIN_FRAGMENT_TAG";

  private Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_container);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(mToolbar);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragment, getNewFragment(), FRAGMENT_TAG)
          .commit();
    }
  }

  /**
   * Gets a new instance of a fragment when creating the activity
   */
  protected abstract Fragment getNewFragment();

  /**
   * Gets the Main top Fragment within the Activity
   */
  protected Fragment getContainedFragment() {
    return getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
  }

  public Toolbar getToolbar() {
    return mToolbar;
  }
}
