package com.adambednarek.flickr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.adambednarek.flickr.R;
import com.adambednarek.flickr.api.controller.FlickrController;
import com.adambednarek.flickr.api.response.Photo;
import com.adambednarek.flickr.api.response.PhotoListing;
import com.adambednarek.flickr.api.response.SearchResponse;
import com.adambednarek.flickr.ui.model.PhotoViewModel;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchFragment extends Fragment {

  private static final String TAG = SearchFragment.class.getSimpleName();

  private final FlickrController mFlickrController = FlickrController.getInstance();
  private PhotoListing mSearchResults;
  private List<PhotoViewModel> mPhotos;
  private TextView mStatusText;
  private RecyclerView mResultsContainer;
  private ContentLoadingProgressBar mLoadingView;

  public static SearchFragment newInstance() {
    SearchFragment fragment = new SearchFragment();
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search_results, container, false);

    mStatusText = (TextView) view.findViewById(R.id.status_text);
    mResultsContainer = (RecyclerView) view.findViewById(R.id.results_container);
    mLoadingView = (ContentLoadingProgressBar) view.findViewById(R.id.loader);

    showStatusText(R.string.search_photos);

    return view;
  }

  public void startSearch(String query) {
    mSearchResults = null;
    showLoader();
    mFlickrController.searchPhotos(query, new Callback<SearchResponse>() {
      @Override public void success(SearchResponse searchResponse, Response response) {
        mSearchResults = searchResponse.getPhotos();
        Log.d(TAG, "Results? " + mSearchResults);
        if (mSearchResults == null || mSearchResults.getTotal() == 0) {
          showStatusText(R.string.no_results);
        } else {
          mPhotos = new ArrayList<>();
          for (Photo photo : mSearchResults.getPhoto()) {
            mPhotos.add(new PhotoViewModel(photo));
          }
          showResults();
        }
      }

      @Override public void failure(RetrofitError error) {
        Log.e(TAG, "Query Failed", error);
        showStatusText(R.string.search_error);
      }
    });
  }

  public void showResults() {
    mStatusText.setVisibility(View.GONE);
    mLoadingView.setVisibility(View.GONE);

    mResultsContainer.setVisibility(View.VISIBLE);

    Log.d(TAG, "Photo URL? " + mPhotos.get(0).getThumbnail());
  }

  private void showStatusText(int errorStringResId) {
    mResultsContainer.setVisibility(View.GONE);
    mLoadingView.setVisibility(View.GONE);
    mStatusText.setText(errorStringResId);
    mStatusText.setVisibility(View.VISIBLE);
  }

  private void showLoader() {
    mStatusText.setVisibility(View.GONE);
    mResultsContainer.setVisibility(View.GONE);
    mLoadingView.setVisibility(View.VISIBLE);
  }
}
