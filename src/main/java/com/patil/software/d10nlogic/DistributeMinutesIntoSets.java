package com.patil.software.d10nlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DistributeMinutesIntoSets {

    private static final int NUMBER_OF_MINUTES = 59;

    public static void main(String[] args) {

        int numberOfSetsYouNeed = 3; // Integer.parseInt(args[0]);

        HashMap<String, List<String>> setAndItsContents = new HashMap<>();

        for (int i=0; i<=NUMBER_OF_MINUTES; i++) {

            int setNumber = i % numberOfSetsYouNeed;

            if (setAndItsContents.containsKey(String.valueOf(setNumber))) {
                setAndItsContents.get(String.valueOf(setNumber)).add(String.valueOf(i));
            } else {
                setAndItsContents
                        .put(
                                String.valueOf(setNumber),
                                new ArrayList<>(Collections.singleton(String.valueOf(i)))
                        );
            }
        }

        System.out.println();
        setAndItsContents.values()
                .stream()
                .peek(i-> i.add(0, "Set:->"))
                .forEach(System.out::println);
    }
}
