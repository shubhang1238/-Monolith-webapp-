package com.example.dataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    @Autowired
    CredentialRepository credentialRepository;
    @GetMapping("/save")
    public String save(){
        Credential credential = new Credential();
        credential.setUserName("Shubhang");
        credential.setPassword("1234");
        credentialRepository.save(credential);
        return "new Cred added";
    }s
}
