
package com.skumbam.flickerassignmet;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ImageResultResponse implements Serializable{

    @SerializedName("farm")
    private Long mFarm;
    @SerializedName("id")
    private String mId;
    @SerializedName("isfamily")
    private Long mIsfamily;
    @SerializedName("isfriend")
    private Long mIsfriend;
    @SerializedName("ispublic")
    private Long mIspublic;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("secret")
    private String mSecret;
    @SerializedName("server")
    private String mServer;
    @SerializedName("title")
    private String mTitle;

    public ImageResultResponse(Long mFarm, String mId, Long mIsfamily,
                               Long mIsfriend, Long mIspublic, String mOwner,
                               String mSecret, String mServer, String mTitle) {
        this.mFarm = mFarm;
        this.mId = mId;
        this.mIsfamily = mIsfamily;
        this.mIsfriend = mIsfriend;
        this.mIspublic = mIspublic;
        this.mOwner = mOwner;
        this.mSecret = mSecret;
        this.mServer = mServer;
        this.mTitle = mTitle;
    }

    public Long getFarm() {
        return mFarm;
    }

    public String getId() {
        return mId;
    }

    public Long getIsfamily() {
        return mIsfamily;
    }

    public Long getIsfriend() {
        return mIsfriend;
    }

    public Long getIspublic() {
        return mIspublic;
    }

    public String getOwner() {
        return mOwner;
    }

    public String getSecret() {
        return mSecret;
    }

    public String getServer() {
        return mServer;
    }

    public String getTitle() {
        return mTitle;
    }


}
