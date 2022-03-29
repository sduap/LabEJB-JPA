package co.estudiantesLab.sessionentities;

import co.estudiantesLab.entidades.Registro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RegistroFacade extends AbstractFacade<Registro> implements RegistroFacadeLocal {

    @PersistenceContext(unitName = "LaboratorioEstudiantesJPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroFacade() {
        super(Registro.class);
    }

    @Override
    public boolean addRegistro(Registro registro) {
        Registro prexistent =getRegistro(registro.getId());
        if(prexistent == null) {
            em.persist(registro);
            return true;
        }
        return false; 
    }

    @Override
    public boolean editRegistro(Registro registro) {
        Registro prexistent =getRegistro(registro.getId());
        if(prexistent != null) {
            em.merge(registro);
            return true;
        }
        return false; 
    }

    @Override
    public boolean deleteRegistro(int id) {
        Registro prexistent =getRegistro(id);
        if(prexistent != null) {
            em.remove(prexistent);
            return true;
        }
        return false; 
    }

    @Override
    public Registro getRegistro(int id) {
       return em.find(Registro.class, id);
    }

    @Override
    public List<Registro> getAllRegistros() {
        return findAll();
    }
    
}
