package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.UserDto.UserLoginDto;
import com.example.demo.model.dto.UserDto.UserRegisterDto;
import com.example.demo.model.po.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.securityUtil.SecurityUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final RoleRepository roleRepository;

    @Autowired UserRepository userRepo;
    @Autowired RoleRepository roleRepo;

    AuthController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute UserLoginDto form, HttpSession session, Model model) {
        Optional<User> userOp = userRepo.findByPhone(form.getPhone());
        if(userOp.isPresent() && SecurityUtil.match(form.getPassword(), userOp.get().getPassword())) {
            session.setAttribute("userId", userOp.get().getUserId());
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
        user.setPassword(SecurityUtil.hash(form.getPassword()));
        user.setRole(roleRepo.findById(1).orElse(null));
        userRepo.save(user);
        model.addAttribute("success", "註冊成功，請登入");
        return "login";
    }
}
