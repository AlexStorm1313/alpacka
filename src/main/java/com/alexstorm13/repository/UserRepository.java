package com.alexstorm13.repository;

import com.alexstorm13.entity.Session;
import com.alexstorm13.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 11/05/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findByPassword(String password);
    User findBySession(Session session);

}
