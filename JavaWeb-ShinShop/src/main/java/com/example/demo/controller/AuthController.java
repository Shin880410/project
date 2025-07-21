package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.dto.UserLoginDto;
import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.model.po.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired UserRepository userRepo;
    @Autowired RoleRepository roleRepo;

    AuthController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute UserLoginDto form, HttpSession session, Model model) {
        Optional<User> userOp = userRepo.findByPhone(form.getPhone());
        if(userOp.isPresent() && passwordEncoder.matches(form.getPassword(), userOp.get().getPassword())) {
            session.setAttribute("userId", userOp.get().getId());
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "手機號碼或密碼錯誤");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute UserRegisterDto form, Model model) {
        if(userRepo.findByPhone(form.getPhone()).isPresent()) {
            model.addAttribute("error", "手機已註冊");
            return "register";
        }
        User user = new User();
        user.setName(form.getName());
        user.setPhone(form.getPhone());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(roleRepo.findById(1).orElse(null));
        userRepo.save(user);
        model.addAttribute("success", "註冊成功，請登入");
        return "login";
    }
}
