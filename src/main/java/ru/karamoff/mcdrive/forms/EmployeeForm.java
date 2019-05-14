package ru.karamoff.mcdrive.forms;

import lombok.Data;
import ru.karamoff.mcdrive.models.Role;

@Data
public class EmployeeForm {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
