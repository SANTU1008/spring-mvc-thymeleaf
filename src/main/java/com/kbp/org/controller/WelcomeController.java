package com.kbp.org.controller;

import com.kbp.org.configuration.WebSiteSecurityConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kartikbvarma on 1/29/17.
 */
@Controller
public class WelcomeController {

   /* @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/home")
    public String welcomePage(
            @RequestParam(value = "name",required = false, defaultValue = "World")String name, Model model){
        String userName = WebSiteSecurityConfiguration.getAuthentication().getName();
        model.addAttribute("name",userName);
        return "home";
    }*/

    @RequestMapping("/")
    public String init(Map<String, Object> model) {
        model.put("title", "PUBLIC AREA");
        model.put("message", "Any user can view this page");
        model.put("username", getUserName());
        model.put("userroles", getUserRoles());
        return "home";
    }

    @RequestMapping("/secure")
    public String secure(Map<String, Object> model) {
        model.put("title", "SECURE AREA");
        model.put("message", "Only Authorised Users Can See This Page");
        model.put("username", getUserName());
        model.put("userroles", getUserRoles());
        return "home";
    }

    private String getUserName() {

        /*final UserDetails currentUser = (UserDetails) WebSiteSecurityConfiguration.getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println(grantedAuthority.getAuthority());
        }*/
        return WebSiteSecurityConfiguration.getAuthentication().getName();

    }

    private Collection<String> getUserRoles() {

        Set<String> roles = new HashSet<>();

//        final UserDetails currentUser = (UserDetails) WebSiteSecurityConfiguration.getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = WebSiteSecurityConfiguration.getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            roles.add(grantedAuthority.getAuthority());
        }
        return roles;

    }
}
