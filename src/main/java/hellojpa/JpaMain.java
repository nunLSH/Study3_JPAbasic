package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 여기서 EntityManager를 꺼내고
        EntityManager em = emf.createEntityManager();

        // transaction
        EntityTransaction tx = em.getTransaction(); // tx 얻기
        tx.begin(); // tx 시작

        try {
            // EntityManager안에서 실제 동작하는 code는 여기에 작성
            Member member = new Member();
            // 멤버의 아이디와 이름 설정
            member.setId(1L);
            member.setName("HelloA");

            em.persist(member); // 멤버 넣기(저장)

            tx.commit(); // 정상적일 때 저장, 문제가 생기면 Rollback(철회)
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 철회
        } finally {
            em.close(); // 작업이 끝나면 EntityManager를 닫아줌.
        }

        // 전체 애플리케이션이 완전히 끝나면 EntityManagerFactory를 닫아주어야 함.
        emf.close();
    }
}
