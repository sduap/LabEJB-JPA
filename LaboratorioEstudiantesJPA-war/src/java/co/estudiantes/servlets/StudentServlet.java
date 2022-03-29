package co.estudiantes.servlets;

import co.estudiantesLab.entidades.Curso;
import co.estudiantesLab.entidades.Estudiante;
import co.estudiantesLab.entidades.Registro;
import co.estudiantesLab.sessionentities.CursoFacadeLocal;
import co.estudiantesLab.sessionentities.EstudianteFacadeLocal;
import co.estudiantesLab.sessionentities.RegistroFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {

    @EJB
    private RegistroFacadeLocal registroFacade;

    @EJB
    private CursoFacadeLocal cursoFacade;

    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    
    enum Accion{
        ADD, EDIT, DELETE, SEARCH
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Obteniendo la elección del usuario
        Accion eleccion = Accion.valueOf(request.getParameter("action"));
        
        //Obteniendo los objetos encapsulados con la información necesaria
        String codigoStr=request.getParameter("codigo");
        int codigo= (codigoStr.equals(""))? 0 : Integer.parseInt(codigoStr);
        String nombre= request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        String year=request.getParameter("year");
        int año= (year.equals(""))? 0 : Integer.parseInt(year);
        
        Estudiante estudiante=new Estudiante(codigo, nombre, apellido, año);       
        boolean flag=false;
         
        //Procesando la elección
        switch(eleccion){
            case ADD:
                flag=estudianteFacade.addEstudiante(estudiante);
                break;
            case EDIT:
                flag=estudianteFacade.editEstudiante(estudiante);
                break;
            case DELETE:
                flag=estudianteFacade.deleteStudent(estudiante.getCodigo());
                break;
            case SEARCH:
                estudiante=estudianteFacade.getEstudiante(estudiante.getCodigo());
                flag=true;
                break;
        }
        if(flag) request.setAttribute("mensaje", "La operacion se completo exitosamente");
        else request.setAttribute("mensaje", "La operacion no se pudo realizar");
        
        request.setAttribute("student", estudiante);        
        request.setAttribute("allStudents", estudianteFacade.getAllEstudiantes());
        request.getRequestDispatcher("estudianteInfo.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

