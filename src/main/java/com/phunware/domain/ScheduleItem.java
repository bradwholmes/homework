package com.phunware.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(prefix = {"m"})
@Getter
public class ScheduleItem {
    @SerializedName("start_date") private Date mStartDate;
    @SerializedName("end_date") private Date mEndDate;
}
