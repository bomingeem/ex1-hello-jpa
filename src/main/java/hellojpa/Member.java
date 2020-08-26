package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
/*
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50)
 */
public class Member {
    //최소한 JPA에게 PK를 알려주어야 함
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    //@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
     private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //@Column(name = "TEAM_ID")
    //private Long teamId;

/*
    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;
    //Java 8에서 제공하는 LocalDate/LocalDateTime 이후로 Temporal를 사용하지 않아도 됨
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;
    @Transient
    private int temp;
    */

    public Member(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
