package hellojpa;

import jakarta.persistence.*;

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
            team.setName("TeamA");
            em.persist(team);

            // 저장
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println(" ============= ");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println(" ============= ");

            tx.commit(); // 그 다음에 DB transactiond이 커밋됨.
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 철회
        } finally {
            em.close(); // 작업이 끝나면 EntityManager를 닫아줌.
        }
        // 전체 애플리케이션이 완전히 끝나면 EntityManagerFactory를 닫아주어야 함.
        emf.close();
    }
}
