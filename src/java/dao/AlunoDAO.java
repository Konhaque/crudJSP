/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Curso;
import util.DbUtil;

/**
 *
 * @author PC
 */
public class AlunoDAO {
    private Connection connection;
    private static final String INSERT_ALUNO = "INSERT INTO ALUNO(NOME, DT_NASCIMENTO, CPF, ID_CURSO) VALUES (?,?,?,?)";
    private static final String DELETE_ALUNO = "DELETE FROM ALUNO WHERE ID=?";
    private static final String UPDATE_ALUNO = "UPDATE ALUNO SET NOME=?, DT_NASCIMENTO=?, CPF=?, ID_CURSO=? WHERE ID=?";
    private static final String ALL_ALUNOS = "SELECT * FROM ALUNO";
    private static final String ALUNO_BY_ID = "SELECT * FROM ALUNO WHERE ID=?";
    private static final String ALUNOS_BY_CURSO = "SELECT * FROM ALUNO WHERE ID_CURSO=?";

    public AlunoDAO(){
        this.connection = DbUtil.getConection();
    }
    
    public void addAluno(Aluno aluno){
          try{
            PreparedStatement ps = connection.prepareStatement(INSERT_ALUNO);
            ps.setString(1, aluno.getNome());
            ps.setDate(2, new java.sql.Date(aluno.getDataNascimento().getTime()));
            ps.setString(3, aluno.getCpf());
            ps.setInt(4, aluno.getIdCurso());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
     public void deleteAluno(Integer id){
        try{
            PreparedStatement ps = connection.prepareStatement(DELETE_ALUNO);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
     
      public void updateAluno(Aluno aluno){
         try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_ALUNO);
            ps.setString(1, aluno.getNome());
            ps.setDate(2, new java.sql.Date(aluno.getDataNascimento().getTime()));
            ps.setString(3, aluno.getCpf());
            ps.setInt(4, aluno.getIdCurso());
            ps.setInt(5, aluno.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
      
    public List<Aluno> getAllAlunos(){
       List<Aluno> alunos = new ArrayList<Aluno>();
       try{
           Statement stmt = connection.createStatement();
           ResultSet rs = stmt.executeQuery(ALL_ALUNOS);
           while(rs.next()){
              alunos.add(carregarAluno(rs));
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       
       return alunos;
    }
    
    public Aluno getAlunoById(Integer id){
       Aluno aluno = new Aluno();
       try{
           PreparedStatement ps = connection.prepareStatement(ALUNO_BY_ID);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           aluno = carregarAluno(rs);
       }catch(SQLException e){
           e.printStackTrace();
       }
       return aluno;
    }
    
    public List<Aluno> getAlunosByCurso(Integer idCurso){
        List<Aluno> alunos = new ArrayList<Aluno>();
        try{
           PreparedStatement ps = connection.prepareStatement(ALUNOS_BY_CURSO);
           ps.setInt(1, idCurso);
           ResultSet rs = ps.executeQuery();
            while(rs.next()){
              alunos.add(carregarAluno(rs));
           }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return alunos;
    }
    
    
   private Aluno carregarAluno(ResultSet rs) throws SQLException{
      Aluno aluno = new Aluno();
      aluno.setId(rs.getInt("ID"));
      aluno.setNome(rs.getString("NOME"));
      aluno.setDataNascimento(rs.getDate("DT_NASCIMENTO"));
      aluno.setCpf(rs.getString("CPF"));
      aluno.setIdCurso(rs.getInt("ID_CURSO"));
      return aluno;
   
   }
      
      
     
    
}
