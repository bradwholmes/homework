package com.phunware.domain;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

@Accessors(prefix = {"m"}, chain = true)
@Getter
@Setter
public class Venue implements Serializable {
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
    @SerializedName("ticket_link") private String mTicketLink;
    @SerializedName("image_url") private String mImageUrl;
    @SerializedName("schedule") private ArrayList<ScheduleItem> mSchedule;

    public String getFullAddress() {
        Joiner joiner = Joiner.on(", ").skipNulls();
        return joiner.join(Strings.emptyToNull(mAddress), Strings.emptyToNull(mCity), Strings.emptyToNull(mState));
    }
}
