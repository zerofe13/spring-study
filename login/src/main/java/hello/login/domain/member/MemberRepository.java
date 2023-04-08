package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long,Member> store =new HashMap<Long,Member>();
    private static long sequence =0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save:member={}", member);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        List<Member> all = findAll();
        return all.stream().filter(m -> m.getLoginId().equals(loginId)).findFirst().map(Optional::of).orElse(null);
    }
    public List<Member> findAll(){
        return new ArrayList<Member>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
