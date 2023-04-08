package hello.sevlet.web.frontcontroller.v3.Controller;

import hello.sevlet.basic.domain.member.Member;
import hello.sevlet.basic.domain.member.MemberRepository;
import hello.sevlet.web.frontcontroller.ModelView;
import hello.sevlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paraMap) {

        List<Member> members = memberRepository.findAll();
        ModelView mv =new ModelView("members");
        mv.getModel().put("member",members);

        return mv;
    }
}
