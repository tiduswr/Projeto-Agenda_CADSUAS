package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.Endereco;
import datamodel.Funcionario;
import datamodel.Municipio;
import datamodel.RG;
import datamodel.Telefone;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FuncionarioDAOTest {
    
    private FuncionarioDAO t;
    private MunicipioDAO mun;
    private DatabaseConnect c;
    private Class cl = FuncionarioDAO.class;
    
    public FuncionarioDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE");
        c = new SQLiteConnection("db/dbTeste.db");
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        mun = new MunicipioDAO(c.getConnection());
        mun.create(new Municipio(-1,"Teste", "tt", null));
        t = new FuncionarioDAO(c.getConnection(), mun.read("Teste-tt").getId());
        
        System.out.println("############# END OF CONNECTION\n");
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        
        Funcionario dados = new Funcionario();
        
        dados.setCpf("12345678910");
        dados.setDtNascimento(new Date());
        dados.setEmail("ola@gmail");
        dados.setEndereco(new Endereco("0","0","0","0",12));
        dados.setEscolaridade("alguma");
        dados.setFone(new Telefone(83, "99999"));
        dados.setNome("harllem");
        dados.setProfissao("alguma");
        dados.setRg(new RG("0", "0", "0", new Date()));
        
        assertTrue(t.create(dados), "Erro no teste create() da " + cl);
    }
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(t.read("12345678910"), "Erro no teste read() da " + cl);
    }
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        
        Funcionario dados = new Funcionario();
        
        dados.setCpf("12345678910");
        dados.setDtNascimento(new Date());
        dados.setEmail("ola@gmail");
        dados.setEndereco(new Endereco("0","0","0","0",12));
        dados.setEscolaridade("alguma");
        dados.setFone(new Telefone(83, "99999"));
        dados.setNome("nomeUpdated");
        dados.setProfissao("alguma");
        dados.setRg(new RG("0", "0", "0", new Date()));
        dados.setIdDatabase(t.read("12345678910").getIdDatabase());
        
        assertTrue(t.update(dados), "Erro no teste update() da " + cl);
    }
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        t.list().forEach(e -> {
            
            System.out.println(e.getCpf() + " -> " + e.getNome());
        
        });
        System.out.println("\n");
        assertNotNull(t.list(), "Erro no teste list() da " + cl);;
    }
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(t.delete("12345678910"), "Erro no teste delete() da " + cl);
        mun.delete("Teste-tt");
        c.closeConnection();
    }
    
}
