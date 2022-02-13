package datamodel;

public enum EquipamentoTipo {
    Prefeitura(0){
    
        @Override
        public String toString(){
          return "Prefeitura";
        }
        
    },
    Secretaria(1){
    
        @Override
        public String toString(){
          return "Secretaria";
        }
        
    },
    CMAS(2){
    
        @Override
        public String toString(){
          return "CMAS";
        }
        
    }, 
    FMAS(3){
    
        @Override
        public String toString(){
          return "FMAS";
        }
        
    }, 
    PBF(4){
    
        @Override
        public String toString(){
          return "PBF";
        }
        
    }, 
    CRAS(5){
    
        @Override
        public String toString(){
          return "CRAS";
        }
        
    }, 
    CREAS(6){
    
        @Override
        public String toString(){
          return "CREAS";
        }
        
    }, 
    SCFV(7){
    
        @Override
        public String toString(){
          return "SCFV";
        }
        
    }, 
    Acolhimento(8){
    
        @Override
        public String toString(){
          return "Acolhimento";
        }
        
    }, 
    Outro(9){
    
        @Override
        public String toString(){
          return "Outro";
        }
        
    };
    
    private EquipamentoTipo(int i){
        this.value = i;
    }
    
    private final int value;
    
    public int getValue(){
        return this.value;
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
                return EquipamentoTipo.Outro;
            default:
                return null;
        }
    }
    
}
