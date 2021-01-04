· 객체와 테이블 매핑 : @Entity, @Table
· 필드와 컬럼 매핑 : @Column
· 기본 키 매핑 : @Id
· 연관관계 매핑 : @ManyToOne, @JoinColumn

@Entity
· @Entity가 붙은 클래스는 JPA가 관리, 엔티티라고 한다
· JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수

@Table
· @Table은 엔티티와 매핑할 테이블 지정
ex) @Table(name="MBR")
    name : 매핑할 테이블 이름

데이터베이스 스키마 자동 생성
· DDL을 애플리케이션 실행 시점에 자동 생성
· 테이블 중심 -> 객체 중심
· 이렇게 생성된 DDL은 개발 장비에서만 사용
· 생성된 DDL은 운영서버에서는 사용하지 않거나, 적절히 다듬은 후 사용

hibernate.hbm2ddl.auto
create : 기존 테이블 삭제 후 다시 생성 (DROP + CREATE)
create-drop : create와 같으나 종료시점에 테이블 DROP
update : 변경분만 반영(운영DB에서는 사용 X)
validate : 엔티티와 테이블이 정상 매핑되었는지만 확인
none : 사용하지 않음

운영 장비에는 절대 create, create-drop, update 사용하면 안됨
개발 초기에는 create 또는 update
테스트 서버에는 update 또는 validate
스테이징과 운영 서버에는 validate 또는 none

DDL 생성기능
· 제약조건 추가 : 회원 이름은 필수, 10자 초과 X
· @Column(nullable = false, length = 10)
· DDL 생성기능은 DDL을 자동 생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다

@Column : 컬럼 매핑
@Temporal : 날짜 타입 매핑
@Enumerated : enum 타입 매핑
@Lob : BLOB, CLOB 매핑
@Transient : 특정 필드를 컬럼에서 제외

@Column
name - 필드와 매핑할 테이블의 컬럼 이름
insertable, updatable - 등록, 변경 가능 여부
nullable - null 값의 허용 여부를 설정, false로 설정하면 DDL생성 시 not null 제약조건이 붙는다
unique - 한 컬럼에 간단히 유니크 제약조건을 걸 때 사용한다
columnDefinition - 데이터베이스 컬럼 정보를 직접 줄 수 있다
length - 문자 길이 제약조건
enum 타입을 매핑할 때 EnumType.ORDINAL 사용 X → enum 순서를 데이터베이스에 저장

기본 키 매핑 어노테이션
· @Id : 직접 할당
· @GeneratedValue : 자동 생성 

IDENTITY 전략
· 기본 키 생성을 데이터베이스에 위임, 주로 MySQL, PostgreSQL..
  (예 : MySQL의 AUTO_INCREMENT)
· AUTO_INCREMENT는 데이터베이스에 INSERT SQL을 실행한 이후에 ID값을 알 수 있음
SEQUENCE 전략
allocationSize
TABLE 전략
· 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
· 장점 : 모든 DB에서 적용가능, 단점 : 성능

권장하는 식별자 전략
· 기본 키 제약 조건 : null 아님 유일, 변하면 안됨
· 미래까지 이 조건을 만족하는 자연키는 찾기 어려우니 대리키(대체키)를 사용하자
  ex) 주민등록번호는 기본키로 사용하기에 적합하지 않음
· 권장 : Long형 + 대체키 + 키 생성전략 사용