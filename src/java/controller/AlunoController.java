/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlunoDAO;
import dao.CursoDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Curso;

/**
 *
 * @author PC
 */
@WebServlet(name = "Curso", urlPatterns = {"/AlunoController"})
public class AlunoController extends HttpServlet {
    private static final long SerialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/aluno.jsp";
    private static String LIST = "/listaAlunos.jsp";
    private AlunoDAO dao;
    
    public AlunoController(){
        super();
        this.dao = new AlunoDAO();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int alunoId = Integer.parseInt(request.getParameter("alunoId"));
            dao.deleteAluno(alunoId);
            forward = LIST;
            request.setAttribute("alunos", dao.getAllAlunos());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int alunoId = Integer.parseInt(request.getParameter("alunoId"));
            Aluno aluno = dao.getAlunoById(alunoId);
            request.setAttribute("curso", aluno);
        } else if (action.equalsIgnoreCase("list")){
            forward = LIST;
            request.setAttribute("alunos", dao.getAllAlunos());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getParameter("nome"));
        try {
            aluno.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento")));
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        aluno.setCpf(request.getParameter("cpf"));
        aluno.setIdCurso(new Integer(request.getParameter("idCurso")));
      
        String userid = request.getParameter("alunoId");
        if(userid == null || userid.isEmpty())
        {
            dao.addAluno(aluno);
        }
        else
        {
            aluno.setId(Integer.parseInt(userid));
            dao.updateAluno(aluno);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        request.setAttribute("alunos", dao.getAllAlunos());
        view.forward(request, response);
    }
    
    
}
