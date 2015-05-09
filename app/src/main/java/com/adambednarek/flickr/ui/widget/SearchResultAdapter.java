package com.adambednarek.flickr.ui.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.adambednarek.flickr.R;
import com.adambednarek.flickr.ui.model.PhotoViewModel;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter {

  private List<PhotoViewModel> mData;

  private Callbacks mCallbacks;

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_photo_impl, parent, false);
    RecyclerView.ViewHolder vh = new PhotoViewHolder(v);
    return vh;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    PhotoViewHolder photoVh = (PhotoViewHolder) holder;
    photoVh.mView.loadImage(mData.get(position).getThumbnail());

    photoVh.mView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mCallbacks != null) {
          mCallbacks.onItemClicked(position);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return mData == null ? 0 : mData.size();
  }

  public void updateData(List<PhotoViewModel> data) {
    mData = data;
    notifyDataSetChanged();
  }

  public void setCallbacks(Callbacks mCallbacks) {
    this.mCallbacks = mCallbacks;
  }

  public interface Callbacks {
    void onItemClicked(int position);
  }

  public static class PhotoViewHolder extends RecyclerView.ViewHolder {
    public PhotoCell mView;

    public PhotoViewHolder(View itemView) {
      super(itemView);
      mView = (PhotoCell) itemView;
    }
  }
}
