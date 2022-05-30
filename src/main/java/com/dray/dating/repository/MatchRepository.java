package com.dray.dating.repository;

import java.util.Map;
import java.util.Set;

public interface MatchRepository {
    void like(String userId, String userIdToBeLiked);

    void ignore(String userId, String userIdToBeIgnored);

    Set<String> getMatches(String userId);

    Map<String, Set<String>> getAllMatches();

    void removeMatchesAndLikes(String userId);
}
