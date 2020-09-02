#ex1-hello-jpa
영속성 전이 : CASCADE
 · 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만들고 싶을 때
 · 예 : 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장
영속성 전이 : CASCADE - 주의 !
 · 영속성 전이는 연관관계를 매핑하는 것과 아무 관련이 없음
 · 엔티티를 영속화할 때 연관됨 엔티티도 함께 영속화하는 편리함을 제공할 뿐

 · ALL : 모두 적용
 · PERSIST : 영속
 · REMOVE : 삭제
 
고아 객체
 · 고아 객체 제거 : 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제
 · orphanRemoval = true
고아 객체 - 주의
 · 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제하는 기능
 · 참조하는 곳이 하나일 때 사용해야함
 · 특정 엔티티가 개인 소유할 때 사용
 · @OneToOne, @OneToMany만 가능
 
cascade = CascadeType.ALL, orphanRemoval = true
 · 두 옵션을 모두 활성화하면 부모 엔티티를 통해서 자식의 생명주기를 관리할 수 있음