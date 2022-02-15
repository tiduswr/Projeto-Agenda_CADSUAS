package datamodel;

import java.util.ArrayList;

public class Equipamento {
    private long idDatabase;
    private String numIdentificador;
    private String nome;
    private Endereco endereco;
    private Telefone fone;
    private String email;
    private EquipamentoTipo tipo;
    private ArrayList<Vinculo> funcionarios;
    
    public Equipamento(long idDatabase, String numIdentificador, String nome, Endereco endereco, Telefone fone, String email, 
            EquipamentoTipo tipo) {
        this.idDatabase = idDatabase;
        this.numIdentificador = numIdentificador;
        this.nome = nome;
        this.endereco = endereco;
        this.fone = fone;
        this.email = email;
        this.funcionarios = new ArrayList<>();
        this.tipo = tipo;
    }
    
    public Equipamento(){
        this.funcionarios = new ArrayList<>();
    }
    
    public String getNumIdentificador() {
        return numIdentificador;
    }

    public void setNumIdentificador(String numIdentificador) {
        this.numIdentificador = numIdentificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getFone() {
        return fone;
    }

    public void setFone(Telefone fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdDatabase() {
        return idDatabase;
    }

    public void setIdDatabase(long idDatabase) {
        this.idDatabase = idDatabase;
    }
    
    public boolean addFuncionarioVinculo(Vinculo f){
        Vinculo teste = this.getFuncionarioVinculo(f.getCpf());
        if(teste == null){
            return this.funcionarios.add(f);
        }else{
            return false;
        }
    }
    
    public boolean removeFuncionarioVinculo(String cpf){
        for(Vinculo f : this.funcionarios){
            if(f.getCpf().equalsIgnoreCase(cpf)){
                return this.funcionarios.remove(f);
            }
        }
        return false;
    }
    
    public Vinculo getFuncionarioVinculo(String cpf){
        for(Vinculo f : this.funcionarios){
            if(f.getCpf().equalsIgnoreCase(cpf)){
                return f;
            }
        }
        return null;
    }

    public ArrayList<Vinculo> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Vinculo> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public EquipamentoTipo getTipo() {
        return tipo;
    }

    public void setTipo(EquipamentoTipo tipo) {
        this.tipo = tipo;
    }
    
}
