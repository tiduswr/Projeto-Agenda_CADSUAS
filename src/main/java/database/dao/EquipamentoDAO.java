package database.dao;

import database.CRUD;
import datamodel.Endereco;
import datamodel.Equipamento;
import datamodel.Telefone;
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
    
    private Equipamento fillEquipamento(ResultSet rs) throws SQLException{
        Equipamento e = new Equipamento();
        
        e.setEmail(rs.getString("email"));
        e.setIdDatabase(rs.getLong("id"));
        e.setNome(rs.getString("nome"));
        e.setNumIdentificador(rs.getString("numIdentificador"));
        
        Telefone t = new Telefone(rs.getInt("ddd_telefone"), rs.getString(rs.getString("num_telefone")));
        Endereco end = new Endereco();
        
        end.setNumCasa(rs.getInt("num_endereco"));
        end.setRua(rs.getString("rua_endereco"));
        end.setBairro(rs.getString("bairro_endereco"));
        end.setCidade(rs.getString("cidade_endereco"));
        end.setEstado("estado_endereco");
        
        e.setFone(t);
        e.setEndereco(end);
        
        return e;
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
    public Equipamento read(String numIdentificador) {
        String sql = "SELECT * FROM equipamentos WHERE numIdentificador=" + numIdentificador + " AND id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs != null && !rs.isClosed()){
                Equipamento e = fillEquipamento(rs);
                closeStatementAndResultSet(rs, st);
                return e;
            }
            closeStatementAndResultSet(rs, st);
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Equipamentos!", ex);
        }
        
        return null;
    }

    @Override
    public boolean update(Equipamento dados) {
        String sql = "UPDATE funcionarios SET numIdentificador=<T>, nome=<T>, email=<T>, ddd_telefone=<T>, num_telefone=<T>, num_endereco=<T>, "
                                            + "rua_endereco=<T>, bairro_endereco=<T>, cidade_endereco=<T>, estado_endereco=<T>, "
                                            + "WHERE id_municipio=" + munId + " AND id=" + String.valueOf(dados.getIdDatabase());
        try {
            sql = createSql(dados, sql);
                      
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            
            Equipamento teste = read(dados.getNumIdentificador());
            
            return teste != null && dados.getIdDatabase() == teste.getIdDatabase();
            
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Equipamento!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String numIdentificador) {
        Equipamento find = read(numIdentificador);
        String sql = "DELETE FROM equipamentos WHERE numIdentificador=" + numIdentificador + " AND id_municipio=" + munId;
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getIdDatabase()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Equipamentos!", ex);
        }
        return false;
    }

    @Override
    public ArrayList<Equipamento> list() {
        String sql = "SELECT * FROM equipamentos WHERE id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Equipamento> l = new ArrayList<>();
            
            while(rs.next()){
                l.add(fillEquipamento(rs));
            }
            closeStatementAndResultSet(rs, st);
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Equipamentos!", ex);
        }
        return null;
    }


    
}
