//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GyungMaeGo.jpashop.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public HomeController() {
    }

    @RequestMapping({"/"})
    public String Home() {
        this.log.info("home controller");
        return "home";
    }
}
