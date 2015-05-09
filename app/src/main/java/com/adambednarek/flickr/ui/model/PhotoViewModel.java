package com.adambednarek.flickr.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.adambednarek.flickr.api.response.Photo;

/**
 * Photo View Model, helper to get photo urls and such from the API
 */
public class PhotoViewModel implements Parcelable {

  public static final Parcelable.Creator<PhotoViewModel> CREATOR =
      new Parcelable.Creator<PhotoViewModel>() {
        public PhotoViewModel createFromParcel(Parcel source) {
          return new PhotoViewModel(source);
        }

        public PhotoViewModel[] newArray(int size) {
          return new PhotoViewModel[size];
        }
      };

  // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
  private static final String URL_FORMAT = "https://farm%s.staticflickr.com/%s/%s_%s_%s.jpg";
  private Photo mPhoto;

  public PhotoViewModel(Photo photo) {
    this.mPhoto = photo;
  }

  private PhotoViewModel(Parcel in) {
    this.mPhoto = in.readParcelable(Photo.class.getClassLoader());
  }

  public String getThumbnail() {
    return getImageUrl("z");
  }

  public String getLarge() {
    return getImageUrl("b");
  }

  /**
   * Return back an Image url with specified Size
   *
   * @param size = suffixes defined here: https://www.flickr.com/services/api/misc.urls.html
   */
  public String getImageUrl(String size) {
    return String.format(URL_FORMAT, mPhoto.getFarm(), mPhoto.getServer(), mPhoto.getId(),
        mPhoto.getSecret(), size);
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.mPhoto, 0);
  }
}
