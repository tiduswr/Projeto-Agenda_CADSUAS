package util;

import datamodel.Message;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataValidator {
    public static String municipioIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        error.put(ParseHelper.testNotEmptyAlphabetic(json, "nome", 100).toJson());
        error.put(ParseHelper.testNotEmptyAlphabetic(json, "uf", 2).toJson());
        error.put(ParseHelper.testNotEmpty(json, "iconPath", 255).toJson());
        
        return error.toString();
    }
    
    public static String leiIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        error.put(ParseHelper.testOnlyNumber(json, "num", -1).toJson());
        error.put(ParseHelper.testIfIsDate(json, "data").toJson());
        error.put(ParseHelper.testNotEmpty(json, "agrupamento", 100).toJson());
        error.put(ParseHelper.testNotEmpty(json, "descricao", 100).toJson());
        
        return error.toString();
    }
    
    public static String contaIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        error.put(ParseHelper.testNotEmptyAlphabetic(json, "nome", 100).toJson());
        error.put(ParseHelper.testOnlyNumber(json, "numero", 50).toJson());
        error.put(ParseHelper.testOnlyNumber(json, "agencia", 50).toJson());
        error.put(ParseHelper.testNotEmpty(json, "tipo", 50).toJson());
        error.put(ParseHelper.testIfIsBoolean(json, "status").toJson());

        return error.toString();
    }
    
    public static String emailIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        error.put(ParseHelper.testNotEmpty(json, "email", 100).toJson());
        error.put(ParseHelper.testNotEmpty(json, "senha", 100).toJson());
        error.put(ParseHelper.testNotEmpty(json, "tipo", 100).toJson());
        
        return error.toString();
    }
    
    public static String vinculoIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        error.put(ParseHelper.testIfIsCPF(json, "cpf").toJson());
        error.put(ParseHelper.testOnlyNumber(json, "id_equipamento", -1).toJson());
        error.put(ParseHelper.checkCargo(json).toJson());
        
        return error.toString();
    }
    
    public static String equipamentoIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        error.put(ParseHelper.fieldExist(json, "numIdentificador", 30).toJson());
        error.put(ParseHelper.testNotEmptyAlphabetic(json, "nome", 100).toJson());
        error.put(ParseHelper.checkEquipamento(json).toJson());
        error.put(ParseHelper.testIfIsEmail(json, "email").toJson());
        
        JSONObject obj = new JSONObject(json);
        
        if(obj.has("telefone")){
            error.put(ParseHelper.testOnlyNumber(obj.get("telefone").toString(), "ddd", -1).toJson());
            error.put(ParseHelper.testOnlyNumber(obj.get("telefone").toString(), "numero", -1).toJson());
        }else{
            error.put(new Message("Erro de Telefone", "O campo Telefone não foi encontrado!", 
                            "finderror", "telefone").toJson());
        }
        if(obj.has("endereco")){
            error.put(ParseHelper.testOnlyNumber(obj.get("endereco").toString(), "numCasa", -1).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "bairro", 100).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "cidade", 100).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "estado", 100).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "rua", 100).toJson());
        }else{
            error.put(new Message("Erro de Endereço", "O campo Endereço não foi encontrado!", 
                            "finderror", "endereco").toJson());
        }
        if(obj.has("vinculados")){
            obj.getJSONArray("vinculados").forEach(e -> {
                new JSONArray(vinculoIsValid(e.toString())).forEach(o -> {
                    error.put((JSONObject) o);
                });
            });
        }
        
        return error.toString();
    }
    
    public static String pessoaIsValid(String json){
        JSONArray error = new JSONArray();
        
        error.put(ParseHelper.testOnlyNumber(json, "id", -1).toJson());
        
        error.put(ParseHelper.testNotEmpty(json, "cpf", 14).toJson());
        error.put(ParseHelper.testNotEmpty(json, "nome", 100).toJson());
        error.put(ParseHelper.testNotEmpty(json, "escolaridade", 100).toJson());
        error.put(ParseHelper.testNotEmpty(json, "profissao", 100).toJson());
        error.put(ParseHelper.testNotEmpty(json, "email", 100).toJson());
        error.put(ParseHelper.testIfIsDate(json, "dtNascimento").toJson());
        
        JSONObject obj = new JSONObject(json);
        
        if(obj.has("rg")){
            error.put(ParseHelper.testNotEmpty(obj.get("rg").toString(), "numero", 50).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("rg").toString(), "og", 10).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("rg").toString(), "uf", 2).toJson());
            error.put(ParseHelper.testIfIsDate(obj.get("rg").toString(), "dtEmissao").toJson());
        }else{
            error.put(new Message("Erro de RG", "O campo RG não foi encontrado!", 
                            "finderror", "rg").toJson());
        }
        if(obj.has("telefone")){
            error.put(ParseHelper.testOnlyNumber(obj.get("telefone").toString(), "ddd", -1).toJson());
            error.put(ParseHelper.testOnlyNumber(obj.get("telefone").toString(), "numero", -1).toJson());
        }else{
            error.put(new Message("Erro de Telefone", "O campo Telefone não foi encontrado!", 
                            "finderror", "telefone").toJson());
        }
        if(obj.has("endereco")){
            error.put(ParseHelper.testOnlyNumber(obj.get("endereco").toString(), "numCasa", -1).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "bairro", 100).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "cidade", 100).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "estado", 100).toJson());
            error.put(ParseHelper.testNotEmpty(obj.get("endereco").toString(), "rua", 100).toJson());
        }else{
            error.put(new Message("Erro de Endereço", "O campo Endereço não foi encontrado!", 
                            "finderror", "endereco").toJson());
        }
        
        return error.toString();
    }
}
