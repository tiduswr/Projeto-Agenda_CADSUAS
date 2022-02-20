package datamodel;

public class Vinculo {
    private long id, idEquip;
    private String cpf;
    private Cargo cargo;

    public Vinculo(int id, int idEquip, String cpf, Cargo cargo) {
        this.id = id;
        this.idEquip = idEquip;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Vinculo(){}
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(long idEquip) {
        this.idEquip = idEquip;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
}
