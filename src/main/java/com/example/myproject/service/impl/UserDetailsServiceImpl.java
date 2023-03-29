package com.example.myproject.service.impl;


import com.example.myproject.entity.Client;
import com.example.myproject.entity.ClientStatus;
import com.example.myproject.service.ClientService;
import com.example.myproject.service.exception.ClientNotRegisteredException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientService clientService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException, ClientNotRegisteredException {
        Client client = clientService.loadClientByLogin(login);

        checkClientNotRegistered(client);

        return User.builder()
                .username(client.getMobilePhone())
                .password(client.getUserProfile().getPassword())
                .roles(client.getUserProfile().getRole().toString())
                .build();
    }

    private void checkClientNotRegistered(@NonNull Client client) {
        Optional.of(client)
                .filter(c -> !ClientStatus.NOT_REGISTERED.equals(c.getClientStatus()))
                .orElseThrow(() -> new ClientNotRegisteredException(
                        String.format("Client with login %s not registered.", client.getMobilePhone())));
    }
}
