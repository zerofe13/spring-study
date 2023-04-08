package hello.springtx.propagation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    public Member(){
    }

    public Member(String username){
        this.username = username;
    }
}
