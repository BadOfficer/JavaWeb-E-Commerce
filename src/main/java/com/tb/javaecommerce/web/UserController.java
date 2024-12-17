package com.tb.javaecommerce.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account")
public class UserController {

    @GetMapping
    public String githubLogin(Model model, @AuthenticationPrincipal OAuth2User principal) {
        String username = principal.getAttribute("login");
        String avatarUrl = principal.getAttribute("avatar_url");
        String githubProfileUrl = "https://github.com/" + username;
        model.addAttribute("username", username);
        model.addAttribute("avatarUrl", avatarUrl);
        model.addAttribute("githubProfileUrl", githubProfileUrl);
        return "account";
    }
}
