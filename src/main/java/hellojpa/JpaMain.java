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
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.clear();

            Member member2 = em.find(Member.class, 150L);
            System.out.println("=========================");
            
            /*
            //동일성 보장
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("result = " + (findMember1 == findMember2));
            */
            
            /*
            //비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            
            //영속 상태
            System.out.println("BEFORE");
            em.persist(member);

            //준영속 상태
            em.detach(member);
            System.out.println("AFTER");

            //1차캐시에 저장된 값을 가져온 것
            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.getId = " + findMember.getId());
            System.out.println("findMember.getName = " + findMember.getName());
            */

            //--------------------------------------------------------------------------------------------------
            //수정
            //JPA가 변경이 되었는지 안되었는지 transaction이 commit하는 시점에서 all check
            //Member findMember = em.find(Member.class, 1L);
            //findMember.setName("HelloJPA");

            /*
            List<Member> result =  em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for(Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            */

            //삭제
            //em.remove(findMember);

            //멤버 저장
            //Member member = new Member();
            //member.setName("보민");
            //member.setId(2L);
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
