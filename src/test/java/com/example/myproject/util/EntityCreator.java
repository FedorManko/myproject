package com.example.myproject.util;

import com.example.myproject.entity.*;

import java.time.LocalDate;
import java.util.UUID;

public class EntityCreator {

    private static final String FIO = "Johny Depp";

    private static final String PHONE = "+375296807584";

    public static Client getClient() {
        return new Client(
            UUID.randomUUID(),
                FIO,
                28,
                PHONE,
                ClientStatus.ACTIVE,
                Profession.STUDENT,
                new UserProfile()
        );
    }
    public static UserProfile getUserProfile(){
        return new UserProfile(
                UUID.randomUUID(),
                true,
                "foka",
                "foka@mail.com",
                "Cat name?",
                "Marlik",
                LocalDate.now(),
                UserRole.USER,
                getClient()
        );
    }
}
