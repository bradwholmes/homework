package com.phunware.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(prefix = {"m"}, chain = true)
@Getter
@Setter
public class ScheduleItem implements Serializable {
    @SerializedName("start_date") private Date mStartDate;
    @SerializedName("end_date") private Date mEndDate;
}
