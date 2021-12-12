package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.subscription.SubscriptionType;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;
import ru.project.fitstyle.model.repository.FitUserRepository;
import ru.project.fitstyle.model.repository.RoleRepository;
import ru.project.fitstyle.model.repository.SubscriptionTypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    FitUserRepository fitUserRepository;

    @Autowired
    SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/generate")
    public void generateUsers() {
        for(int i = 0; i < 10000; i++) {
            Subscription subscription = new Subscription();
            subscription.setBeginDate(new Date(new Date().getTime()));
            subscription.setEndDate(new Date(new Date().getTime()));
            SubscriptionType subscriptionType = subscriptionTypeRepository.findById(1L).get();
            subscription.setSubscriptionType(subscriptionType);
            subscription.setContractNumber("3453453");
            FitUser fitUser = new FitUser("test", "test", "test", "test" + Integer.toString(i) + "@gmail.com", "test11111",
                    "32", "M", new Date(new Date().getTime()), "88005553535", "3543953953", "klfsldflsfla");
            fitUser.setSubscription(subscription);
            Role role = roleRepository.findById(1L).get();
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            fitUser.setRoles(roles);
            fitUserRepository.save(fitUser);
        }
    }
}
