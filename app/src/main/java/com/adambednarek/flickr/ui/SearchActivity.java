package com.adambednarek.flickr.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import com.adambednarek.flickr.R;

public class SearchActivity extends BaseToolbarContainerActivity {

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    String query = intent.getStringExtra(SearchManager.QUERY);
    // Let the Fragment handle the Search / Displaying of Results
    SearchFragment fragment = (SearchFragment) getContainedFragment();
    fragment.startSearch(query);
  }

  @Override protected Fragment getNewFragment() {
    return SearchFragment.newInstance();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_search, menu);

    final MenuItem searchItem = menu.findItem(R.id.action_search);

    if (searchItem != null) {
      final SearchView searchView = (SearchView) searchItem.getActionView();
      if (searchView != null) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override public boolean onQueryTextSubmit(String query) {
            searchView.clearFocus();
            return false;
          }

          @Override public boolean onQueryTextChange(String newText) {
            return false;
          }
        });
      }
    }
    return true;
  }
}
