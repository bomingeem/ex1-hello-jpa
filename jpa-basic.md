#ex1-hello-jpa

JPA 설정 - persistence.xml
 · META-INF/persistence.xml 위치
 · persistence-unit name으로 이름 지정
 · javax.persistence로 시작 : JPA 표준 속성
 · hibernate로 시작 : 하이버네이트 전용 속성
 
데이터베이스 방언 : SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능
 - JPA는 특정 데이터베이스에 종속 X
 - hibernate.dialect 속성에 지정(하이버네이트는 40가지 이상의 데이터베이스 방언 지원)
 
 
 
