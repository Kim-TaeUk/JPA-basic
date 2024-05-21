package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        //EntityManagerFactory: 애플리케이션 로딩 시점에 딱 하나만
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //persistence.xml의 persistence-unit을 넘겨야한다
        //META-INF/persistence.xml의 정보를 바탕으로 생성되기 때문

        //EntityManager: 커넥션을 얻어서 쿼리를 날리고 종료되는 어떠한 일관적인 단위의 일을 할 때마다 생성해서 사용
        //쓰레드 간 공유하면 절대 안됨, 사용하고 버리기
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행해야함
        try {

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
