package com.patil.software;

import com.patil.software.entities.Schedule;
import io.reactivex.Observable;

public class SimulatorStartup {
    public static void main(String[] args) {

        int theseMany = 1;
        if(args.length>0)
            theseMany = Integer.parseInt(args[0]);

        TryNumberedSchedules(theseMany);

        TryTimedSchedules(theseMany);


    }

    private static void TryTimedSchedules(int theseMany) {

        Observable<Schedule> scheduleFeed = new ScheduleSimulator().generateTimedSchedule(theseMany);
        scheduleFeed.subscribe(System.out::println);
    }

    private static void TryNumberedSchedules(int theseMany) {
        Observable<Schedule> scheduleFeed = new ScheduleSimulator().generateNumberedSchedule(theseMany);
        scheduleFeed.subscribe(System.out::println).dispose();
    }
}
