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
import model.Curso;
import util.DbUtil;

/**
 *
 * @author PC
 */
public class CursoDAO {
    private Connection connection;
    private static final String INSERT_CURSO = "INSERT INTO CURSO(CURSO, DURACAO) VALUES (?,?)";
    private static final String DELETE_CURSO = "DELETE FROM CURSO WHERE ID=?";
    private static final String UPDATE_CURSO = "UPDATE CURSO SET CURSO=?, DURACAO=? WHERE ID=?";
    private static final String ALL_CURSOS = "SELECT * FROM CURSO";
    private static final String CURSO_BY_ID = "SELECT * FROM CURSO WHERE ID=?";
    
    
    public CursoDAO(){
        this.connection = DbUtil.getConection();
    }
    
    public void addCurso(Curso curso){
        try{
            PreparedStatement ps = connection.prepareStatement(INSERT_CURSO);
            ps.setString(1, curso.getCurso());
            ps.setInt(2, curso.getDuracao());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deleteCurso(Integer id){
        try{
            PreparedStatement ps = connection.prepareStatement(DELETE_CURSO);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateCurso(Curso curso){
         try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_CURSO);
            ps.setString(1, curso.getCurso());
            ps.setInt(2, curso.getDuracao());
            ps.setInt(3, curso.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public List<Curso> getAllCursos(){
       List<Curso> cursos = new ArrayList<Curso>();
       try{
           Statement stmt = connection.createStatement();
           ResultSet rs = stmt.executeQuery(ALL_CURSOS);
           while(rs.next()){
              cursos.add(carregarCurso(rs));
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       
       return cursos;
    }
    
    
    public Curso getCursoById(Integer id){
       Curso curso = new Curso();
       try{
           PreparedStatement ps = connection.prepareStatement(CURSO_BY_ID);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           curso = carregarCurso(rs);
       }catch(SQLException e){
           e.printStackTrace();
       }
       return curso;
    }
    
    private Curso carregarCurso(ResultSet rs) throws SQLException{
        Curso curso = new Curso();
        curso.setId(rs.getInt("ID"));
        curso.setCurso(rs.getString("CURSO"));
        curso.setDuracao(rs.getInt("DURACAO"));
        return curso;
    }
}
