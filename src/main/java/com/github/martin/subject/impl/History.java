package com.github.martin.subject.impl;

import com.github.martin.subject.Subject;

public class History implements Subject {

    @Override
    public String displayName() {
        return "Történelem";
    }

    @Override
    public String id() {
        return "_tort";
    }

    @Override
    public boolean resolve(String name) {
        return name.equalsIgnoreCase("történelem") || name.equalsIgnoreCase("töri")
                || name.equalsIgnoreCase("tört") || name.equalsIgnoreCase("tori")
                || name.equalsIgnoreCase("tortenelem") || name.equalsIgnoreCase("tort");
    }
}