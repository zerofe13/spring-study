package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

//체크에러를 사용하기 때문에 SQLException 를 종속하게된다
public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;

    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;

    void delete(String memberId) throws SQLException;
}
