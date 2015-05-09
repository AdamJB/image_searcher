package com.adambednarek.flickr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.adambednarek.flickr.R;

public class SearchFragment extends Fragment {

  public static SearchFragment newInstance() {
    SearchFragment fragment = new SearchFragment();
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_search_results, container, false);
  }

  public void startSearch(String query) {
    Toast.makeText(getActivity(), "Searching: " + query, Toast.LENGTH_LONG).show();
  }

}
