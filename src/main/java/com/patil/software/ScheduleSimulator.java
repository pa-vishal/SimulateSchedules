package com.patil.software;

import com.patil.software.entities.Schedule;
import com.patil.software.entities.SchedulerTimeZone;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;

class ScheduleSimulator {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    Observable<Schedule> generateNumberedSchedule(int theseMany){

        return Observable.create(subscriber-> doNumberedSimulation(subscriber, theseMany));
    }

    Observable<Schedule> generateTimedSchedule(int everyTheseSeconds){

        return Observable.create(subscriber-> doTimedSimulation(subscriber, everyTheseSeconds));
    }

    private void doNumberedSimulation(ObservableEmitter<Schedule> subscriber, int theseMany) {
        System.out.println("\n\nsimulating\n\n***************\n");

        IntStream.range(0, theseMany).mapToObj(this::getSchedule).forEach(subscriber::onNext);

        subscriber.onComplete();
        System.out.println("\n\n***************\n\nsimulation end\n");

    }

    private void doTimedSimulation(ObservableEmitter<Schedule> subscriber, int everyTheseSeconds) {
        System.out.println("\n\nsimulating\n\n***************\n");

        AtomicInteger i = new AtomicInteger();

        scheduler.scheduleAtFixedRate(()->subscriber.onNext(getSchedule(i.getAndIncrement())), 0, everyTheseSeconds, SECONDS);

        //subscriber.onComplete();
        System.out.println("\n\n***************\n\nsimulation end\n");

    }

    private Schedule getSchedule(int i) {
        ZoneId zoneId = SchedulerTimeZone.at(i);
        ZonedDateTime startDate = LocalDateTime.now().atZone(zoneId);

        Duration executionTime = Duration.ofMinutes((500 * i) % 1440);

        return new Schedule(zoneId, startDate, executionTime);
    }

}
