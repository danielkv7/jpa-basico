package part2;

import classes.Aluno;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutionPart2 {
    public static void main(String[] args) {

        // OBS: Esse codigo deve executar SEM ERROS com a implementacao JPA que foi definida no "persistence.xml".
        // Se estiver somente com o JPA baixado, o codigo NAO IRA funcionar.

        // O ideal é que nessa parte (Parte 2) o codigo EXECUTE SEM ERROS, pois aqui tera uma implementacao JPA sendo utilizada.

        // 1 - Passos iniciais para criar um gerenciadop de entidades com o banco de dados especificado no arquivo  "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 2.1 - Criar instancias para serem adicionadas no banco de dados
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        Aluno alunoParaAdicionar = new Aluno("Daniel", 29, estadoParaAdicionar);

        // 2.2 - Iniciar uma trasacao para adiconar as instancias no banco de dados
        entityManager.getTransaction().begin();

        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);

        entityManager.getTransaction().commit();

        // 3 - Resgatar instâncias no banco de dados
//        Estado estadoEncontrado = entityManager.find(Estado.class, 1);
//        Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);
//
//        System.out.println(estadoEncontrado);
//        System.out.println(alunoEncontrado);

        // 4 - Alterar uma entidade
//        entityManager.getTransaction().begin();
//
//        alunoEncontrado.setNome("Karam");
//        alunoEncontrado.setIdade(20);
//
//        entityManager.getTransaction().commit();

        // 5 - Remover uma entidade
//        entityManager.getTransaction().begin();
//
//        entityManager.remove(alunoEncontrado);
//
//        entityManager.getTransaction().commit();

        // 6 - Encerrar o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade.
        entityManager.close();
        entityManagerFactory.close();

    }
}
