package com.example.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy ="org.hibernate.id.UUIDGenerator" )
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "age")
    private Integer age;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "client_status")
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;

    @Column(name = "profession")
    @Enumerated(value = EnumType.STRING)
    private Profession profession;

    @OneToOne(mappedBy = "client", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private UserProfile userProfile;

}
