package com.github.martin.subject.impl;

import com.github.martin.subject.Subject;

public class Math implements Subject {

    @Override
    public String displayName() {
        return "Matematika";
    }

    @Override
    public String id() {
        return "_mat";
    }

    @Override
    public boolean resolve(String name) {
        return name.equalsIgnoreCase("matematika")
                || name.equalsIgnoreCase("matek") || name.equalsIgnoreCase("mat");
    }
}