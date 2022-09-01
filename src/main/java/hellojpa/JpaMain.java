package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * - EntityManagerFactory 는 하나만 생성해서 애플리케이션 전체에서 공유
         * - EntityManager 는 쓰레드간에 공유 X (사용하고 버려야 한다)
         * - JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 150L);
            member.setName("김민보");

            //em.detach(member);
            em.clear();

            System.out.println("========================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
