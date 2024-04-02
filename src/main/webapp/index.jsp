<%-- 
    Document   : index
    Created on : 31 mar. 2024, 12:38:12
    Author     : illim
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.Persona"%>
<%
    
    //si devuelve null es la primera vez
    if (session.getAttribute("listaper")==null){
    ArrayList<Persona> lisAux= new ArrayList<Persona> ();
    session.setAttribute("listaper", lisAux);
    }
    ArrayList<Persona> lista = (ArrayList<Persona>)session.getAttribute("listaper");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de registros</h1>
        <a href="MainServlet?op=nuevo">Nuevo registro</a>
        <br>
        <table border="1">
            <tr>
                
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>edad</th>
                <th></th>
                <th></th>
            </tr>
            
            <%
            if (lista!=null){
            for(Persona item: lista){
            
            %>
            <tr>
                <td><%=item.getId() %></td>
                <td><%=item.getNombre() %></td>
                <td><%=item.getApellidos() %></td>
                <td><%=item.getEdad() %></td>
                <td><a href="MainServlet?op=editar&id=<%=item.getId() %>">Editar</a></td>
                <td><a href="MainServlet?op=eliminar&id=<%=item.getId() %>"onclick="return(confirm('ESta suguro de eliminar?'))"  
                       >Eliminar</a></td>
                
                
            </tr>
            <%
            }
            }
            %>
            
        </table>
        
        
    </body>
</html>
