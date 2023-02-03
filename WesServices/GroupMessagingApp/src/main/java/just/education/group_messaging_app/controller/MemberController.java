package just.education.group_messaging_app.controller;

import just.education.group_messaging_app.dto.MemberReadDto;
import just.education.group_messaging_app.dto.MemberUpdateDto;
import just.education.group_messaging_app.service.MemberService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;


    public MemberController() {
    }

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }


    @GetMapping(path = "/{id}")
    public MemberReadDto findMemberById(@PathVariable("id") final long id) {
        return memberService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public MemberReadDto updateMember(@PathVariable("id") final long id, @RequestBody MemberUpdateDto memberUpdateDto) {
        return memberService.updateById(id, memberUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public MemberReadDto deleteMemberById(@PathVariable("id") final int id) {
        return memberService.deleteById(id);
    }
}