package com.abou.webapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.abou.webapp.Model.User;
import com.abou.webapp.Service.UserService ;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<User> listUser = service.getUsers();
        model.addAttribute("users", listUser);

        return "home";
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "formNewUser";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") final int id, Model model) {
        User u = service.getUser(id);
        model.addAttribute("user", u);
        return "formUpdateUser";
    }

    @GetMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") final int id) {
        service.deleteUser(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveUser")
    public ModelAndView saveUser(@ModelAttribute User user) {
        if(user.getId() != null) {
            // User from update form has the password field not filled,
            // so we fill it with the current password.
            User current = service.getUser(user.getId());
            user.setPassword(current.getPassword());
        }
        service.saveUser(user);
        return new ModelAndView("redirect:/");
    }

}