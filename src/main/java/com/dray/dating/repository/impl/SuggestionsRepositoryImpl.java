package com.dray.dating.repository.impl;

import com.dray.dating.models.CoOrdinates;
import com.dray.dating.models.User;
import com.dray.dating.repository.SuggestionsRepository;
import com.dray.dating.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SuggestionsRepositoryImpl implements SuggestionsRepository {

    UserRepository userRepository;

    public SuggestionsRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getPotentialMatches(String userId) {
        User user = userRepository.getUserById(userId);
        return userRepository.getUsers().stream()
                .filter(Objects::nonNull)
                .filter(match -> !userId.equals(match.getUserId()))
                .sorted((user1, user2) -> Double.compare(getWeight(user1, user), getWeight(user2, user)))
                .map(User::getUserId)
                //Add pagination here
                .collect(Collectors.toList());
    }

    private double getWeight(User user1, User user2) {
        int ageDiff = Math.abs(user1.getAge() - user2.getAge());
        double distanceDiff = getDistanceDiff(user1.getCoOrdinates(), user2.getCoOrdinates());
        int genderDiff = user1.getGender() != user2.getGender() ? 0 : 1;

        double weight = genderDiff * 200 + distanceDiff * 30 + ageDiff * 10;

        return weight;

    }

    private double getDistanceDiff(CoOrdinates coOrdinate1, CoOrdinates coOrdinate2) {
        return Math.sqrt((coOrdinate1.getxCoOrdinate() - coOrdinate2.getxCoOrdinate())*(coOrdinate1.getxCoOrdinate() - coOrdinate2.getxCoOrdinate())
                + (coOrdinate1.getyCoOrdinate() - coOrdinate2.getyCoOrdinate())*(coOrdinate1.getyCoOrdinate() - coOrdinate2.getyCoOrdinate()));
    }
}
