<%-- 
    Document   : editar
    Created on : 31 mar. 2024, 12:59:34
    Author     : illim
--%>
<%@page import="com.emergentes.Persona"%>
<%
Persona reg = (Persona) request.getAttribute("miobjper");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de persona</h1>
        <form action="MainServlet" method="post">
            
            <table >
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%=reg.getId() %>" size="2" readonly></td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="<%=reg.getNombre() != null ? reg.getNombre() :"" %>" placeholder="nombre"></td>
                </tr><!-- comment -->
                <tr>
                    <td>Apellidos</td>
                    <td><input type="text" name="apellidos" value="<%=reg.getApellidos() != null ? reg.getApellidos() :""  %>" placeholder="apellidos" ></td>
                </tr>
                <tr>
                    <td>Edad</td>
                    <td><input type="text" name="edad" value="<%=reg.getEdad() %>" placeholder="edad" ></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="enviar"></td>
                   
                </tr>
            </table>

        </form>
    </body>
</html>
