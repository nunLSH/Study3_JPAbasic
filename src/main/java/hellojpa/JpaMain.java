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

            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            // 조회
            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit(); // 그 다음에 DB transactiond이 커밋됨.
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 철회
        } finally {
            em.close(); // 작업이 끝나면 EntityManager를 닫아줌.
        }
        // 전체 애플리케이션이 완전히 끝나면 EntityManagerFactory를 닫아주어야 함.
        emf.close();
    }

    private static Member saveMember(EntityManager em) {
        Member member = new Member();
        member.setUsername("member1");

        em.persist(member);
        return member;
    }
}
