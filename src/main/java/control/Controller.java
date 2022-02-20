package control;

import datamodel.*;
import database.*;
import org.json.JSONObject;
import util.Validator;

public class Controller{
    private Municipio munLogged;
    
    public Controller(){
        munLogged = new Municipio();
    }

    public Municipio getMunicipioLogged() {
        return munLogged;
    }
    
    public String inserirContaBancaria(String json){
        String jsonTest = Validator.contaCorrenteIsValid(json);
        
        if(jsonTest == null){
            JSONObject j = new JSONObject(json);
        }else{
            return jsonTest;
        }
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
