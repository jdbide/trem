package data.model.databean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NoteEntityManager {

    private static NoteEntityManager instance;

    private static final String PERSISTENCE_UNIT_NAME = "PU_socle";
    private EntityManagerFactory emf;
    private EntityManager em;

    private NoteEntityManager() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    public static synchronized NoteEntityManager getInstance() {
        if (instance == null) {
            instance = new NoteEntityManager();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return em;
    }

}
