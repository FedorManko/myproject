package com.example.myproject.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_profiles", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "com.example.myproject.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "sms_notification")
    private Boolean smsNotification;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "security_question")
    private String securityQuestion;

    @Column(name = "security_answer")
    private String securityAnswer;

    @Column(name = "app_registration_date")
    private LocalDate appRegistrationDate;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

}
