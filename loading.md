#ex1-hello-jpa
즉시로딩과 지연로딩
지연로딩 LAZY(fetch = FetchType.LAZY)을 사용 → 프록시로 조회
즉시 로딩 EAGER를 사용 → 실제 엔티티

프록시와 즉시로딩 주의
 · 가급적 지연 로딩만 사용(특히 실무에서)
 · 즉시 로딩을 적용하면 예상하지 못한 SQL이 발생
 · 즉시 로딩은 JPQL에서 N+1 문제를 일으킴
 · @ManyToOne, @OneToOne은 기본이 즉시 로딩 → LAZY로 설정
 · @OneToMany, @ManyToMany는 기본이 지연 로딩

지연 로딩 활용 - 실무
 · 모든 연관관계에서 지연 로딩을 사용해라
 ·JPQL fetch 조인이나, 엔티티 그래프 기능을 사용해라