package com.avancial.app.utilitaire;

import java.util.HashMap;
import java.util.List;
import org.hibernate.mapping.Map;

public class MapTraitementImportBrut extends HashMap<String, ITraitementDonnees> {
    
    /**
     * Map contenant les champ d'import brut devant etre traiter ainsi que l'instance de la classe qui les traite
     */
    private static final long serialVersionUID = 1L;

    public MapTraitementImportBrut(){
        
        
        TranscodageRegimeMotrice transco=new TranscodageRegimeMotrice();
        DateSetterRegimeMotrice dateSetter = new DateSetterRegimeMotrice();
        AppSetterRegimeMotrice appSetter = new AppSetterRegimeMotrice();
        
        this.put("KAPP_APP", appSetter);
        this.put("KAPP_ORI", dateSetter);
        this.put("CATH_REGI", transco);
        this.put("CATR_REGI", transco);
        this.put("CDEM_REGI", transco);
        this.put("DSTR_REGI", transco);
        this.put("ETVO_REGI", transco);
        this.put("PARE_REGI", transco);
        this.put("RAME_REGI", transco);
        this.put("RCTH_REGI", transco);
        this.put("SPCO_REGI", transco);
        this.put("SPPL_REGI", transco);
        this.put("SVTH_REGI", transco);
        this.put("TATH_REGI", transco);
        this.put("TRA1_REGI_NON_EXTR", transco);
        this.put("TRA1_REGI_VAL", transco);
        this.put("TRA1_REGI_VAL_TRTH", transco);
        this.put("TRCH_REGI_VAL", transco);
        this.put("TRCH_REGI_VAL_DC", transco);
        this.put("VOIT_REGI_UTIL", transco); 
        
    }

  
    
//    public HashMap<Integer,Object> getIndexInstance(String table, List<String> columns){
//        HashMap<Integer,Object> res = new HashMap<>();
//        int count = 0;
//        for (String columnName : columns) {
//            if(this.getInstance(table + "." +columnName )!=null)
//                res.put(count, this.getInstance(table + "." +columnName ));
//            count ++;
//        }
//        
//        return res;
//        
//    }
    
    

}
