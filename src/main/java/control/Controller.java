package control;

import datamodel.*;

public class Controller{
    private Municipio munLogged = null;
    
    public Controller(){}

    public String getMunicipioLoggedAsJSON() {
        return munLogged.toJson().toString();
    }
    
    public boolean isLogged(){
        return munLogged != null;
    }
    
    public String inserirContaBancaria(String json){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String atualizarContaBancaria(String json){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String excluirContaBancaria(int id){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String listarContasBancarias(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
