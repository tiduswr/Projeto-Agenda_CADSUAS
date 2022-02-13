package database.dao;

import database.CRUD;
import datamodel.Equipamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.SQL_ERROR_LOG;

public class EquipamentoDAO implements CRUD<Equipamento, String>{
    
    private Connection con;
    private String munId;
    private VinculoDAO v;
    public EquipamentoDAO(Connection con, long munId){
        this.con = con;
        this.munId = String.valueOf(munId);
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
    private String createSql(Equipamento dados, String sql){
        sql = sql.replaceFirst("<T>", "'" + dados.getNumIdentificador() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getNome() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getEmail() + "'");
        sql = sql.replaceFirst("<T>", String.valueOf(dados.getFone().getDdd()));
        sql = sql.replaceFirst("<T>", "'" + dados.getFone().getNumero() + "'");
        sql = sql.replaceFirst("<T>", String.valueOf(dados.getEndereco().getNumCasa()));
        sql = sql.replaceFirst("<T>", "'" + dados.getEndereco().getRua() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getEndereco().getBairro() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getEndereco().getCidade() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getEndereco().getEstado() + "'");
        sql = sql.replaceFirst("<T>", munId);
        return sql;
    }
    
    @Override
    public boolean create(Equipamento dados) {
        Equipamento find = read(dados.getNumIdentificador());
        String sql = "INSERT INTO equipamentos (numIdentificador, nome, email, ddd_telefone, num_telefone, num_endereco, "
                                            + "rua_endereco, bairro_endereco, cidade_endereco, estado_endereco, "
                                            + "id_municipio VALUES (<T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, "
                                            + "<T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>)";
        try {
            if(find == null){
                sql = createSql(dados, sql);
                
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in Insert Equipamentos!", ex);
        }
        return false;
    }

    @Override
    public Equipamento read(String searchValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Equipamento dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equipamento> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
