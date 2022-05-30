package com.dray.dating;

import com.dray.dating.models.CoOrdinates;
import com.dray.dating.models.Gender;
import com.dray.dating.models.User;
import com.dray.dating.repository.MatchRepository;
import com.dray.dating.repository.SuggestionsRepository;
import com.dray.dating.repository.UserRepository;
import com.dray.dating.repository.impl.MatchRepositoryImpl;
import com.dray.dating.repository.impl.SuggestionsRepositoryImpl;
import com.dray.dating.repository.impl.UserRepositoryImpl;
import com.dray.dating.service.DatingService;
import com.dray.dating.service.impl.DatingServiceImpl;

public class Client {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        MatchRepository matchRepository = new MatchRepositoryImpl();
        SuggestionsRepository suggestionsRepository = new SuggestionsRepositoryImpl(userRepository);


        DatingService datingService = new DatingServiceImpl(userRepository, matchRepository, suggestionsRepository);

        User user_A = new User("user_A", new CoOrdinates(2,3), 23, Gender.MALE);
        User user_B = new User("user_B", new CoOrdinates(3,4), 45, Gender.FEMALE);
        User user_C = new User("user_C", new CoOrdinates(4,6), 32, Gender.FEMALE);
        User user_D = new User("user_D", new CoOrdinates(7,8), 23, Gender.MALE);
        User user_E = new User("user_E", new CoOrdinates(8,12), 23, Gender.FEMALE);


        datingService.createAccount(user_A);
        datingService.createAccount(user_B);
        datingService.createAccount(user_C);
        datingService.createAccount(user_D);
        datingService.createAccount(user_E);

        System.out.println(datingService.potentialMatch(user_A.getUserId()));

        datingService.like(user_A.getUserId(), user_B.getUserId());
        datingService.like(user_A.getUserId(), user_C.getUserId());

        System.out.println(datingService.potentialMatch(user_B.getUserId()));

        datingService.like(user_B.getUserId(), user_A.getUserId());

        System.out.println(datingService.showMatches(user_A.getUserId()));

        System.out.println(datingService.showMatches(user_B.getUserId()));

        datingService.deleteAccount(user_B.getUserId());

        System.out.println(datingService.showMatches(user_A.getUserId()));


    }

}
