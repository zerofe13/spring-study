package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 3L);
            System.out.println("=======================");

            Member member1 = new Member();
            member1.setId(78L);
            member1.setName("ooo");

            em.persist(member1);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            emf.close();
        }

        emf.close();
    }
}
