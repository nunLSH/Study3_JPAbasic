package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 여기서 EntityManager를 꺼내고
        EntityManager em = emf.createEntityManager();

        // transaction
        EntityTransaction tx = em.getTransaction(); // tx 얻기
        tx.begin(); // tx 시작

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            team.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2") ;
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();


            tx.commit(); // 그 다음에 DB transactiond이 커밋됨.
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 철회
        } finally {
            em.close(); // 작업이 끝나면 EntityManager를 닫아줌.
        }
        // 전체 애플리케이션이 완전히 끝나면 EntityManagerFactory를 닫아주어야 함.
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2: " + (m1 instanceof Member));
        System.out.println("m1 == m2: " + (m2 instanceof Member));
    }
}
