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
          return "Presidente";
        }
        
    }, 
    VICE_PRESIDENTE(5){
    
        @Override
        public String toString(){
          return "Vice Presidente";
        }
        
    }, 
    PREFEITO(6){
    
        @Override
        public String toString(){
          return "Prefeito";
        }
        
    },
    Outro(7){
    
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
                return Cargo.Outro;
            default:
                return null;
        }
    }
    
}
