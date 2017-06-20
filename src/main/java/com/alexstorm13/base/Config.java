package com.alexstorm13.base;

import com.alexstorm13.entity.AccessToken;
import com.alexstorm13.repository.AccessTokenRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Component
public class Config implements ApplicationRunner {
    private final AccessTokenRepository accessTokenRepository;
    private AccessToken accessToken;

    public Config(AccessTokenRepository accessTokenRepository){
        accessToken = new AccessToken();
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        accessTokenRepository.save(accessToken);
        System.out.println("Token");
        System.out.println(accessTokenRepository.findOne(accessToken.getId()).getToken());
    }
}
