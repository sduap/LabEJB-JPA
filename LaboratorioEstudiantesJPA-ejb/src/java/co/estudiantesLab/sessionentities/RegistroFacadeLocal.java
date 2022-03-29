package co.estudiantesLab.sessionentities;

import co.estudiantesLab.entidades.Registro;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RegistroFacadeLocal {

    void create(Registro registro);

    void edit(Registro registro);

    void remove(Registro registro);

    Registro find(Object id);

    List<Registro> findAll();

    List<Registro> findRange(int[] range);

    int count();
    
    boolean addRegistro(Registro registro);

    boolean editRegistro(Registro registro);

    boolean deleteRegistro(int id);

    Registro getRegistro(int id);

    List<Registro> getAllRegistros();
    
}
