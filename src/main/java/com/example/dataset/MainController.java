package com.example.dataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MainController {
    @GetMapping("/")
    public String getLandingPage()
    {
        return "LandingPage";

    }
    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    UserdetailRepository userdetailRepository;

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password){
        Credential credential = new Credential();
        credential.setUserName(username);
        credential.setPassword(password);
        credentialRepository.save(credential);
        return "Dashboard";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model){
        Optional<Credential> matchedCredential = credentialRepository.findById(username);
        if(matchedCredential.isPresent()){
            if(matchedCredential.get().getPassword().equals(password)){
                session.setAttribute("username",username);
                Optional<Userdetail> userdetail = userdetailRepository.findById(username);
                if(userdetail.isPresent()){
                    model.addAttribute("userdetail",userdetail.get());
                }
                return "Dashboard";
            }
            else {
                return "LandingPage";
            }
        }
        else{
            return "LandingPage";
        }
    }

    @PostMapping("/signing")
    public String signing(@RequestParam("username") String username,@RequestParam("fName") String fname,@RequestParam("lName") String lname,@RequestParam("email") String email,@RequestParam("phone") String phone){
        Userdetail userdetail = new Userdetail();
        userdetail.setUsername(username);
        userdetail.setFname(fname);
        userdetail.setLname(lname);
        userdetail.setEmail(email);
        userdetail.setPhone(phone);
        userdetailRepository.save(userdetail);
        return "last";
    }

}
