package hello.sevlet.web.frontcontroller.v3.Controller;

import hello.sevlet.basic.domain.member.Member;
import hello.sevlet.basic.domain.member.MemberRepository;
import hello.sevlet.web.frontcontroller.ModelView;
import hello.sevlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paraMap) {
        String userName = paraMap.get("username");
        int age = Integer.parseInt(paraMap.get("age"));


        Member member = new Member(userName, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;
    }
}
