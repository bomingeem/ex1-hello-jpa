다양한 연관관계 매핑

연관관계 매핑시 고려사항 3가지 
 1. 다중성
 2. 단방향, 양방향
 3. 연관관계의 주인

다중성
 - 다대일 : @ManyToOne
 - 일대다 : @OneToMany
 - 일대일 : @OneToOne
 - 다대다 : @ManyToMany
단방향, 양방향
 - 테이블
    - 외래 키 하나로 양쪽 조인 가능
    - 사실 방향이라는 개념이 없음
 - 객체
    - 참조용 필드가 있는 쪽으로만 참조 가능
    - 한쪽만 참조하면 단방향
    - 양쪽이 서로 참조하면 양방향
연관관계의 주인
 - 테이블은 외래키 하나로 두 테이블이 연관관계를 맺음
 - 객체 양방향 관계는 A→B, B→A 처럼 참조가 두군데
 - 둘중 테이블의 외래키를 관리할 곳을 지정해야함
 - 연관관계의 주인 : 외래키를 관리하는 참조
 - 주인의 반대편 : 외래키에 영향을 주지않음, 단순 조회만 가능

다대일 [N:1]
 다대일 단방향
  - 가장 많이 사용하는 연관관계
 다대일 양방향
  - 외래키가 있는 쪽이 연관관계의 주인
  - 양쪽을 서로 참조하도록 개발
일대다 [1:N]
 일대다 단방향
  - 일대다 단방향은 일대다(1:N)에서 일(1)이 연관관계의 주인
  - 테이블 일대다 관계는 항상 다(N)쪽에 외래키가 있음
  - 객체와 테이블의 차이 때문에 반대편 테이블의 외래키를 관리하는 특이한 구조
  - @JoinColumn을 꼭 사용, 그렇지 않으면 조인 테이블 방식을 사용함(중간에 테이블 하나 추가)
  - 엔티티가 관리하는 외래키가 다른 테이블에 있음, 연관관계 관리를 위해 추가로 UPDATE SQL 실행
  - 일대다 단방향 매핑보다는 다대일 양방향 매핑 사용
 일대다 양방향
  - 공식적으로 존재 X
  - @JoinColumn(insertable=false, updatable=false)
  - 읽기 전용 필드를 사용해서 양방향처럼 사용하는 방법
일대일 [1:1]
 - 주 테이블이나 대상 테이블 중에 외래키 선택 가능
 - 외래키에 데이터베이스 유니크 제약조건 추가
 주 테이블에 외래키 단방향
  - 다대일 단방향 매핑과 유사
 주 테이블에 외래키 양방향
  - 다대일 양방향 매핑처럼 외래키가 있는곳이 연관관계의 주인
  - 반대편 mappedBy 적용
 대상 테이블에 외래키 단방향
  - 단방향 관계는 JPA 지원 X 
 대상 테이블에 외래키 양방향
  - 사실 일대일 주테이블에 외래키 양방향과 매핑방법은 같음
다대다 [N:M]
 - 관계형 데이터베이스는 정규화된 테이블 2개로 다대다 관계를 표현할 수 없음
 - 연결 테이블을 추가해서 일대다, 다대일 관계로 풀어내야함
 - 객체는 컬렉션을 이용해서 객체 2개로 다대다 관계 가능
 다대다 매핑의 한계
  - 실무에서 사용 X
  - 연결 테이블이 단순히 연결만 하고 끝나지 않음
  - 주문시간, 수량같은 데이터가 들어올 수 있음
 다대다 한계 극복
  - 연결 테이블용 엔티티 추가(연결 테이블을 엔티티로 승격)
  - @ManyToMany → @OneToMany, @ManyToOne

   