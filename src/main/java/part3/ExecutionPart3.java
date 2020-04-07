package part3;

import classes.Aluno;
import classes.Aluno_;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ExecutionPart3 {

    public static void main(String[] args) {

        // 1 - Dados instanciados para serem utilizados como exemplo
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(new Estado("Sao Paulo", "SP"));
        entityManager.persist(new Aluno("Daniel", 29, estadoParaAdicionar));
        entityManager.persist(new Aluno("Joao", 20, estadoParaAdicionar));
        entityManager.persist(new Aluno("Pedro", 30, estadoParaAdicionar));
        entityManager.getTransaction().commit();

        // 2 - Vamos transformar a seguinte consulta de SQL nativo em JPQL e JPA Criteria API
        // SELECT * FROM Aluno WHERE nome = 'Daniel';

        // 3 - Em JPQL
        String jpql = "select a from Aluno a where a.nome = :nome";
        Aluno alunoJPQL = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", "Daniel")
                .getSingleResult();

        // 4 - Em JPA Criteria API + JPA Metamodel
        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRoot = criteriaQuery.from(Aluno.class);
        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoRoot.get(Aluno_.nome));
        inClause.value("Joao");
        criteriaQuery.select(alunoRoot).where(inClause);
        Aluno alunoAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();

        // 5 - Resultado das consultas acima
        System.out.println("Resultado em JPQL: " + alunoJPQL);
        System.out.println("Resultado em API Criteria: " + alunoAPICriteria);

    }

}
