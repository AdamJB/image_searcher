package com.adambednarek.flickr.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.adambednarek.flickr.ui.model.PhotoViewModel;
import java.util.ArrayList;

public class FullScreenPhotoActivity extends BaseToolbarContainerActivity {

  private static final String PARAMS_PAGE = "PARAM_PAGE";
  private static final String PARAMS_PHOTOS = "PARAM_PHOTOS";

  public static Intent newIntent(Context context, int page,
      ArrayList<PhotoViewModel> photoViewModels) {
    Intent intent = new Intent(context, FullScreenPhotoActivity.class);
    intent.putExtra(PARAMS_PAGE, page);
    intent.putParcelableArrayListExtra(PARAMS_PHOTOS, photoViewModels);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
  }

  @Override protected Fragment getNewFragment() {
    int page = getIntent().getIntExtra(PARAMS_PAGE, 0);
    ArrayList<PhotoViewModel> photoViewModels =
        getIntent().getParcelableArrayListExtra(PARAMS_PHOTOS);
    return FullScreenPhotoFragment.newInstance(page, photoViewModels);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    onBackPressed();
    return true;
  }
}
