package ru.karamoff.mcdrive.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.karamoff.mcdrive.models.User;
import ru.karamoff.mcdrive.repositories.UserRepository;

import java.util.Optional;

@Component(value = "mcdrive")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw new SecurityException();
        }
    }
}
