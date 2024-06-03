package com.github.martin.subject;

public interface Subject {

    String displayName();
    String id();
    boolean resolve(String name);
}