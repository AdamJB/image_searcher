package com.adambednarek.flickr.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.adambednarek.flickr.R;
import com.adambednarek.flickr.ui.model.PhotoViewModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class FullScreenPhotoFragment extends Fragment {

  private static final String TAG = FullScreenPhotoFragment.class.getSimpleName();

  private static final String PARAMS_PAGE = "PARAM_PAGE";
  private static final String PARAMS_PHOTOS = "PARAM_PHOTOS";

  private List<PhotoViewModel> mPhotos;
  private int mPage;
  private ImageView mImageView;

  public static FullScreenPhotoFragment newInstance(int page,
      ArrayList<PhotoViewModel> photoViewModels) {
    FullScreenPhotoFragment fragment = new FullScreenPhotoFragment();
    Bundle args = new Bundle();
    args.putInt(PARAMS_PAGE, page);
    args.putParcelableArrayList(PARAMS_PHOTOS, photoViewModels);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_full_screen_photo, container, false);

    mImageView = (ImageView) view.findViewById(R.id.photo);

    if (getArguments() != null) {
      mPage = getArguments().getInt(PARAMS_PAGE);
      mPhotos = getArguments().getParcelableArrayList(PARAMS_PHOTOS);
    }

    if (savedInstanceState != null) {
      mPage = savedInstanceState.getInt(PARAMS_PAGE);
      mPhotos = savedInstanceState.getParcelableArrayList(PARAMS_PHOTOS);
    }

    Picasso.with(getActivity()).load(mPhotos.get(mPage).getLarge()).into(mImageView);

    return view;
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(PARAMS_PAGE, mPage);
    outState.putParcelableArrayList(PARAMS_PHOTOS, (ArrayList<? extends Parcelable>) mPhotos);
  }
}
