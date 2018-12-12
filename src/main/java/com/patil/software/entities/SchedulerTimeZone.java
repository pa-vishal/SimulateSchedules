package com.patil.software.entities;

import java.time.ZoneId;

public enum SchedulerTimeZone {

    PST(ZoneId.of("America/Los_Angeles")),
    MST(ZoneId.of("America/Denver")),
    CST(ZoneId.of("America/Chicago")),
    EST(ZoneId.of("America/New_York"));


    private static  SchedulerTimeZone[] timeZones = SchedulerTimeZone.values();
    private ZoneId zoneId;

    SchedulerTimeZone(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public static ZoneId at(int index){
        return timeZones[index%4].zoneId;
    }

}
