package com.curso.expecializacion.command.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestControllerRoles {
    @GetMapping("/accesUser")
    @PreAuthorize("hasRole('USER')")
    public String accesUser() {
        return "accesUser";
    }

    @GetMapping("/accesAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesAdmin() {
        return "accesAdmin";
    }


    @GetMapping("/accesInvited")
    @PreAuthorize("hasRole('INVITED')")
    public String accesInvited() {
        return "accesInvited";
    }
}
