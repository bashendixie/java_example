package com.algorithm.demo.designpatterns.iterator.example.iterators;

import com.algorithm.demo.designpatterns.iterator.example.profile.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}