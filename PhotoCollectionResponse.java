package com.skumbam.flickerassignmet;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skumbam on 10/25/18.
 */

public class PhotoCollectionResponse implements Serializable {

    @SerializedName("photos")
    Photo mPhotoInfo;

    public PhotoCollectionResponse(Photo mPhotoInfo) {
        this.mPhotoInfo = mPhotoInfo;
    }

    public Photo getmPhotoInfo() {
        return mPhotoInfo;
    }

    class Photo {

        public Photo(List<ImageResultResponse> photoData) {
            this.photoData = photoData;
        }

        @SerializedName("photo")
        List<ImageResultResponse> photoData;

        public List<ImageResultResponse> getPhotoData() {
            return photoData;
        }
    }
}
