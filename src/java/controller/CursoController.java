/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CursoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Curso;

/**
 *
 * @author PC
 */
@WebServlet(name = "Curso", urlPatterns = {"/CursoController"})
public class CursoController extends HttpServlet {
    private static final long SerialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/curso.jsp";
    private static String LIST = "/listaCurso.jsp";
    private CursoDAO dao;
    
    public CursoController(){
        super();
        dao = new CursoDAO();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int cursoId = Integer.parseInt(request.getParameter("cursoId"));
            dao.deleteCurso(cursoId);
            forward = LIST;
            request.setAttribute("cursos", dao.getAllCursos());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int cursoId = Integer.parseInt(request.getParameter("cursoId"));
            Curso curso = dao.getCursoById(cursoId);
            request.setAttribute("curso", curso);
        } else if (action.equalsIgnoreCase("list")){
            forward = LIST;
            request.setAttribute("cursos", dao.getAllCursos());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Curso curso = new Curso();
        curso.setCurso(request.getParameter("curso"));
        curso.setDuracao(new Integer(request.getParameter("duracao")));
      
        String userid = request.getParameter("cursoId");
        if(userid == null || userid.isEmpty())
        {
            dao.addCurso(curso);
        }
        else
        {
            curso.setId(Integer.parseInt(userid));
            dao.updateCurso(curso);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("cursos", dao.getAllCursos());
        view.forward(request, response);
    }
    
    
    
    
    
    
}
