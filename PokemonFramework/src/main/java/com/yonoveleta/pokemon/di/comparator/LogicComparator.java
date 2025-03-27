package com.yonoveleta.pokemon.di.comparator;

import java.util.Comparator;

import com.yonoveleta.pokemon.di.annotation.Logic;

public class LogicComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        int order1 = getOrderFromLogicAnnotation(o1);
        int order2 = getOrderFromLogicAnnotation(o2);
        return Integer.compare(order1, order2);
    }

    private int getOrderFromLogicAnnotation(Object obj) {
        // Check if the class is annotated with @Logic
        if (obj == null) {
            return Integer.MAX_VALUE; // Return a default order for nulls
        }

        Class<?> clazz = obj.getClass();
        Logic logicAnnotation = clazz.getAnnotation(Logic.class);
        if (logicAnnotation != null) {
            return logicAnnotation.order(); // Return the order specified in the annotation
        }

        return Integer.MAX_VALUE; // Default order if no annotation is present
    }
}
