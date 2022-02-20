package database.dao;

import database.CRUD;
import datamodel.Lei;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SQL_ERROR_LOG;

public class LeiDAO implements CRUD<Lei, String>{
    
    private Connection con;
    private String munId;
    private VinculoDAO v;
    
    public LeiDAO(Connection con, long munId){
        this.con = con;
        this.munId = String.valueOf(munId);
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
    private String createSql(Lei dados, String sql){
        sql = sql.replaceFirst("<T>", String.valueOf(dados.getNum()));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sql = sql.replaceFirst("<T>", "'" + sdf.format(dados.getData()) + "'");
        
        sql = sql.replaceFirst("<T>", "'" + dados.getAgrupamento() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getDescricao() + "'");
        sql = sql.replaceFirst("<T>", munId);
        return sql;
    }
    
    private Lei fillLei(ResultSet rs) throws SQLException, ParseException{
        Lei l = new Lei();
        
        l.setId(rs.getLong("id"));
        l.setNum(rs.getInt("num"));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        l.setData(sdf.parse(rs.getString("data")));
        
        l.setAgrupamento(rs.getString("agrupamento"));
        l.setDescricao(rs.getString("descricao"));
        
        return l;
    }
    
    @Override
    public boolean create(Lei dados) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Lei find = read(String.valueOf(dados.getNum()) + "-" + sdf.format(dados.getData()));
        
        String sql = "INSERT INTO leis (num, data, agrupamento, descricao, id_municipio)"
                                            + "VALUES (<T>, <T>, <T>, <T>, <T>)";

        try {
            if(find == null){
                sql = createSql(dados, sql);
                
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in Insert Lei!", ex);
        }
        return false;
    }

    @Override
    public Lei read(String searchValue) {
        String sql = "SELECT * FROM leis WHERE num=<T> AND data=<T> AND id_municipio=<T>";
        
        try {
            String splited[] = searchValue.split("-");
            
            if(splited.length > 1){
                sql = sql.replaceFirst("<T>", "'" + splited[0] + "'");
                sql = sql.replaceFirst("<T>", "'" + splited[1] + "'");
                sql = sql.replaceFirst("<T>", munId);
                Statement st = con.createStatement();
                    
                ResultSet rs = st.executeQuery(sql);
                if(rs != null && !rs.isClosed()){
                    Lei l = fillLei(rs);
                    closeStatementAndResultSet(rs, st);
                    return l;
                }
                closeStatementAndResultSet(rs, st);
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Lei!", ex);
        } catch (ParseException ex) {
            Logger.getLogger(LeiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Lei dados) {
        String sql = "UPDATE leis SET num=<T>, data=<T>, agrupamento=<T>, descricao=<T>" 
                    + " WHERE id=" + String.valueOf(dados.getId());
        try {
            
            sql = createSql(dados, sql);
                      
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Lei teste = read(dados.getNum() + "-" + sdf.format(dados.getData()));
            
            return teste != null && dados.getId() == teste.getId();
            
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Lei!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String searchValue) {
        Lei find = read(searchValue);
        String sql = "DELETE FROM leis WHERE id=<T> AND id_municipio=" + munId;
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getId()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Lei!", ex);
        }
        return false;
    }

    @Override
    public ArrayList<Lei> list() {
        String sql = "SELECT * FROM leis WHERE id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Lei> l = new ArrayList<>();
            
            while(rs.next()){
                l.add(fillLei(rs));
            }
            closeStatementAndResultSet(rs, st);
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Lei!", ex);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
