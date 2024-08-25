//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GyungMaeGo.jpashop.Controller;

import lombok.Generated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping({"/login"})
    public String login() {
        return "home";
    }

    @GetMapping({"/logout"})
    public String logout() {
        return "logout";
    }

    @RequestMapping({"/hello"})
    public String hello() {
        return "hello";
    }

    @Generated
    public LoginController() {
    }
}
