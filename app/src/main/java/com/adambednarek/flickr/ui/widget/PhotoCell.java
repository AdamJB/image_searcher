package com.adambednarek.flickr.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.adambednarek.flickr.R;
import com.squareup.picasso.Picasso;

public class PhotoCell extends RelativeLayout {

  private static final String TAG = PhotoCell.class.getSimpleName();
  private ImageView mImageView;

  public PhotoCell(Context context) {
    this(context, null);
  }

  public PhotoCell(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PhotoCell(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    inflate(context, R.layout.cell_photo, this);

    mImageView = (ImageView) findViewById(R.id.photo);
  }

  public void loadImage(String imageUrl) {
    //Picasso.with(getContext()).cancelRequest(mImageView);
    Log.d(TAG, "Load Image withURL: " + imageUrl);
    Picasso.with(getContext()).load(imageUrl).into(mImageView);
  }
}
