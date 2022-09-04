package hellojpa;

import javax.persistence.*;

@Entity
//@TableGenerator(name = "member_seq_generator", table = "my_sequences", pkColumnValue = "member_seq", allocationSize = 1)
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 50)
public class Member {
    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    /**
     private Integer age;
     @Enumerated(EnumType.STRING)
     private RoleType roleType;
     @Temporal(TemporalType.TIMESTAMP)
     private Date createdDate;
     @Temporal(TemporalType.TIMESTAMP)
     private Date lastModifiedDate;
     private LocalDate testLocalDate;
     private LocalDateTime testLocalDateTime;
     @Lob
     private String description;
     @Transient
     private int temp;
     */

    public Member() {
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
}
