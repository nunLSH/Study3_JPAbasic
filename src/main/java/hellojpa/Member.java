package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity  // 이걸 넣어줘야 JPA를 처음 로딩할 때, JPA를 사용하는 클래스라는 것을 인지하고 관리함.
// DB와 매핑되는 Member 클래스
public class Member {

    @Id
    private Long id;
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 필요한 Getter와 Setter 생성
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
