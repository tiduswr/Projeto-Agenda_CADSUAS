package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.Lei;
import datamodel.Municipio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeiDAOTest {
    
    private LeiDAO l;
    private MunicipioDAO mun;
    
    private DatabaseConnect c;
    private Class cl = EquipamentoDAO.class;
    
    public LeiDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE");
        c = new SQLiteConnection("db/dbTeste.db");
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        mun = new MunicipioDAO(c.getConnection());
        mun.create(new Municipio(-1,"Teste", "tt", null));
        
        l = new LeiDAO(c.getConnection(), mun.read("Teste-tt").getId());
        
        System.out.println("############# END OF CONNECTION\n");
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        
        Lei d = new Lei();
        
        d.setNum(1);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d.setData(sdf.parse("13/04/1998"));
        } catch (ParseException ex) {
            Logger.getLogger(LeiDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        d.setDescricao("Lei do CMAS");
        d.setAgrupamento("CMAS");

        assertTrue(l.create(d), "Erro no teste create() da " + cl);
    }
    
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(l.read("1-13/04/1998"), "Erro no teste read() da " + cl);
    }
    
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        
        Lei d = new Lei();
        
        d.setNum(1);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d.setData(sdf.parse("13/04/1998"));
        } catch (ParseException ex) {
            Logger.getLogger(LeiDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        d.setDescricao("Lei do Fundo Municipal");
        d.setAgrupamento("FMAS");
        d.setId(l.read("1-13/04/1998").getId());
        
        assertTrue(l.update(d), "Erro no teste update() da " + cl);
    }
    
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        l.list().forEach(lei -> {
            
            System.out.println("Lei nº " + lei.getNum() + " de " + lei.getData());
        
        });
        System.out.println("\n");
        assertNotNull(l.list(), "Erro no teste list() da " + cl);;
    }
    
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(l.delete("1-13/04/1998"), "Erro no teste delete() da " + cl);
        mun.delete("Teste-tt");
        c.closeConnection();
    }
    
}
