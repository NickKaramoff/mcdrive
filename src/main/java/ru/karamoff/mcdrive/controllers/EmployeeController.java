package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.karamoff.mcdrive.forms.EmployeeForm;
import ru.karamoff.mcdrive.forms.IdForm;
import ru.karamoff.mcdrive.models.User;
import ru.karamoff.mcdrive.services.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String employeesListPage(ModelMap map) {
        List<User> users = userService.getAllUsers();
        map.addAttribute("employees", users);
        return "employees_list";
    }

    @DeleteMapping(value = "/remove", consumes = "application/json")
    public ResponseEntity<String> deleteEmployee(@RequestBody IdForm form) {
        userService.removeUser(form.getId());
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/new")
    public String createEmployeePage() {
        return "employee_new";
    }

    @PostMapping("/new")
    public String createEmployee(EmployeeForm form) {
        userService.addUser(form);
        return "redirect:/employees";
    }
}
