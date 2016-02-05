package com.manage.courses.internal.datastore;

import java.util.AbstractMap;
import java.util.Comparator;

public class TotalMarksComparator implements Comparator<AbstractMap.SimpleEntry<String, Float>>{
    @Override
    public int compare(AbstractMap.SimpleEntry<String, Float> o1, AbstractMap.SimpleEntry<String, Float> o2) {
        if (o1.getValue() < o2.getValue()) {
            return -1;
        } else if (o1.getValue() > o2.getValue()) {
            return 1;
        }
        return 0;
    }
}
