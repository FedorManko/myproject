package com.example.myproject.util;

import com.example.myproject.entity.Client;
import com.example.myproject.entity.ClientStatus;
import com.example.myproject.entity.Profession;
import com.example.myproject.entity.UserProfile;

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
}
