<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Information</title>
    </head>
    <body>
        <h1>Informaci&oacute;n de estudiantes</h1>
        <form action="StudentServlet" method="POST">
            <table>
                <tr>
                    <td>C&oacute;digo</td>
                    <td><input type="text" name="codigo" value="${student.codigo}" /></td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="${student.nombre}" /></td>
                </tr>
                <tr>
                    <td>Apellido</td>
                    <td><input type="text" name="apellido" value="${student.apellido}" /></td>
                </tr>
                <tr>
                    <td>Año</td>
                    <td><input type="text" name="year" value="${student.año}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="ADD" />
                        <input type="submit" name="action" value="EDIT" />
                        <input type="submit" name="action" value="DELETE" />
                        <input type="submit" name="action" value="SEARCH" />
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <p class="mensaje" name="mensaje">${mensaje}</p>
        <br>
        <table border="1">
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Year Level</th>
                <c:forEach items="${allStudents}" var="stud">
                <tr>
                    <td>${stud.codigo}</td>
                    <td>${stud.nombre}</td>
                    <td>${stud.apellido}</td>
                    <td>${stud.año}</td>
                </tr>
            </c:forEach>           
        </table>
        <h1>Informaci&oacute;n de cursos</h1>
        <form action="CoursesServlet" method="POST">
            <table>
                <tr>
                    <td>C&oacute;digo</td>
                    <td><input type="text" name="codigoCurso" value="${course.codigo}" /></td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombreCurso" value="${course.nombre}" /></td>
                </tr>
                <tr>
                    <td>Creditos</td>
                    <td><input type="text" name="creditos" value="${course.creditos}" /></td>
                </tr>
                <tr>
                    <td>Semestre</td>
                    <td><input type="text" name="semestre" value="${course.semestre}" /></td>
                </tr>
                <tr>
                    <td>Total estudiantes</td>
                    <td><input type="text" name="totalEstudiantes" value="${course.totalestudiantes}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="ADD" />
                        <input type="submit" name="action" value="EDIT" />
                        <input type="submit" name="action" value="DELETE" />
                        <input type="submit" name="action" value="SEARCH" />
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <p class="mensaje" name="mensajeCurso">${mensajeCurso}</p>
        <br>
        <table border="1">
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Creditos</th>
            <th>Semestre</th>
            <th>Total Estudiantes</th>
                <c:forEach items="${allCourses}" var="cours">
                <tr>
                    <td>${cours.codigo}</td>
                    <td>${cours.nombre}</td>
                    <td>${cours.creditos}</td>
                    <td>${cours.semestre}</td>
                    <td>${cours.totalestudiantes}</td>
                </tr>
            </c:forEach>           
        </table>
        <h1>Informaci&oacute;n de Matriculaci&oacute;n</h1>
        <form action="EnrollmentServlet" method="POST">
            <table>
                <tr>
                    <td>Id Registro</td>
                    <td><input type="text" name="id" value="${enrollment.id}" /></td>
                </tr>
                <tr>
                    <td>Codigo Estudiante</td>
                    <td><input type="text" name="codigoEstudiante" value="${enrollment.codigoestudiante.codigo}" /></td>
                </tr>
                <tr>
                    <td>Codigo Curso</td>
                    <td><input type="text" name="codigoCurso" value="${enrollment.codigocurso.codigo}" /></td>
                </tr>                
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="ADD" />
                        <input type="submit" name="action" value="EDIT" />
                        <input type="submit" name="action" value="DELETE" />
                        <input type="submit" name="action" value="SEARCH" />
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <p class="mensaje" name="mensajeRegistro">${mensajeRegistro}</p>
        <br>
        <table border="1">
            <th>Id Registro</th>
            <th>Codigo Estudiante</th>
            <th>Codigo Curso</th>            
                <c:forEach items="${allEnrollments}" var="enroll">
                <tr>
                    <td>${enroll.id}</td>
                    <td>${enroll.codigoestudiante.codigo}</td>
                    <td>${enroll.codigocurso.codigo}</td> 
                </tr>
            </c:forEach>           
        </table>
    </body>
</html>