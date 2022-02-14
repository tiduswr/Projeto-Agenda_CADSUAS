package database.dao;

import database.CRUD;
import datamodel.Municipio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.SQL_ERROR_LOG;

public class MunicipioDAO implements CRUD<Municipio, String>{
    //Passar municipio-uf como parametro unico de checagem
    
    private Connection con;
    
    public MunicipioDAO(Connection con){
        this.con = con;
    }
    
    private String createSql(Municipio data, String sql){
        sql = sql.replaceFirst("<T>", "'" + data.getNome() + "'");
        sql = sql.replaceFirst("<T>", "'" + data.getUf() + "'");
        sql = sql.replaceFirst("<T>", "'" + data.getIconPath() + "'");
        return sql;
    }
    
    @Override
    public boolean create(Municipio dados) {
        Municipio find = read(dados.getNome() + "-" + dados.getUf());
        String sql = "INSERT INTO municipios (nome, uf, iconPath) VALUES (<T>, <T>, <T>)";
        sql = createSql(dados, sql);
        
        try {
            if(find == null){
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in Insert Municipio!", ex);
        }
        return false;
    }

    @Override
    public Municipio read(String key) {
        String sql = "SELECT * FROM municipios WHERE nome=<T> AND uf=<T>";
        
        try {
            String splited[] = key.split("-");
            
            if(splited.length > 1){
                sql = sql.replaceFirst("<T>", "'" + splited[0] + "'");
                sql = sql.replaceFirst("<T>", "'" + splited[1] + "'");
                Statement st = con.createStatement();
                
                ResultSet rs = st.executeQuery(sql);
                
                if(rs != null && !rs.isClosed()){
                    Municipio m = new Municipio();

                    m.setId(rs.getLong("id"));
                    m.setNome(rs.getString("nome"));
                    m.setUf(rs.getString("uf"));
                    m.setIconPath(rs.getString("iconPath"));
                    
                    closeStatementAndResultSet(rs, st);
                    
                    return m;
                }
                
                closeStatementAndResultSet(rs, st);
                
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Municipio!", ex);
        }
        
        return null;
    }

    @Override
    public boolean update(Municipio dados) {
        String sql = "UPDATE municipios SET nome=<T>, uf=<T> WHERE id=<T>";
        
        try {
            sql = createSql(dados, sql);
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            Municipio teste = read(dados.getNome() + "-" + dados.getUf());
            return teste != null && dados.getId() == teste.getId();
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Municipio!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String key) {
        Municipio find = (Municipio) read(key);
        
        String sql = "DELETE FROM municipios WHERE id=<T>";
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getId()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Municipio!", ex);
        }
        
        return false;
    }

    @Override
    public ArrayList<Municipio> list() {
        String sql = "SELECT * FROM municipios";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Municipio> l = new ArrayList<>();
            
            while(rs.next()){
                Municipio m = new Municipio();
                
                m.setId(rs.getLong("id"));
                m.setNome(rs.getString("nome"));
                m.setUf(rs.getString("uf"));
                
                l.add(m);
            }
            closeStatementAndResultSet(rs, st);
            
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Municipio!", ex);
        }
        return null;
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
}
