package co.estudiantesLab.sessionentities;

import co.estudiantesLab.entidades.Estudiante;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EstudianteFacadeLocal {

    void create(Estudiante estudiante);

    void edit(Estudiante estudiante);

    void remove(Estudiante estudiante);

    Estudiante find(Object id);

    List<Estudiante> findAll();

    List<Estudiante> findRange(int[] range);

    int count();

    boolean addEstudiante(Estudiante estudiante);

    boolean editEstudiante(Estudiante estudiante);

    boolean deleteStudent(int codigoEstudiante);

    Estudiante getEstudiante(int codigoEstudiante);

    List<Estudiante> getAllEstudiantes();
    
}
