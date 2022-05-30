package com.dray.dating.service.impl;

import com.dray.dating.models.User;
import com.dray.dating.repository.UserRepository;
import com.dray.dating.service.DatingService;
import com.dray.dating.repository.MatchRepository;
import com.dray.dating.repository.SuggestionsRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatingServiceImpl implements DatingService {

    UserRepository userRepository;

    MatchRepository matchRepository;

    SuggestionsRepository suggestionsRepository;

    public DatingServiceImpl(UserRepository userRepository, MatchRepository matchRepository, SuggestionsRepository suggestionsRepository) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.suggestionsRepository = suggestionsRepository;
    }

    @Override
    public boolean createAccount(User user) {
        try {
            validateIfUserDoesNotExist(user.getUserId());
            userRepository.createUser(user);
            return true;
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean deleteAccount(String userId) {
        try {
            validateUser(userId);
            userRepository.deleteUser(userId);
            matchRepository.removeMatchesAndLikes(userId);
            return true;
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<String> potentialMatch(String userId) {
        try {
            validateUser(userId);
            return suggestionsRepository.getPotentialMatches(userId);
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean like(String user1, String user2) {
        try {
            validateUser(user1);
            validateUser(user2);
            matchRepository.like(user1, user2);
            return true;
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean ignore(String user1, String user2) {
        try {
            validateUser(user1);
            validateUser(user2);
            matchRepository.ignore(user1, user2);
            return true;
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Set<String> showMatches(String userId) {
        try {
            validateUser(userId);
            return matchRepository.getMatches(userId);
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Set<String>> showAllMatches() {
        try {
            return matchRepository.getAllMatches();
        }catch (Exception e) {
            System.out.println("Operation could not be performed due to : " + e.getMessage());
        }
        return null;
    }

    private void validateUser(String userId) {
        User user = userRepository.getUserById(userId);

        if (user == null) {
            throw new IllegalArgumentException(String.format("User id %s is not valid", userId));
        }
    }

    private void validateIfUserDoesNotExist(String userId) {
        User user = userRepository.getUserById(userId);

        if (user != null) {
            throw new IllegalArgumentException(String.format("User id %s is already present", userId));
        }
    }
}
