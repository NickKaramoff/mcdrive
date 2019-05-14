package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.forms.EmployeeForm;
import ru.karamoff.mcdrive.models.User;
import ru.karamoff.mcdrive.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByNameAsc();
    }

    @Override
    public void addUser(EmployeeForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .name(form.getName())
                .phone(form.getPhone())
                .role(form.getRole())
                .passHash(passwordEncoder.encode(form.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public void removeUser(Long employeeId) {
        userRepository.deleteById(employeeId);
    }
}
