/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author illim
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //peticion desde enlace
        String op = request.getParameter("op");
        
        Persona objper = new Persona();
        int id, posicion;
        
        HttpSession ses = request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
        
        switch (op) {
            case "nuevo":
                //enviar un objeto vacio a editar
                request.setAttribute("miobjper", objper);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                //averiguar la posicion del elemento en la lista
                posicion = buscaPorIndice(request, id);
                //obtener el objeto
                objper = lista.get(posicion);
                
                request.setAttribute("miobjper", objper);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                //enciar un objeto a editar pero con contenido
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                posicion = buscaPorIndice(request, id);
                if (posicion >= 0) {
                    lista.remove(posicion);
                }
                request.setAttribute("listaper", lista);
                
                response.sendRedirect("index.jsp");
                //eliminar el registro de la coleccion segun el id que se reciba
                break;
            
            default:
        }
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
        
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
        
        Persona objper = new Persona();
        objper.setId(id);
        objper.setNombre(request.getParameter("nombre"));
        objper.setApellidos(request.getParameter("apellidos"));
        objper.setEdad(Integer.parseInt(request.getParameter("edad")));
        
        if (id == 0) {
            //nuevo registro
            int idNuevo = obtenerID(request);
            objper.setId(idNuevo);
            lista.add(objper);
        } else {
            //edicion de registro
            int pos = buscaPorIndice(request, id);
            lista.set(pos, objper);
            
        }
        request.setAttribute("listaper", lista);
        response.sendRedirect("index.jsp");
        
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

    public int buscaPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
        
        int pos = -1;
        if (lista != null) {
            for (Persona ele : lista) {
                ++pos;
                if (ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }
    
    public int obtenerID(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");

//buscar el ultimo id
        int idm = 0;
        
        for (Persona ele : lista) {
            idm = ele.getId();
        }
        
        return idm + 1;
        
    }
}
