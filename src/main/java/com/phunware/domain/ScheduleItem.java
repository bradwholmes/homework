package com.phunware.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Accessors(prefix = {"m"}, chain = true)
@Getter
@Setter
public class ScheduleItem implements Serializable {
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("M/d", Locale.getDefault());
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("h:mma", Locale.getDefault());
    private static final SimpleDateFormat mWeekDayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

    @SerializedName("start_date") private Date mStartDate;
    @SerializedName("end_date") private Date mEndDate;

    @Override public String toString() {
        String weekDay = mWeekDayFormat.format(mStartDate);
        String startDate = mDateFormat.format(mStartDate);
        String startTime = mTimeFormat.format(mStartDate).toLowerCase(Locale.getDefault());
        String endTime = mTimeFormat.format(mEndDate).toLowerCase(Locale.getDefault());

        return weekDay + " " + startDate + " " + startTime + " to " + endTime;
    }
}
