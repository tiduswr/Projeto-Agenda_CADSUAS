package datamodel;

import org.json.JSONObject;

public enum Cargo implements JSONTransform{
    COORDENADOR(0){
    
        @Override
        public String toString(){
          return "Coordenador";
        }

        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    SECRETARIO(1){
    
        @Override
        public String toString(){
          return "Secretario";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    SECRETARIO_ADJUNTO(2){
    
        @Override
        public String toString(){
          return "Secretário Adjunto";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    SECRETARIO_EXECUTIVO(3){
    
        @Override
        public String toString(){
          return "Secretário Executivo";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    PRESIDENTE(4){
    
        @Override
        public String toString(){
          return "Conselheiro Presidente";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    VICE_PRESIDENTE(5){
    
        @Override
        public String toString(){
          return "Conselheiro Vice Presidente";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    }, 
    PREFEITO(6){
    
        @Override
        public String toString(){
          return "Prefeito";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    TECNICO_NIVEL_SUPERIOR(7){
    
        @Override
        public String toString(){
          return "Técnico de Nivel Superior";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    TECNICO_NIVEL_MEDIO(8){
    
        @Override
        public String toString(){
          return "Técnico de Nivel Médio";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    EDUCADOR_SOCIAL(9){
    
        @Override
        public String toString(){
          return "Educador Social";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    CADASTRADOR(10){
    
        @Override
        public String toString(){
          return "Cadastrador/Entrevistador Social";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    APOIO_ADM(11){
    
        @Override
        public String toString(){
          return "Apoio Administrativo";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    ESTAGIARIO(12){
    
        @Override
        public String toString(){
          return "Estagiario";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    SERVICOS_GERAIS(13){
    
        @Override
        public String toString(){
          return "Prefeito";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    CONS_TIT(14){
    
        @Override
        public String toString(){
          return "Conselheiro Titular";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    CONS_SUP(15){
    
        @Override
        public String toString(){
          return "Conselheiro Suplente";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    VISITADOR_PCF(16){
    
        @Override
        public String toString(){
          return "Visitador Criança Feliz";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    SUPERVISOR_PCF(17){
    
        @Override
        public String toString(){
          return "Supervisor Criança Feliz";
        }
        
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            
            json.put("code", getValue());
            json.put("nome", toString());
            
            return json;
        }
        
    },
    Outro(18){
    
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
    
    private Cargo(int i){
        this.value = i;
    }
    
    private final int value;
    
    public int getValue(){
        return this.value;
    }
    
    public static Cargo getByJson(String json){
        JSONObject j = new JSONObject(json);
        
        int value = j.getInt("code");
        
        return Cargo.getByInt(value);
    }
    
    public static Cargo getByInt(int i){
        switch(i){
            case 0:
                return Cargo.COORDENADOR;
            case 1:
                return Cargo.SECRETARIO;
            case 2:
                return Cargo.SECRETARIO_ADJUNTO;
            case 3:
                return Cargo.SECRETARIO_EXECUTIVO;
            case 4:
                return Cargo.PRESIDENTE;
            case 5:
                return Cargo.VICE_PRESIDENTE;
            case 6:
                return Cargo.PREFEITO;
            case 7:
                return Cargo.TECNICO_NIVEL_SUPERIOR;
            case 8:
                return Cargo.TECNICO_NIVEL_MEDIO;
            case 9:
                return Cargo.EDUCADOR_SOCIAL;
            case 10:
                return Cargo.CADASTRADOR;
            case 11:
                return Cargo.APOIO_ADM;
            case 12:
                return Cargo.ESTAGIARIO;
            case 13:
                return Cargo.SERVICOS_GERAIS;
            case 14:
                return Cargo.CONS_TIT;
            case 15:
                return Cargo.CONS_SUP;  
            case 16:
                return Cargo.Outro;
            default:
                return null;
        }
    }
    
    public static int getQtdCargos(){
        return 17;
    }
    
}
