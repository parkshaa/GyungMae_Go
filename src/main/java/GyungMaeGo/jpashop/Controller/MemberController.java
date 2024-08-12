package GyungMaeGo.jpashop.Controller;

import GyungMaeGo.jpashop.Service.MemberService;
import GyungMaeGo.jpashop.domain.Address;
import GyungMaeGo.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm" , new MemberForm());
        return "members/createMemberForm";
    }


    @GetMapping("/members/findPasswordForm")
    public String findPassword(Model model){
     //   model.addAttribute("memberForm", new MemberForm());
        return "members/FindPasswordForm";
    }



    @PostMapping("/members/new")
    public String create(MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setPassword(form.getPassword());

        memberService.join(member);

        return "redirect:/"; //첫번째 페이지로 넘어가게됨.

    }

    @PostMapping("/members/findPassword")
    public String MemberFindPassword(MemberForm form, Model model){

        String Password = memberService.findPassword(form.getName());


        model.addAttribute("password" , Password);

        return "members/passwordResult";

    }



}
