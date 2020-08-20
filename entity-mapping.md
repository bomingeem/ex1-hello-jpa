#ex1-hello-jpa
· 객체와 테이블 매핑 : @Entity, @Table
· 필드와 컬럼 매핑 : @Column
· 기본 키 매핑 : @Id
· 연관관계 매핑 : @ManyToOne, @JoinColumn

@Entity
· @Entity가 붙은 클래스는 JPA가 관리, 엔티티라고 한다
· JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수

@Table
· @Table은 엔티티와 매핑할 테이블 지정

데이터베이스 스키마 자동 생성
· DDL을 애플리케이션 실행 시점에 자동 생성
· 테이블 중심 -> 객체 중심
· 이렇게 생성된 DDL은 개발 장비에서만 사용
· 생성된 DDL은 운영서버에서는 사용하지 않거나, 적절한 수정 후 사용

hibernate.hbm2ddl.auto
create : 기존 테이블 삭제 후 다시 생성 (DROP + CREATE)
create-drop : create와 같으나 종료시점에 테이블 DROP
update : 변경분만 반영(운영DB에서는 사용 X)
validate : 엔티티와 테이블이 정상 매핑되었는지만 확인
none : 사용하지 않음

개발 초기에는 create 또는 update
테스트 서버에는 update 또는 validate
스테이징과 운영 서버에는 validate 또는 none

DDL 생성기능
· 제약조건 추가 : 회원 이름은 필수, 10자 초과 X
· @Column(nullable = false, length = 10)
· DDL 생성기능은 DDL을 자동 생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다
``