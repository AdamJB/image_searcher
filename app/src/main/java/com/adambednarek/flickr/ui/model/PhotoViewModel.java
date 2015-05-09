package com.adambednarek.flickr.ui.model;

import com.adambednarek.flickr.api.response.Photo;

/**
 * Photo View Model, helper to get photo urls and such from the API
 */
public class PhotoViewModel {

  // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
  private static final String URL_FORMAT = "https://farm%s.staticflickr.com/%s/%s_%s_%s.jpg";

  private Photo mPhoto;

  public PhotoViewModel(Photo photo) {
    this.mPhoto = photo;
  }

  public String getThumbnail() {
    return getImageUrl("m");
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
}
