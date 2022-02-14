package database.dao;

import database.CRUD;
import datamodel.Cargo;
import datamodel.EquipamentoTipo;
import datamodel.Vinculo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.SQL_ERROR_LOG;

public class VinculoDAO implements CRUD<Vinculo, String>{

    private Connection con;
    private String munId, equipId;
    
    public VinculoDAO(Connection con, long munId, long equipId){
        this.con = con;
        this.munId = String.valueOf(munId);
        this.equipId = String.valueOf(equipId);
    }
    
    private String createSql(Vinculo dados, String sql){
        sql = sql.replaceFirst("<T>", "'" + dados.getCpf() + "'");
        sql = sql.replaceFirst("<T>", String.valueOf(dados.getCargo().getValue()));
        sql = sql.replaceFirst("<T>", String.valueOf(dados.getEquip().getValue()));
        sql = sql.replaceFirst("<T>", equipId);
        sql = sql.replaceFirst("<T>", munId);
        return sql;
    }
    
    private Vinculo fillVinculo(ResultSet rs) throws SQLException{
        Vinculo v = new Vinculo();
        
        v.setId(rs.getLong("id"));
        v.setCpf(rs.getString("cpf"));
        v.setCargo(Cargo.getByInt(rs.getInt("cargo_enum")));
        v.setEquip(EquipamentoTipo.getByInt(rs.getInt("equipamento_enum")));
        v.setIdEquip(rs.getInt("id_equipamento"));
        
        return v;
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
    @Override
    public boolean create(Vinculo dados) {
        Vinculo find = read(dados.getCpf());
        String sql = "INSERT INTO vinculos (cpf, cargo_enum, equipamento_enum, id_equipamento, id_municipio) "
                                            + "VALUES (<T>, <T>, <T>, <T>, <T>)";
        try{
            if(find == null){
                sql = createSql(dados, sql);
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        }catch(SQLException ex){
            SQL_ERROR_LOG.message("Error in Insert Vinculo!", ex);
        }
        return false;
    }

    @Override
    public Vinculo read(String cpf) {
        String sql = "SELECT * FROM vinculos WHERE cpf=" + cpf + " AND id_equipamento=" + equipId + " AND id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs != null && !rs.isClosed()){
                Vinculo f = fillVinculo(rs);
                closeStatementAndResultSet(rs, st);
                return f;
            }
            closeStatementAndResultSet(rs, st);
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Vinculo!", ex);
        }
        return null;
    }

    @Override
    public boolean update(Vinculo dados) {        
        String sql = "UPDATE vinculos SET cpf=<T>, cargo_enum=<T>, equipamento_enum=<T>, id_equipamento=<T>, "
                + "id_municipio=<T> WHERE id_equipamento=" + equipId + " AND id_municipio=" + munId;
        try {
            
            sql = createSql(dados, sql);
                      
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            
            Vinculo teste = read(dados.getCpf());
            
            return teste != null && dados.getId() == teste.getId();
            
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Conta!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String cpf) {
        Vinculo find = read(cpf);
        String sql = "DELETE FROM vinculos WHERE id=<T> AND id_municipio=" + munId;
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getId()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Vinculo!", ex);
        }
        return false;
    }
    
    @Override
    public ArrayList<Vinculo> list() {
        String sql = "SELECT * FROM vinculos WHERE id_equipamento=" + equipId + " AND id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Vinculo> l = new ArrayList<>();
            
            while(rs.next()){
                l.add(fillVinculo(rs));
            }
            closeStatementAndResultSet(rs, st);
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Vinculos!", ex);
        }
        return null;
    }
}
