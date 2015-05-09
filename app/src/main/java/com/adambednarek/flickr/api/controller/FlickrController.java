package com.adambednarek.flickr.api.controller;

import com.adambednarek.flickr.BuildConfig;
import com.adambednarek.flickr.api.response.SearchResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class FlickrController {

  public static final String FLICKR_ENDPOINT = "https://api.flickr.com/services/rest";
  private static FlickrController sInstance;

  private final FlickrRequest mRequest;

  public FlickrController() {
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(FLICKR_ENDPOINT)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build();
    mRequest = restAdapter.create(FlickrRequest.class);
  }

  public static FlickrController getInstance() {
    if (sInstance == null) {
      sInstance = new FlickrController();
    }
    return sInstance;
  }

  public void searchPhotos(String query, Callback<SearchResponse> callback) {
    mRequest.searchPhotos(BuildConfig.FLICKR_KEY, query, callback);
  }

  interface FlickrRequest {
    @GET("/?format=json&nojsoncallback=1&method=flickr.photos.search") void searchPhotos(
        @Query("api_key") String apiKey,
        @Query("text") String query,
        Callback<SearchResponse> cb);
  }
}
