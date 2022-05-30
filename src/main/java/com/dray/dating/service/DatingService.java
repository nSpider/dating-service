package com.dray.dating.service;

import com.dray.dating.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatingService {
    boolean createAccount(User user);
    boolean deleteAccount(String userId);
    List<String> potentialMatch(String userId);
    boolean like(String user1, String user2);
    boolean ignore(String user1, String user2);
    Set<String> showMatches(String userId);
    Map<String, Set<String>> showAllMatches();
}
