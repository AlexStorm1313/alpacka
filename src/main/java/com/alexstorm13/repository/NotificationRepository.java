package com.alexstorm13.repository;

import com.alexstorm13.entity.Notification;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Notification findByName(String name);
}