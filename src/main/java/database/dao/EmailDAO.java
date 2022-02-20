package database.dao;

import database.CRUD;
import datamodel.EmailComSenha;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.SQL_ERROR_LOG;

public class EmailDAO implements CRUD<EmailComSenha, String> {

    private Connection con;
    private String munId;
    private VinculoDAO v;
    
    public EmailDAO(Connection con, long munId){
        this.con = con;
        this.munId = String.valueOf(munId);
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
    private String createSql(EmailComSenha dados, String sql){
        sql = sql.replaceFirst("<T>", "'" + dados.getEmail() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getSenha() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getTipo() + "'");
        sql = sql.replaceFirst("<T>", munId);
        return sql;
    }
    
    private EmailComSenha fillEmail(ResultSet rs) throws SQLException{
        EmailComSenha e = new EmailComSenha();
        
        e.setId(rs.getLong("id"));
        e.setEmail(rs.getString("email"));
        e.setSenha(rs.getString("senha"));
        e.setTipo(rs.getString("tipo"));
        
        return e;
    }
    
    @Override
    public boolean create(EmailComSenha dados) {
        EmailComSenha find = read(dados.getEmail());
        String sql = "INSERT INTO emails (email, senha, tipo, id_municipio) "
                                            + "VALUES (<T>, <T>, <T>, <T>)";
        try{
            if(find == null){
                sql = createSql(dados, sql);
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        }catch(SQLException ex){
            SQL_ERROR_LOG.message("Error in Insert Email!", ex);
        }
        return false;
    }

    @Override
    public EmailComSenha read(String searchValue) {
        String sql = "SELECT * FROM emails WHERE email='" + searchValue + "' AND id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs != null && !rs.isClosed()){
                EmailComSenha e = fillEmail(rs);
                closeStatementAndResultSet(rs, st);
                return e;
            }
            closeStatementAndResultSet(rs, st);
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Email!", ex);
        }
        return null;
    }

    @Override
    public boolean update(EmailComSenha dados) {
        String sql = "UPDATE emails SET email=<T>, senha=<T>, tipo=<T>, id_municipio=<T> "
                + "WHERE id=" + String.valueOf(dados.getId());
        try {
            
            sql = createSql(dados, sql);
                      
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            
            EmailComSenha teste = read(dados.getEmail());
            
            return teste != null && dados.getId() == teste.getId();
            
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Email!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String searchValue) {
        EmailComSenha find = read(searchValue);
        String sql = "DELETE FROM emails WHERE id=<T> AND id_municipio=" + munId;
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getId()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Email!", ex);
        }
        return false;
    }

    @Override
    public ArrayList<EmailComSenha> list() {
        String sql = "SELECT * FROM emails WHERE id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<EmailComSenha> l = new ArrayList<>();
            
            while(rs.next()){
                l.add(fillEmail(rs));
            }
            closeStatementAndResultSet(rs, st);
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Emails!", ex);
        }
        return null;
    }
    
}
