package com.adambednarek.flickr.api.response;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseResponse implements Parcelable {

  protected String stat;
  protected int code;
  protected String message;

  public BaseResponse() {
  }

  private BaseResponse(Parcel in) {
    this.stat = in.readString();
    this.code = in.readInt();
    this.message = in.readString();
  }

  public String getStat() {
    return stat;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  @Override public String toString() {
    return "BaseResponse{" +
        "stat='" + stat + '\'' +
        ", code=" + code +
        ", message='" + message + '\'' +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BaseResponse that = (BaseResponse) o;

    if (code != that.code) return false;
    if (stat != null ? !stat.equals(that.stat) : that.stat != null) return false;
    return !(message != null ? !message.equals(that.message) : that.message != null);
  }

  @Override public int hashCode() {
    int result = stat != null ? stat.hashCode() : 0;
    result = 31 * result + code;
    result = 31 * result + (message != null ? message.hashCode() : 0);
    return result;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.stat);
    dest.writeInt(this.code);
    dest.writeString(this.message);
  }
}
