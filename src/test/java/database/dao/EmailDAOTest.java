package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.EmailComSenha;
import datamodel.Municipio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailDAOTest {
    
    private EmailDAO e;
    private MunicipioDAO mun;
    
    private DatabaseConnect c;
    private Class cl = EquipamentoDAO.class;
    
    public EmailDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE");
        c = new SQLiteConnection("db/dbTeste.db");
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        mun = new MunicipioDAO(c.getConnection());
        mun.create(new Municipio(-1,"Teste", "tt", null));
        
        e = new EmailDAO(c.getConnection(), mun.read("Teste-tt").getId());
        
        System.out.println("############# END OF CONNECTION\n");
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        
        EmailComSenha d = new EmailComSenha();
        
        d.setEmail("teste@gmail.com");
        d.setSenha("@senha");
        d.setTipo("Adjunta");
        
        assertTrue(e.create(d), "Erro no teste create() da " + cl);
    }
    
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(e.read("teste@gmail.com"), "Erro no teste read() da " + cl);
    }
    
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        
        EmailComSenha d = new EmailComSenha();
        
        d.setEmail("update_teste@gmail.com");
        d.setSenha("@senha");
        d.setTipo("Adjunta");
        d.setId(e.read("teste@gmail.com").getId());
        
        assertTrue(e.update(d), "Erro no teste update() da " + cl);
    }
    
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        e.list().forEach(em -> {
            
            System.out.println(em.getEmail() + ": " + em.getSenha());
        
        });
        System.out.println("\n");
        assertNotNull(e.list(), "Erro no teste list() da " + cl);;
    }
    
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(e.delete("update_teste@gmail.com"), "Erro no teste delete() da " + cl);
        mun.delete("Teste-tt");
        c.closeConnection();
    }
    
}
