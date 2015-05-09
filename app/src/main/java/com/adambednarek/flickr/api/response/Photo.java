package com.adambednarek.flickr.api.response;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {

  public static final Creator<Photo> CREATOR = new Creator<Photo>() {
    public Photo createFromParcel(Parcel source) {
      return new Photo(source);
    }

    public Photo[] newArray(int size) {
      return new Photo[size];
    }
  };

  private String id;
  private String owner;
  private String secret;
  private String server;
  private int farm;
  private String title;
  private int ispublic;
  private int isfriend;
  private int isfamily;

  public Photo() {
  }

  private Photo(Parcel in) {
    this.id = in.readString();
    this.owner = in.readString();
    this.secret = in.readString();
    this.server = in.readString();
    this.farm = in.readInt();
    this.title = in.readString();
    this.ispublic = in.readInt();
    this.isfriend = in.readInt();
    this.isfamily = in.readInt();
  }

  public String getId() {
    return id;
  }

  public String getOwner() {
    return owner;
  }

  public String getSecret() {
    return secret;
  }

  public String getServer() {
    return server;
  }

  public int getFarm() {
    return farm;
  }

  public String getTitle() {
    return title;
  }

  public int getIspublic() {
    return ispublic;
  }

  public int getIsfriend() {
    return isfriend;
  }

  public int getIsfamily() {
    return isfamily;
  }

  @Override public String toString() {
    return "Photo{" +
        "id='" + id + '\'' +
        ", owner='" + owner + '\'' +
        ", secret='" + secret + '\'' +
        ", server='" + server + '\'' +
        ", farm=" + farm +
        ", title='" + title + '\'' +
        ", ispublic=" + ispublic +
        ", isfriend=" + isfriend +
        ", isfamily=" + isfamily +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Photo photo = (Photo) o;

    if (farm != photo.farm) return false;
    if (ispublic != photo.ispublic) return false;
    if (isfriend != photo.isfriend) return false;
    if (isfamily != photo.isfamily) return false;
    if (id != null ? !id.equals(photo.id) : photo.id != null) return false;
    if (owner != null ? !owner.equals(photo.owner) : photo.owner != null) return false;
    if (secret != null ? !secret.equals(photo.secret) : photo.secret != null) return false;
    if (server != null ? !server.equals(photo.server) : photo.server != null) return false;
    return !(title != null ? !title.equals(photo.title) : photo.title != null);
  }

  @Override public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (owner != null ? owner.hashCode() : 0);
    result = 31 * result + (secret != null ? secret.hashCode() : 0);
    result = 31 * result + (server != null ? server.hashCode() : 0);
    result = 31 * result + farm;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + ispublic;
    result = 31 * result + isfriend;
    result = 31 * result + isfamily;
    return result;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.owner);
    dest.writeString(this.secret);
    dest.writeString(this.server);
    dest.writeInt(this.farm);
    dest.writeString(this.title);
    dest.writeInt(this.ispublic);
    dest.writeInt(this.isfriend);
    dest.writeInt(this.isfamily);
  }
}
