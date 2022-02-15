package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.ContaBancaria;
import datamodel.Endereco;
import datamodel.Equipamento;
import datamodel.EquipamentoTipo;
import datamodel.Municipio;
import datamodel.Telefone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipamentoDAOTest {
    
    private EquipamentoDAO t;
    private MunicipioDAO mun;
    private DatabaseConnect c;
    private Class cl = EquipamentoDAO.class;
    
    public EquipamentoDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE");
        c = new SQLiteConnection();
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        mun = new MunicipioDAO(c.getConnection());
        mun.create(new Municipio(-1,"Teste", "tt", null));
        
        t = new EquipamentoDAO(c.getConnection(), mun.read("Teste-tt").getId());
        
        System.out.println("############# END OF CONNECTION\n");
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        
        Equipamento dados = new Equipamento();
        
        dados.setEmail("emailteste");
        dados.setEndereco(new Endereco("0","0","0","0",12));
        dados.setFone(new Telefone(83, "99999"));
        dados.setIdDatabase(-1);
        dados.setNome("OLA MUNDO");
        dados.setNumIdentificador("123456");
        dados.setTipo(EquipamentoTipo.CMAS);
        
        assertTrue(t.create(dados), "Erro no teste create() da " + cl);
    }
    
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(t.read("123456-" + String.valueOf(EquipamentoTipo.CMAS.getValue())), "Erro no teste read() da " + cl);
    }
    
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        
        Equipamento dados = new Equipamento();
        
        dados.setEmail("emailteste");
        dados.setEndereco(new Endereco("0","0","0","0",12));
        dados.setFone(new Telefone(83, "99999"));
        dados.setIdDatabase(t.read("123456-" + String.valueOf(EquipamentoTipo.CMAS.getValue())).getIdDatabase());
        dados.setNome("testeUpdated");
        dados.setNumIdentificador("111111111");
        dados.setTipo(EquipamentoTipo.CMAS);
        
        assertTrue(t.update(dados), "Erro no teste update() da " + cl);
    }
    
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        t.list().forEach(e -> {
            
            System.out.println(e.getNumIdentificador() + "-" + e.getTipo() + " -> " + e.getNome());
        
        });
        System.out.println("\n");
        assertNotNull(t.list(), "Erro no teste list() da " + cl);;
    }
    
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(t.delete("111111111-" + String.valueOf(EquipamentoTipo.CMAS.getValue())), "Erro no teste delete() da " + cl);
        mun.delete("Teste-tt");
        c.closeConnection();
    }
    
}
