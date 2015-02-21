package com.phunware.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(prefix = {"m"})
@Getter
public class Venue {
    @SerializedName("id") private long mId;
    @SerializedName("pcode") private int mPcode;
    @SerializedName("latitude") private double mLatitude;
    @SerializedName("longitude") private double mLongitude;
    @SerializedName("name") private String mName;
    @SerializedName("address") private String mAddress;
    @SerializedName("city") private String mCity;
    @SerializedName("state") private String mState;
    @SerializedName("zip") private String mZip;
    @SerializedName("phone") private String mPhone;
    @SerializedName("tollfreephone") private String mTollFreePhone;
    @SerializedName("url") private String mUrl;
    @SerializedName("description") private String mDescription;
    @SerializedName("ticketlink") private String mTicketLink;
    @SerializedName("imageurl") private String mImageUrl;
    @SerializedName("schedule") private List<ScheduleItem> mSchedule;
}
