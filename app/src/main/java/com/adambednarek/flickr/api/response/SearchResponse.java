package com.adambednarek.flickr.api.response;

import android.os.Parcel;

public class SearchResponse extends BaseResponse implements android.os.Parcelable {

  public static final Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {
    public SearchResponse createFromParcel(Parcel source) {
      return new SearchResponse(source);
    }

    public SearchResponse[] newArray(int size) {
      return new SearchResponse[size];
    }
  };

  private PhotoListing photos;

  public SearchResponse() {
  }

  private SearchResponse(Parcel in) {
    this.photos = in.readParcelable(PhotoListing.class.getClassLoader());
    this.stat = in.readString();
    this.code = in.readInt();
    this.message = in.readString();
  }

  public PhotoListing getPhotos() {
    return photos;
  }

  @Override public String toString() {
    return "SearchResponse{" +
        "photos=" + photos +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    SearchResponse that = (SearchResponse) o;

    return !(photos != null ? !photos.equals(that.photos) : that.photos != null);
  }

  @Override public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (photos != null ? photos.hashCode() : 0);
    return result;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.photos, 0);
    dest.writeString(this.stat);
    dest.writeInt(this.code);
    dest.writeString(this.message);
  }
}
