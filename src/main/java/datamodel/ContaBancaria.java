package datamodel;

public class ContaBancaria {
    private long id;
    private String nome, agencia, num, tipo;
    private boolean status;

    public ContaBancaria(long id, String agencia, String nome, String num, String tipo, boolean status) {
        this.id = id;
        this.nome = nome;
        this.num = num;
        this.tipo = tipo;
        this.agencia = agencia;
        this.status = status;
    }
    
    public ContaBancaria(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
