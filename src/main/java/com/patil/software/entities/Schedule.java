package com.patil.software.entities;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Schedule {

    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    private static int scheduleId;
    private ZoneId timeZone;
    private ZonedDateTime startDate;
    private Duration executionTime;

    public Schedule(ZoneId timeZone, ZonedDateTime startDate, Duration executionTime) {

        this.timeZone = timeZone;
        this.startDate = startDate;
        this.executionTime = executionTime;

        scheduleId += new Random(100).nextInt(111);

    }

    @Override
    public String toString(){

        String json ;

        /*Gson gson = new Gson();
         json = gson.toJson(this);
        */

        json = String.format(
                "{" +
                        "\n\tid: %s," +
                        "\n\ttimeZone:@%s," +
                        "\n\tstartDateTime:%s" +
                        "\n}",
                scheduleId,
                this.timeZone,
                this.startDate.plusMinutes(this.executionTime.toMinutes()).format(DateTimeFormatter.ofPattern(DATE_FORMAT))
        );


        return json;
    }


}
