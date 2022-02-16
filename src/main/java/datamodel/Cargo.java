package datamodel;

public enum Cargo {
    COORDENADOR(0){
    
        @Override
        public String toString(){
          return "Coordenador";
        }
        
    }, 
    SECRETARIO(1){
    
        @Override
        public String toString(){
          return "Secretario";
        }
        
    }, 
    SECRETARIO_ADJUNTO(2){
    
        @Override
        public String toString(){
          return "Secretário Adjunto";
        }
        
    }, 
    SECRETARIO_EXECUTIVO(3){
    
        @Override
        public String toString(){
          return "Secretário Executivo";
        }
        
    }, 
    PRESIDENTE(4){
    
        @Override
        public String toString(){
          return "Conselheiro Presidente";
        }
        
    }, 
    VICE_PRESIDENTE(5){
    
        @Override
        public String toString(){
          return "Conselheiro Vice Presidente";
        }
        
    }, 
    PREFEITO(6){
    
        @Override
        public String toString(){
          return "Prefeito";
        }
        
    },
    TECNICO_NIVEL_SUPERIOR(7){
    
        @Override
        public String toString(){
          return "Técnico de Nivel Superior";
        }
        
    },
    TECNICO_NIVEL_MEDIO(8){
    
        @Override
        public String toString(){
          return "Técnico de Nivel Médio";
        }
        
    },
    EDUCADOR_SOCIAL(9){
    
        @Override
        public String toString(){
          return "Educador Social";
        }
        
    },
    CADASTRADOR(10){
    
        @Override
        public String toString(){
          return "Cadastrador/Entrevistador Social";
        }
        
    },
    APOIO_ADM(11){
    
        @Override
        public String toString(){
          return "Apoio Administrativo";
        }
        
    },
    ESTAGIARIO(12){
    
        @Override
        public String toString(){
          return "Estagiario";
        }
        
    },
    SERVICOS_GERAIS(13){
    
        @Override
        public String toString(){
          return "Prefeito";
        }
        
    },
    CONS_TIT(14){
    
        @Override
        public String toString(){
          return "Conselheiro Titular";
        }
        
    },
    CONS_SUP(15){
    
        @Override
        public String toString(){
          return "Conselheiro Suplente";
        }
        
    },
    Outro(16){
    
        @Override
        public String toString(){
          return "Outro";
        }
        
    };
    
    private Cargo(int i){
        this.value = i;
    }
    
    private final int value;
    
    public int getValue(){
        return this.value;
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
