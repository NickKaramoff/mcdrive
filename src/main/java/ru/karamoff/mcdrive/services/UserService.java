package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.forms.EmployeeForm;
import ru.karamoff.mcdrive.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(EmployeeForm form);
    void removeUser(Long employeeId);
}
