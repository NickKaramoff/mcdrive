package ru.karamoff.mcdrive.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.karamoff.mcdrive.security.UserDetailsImpl;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = ((UserDetailsImpl) auth.getPrincipal()).getRole();

        if ("operator".equals(role)) {
            return "redirect:/orders";
        } else if ("admin".equals(role)) {
            return "redirect:/foodpieces";
        } else {
            //TODO: replace with 500 code
            return null;
        }
    }
}
