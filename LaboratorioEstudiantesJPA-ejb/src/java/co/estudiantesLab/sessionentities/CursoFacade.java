package co.estudiantesLab.sessionentities;

import co.estudiantesLab.entidades.Curso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CursoFacade extends AbstractFacade<Curso> implements CursoFacadeLocal {

    @PersistenceContext(unitName = "LaboratorioEstudiantesJPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

    @Override
    public boolean addCurso(Curso curso) {
        Curso prexistente = getCurso(curso.getCodigo());
        if(prexistente==null){
            em.persist(curso);
            return true;
        }
        return false;
    }

    @Override
    public boolean editCurso(Curso curso) {
        Curso prexistente = getCurso(curso.getCodigo());
        if(prexistente!=null){
            em.merge(curso);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCurso(String codigo) {
        Curso prexistente = getCurso(codigo);
        if(prexistente!=null){
            em.remove(prexistente);
            return true;
        }
        return false;
    }

    @Override
    public Curso getCurso(String codigo) {
        return em.find(Curso.class, codigo);
    }

    @Override
    public List<Curso> getAllCursos() {
        return findAll();
    }
    
}
