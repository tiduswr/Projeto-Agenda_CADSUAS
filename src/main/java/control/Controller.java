package control;

import database.SQLiteConnection;
import database.dao.ContaBancariaDAO;
import datamodel.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import util.DataValidator;

public class Controller{
    private Municipio munLogged;
    private SQLiteConnection sql;
    
    public Controller(){
        sql = new SQLiteConnection();
    }
    
    public String getMunicipioLoggedAsJSON() {
        return munLogged.toJson().toString();
    }
    
    public boolean isLogged(){
        return munLogged != null;
    }
    
    private String messageNotLogged(){
        Message m = new Message("O municipio não esta Logado!", "Faça login no Municipio para continuar!", "error", "municipio");
        return m.toJson().toString();
    }
    
    private String checkJson(String dataValidatorArray){
        JSONArray obj = new JSONArray(dataValidatorArray);
        
        for(Object e : obj){
            Message m = new Message(e.toString());
            if(m.getType() != null && !m.getType().equalsIgnoreCase("noterror")){
                return obj.toString();
            }
        }
        return null;
    }
    
    public String inserirContaBancaria(String json){
        if(!this.isLogged()) return messageNotLogged();
        
        String test = checkJson(DataValidator.contaIsValid(json));
        
        if(test == null){    
            ContaBancariaDAO db = new ContaBancariaDAO(sql.getConnection(), munLogged.getId());
            ContaBancaria cc = new ContaBancaria(json);
            
            if(db.create(cc)){
                Message m = new Message("Conta Inserida!", "A conta foi inserida com Sucesso!", "done", "null");
                JSONArray response = new JSONArray();
                response.put(new JSONObject(m.toString()));

                return response.toString();
            }else{
                Message m = new Message("Conta Não Inserida!", "Essa conta ja existe no Banco de Dados!", "error", "conta");
                JSONArray response = new JSONArray();
                response.put(new JSONObject(m.toString()));

                return response.toString();
            }
        }else{
            return test;
        }
    }
    
    public String atualizarContaBancaria(String json){
        if(!this.isLogged()) return messageNotLogged();
        
        String test = checkJson(DataValidator.contaIsValid(json));
        
        if(test == null){    
            ContaBancariaDAO db = new ContaBancariaDAO(sql.getConnection(), munLogged.getId());
            ContaBancaria cc = new ContaBancaria(json);
            
            if(db.update(cc)){
                Message m = new Message("Conta Atualizada!", "A conta foi Atualizada com Sucesso!", "done", "null");
                JSONArray response = new JSONArray();
                response.put(new JSONObject(m.toString()));

                return response.toString();
            }else{
                Message m = new Message("Conta Não Atualizada!", "Não foi possivel atualizar a conta!", "error", "conta");
                JSONArray response = new JSONArray();
                response.put(new JSONObject(m.toString()));

                return response.toString();
            }
        }else{
            return test;
        }
    }
    
    public String excluirContaBancaria(String agencia, String conta){
        if(!this.isLogged()) return messageNotLogged();
        
        ContaBancariaDAO db = new ContaBancariaDAO(sql.getConnection(), munLogged.getId());
        ContaBancaria cc = db.read(agencia + "-" + conta);

        if(cc != null && db.delete(cc.getAgencia() + "-" + cc.getNum())){
            Message m = new Message("Conta Excluida!", "A conta foi excluida com Sucesso!", "done", "null");
            JSONArray response = new JSONArray();
            response.put(new JSONObject(m.toString()));

            return response.toString();
        }else{
            Message m = new Message("Conta Não Excluida!", "Não foi possivel Excluir essa Conta!", "error", "conta");
            JSONArray response = new JSONArray();
            
            if(cc == null){
                m.setMessage("Conta não encontrada no Banco de Dados!");
            }
            response.put(new JSONObject(m.toString()));

            return response.toString();
        }
    }
    
    public String lerContaBancaria(String agencia, String conta){
        if(!this.isLogged()) return messageNotLogged();
        
        ContaBancariaDAO db = new ContaBancariaDAO(sql.getConnection(), munLogged.getId());
        ContaBancaria cc = db.read(agencia + "-" + conta);

        if(cc != null){
            return cc.toJson().toString();
        }else{
            return null;
        }
    }
    
    public String listarContasBancarias(){
        if(!this.isLogged()) return messageNotLogged();
        
        ContaBancariaDAO db = new ContaBancariaDAO(sql.getConnection(), munLogged.getId());
        ArrayList<ContaBancaria> l = db.list();
        JSONArray arr = new JSONArray();
        
        l.forEach(e -> {
            arr.put(e.toJson());
        });
        
        if(!arr.isEmpty()){
            return arr.toString();
        }else{
            return null;
        }
    }
    
}
