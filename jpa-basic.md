#ex1-hello-jpa

JPA 설정 - persistence.xml
 · META-INF/persistence.xml 위치
 · persistence-unit name으로 이름 지정
 · javax.persistence로 시작 : JPA 표준 속성
 · hibernate로 시작 : 하이버네이트 전용 속성
 
데이터베이스 방언 : SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능
 - JPA는 특정 데이터베이스에 종속 X
 - hibernate.dialect 속성에 지정(하이버네이트는 40가지 이상의 데이터베이스 방언 지원)
 
Persistence가 META-INF/persistence.xml의 설정 정보를 조회하여 EntityManagerFactory를 생성 
EntityManagerFactory라는 공장에서 EntityManager(들)을 생성

객체와 테이블을 생성하고 매핑하기
@Entity : JPA가 관리할 객체
@Id : 데이터베이스 PK와 매핑

 · EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
 · EntityManager는 쓰레드간에 공유 X (사용하고 버려야 함)
 · JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

JPQL
 · JPA를 사용하면 엔티티 객체를 중심으로 개발
 · 문제는 검색 쿼리
 · 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
 · 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
 · 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요
 · JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
 · SQL과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
 · JPQL은 엔티티 객체를 대상으로 쿼리 -> 방언을 바꿔도 문제 없음
 · SQL은 데이터베이스 테이블을 대상으로 쿼리 
 · 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
 · SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X
 
  
 

 
 
 
 
