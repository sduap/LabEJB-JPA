package co.estudiantes.servlets;

import co.estudiantesLab.entidades.Curso;
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

public class EnrollmentServlet extends HttpServlet {

    @EJB
    private EstudianteFacadeLocal estudianteFacade;

    @EJB
    private CursoFacadeLocal cursoFacade;

    @EJB
    private RegistroFacadeLocal registroFacade;
    
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
        Accion eleccion = Accion.valueOf(request.getParameter("action"));
        
        //Obteniendo los objetos encapsulados con la informacion necesaria
        String idStr=request.getParameter("id");
        int id= (idStr.equals(""))? 0 : Integer.parseInt(idStr);
        String codigoEstudianteStr= request.getParameter("codigoEstudiante");
        int codigoEstudiante= (codigoEstudianteStr.equals(""))? 0:Integer.parseInt(codigoEstudianteStr);
        String codigoCurso=request.getParameter("codigoCurso");        
        
        Registro registro=new Registro(id, cursoFacade.getCurso(codigoCurso), estudianteFacade.getEstudiante(codigoEstudiante));  
        
        boolean flag=false;
         
        //Procesando la eleccion
        switch(eleccion){
            case ADD:
                flag=registroFacade.addRegistro(registro);
                break;
            case EDIT:
                flag=registroFacade.editRegistro(registro);
                break;
            case DELETE:
                flag=registroFacade.deleteRegistro(registro.getId());
                break;
            case SEARCH:
                registro=registroFacade.getRegistro(id);
                flag=true;
                break;
        }
        if(flag) request.setAttribute("mensajeRegistro", "La operacion se completo exitosamente");
        else request.setAttribute("mensajeRegistro", "La operacion no se pudo realizar");
        
        request.setAttribute("enrollment", registro);        
        request.setAttribute("allEnrollments", registroFacade.getAllRegistros());
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
