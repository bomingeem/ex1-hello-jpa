#ex1-hello-jpa
JPQL
 · JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
 · JPQL은 엔티티 객체를 대상으로 쿼리
 · SQL은 데이터베이스 테이블을 대상으로 쿼리
 · 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
 · SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X
 · JPQL을 한마디로 정의하면 객체 지향 SQL 
ex) List<Member> result = em.createQuery("select m from Member m where m.username like '%kim%'", Member.class).getResultList();

Criteria 소개
 · 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
 · JPQL 빌더 역할
 · 너무 복잡하고 실용성이 없다
 · Criteria 대신에 QueryDSL 사용 권장
//Criteria 사용 준비
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Member> query = cb.createQuery(Member.class);
//루트 클래스(조회를 시작할 클래스)
Root<Member> m = query.from(Member.class);
CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
List<Member> resultList = em.createQuery(cq).getResultList();

QueryDSL 소개
 · 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
 · JPQL 빌더 역할
 · 컴파일 시점에 문법 오류를 찾을 수 있음
 · 동적쿼리 작성 편리함
 · 단순하고 쉬움
 · 실무 사용 권장

JDBC 직접 사용, SpringJdbcTemplate 등
 · JPA를 사용하면서 JDBC 커넥션을 직접 사용하거나, 스프링 JdbcTemplate, 마이바티스 등을 함께 사용 가능
 · 단 영속성 컨텍스트를 적절한 시점에 강제로 플러시 필요
 
JPQL
