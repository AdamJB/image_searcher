package com.adambednarek.flickr.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
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
import com.adambednarek.flickr.ui.widget.SearchResultAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchFragment extends Fragment {

  private static final String TAG = SearchFragment.class.getSimpleName();

  private static final String SAVE_SEARCH_RESULTS = "SAVE_SEARCH_RESULTS";
  private static final String SAVE_PHOTOS = "SAVE_PHOTOS";
  private static final String SAVE_STATE_MESSAGE_ID = "SAVE_STATE_MESSAGE_ID";

  private final FlickrController mFlickrController = FlickrController.getInstance();
  private PhotoListing mSearchResults;
  private List<PhotoViewModel> mPhotos;
  private int mLastMessageStringResId;
  private TextView mStatusText;
  private RecyclerView mRecyclerView;
  private ContentLoadingProgressBar mLoadingView;
  private SearchResultAdapter mAdapter;
  private GridLayoutManager mLayoutManager;
  private int mNumSpan;

  public static SearchFragment newInstance() {
    SearchFragment fragment = new SearchFragment();
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search_results, container, false);

    Log.d(TAG, "OnCreate with SavedInstance? " + savedInstanceState);
    if (savedInstanceState != null) {
      mSearchResults = savedInstanceState.getParcelable(SAVE_SEARCH_RESULTS);
      mPhotos = savedInstanceState.getParcelableArrayList(SAVE_PHOTOS);
      mLastMessageStringResId = savedInstanceState.getInt(SAVE_STATE_MESSAGE_ID);
    }

    mNumSpan = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
        ? 3 : 2;

    mStatusText = (TextView) view.findViewById(R.id.status_text);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.results_container);
    mLoadingView = (ContentLoadingProgressBar) view.findViewById(R.id.loader);

    showStatusText(R.string.search_photos);

    mLayoutManager = new GridLayoutManager(getActivity(), mNumSpan);
    mRecyclerView.setLayoutManager(mLayoutManager);

    mAdapter = new SearchResultAdapter();
    mRecyclerView.setAdapter(mAdapter);

    if (mPhotos != null) {
      showResults();
    } else if (mLastMessageStringResId != 0) {
      showStatusText(mLastMessageStringResId);
    }

    return view;
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable(SAVE_SEARCH_RESULTS, mSearchResults);
    outState.putParcelableArrayList(SAVE_PHOTOS, (ArrayList<? extends Parcelable>) mPhotos);
    outState.putInt(SAVE_STATE_MESSAGE_ID, mLastMessageStringResId);
    Log.d(TAG, "Saving Instance");
  }

  public void startSearch(String query) {
    mSearchResults = null;
    mPhotos = null;
    mAdapter.updateData(null);
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
    mAdapter.updateData(mPhotos);
    mRecyclerView.setVisibility(View.VISIBLE);
  }

  private void showStatusText(int errorStringResId) {
    mRecyclerView.setVisibility(View.GONE);
    mLoadingView.setVisibility(View.GONE);
    mStatusText.setText(errorStringResId);
    mStatusText.setVisibility(View.VISIBLE);
  }

  private void showLoader() {
    mStatusText.setVisibility(View.GONE);
    mRecyclerView.setVisibility(View.GONE);
    mLoadingView.setVisibility(View.VISIBLE);
  }
}
