import database.CreateDataBase;
import database.DatabaseConnect;
import database.SQLiteConnection;
import database.dao.ContaBancariaDAO;
import database.dao.FuncionarioDAO;
import database.dao.MunicipioDAO;
import datamodel.ContaBancaria;
import datamodel.Endereco;
import datamodel.Funcionario;
import datamodel.Municipio;
import datamodel.RG;
import datamodel.Telefone;
import java.util.Date;

public class main {

    public static void main(String args[]) {
        DatabaseConnect c = new SQLiteConnection();
        c.connect();
        CreateDataBase.createDataBaseAndTables(c);
        
        MunicipioDAO m = new MunicipioDAO(c.getConnection());
        System.out.println(m.create(new Municipio(-1,"Coremas", "PB", null)));
        
        ContaBancariaDAO cc = new ContaBancariaDAO(c.getConnection(), m.read("Coremas-PB").getId());
        System.out.println(cc.create(new ContaBancaria(-1, "555", "CC HARLLEM", "329035", "FEAS", true)));
        
        System.out.println(cc.update(new ContaBancaria(1, "543", "CC HARLLEM", "12329035", "FEAS", true)));
        
        FuncionarioDAO ff = new FuncionarioDAO(c.getConnection(), m.read("Coremas-PB").getId());
        Funcionario dados = new Funcionario();
        dados.setCpf("12312312312");
        dados.setDtNascimento(new Date());
        dados.setEmail("ola@gmail");
        dados.setEndereco(new Endereco("0","0","0","0",12));
        dados.setEscolaridade("alguma");
        dados.setFone(new Telefone(83, "99999"));
        dados.setNome("harllem");
        dados.setProfissao("alguma");
        dados.setRg(new RG("0", "0", "0", new Date()));
        System.out.println(ff.create(dados));
        dados.setNome("Neto");
        dados.setIdDatabase(1);
        ff.update(dados);
        
        c.closeConnection();
        
    }
    
}
