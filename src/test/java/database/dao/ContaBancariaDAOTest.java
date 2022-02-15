package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.ContaBancaria;
import datamodel.Municipio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaDAOTest {
    
    private ContaBancariaDAO t;
    private MunicipioDAO mun;
    private DatabaseConnect c;
    private Class cl = ContaBancariaDAO.class;
    
    public ContaBancariaDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE");
        c = new SQLiteConnection();
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        mun = new MunicipioDAO(c.getConnection());
        mun.create(new Municipio(-1,"Teste", "tt", null));
        t = new ContaBancariaDAO(c.getConnection(), mun.read("Teste-tt").getId());
        
        System.out.println("############# END OF CONNECTION\n");
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        
        assertTrue(t.create(new ContaBancaria(-1, "555", "CC HARLLEM", "329035", "FEAS", true))
                , "Erro no teste create() da " + cl);
    }
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(t.read("555-329035"), "Erro no teste read() da " + cl);
    }
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        
        assertTrue(t.update(new ContaBancaria(t.read("555-329035").getId(), "511", "CC HARLLEM", "444555", "FEAS", true))
                , "Erro no teste create() da " + cl);
    }
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        t.list().forEach(e -> {
            
            System.out.println(e.getAgencia() + "-" + e.getNum() + " -> " + e.getNome());
        
        });
        System.out.println("\n");
        assertNotNull(t.list(), "Erro no teste list() da " + cl);;
    }
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(t.delete("511-444555"), "Erro no teste delete() da " + cl);
        mun.delete("Teste-tt");
        c.closeConnection();
    }
    
}
