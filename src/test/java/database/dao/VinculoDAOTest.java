package database.dao;

import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import datamodel.Cargo;
import datamodel.Endereco;
import datamodel.Equipamento;
import datamodel.EquipamentoTipo;
import datamodel.Funcionario;
import datamodel.Municipio;
import datamodel.RG;
import datamodel.Telefone;
import datamodel.Vinculo;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VinculoDAOTest {
    
    private VinculoDAO v;
    private MunicipioDAO mun;
    
    private Equipamento ed;
    private Funcionario fd;
    private Municipio md;
    
    private DatabaseConnect c;
    private Class cl = VinculoDAO.class;
    
    public VinculoDAOTest() {
        System.out.println("############ CONNECTING ON DATABASE\n");
        c = new SQLiteConnection();
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        testInstances();
        
        System.out.println("############# END OF CONNECTION\n");
    }
    
    private void testInstances(){
        //Adiciona municipio para testes
        System.out.println("!!!!! INSERTING MODEL MUNICIPIO");
        mun = new MunicipioDAO(c.getConnection());
        
        md = new Municipio(-1,"Teste", "tt", null);
        mun.create(md);
        long munId = mun.read("Teste-tt").getId();
        md.setId(munId);
        
        //Adiciona Equipamento para testes
        System.out.println("!!!!! INSERTING MODEL EQUIPAMENTO");
        EquipamentoDAO t = new EquipamentoDAO(c.getConnection(), munId);
        ed = new Equipamento();
        
        ed.setEmail("emailteste");
        ed.setEndereco(new Endereco("0","0","0","0",12));
        ed.setFone(new Telefone(83, "99999"));
        ed.setIdDatabase(-1);
        ed.setNome("OLA MUNDO");
        ed.setNumIdentificador("123456");
        ed.setTipo(EquipamentoTipo.CMAS);
        t.create(ed);
        ed.setIdDatabase(t.read(ed.getNumIdentificador() + "-" + String.valueOf(ed.getTipo().getValue())).getIdDatabase());
        
        //Adiciona pessoa para testes
        System.out.println("!!!!! INSERTING MODEL FUNCIONARIO");
        FuncionarioDAO f = new FuncionarioDAO(c.getConnection(), munId);
        fd = new Funcionario();
        
        fd.setCpf("12345678910");
        fd.setDtNascimento(new Date());
        fd.setEmail("ola@gmail");
        fd.setEndereco(new Endereco("0","0","0","0",12));
        fd.setEscolaridade("alguma");
        fd.setFone(new Telefone(83, "99999"));
        fd.setNome("harllem");
        fd.setProfissao("alguma");
        fd.setRg(new RG("0", "0", "0", new Date()));
        f.create(fd);
        fd.setIdDatabase(f.read(fd.getCpf()).getIdDatabase());
        
        System.out.println("!!!!! CREATING VINCULO DAO");
        v = new VinculoDAO(c.getConnection(), md.getId(), ed.getIdDatabase());
        
    }
    
    @Test
    public void testCreate() {
        System.out.println("\n############# TEST OF CREATE");
        
        Vinculo teste = new Vinculo();
        
        teste.setCpf(fd.getCpf());
        teste.setIdEquip(ed.getIdDatabase());
        teste.setCargo(Cargo.COORDENADOR);
        
        assertTrue(v.create(teste), "Erro no teste create() da " + cl);
    }
    
    @Test
    public void testRead() {
        System.out.println("############# TEST OF READ");
        assertNotNull(v.read(fd.getCpf()), "Erro no teste read() da " + cl);
    }
    
    @Test
    public void testUpdate() {
        System.out.println("############# TEST OF UPDATE");
        
        Vinculo teste = new Vinculo();
        
        teste.setCpf(fd.getCpf());
        teste.setIdEquip(ed.getIdDatabase());
        teste.setCargo(Cargo.SECRETARIO);
        teste.setId(v.read(fd.getCpf()).getId());
        
        assertTrue(v.update(teste), "Erro no teste update() da " + cl);
    }
    
    @Test
    public void testList() {
        System.out.println("############# TEST OF LIST");
        System.out.println("\nPrintando lista:\n");
        v.list().forEach(e -> {
            
            System.out.println(e.getCpf() + " -> " + e.getCargo().toString());
        
        });
        System.out.println("\n");
        assertNotNull(v.list(), "Erro no teste list() da " + cl);;
    }
    
    @Test
    public void testDelete() {
        System.out.println("############# TEST OF DELETE\n");
        assertTrue(v.delete(fd.getCpf()), "Erro no teste delete() da " + cl);
        mun.delete("Teste-tt");
        c.closeConnection();
    }
    
}
