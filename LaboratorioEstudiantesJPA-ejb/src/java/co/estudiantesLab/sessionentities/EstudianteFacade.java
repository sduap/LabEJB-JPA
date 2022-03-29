package co.estudiantesLab.sessionentities;

import co.estudiantesLab.entidades.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "LaboratorioEstudiantesJPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
    public boolean addEstudiante(Estudiante estudiante) {
        Estudiante prexistent =getEstudiante(estudiante.getCodigo());
        if(prexistent == null) {
            em.persist(estudiante);
            return true;
        }
        return false;      
    }

    @Override
    public boolean editEstudiante(Estudiante estudiante) {
        Estudiante prexistent =getEstudiante(estudiante.getCodigo());
        if(prexistent != null) {
            em.merge(estudiante);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int codigoEstudiante) {
        Estudiante estudiante = getEstudiante(codigoEstudiante);
        if(estudiante!=null){
            em.remove(estudiante);
            return true;
        }
        return false;
    }

    @Override
    public Estudiante getEstudiante(int codigoEstudiante) {
        return em.find(Estudiante.class, codigoEstudiante);
    }

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return findAll();
    }
    
    
    
    
    
    
    
    
    
}
