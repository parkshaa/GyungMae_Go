//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GyungMaeGo.jpashop.Controller;

import GyungMaeGo.jpashop.Service.MemberService;
import GyungMaeGo.jpashop.domain.Address;
import GyungMaeGo.jpashop.domain.Member;
import lombok.Generated;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping({"/members/new"})
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @GetMapping({"/members/findPasswordForm"})
    public String findPassword(Model model) {
        return "members/FindPasswordForm";
    }

    @PostMapping({"/members/new"})
    public String create(MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        } else {
            Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
            Member member = new Member();
            member.setName(form.getName());
            member.setAddress(address);
            member.setPassword(form.getPassword());
            this.memberService.join(member);
            return "redirect:/";
        }
    }

    @PostMapping({"/members/findPassword"})
    public String MemberFindPassword(MemberForm form, Model model) {
        String Password = this.memberService.findPassword(form.getName());
        model.addAttribute("password", Password);
        return "members/passwordResult";
    }

    @Generated
    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }
}
