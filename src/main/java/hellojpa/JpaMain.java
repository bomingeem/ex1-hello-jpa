package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * - EntityManagerFactory 는 하나만 생성해서 애플리케이션 전체에서 공유
         * - EntityManager 는 쓰레드간에 공유 X (사용하고 버려야 한다)
         * - JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //EntityManager 는 데이터 변경시 트랜잭션을 시작해야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /**
             * 엔티티의 생명주기
             * - 비영속: 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태(객체를 생성한 상태)
             * - 영속: 영속성 컨텍스트에 관리되는 상태(객체를 저장한 상태)
             * - 준영속: 영속성 컨텍스트에 저장되었다가 분리된 상태
             * - 삭제: 삭제된 상태
             *
             * 영속성 컨텍스트의 이점
             * - 1차 캐시
             * - 동일성(identity) 보장
             * - 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
             * - 변경 감지(Dirty Checking)
             * - 지연 로딩(Lazy Loading)
             *
             * 플러시 발생
             * - 변경 감지
             * - 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
             * - 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송(등록, 수정, 삭제 쿼리)
             *
             * 영속성 컨텍스트를 플러시하는 방법
             * - em.flush(): 직접 호출
             * - 트랜잭션 커밋: 플러시 자동 호출
             * - JPQL 쿼리 실행: 플러시 자동 호출
             *
             * 플러시는 영속성 컨텍스트를 비우지 않으며 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화 한다.
             */
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            // em.remove(findParent);
            findParent.getChildList().remove(0);

            //커밋하는 순간 데이터베이에 SQL 을 보낸다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
