package com.alexstorm13.base;

import com.alexstorm13.entity.*;
import com.alexstorm13.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Component
public class DatabaseLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final SessionRepository sessionRepository;
    private final SoftwareRepository softwareRepository;
    private final BundleRepository bundleRepository;

    public DatabaseLoader(UserRepository userRepository, NotificationRepository notificationRepository, SessionRepository sessionRepository, SoftwareRepository softwareRepository, BundleRepository bundleRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.sessionRepository = sessionRepository;
        this.softwareRepository = softwareRepository;
        this.bundleRepository = bundleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Notification emailDuplicate = new Notification("email_duplicate", "Email address already in use", "error");
        Notification tokenMisMatch = new Notification("token_mis_match", "Access Token is incorrect", "error");
        Notification incorrectCredentials = new Notification("incorrect_credentials", "Credentials are incorrect", "error");
        Notification noAdmin = new Notification("no_admin", "You are not an Admin", "error");
        notificationRepository.save(emailDuplicate);
        notificationRepository.save(tokenMisMatch);
        notificationRepository.save(incorrectCredentials);
        notificationRepository.save(noAdmin);
        Session session = new Session();
        sessionRepository.save(session);
        User alex = new User("Alex", "Brasser", "alexbrasser@gmail.com", "Sterk123", "https://secure.gravatar.com/avatar/57532f4eb66a51505f9817de8d1b0dc4", "admin");
        alex.setSession(session);
        userRepository.save(alex);
        System.out.println("Session");
        System.out.println(alex.getSession().getKey());

        Software sublime = new Software("Sublime", "https://upload.wikimedia.org/wikipedia/en/4/4c/Sublime_Text_Logo.png", "De beste verse text verwerker", "https://download.sublimetext.com/Sublime%20Text%20Build%203126.dmg");
        Software atom = new Software("Atom", "https://upload.wikimedia.org/wikipedia/en/4/4c/Sublime_Text_Logo.png", "Atom is nog verser", "https://download.sublimetext.com/Sublime%20Text%20Build%203126.dmg");
        softwareRepository.save(atom);
        softwareRepository.save(sublime);

        Bundle bundle = new Bundle("Progger", "https://image-ticketfly.imgix.net/00/02/49/83/46-og.jpg?w=500&h=500", "Voor de ekte progger");
        bundleRepository.save(bundle);

        bundle.addSoftware(sublime);
        bundle.addSoftware(atom);
        bundle.addOwner(alex);
        alex.addBundle(bundle);
        bundleRepository.save(bundle);
        userRepository.save(alex);
    }
}
