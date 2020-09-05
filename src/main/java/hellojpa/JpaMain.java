package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //JPA에서는 트랜잭션이라는 단위의 개념이 중요
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFood().add("치킨");
            member.getFavoriteFood().add("족발");
            member.getFavoriteFood().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("=============== START ===============");
            Member findMember = em.find(Member.class, member.getId());
            Address old = findMember.getHomeAddress();

            //homeCity -> newCity
            //findMember.setHomeAddress(new Address("newCity", old.getStreet(), old.getZipcode()));

            //치킨 -> 한식
            //findMember.getFavoriteFood().remove("치킨");
            //findMember.getFavoriteFood().add("한식");

            //ol1 -> new1
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));

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
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member){
        System.out.println("member = " + member.getUsername());
    }
}
