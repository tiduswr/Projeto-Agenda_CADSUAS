package database.dao;

import database.CRUD;
import datamodel.ContaBancaria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.SQL_ERROR_LOG;

public class ContaBancariaDAO implements CRUD<ContaBancaria, String>{
    //Formato de busca agencia-conta    
    
    private Connection con;
    private String munId;
    
    public ContaBancariaDAO(Connection con, long munId){
        this.con = con;
        this.munId = String.valueOf(munId);
    }

    private String createSql(ContaBancaria data, String sql){
        sql = sql.replaceFirst("<T>", "'" + data.getNome() + "'");
        sql = sql.replaceFirst("<T>", "'" + data.getNum() + "'");
        sql = sql.replaceFirst("<T>", "'" + data.getAgencia() + "'");
        sql = sql.replaceFirst("<T>", "'" + data.getTipo() + "'");
        if(data.isActive()){
            sql = sql.replaceFirst("<T>", "1");
        }else{
            sql = sql.replaceFirst("<T>", "0");
        }
        sql = sql.replaceFirst("<T>", munId);
        return sql;
    }
    
    private ContaBancaria fillConta(ResultSet rs) throws SQLException{
        ContaBancaria c = new ContaBancaria();
                
        c.setAgencia(rs.getString("agencia"));
        c.setId(rs.getLong("id"));
        c.setNome("nome");
        c.setNum(rs.getString("numero"));
        c.setStatus(rs.getInt("status") == 1);
        c.setTipo(rs.getString("tipo"));

        return c;
    }
    
    @Override
    public boolean create(ContaBancaria dados) {
        ContaBancaria find = read(dados.getAgencia() + "-" + dados.getNum());
        String sql = "INSERT INTO contas (nome, numero, agencia, tipo, status, id_municipio) " + 
                                            "VALUES (<T>, <T>, <T>, <T>, <T>, <T>)";

        try {
            if(find == null){
                sql = createSql(dados, sql);
                
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in Insert Conta!", ex);
        }
        return false;
    }

    @Override
    public ContaBancaria read(String key) {
        String sql = "SELECT * FROM contas WHERE agencia=<T> AND numero=<T> AND id_municipio=<T>";
        
        try {
            String splited[] = key.split("-");
            
            if(splited.length > 1){
                sql = sql.replaceFirst("<T>", "'" + splited[0] + "'");
                sql = sql.replaceFirst("<T>", "'" + splited[1] + "'");
                sql = sql.replaceFirst("<T>", munId);
                Statement st = con.createStatement();
                    
                ResultSet rs = st.executeQuery(sql);
                if(rs != null && !rs.isClosed()){
                    ContaBancaria cc = fillConta(rs);
                    closeStatementAndResultSet(rs, st);
                    return cc;
                }
                closeStatementAndResultSet(rs, st);
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Conta!", ex);
        }
        return null;
    }

    @Override
    public boolean update(ContaBancaria dados) {
        String sql = "UPDATE contas SET nome=<T>, numero=<T>, agencia=<T>, tipo=<T>, status=<T>, id_municipio=<T> "
                            + "WHERE id=<T>";
        
        try {
            
            sql = createSql(dados, sql);
            sql = sql.replaceFirst("<T>", String.valueOf(dados.getId()));
            
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            
            ContaBancaria teste = read(dados.getAgencia() + "-" + dados.getNum());
            
            return teste != null && dados.getId() == teste.getId();
            
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Conta!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String key) {
        ContaBancaria find = read(key);
        
        String sql = "DELETE FROM contas WHERE id=<T> AND id_municipio=" + munId;
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getId()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Conta!", ex);
        }
        
        return false;
    }

    @Override
    public ArrayList<ContaBancaria> list() {
        String sql = "SELECT * FROM contas WHERE id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<ContaBancaria> l = new ArrayList<>();
            
            while(rs.next()){
                l.add(fillConta(rs));
            }
            closeStatementAndResultSet(rs, st);
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Contas!", ex);
        }
        return null;
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
}
