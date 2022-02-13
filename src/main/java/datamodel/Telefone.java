package datamodel;

public class Telefone {
    private int ddd;
    private String numero;

    public Telefone(int ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }
    
    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getNumeroFormatado(){
        return "(" + String.valueOf(ddd) + ")" + numero.substring(0, 0) + 
                numero.substring(1, 5) + "-" + numero.substring(5,numero.length());
    }
    
}
