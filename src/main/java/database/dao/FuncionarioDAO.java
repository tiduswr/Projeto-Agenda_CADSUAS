package database.dao;

import database.CRUD;
import datamodel.Endereco;
import datamodel.Funcionario;
import datamodel.RG;
import datamodel.Telefone;
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

public class FuncionarioDAO implements CRUD<Funcionario, String>{
    
    private Connection con;
    private String munId;
    
    public FuncionarioDAO(Connection con, long munId){
        this.con = con;
        this.munId = String.valueOf(munId);
    }
    
    public String createSql(Funcionario dados, String sql){
        sql = sql.replaceFirst("<T>", "'" + dados.getNome() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getCpf() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getEscolaridade() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getProfissao() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getEmail() + "'");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sql = sql.replaceFirst("<T>", "'" + sdf.format(dados.getDtNascimento()) + "'");
        
        sql = sql.replaceFirst("<T>", "'" + dados.getRg().getNumero() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getRg().getOg() + "'");
        sql = sql.replaceFirst("<T>", "'" + dados.getRg().getUf() + "'");
        sql = sql.replaceFirst("<T>", "'" + sdf.format(dados.getRg().getDtEmissao()) + "'");
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
    
    private Funcionario fillFuncionario(ResultSet rs) throws SQLException, ParseException{
        Funcionario c = new Funcionario();
               
        c.setIdDatabase(rs.getLong("id"));
        c.setNome(rs.getString("nome"));
        c.setCpf(rs.getString("cpf"));
        c.setEscolaridade(rs.getString("escolaridade"));
        c.setProfissao(rs.getString("profissao"));
        c.setEmail(rs.getString("email"));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        c.setDtNascimento(sdf.parse(rs.getString("dt_nascimento")));
        
        RG r = new RG();
        r.setNumero(rs.getString("num_rg"));
        r.setOg(rs.getString("og_rg"));
        r.setUf(rs.getString("uf_rg"));
        r.setDtEmissao(sdf.parse(rs.getString("dt_emissao_rg")));
        c.setRg(r);
        
        Telefone f = new Telefone(rs.getInt("ddd_telefone"), rs.getString("num_telefone"));
        c.setFone(f);
        
        Endereco e = new Endereco();
        e.setNumCasa(rs.getInt("num_endereco"));
        e.setRua(rs.getString("rua_endereco"));
        e.setBairro(rs.getString("bairro_endereco"));
        e.setCidade(rs.getString("cidade_endereco"));
        e.setEstado("estado_endereco");
        c.setEndereco(e);
        
        return c;
    }
    
    private void closeStatementAndResultSet(ResultSet rs, Statement st) throws SQLException{
        if(rs != null && !rs.isClosed()) rs.close();
        if(st != null && !st.isClosed()) st.close();
    }
    
    @Override
    public boolean create(Funcionario dados) {
        Funcionario find = read(dados.getCpf());
        String sql = "INSERT INTO funcionarios (nome, cpf, escolaridade, profissao, email, dt_nascimento, num_rg, og_rg, "
                                            + "uf_rg, dt_emissao_rg, ddd_telefone, num_telefone, num_endereco, rua_endereco, "
                                            + "bairro_endereco, cidade_endereco, estado_endereco, id_municipio) "
                                            + "VALUES (<T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, <T>, "
                                            + "<T>, <T>, <T>, <T>, <T>)";

        try {
            if(find == null){
                sql = createSql(dados, sql);
                
                Statement st = con.createStatement();
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in Insert Funcionario!", ex);
        }
        return false;
    }

    @Override
    public Funcionario read(String searchValue) {
        String sql = "SELECT * FROM funcionarios WHERE cpf=" + searchValue + " AND id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs != null && !rs.isClosed()){
                Funcionario f = fillFuncionario(rs);
                closeStatementAndResultSet(rs, st);
                return f;
            }
            closeStatementAndResultSet(rs, st);
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in read Funcionario!", ex);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Funcionario dados) {
        String sql = "UPDATE funcionarios SET nome=<T>, cpf=<T>, escolaridade=<T>, profissao=<T>, email=<T>, "
                + "dt_nascimento=<T>, num_rg=<T>, og_rg=<T>, uf_rg=<T>, dt_emissao_rg=<T>, ddd_telefone=<T>, "
                + "num_telefone=<T>, num_endereco=<T>, rua_endereco=<T>, bairro_endereco=<T>, cidade_endereco=<T>, "
                + "estado_endereco=<T> WHERE id_municipio=<T> AND id=" + String.valueOf(dados.getIdDatabase());
        try {
            
            sql = createSql(dados, sql);
                      
            Statement st = con.createStatement();
            st.execute(sql);
            closeStatementAndResultSet(null, st);
            
            Funcionario teste = read(dados.getCpf());
            
            return teste != null && dados.getIdDatabase() == teste.getIdDatabase();
            
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in update Conta!", ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(String cpf) {
        Funcionario find = read(cpf);
        String sql = "DELETE FROM funcionarios WHERE id=<T>";
        
        try {
            if(find != null){
                sql = sql.replaceFirst("<T>",String.valueOf(find.getIdDatabase()));
                Statement st = con.createStatement();
                
                st.execute(sql);
                closeStatementAndResultSet(null, st);
                return true;
            }
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in delete Funcionario!", ex);
        }
        return false;
    }

    @Override
    public ArrayList<Funcionario> list() {
        String sql = "SELECT * FROM funcionarios WHERE id_municipio=" + munId;
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Funcionario> l = new ArrayList<>();
            
            while(rs.next()){
                l.add(fillFuncionario(rs));
            }
            closeStatementAndResultSet(rs, st);
            return l;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in create list of Funcionario!", ex);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
