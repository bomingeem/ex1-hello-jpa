package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "USER") -> DB테이블의 이름이 USER일 경우 이름 지정
public class Member {
    //최소한 JPA에게 PK를 알려주어야 함
    @Id
    private Long id;
    //@Column(name = "username") -> DB테이블의 컬렁명이 username일 경우 이름 지정
    private  String name;

    public Member(){
    }
    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }

    //Alt + Insert = getter/setter 자동생성 단축키
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
