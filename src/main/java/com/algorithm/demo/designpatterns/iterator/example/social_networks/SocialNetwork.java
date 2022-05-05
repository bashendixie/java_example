package com.algorithm.demo.designpatterns.iterator.example.social_networks;

import com.algorithm.demo.designpatterns.iterator.example.iterators.ProfileIterator;

public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}
