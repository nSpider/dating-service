package com.dray.dating.repository;

import java.util.List;

public interface SuggestionsRepository {
    List<String> getPotentialMatches(String userId);
}
