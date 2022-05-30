package com.dray.dating.repository.impl;

import com.dray.dating.models.Action;
import com.dray.dating.repository.MatchRepository;

import java.util.*;

public class MatchRepositoryImpl implements MatchRepository {

    Map<String, Map<String, Action>> userToUserResponseMap;
    Map<String, Set<String>> userToMatchedUserMap;

    public MatchRepositoryImpl() {
        userToUserResponseMap = new HashMap<>();
        userToMatchedUserMap = new HashMap<>();
    }

    @Override
    public void like(String userId, String userIdToBeLiked) {
        userToUserResponseMap.putIfAbsent(userId, new HashMap<>());

        Map<String, Action> previousRecords = userToUserResponseMap.get(userId);

        if (Action.IGNORE.equals(previousRecords.get(userIdToBeLiked))) {
            throw new IllegalArgumentException(userIdToBeLiked + " is already ignored by " + userId);
        }

        previousRecords.put(userIdToBeLiked, Action.LIKE);

        Map<String, Action> userIdToBeLikedMap = userToUserResponseMap.get(userIdToBeLiked);

        if (userIdToBeLikedMap != null && !userIdToBeLikedMap.isEmpty()) {
            Action action = userIdToBeLikedMap.get(userId);

            if (Action.LIKE.equals(action)) {
                //IT'S A MATCH
                userToMatchedUserMap.putIfAbsent(userId, new HashSet<>());
                userToMatchedUserMap.get(userId).add(userIdToBeLiked);

                userToMatchedUserMap.putIfAbsent(userIdToBeLiked, new HashSet<>());
                userToMatchedUserMap.get(userIdToBeLiked).add(userId);
            }
        }

    }

    @Override
    public void ignore(String userId, String userIdToBeIgnored) {
        userToUserResponseMap.putIfAbsent(userId, new HashMap<>());

        Map<String, Action> previousRecords = userToUserResponseMap.get(userId);

        previousRecords.put(userIdToBeIgnored, Action.IGNORE);

        Set<String> userMatchSet = userToMatchedUserMap.get(userId);

        if (userMatchSet != null && !userMatchSet.isEmpty()) {
            userMatchSet.remove(userIdToBeIgnored);
        }

        Set<String> userIgnoredMatchSet = userToMatchedUserMap.get(userIdToBeIgnored);

        if (userIgnoredMatchSet != null && !userIgnoredMatchSet.isEmpty()) {
            userIgnoredMatchSet.remove(userId);
        }
    }

    @Override
    public Set<String> getMatches(String userId) {
        userToMatchedUserMap.putIfAbsent(userId, new HashSet<>());
        return userToMatchedUserMap.get(userId);
    }

    @Override
    public Map<String, Set<String>> getAllMatches() {
        return Map.copyOf(userToMatchedUserMap);
    }

    @Override
    public void removeMatchesAndLikes(String userId) {
        userToMatchedUserMap.remove(userId);
        userToUserResponseMap.remove(userId);

        userToMatchedUserMap.forEach((key, value) -> {
            value.remove(userId);
        });

        userToUserResponseMap.forEach((key, value) -> {
            value.remove(userId);
        });
    }
}
