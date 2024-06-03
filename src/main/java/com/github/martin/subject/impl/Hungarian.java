package com.github.martin.subject.impl;

import com.github.martin.subject.Subject;

public class Hungarian implements Subject {

    @Override
    public String displayName() {
        return "Magyar nyelv és irodalom";
    }

    @Override
    public String id() {
        return "_magyir";
    }

    @Override
    public boolean resolve(String name) {
        return name.equalsIgnoreCase("irodalom")
                || name.equalsIgnoreCase("nyelvtan")
                || name.equalsIgnoreCase("magyar");
    }
}