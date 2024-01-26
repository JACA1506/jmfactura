package com.jmfactura.jmfactura.users;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    @PostMapping(value = "insert")
    public String addUser() {

        return "insert endpoint";
    }

}
