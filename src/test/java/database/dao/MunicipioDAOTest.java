package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.Municipio;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MunicipioDAOTest {
    
    private MunicipioDAO t;
    private DatabaseConnect c;
    private Class cl = MunicipioDAO.class;
    
    public MunicipioDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE");
        c = new SQLiteConnection();
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        t = new MunicipioDAO(c.getConnection());
        System.out.println("############# END OF CONNECTION\n");
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        assertTrue(t.create(new Municipio(-1,"Teste", "tt", null)), "Erro no teste create() da " + cl);
    }
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(t.read("Teste-tt"), "Erro no teste read() da " + cl);
    }
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        assertTrue(t.update(new Municipio(t.read("Teste-tt").getId(),"TesteUpdated", "tt", null)), "Erro no teste update() da " + cl);
    }
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        t.list().forEach(e -> {
            
            System.out.println(e.getId() + " -> " + e.getNome());
        
        });
        System.out.println("\n");
        assertNotNull(t.list(), "Erro no teste list() da " + cl);
    }
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(t.delete("TesteUpdated-tt"), "Erro no teste delete() da " + cl);
        c.closeConnection();
    }
    
}
