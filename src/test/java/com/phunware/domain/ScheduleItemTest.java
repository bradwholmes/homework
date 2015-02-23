package com.phunware.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScheduleItemTest {

    private final SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    private final String mRawEndDate = "2013-02-01 00:00:00 -0800";
    private final String mRawStartDate = "2013-01-31 10:00:00 -0800";

    private final Date mParsedStartDate = parseDate(mRawStartDate);
    private final Date mParsedEndDate = parseDate(mRawEndDate);

    private final String mRawSampleSchedule = "{\n" +
            "\"start_date\": \"" + mRawStartDate + "\",\n" +
            "\"end_date\": \"" + mRawEndDate + "\"\n" +
            "}";

    private final Gson mGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss Z")
            .create();

    @Test
    public void shouldDeserializeStartTimeCorrectly() throws ParseException {
        ScheduleItem deserializedItem = mGson.fromJson(mRawSampleSchedule, ScheduleItem.class);

        assertThat(deserializedItem.getStartDate()).isEqualTo(mParsedStartDate);
    }

    @Test
    public void shouldDeserializeEndTimeCorrectly() throws ParseException {
        ScheduleItem deserializedItem = mGson.fromJson(mRawSampleSchedule, ScheduleItem.class);

        assertThat(deserializedItem.getEndDate()).isEqualTo(mParsedEndDate);
    }

    @Test
    public void shouldToStringAsDisplayableText() {
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2015);
        start.set(Calendar.MONTH, Calendar.FEBRUARY);
        start.set(Calendar.DAY_OF_MONTH, 23);
        start.set(Calendar.HOUR_OF_DAY, 8);
        start.set(Calendar.MINUTE, 0);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.YEAR, 2015);
        end.set(Calendar.MONTH, Calendar.FEBRUARY);
        end.set(Calendar.DAY_OF_MONTH, 24);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);

        ScheduleItem scheduleItem = new ScheduleItem()
                .setStartDate(start.getTime())
                .setEndDate(end.getTime());

        String displayText = scheduleItem.toString();

        assertThat(displayText).isEqualTo("Monday 2/23 8:00am to 12:00am");
    }

    private Date parseDate(String rawStartDate) {
        try {
            return mFormatter.parse(rawStartDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}