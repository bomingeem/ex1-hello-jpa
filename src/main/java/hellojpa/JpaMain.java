package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //JPA에서는 트랜잭션이라는 단위의 개념이 중요
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //JPA가 변경이 되었는지 안되었는지 transaction이 commit하는 시점에서 all check
            //수정
            //Member findMember = em.find(Member.class, 1L);
            //findMember.setName("HelloJPA");

            List<Member> result =  em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for(Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            //삭제
            //em.remove(findMember);
            //멤버 저장
            //em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
