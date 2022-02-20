package datamodel;

import org.json.JSONObject;

public enum EquipamentoTipo implements JSONTransform{
    Prefeitura(0){
    
        @Override
        public String toString(){
          return "Prefeitura";
        }

        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    Secretaria(1){
    
        @Override
        public String toString(){
          return "Secretaria";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    CMAS(2){
    
        @Override
        public String toString(){
          return "CMAS";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    FMAS(3){
    
        @Override
        public String toString(){
          return "FMAS";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    PBF(4){
    
        @Override
        public String toString(){
          return "PBF";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    CRAS(5){
    
        @Override
        public String toString(){
          return "CRAS";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    CREAS(6){
    
        @Override
        public String toString(){
          return "CREAS";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    SCFV(7){
    
        @Override
        public String toString(){
          return "SCFV";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    Acolhimento(8){
    
        @Override
        public String toString(){
          return "Acolhimento";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    CT(9){
    
        @Override
        public String toString(){
          return "Conselho Tutelar";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    CMDCA(10){
    
        @Override
        public String toString(){
          return "CMDCA";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    CENTRODIA(11){
    
        @Override
        public String toString(){
          return "Centro Dia";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    CENTROPOP(12){
    
        @Override
        public String toString(){
          return "Centro Pop";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    OUTRO(13){
    
        @Override
        public String toString(){
          return "Outro";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    };
    
    private EquipamentoTipo(int i){
        this.value = i;
    }
    
    private final int value;
    
    public int getValue(){
        return this.value;
    }
    
    public static EquipamentoTipo getByJson(String json){
        JSONObject j = new JSONObject(json);
        
        int value = j.getInt("code");
        
        return EquipamentoTipo.getByInt(value);
    }
    
    public static EquipamentoTipo getByInt(int i){
        switch(i){
            case 0:
                return EquipamentoTipo.Prefeitura;
            case 1:
                return EquipamentoTipo.Secretaria;
            case 2:
                return EquipamentoTipo.CMAS;
            case 3:
                return EquipamentoTipo.FMAS;
            case 4:
                return EquipamentoTipo.PBF;
            case 5:
                return EquipamentoTipo.CRAS;
            case 6:
                return EquipamentoTipo.CREAS;
            case 7:
                return EquipamentoTipo.SCFV; 
            case 8:
                return EquipamentoTipo.Acolhimento;
            case 9:
                return EquipamentoTipo.CT;
            case 10:
                return EquipamentoTipo.CMDCA;
            case 11:
                return EquipamentoTipo.CENTRODIA;
            case 12:
                return EquipamentoTipo.CENTROPOP;
            case 13:
                return EquipamentoTipo.OUTRO;
            default:
                return null;
        }
    }
    
    public static int getQtdEquipamentos(){
        return 14;
    }
    
}
