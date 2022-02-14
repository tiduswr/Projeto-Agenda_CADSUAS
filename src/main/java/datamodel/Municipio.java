package datamodel;

public class Municipio {
    private long id;
    private String nome, uf, iconPath;

    public Municipio(long id, String nome, String uf, String iconPath) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.iconPath = iconPath;
    }
    
    public Municipio(){}
    
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
    
}
