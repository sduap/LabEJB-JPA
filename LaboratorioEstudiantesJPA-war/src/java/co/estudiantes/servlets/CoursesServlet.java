package co.estudiantes.servlets;

import co.estudiantesLab.entidades.Curso;
import co.estudiantesLab.sessionentities.CursoFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {

    @EJB
    private CursoFacadeLocal cursoFacade;
    
    enum Accion{
        ADD, EDIT, DELETE, SEARCH
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obteniendo la elección del usuario
        Accion eleccion = Accion.valueOf(request.getParameter("action"));
        
        //Obteniendo los objetos encapsulados con la información necesaria
        String codigo=request.getParameter("codigoCurso");        
        String nombre= request.getParameter("nombreCurso");
        String creditosStr=request.getParameter("creditos");
        int creditos= (creditosStr.equals(""))? 0 : Integer.parseInt(creditosStr);
        String semestreStr=request.getParameter("semestre");
        int semestre= (semestreStr.equals(""))? 0 : Integer.parseInt(semestreStr);
        String totalEstudiantesStr=request.getParameter("totalEstudiantes");
        int totalEstudiantes= (totalEstudiantesStr.equals(""))? 0 : Integer.parseInt(totalEstudiantesStr);
        
        Curso curso=new Curso(codigo, nombre, creditos, semestre, totalEstudiantes);       
        boolean flag=false;
         
        //Procesando la elección
        switch(eleccion){
            case ADD:
                flag=cursoFacade.addCurso(curso);
                break;
            case EDIT:
                flag=cursoFacade.editCurso(curso);
                break;
            case DELETE:
                flag=cursoFacade.deleteCurso(curso.getCodigo());
                break;
            case SEARCH:
                curso=cursoFacade.getCurso(curso.getCodigo());
                flag=true;
                break;
        }
        if(flag) request.setAttribute("mensajeCurso", "La operacion se completo exitosamente");
        else request.setAttribute("mensajeCurso", "La operacion no se pudo realizar");
        
        request.setAttribute("course", curso);        
        request.setAttribute("allCourses", cursoFacade.getAllCursos());
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
