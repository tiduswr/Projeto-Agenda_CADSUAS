package datamodel;

import java.util.Date;

public abstract class Pessoa {
    private long idDatabase;
    private String cpf, nome, escolaridade, profissao;
    private RG rg;
    private Date dtNascimento;
    private String email;
    private Telefone fone;
    private Endereco endereco;

    public Pessoa(long idDatabase, String cpf, String nome, String escolaridade, String profissao, RG rg, 
                        Date dtNascimento, String email, Telefone fone, Endereco endereco) {
        this.idDatabase = idDatabase;
        this.cpf = cpf;
        this.nome = nome;
        this.escolaridade = escolaridade;
        this.profissao = profissao;
        this.rg = rg;
        this.dtNascimento = dtNascimento;
        this.email = email;
        this.fone = fone;
        this.endereco = endereco;
    }
    
    public Pessoa(){}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public RG getRg() {
        return rg;
    }

    public void setRg(RG rg) {
        this.rg = rg;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getFone() {
        return fone;
    }

    public void setFone(Telefone fone) {
        this.fone = fone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public long getIdDatabase() {
        return idDatabase;
    }

    public void setIdDatabase(long idDatabase) {
        this.idDatabase = idDatabase;
    }
    
}
