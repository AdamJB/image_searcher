package com.adambednarek.flickr.api.response;

import android.os.Parcel;
import java.util.List;

public class PhotoListing implements android.os.Parcelable {

  public static final Creator<PhotoListing> CREATOR = new Creator<PhotoListing>() {
    public PhotoListing createFromParcel(Parcel source) {
      return new PhotoListing(source);
    }

    public PhotoListing[] newArray(int size) {
      return new PhotoListing[size];
    }
  };
  
  private int page;
  private int pages;
  private int perpage;
  private long total;
  private List<Photo> photo;

  public PhotoListing() {
  }

  private PhotoListing(Parcel in) {
    this.page = in.readInt();
    this.pages = in.readInt();
    this.perpage = in.readInt();
    this.total = in.readLong();
    in.readTypedList(photo, Photo.CREATOR);
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public int getPerpage() {
    return perpage;
  }

  public void setPerpage(int perpage) {
    this.perpage = perpage;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<Photo> getPhoto() {
    return photo;
  }

  @Override public String toString() {
    return "PhotoListing{" +
        "page=" + page +
        ", pages=" + pages +
        ", perpage=" + perpage +
        ", total=" + total +
        ", photo=" + photo +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PhotoListing that = (PhotoListing) o;

    if (page != that.page) return false;
    if (pages != that.pages) return false;
    if (perpage != that.perpage) return false;
    if (total != that.total) return false;
    return !(photo != null ? !photo.equals(that.photo) : that.photo != null);
  }

  @Override public int hashCode() {
    int result = page;
    result = 31 * result + pages;
    result = 31 * result + perpage;
    result = 31 * result + (int) (total ^ (total >>> 32));
    result = 31 * result + (photo != null ? photo.hashCode() : 0);
    return result;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.page);
    dest.writeInt(this.pages);
    dest.writeInt(this.perpage);
    dest.writeLong(this.total);
    dest.writeTypedList(photo);
  }
}
