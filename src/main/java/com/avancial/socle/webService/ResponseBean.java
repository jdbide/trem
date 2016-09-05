/**
 * 
 */
package com.avancial.socle.webService;

import java.io.Serializable;
import org.json.simple.JSONArray;

/**
 * @author sebastien.benede
 *
 */
public class ResponseBean implements Serializable {
   
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private boolean status;
   private String message;
   private Object data;
   private JSONArray datas;

   /**
    * @return the importOK
    */
   public boolean isStatus() {
      return this.status;
   }

   /**
    * @param importOK the importOK to set
    */
   public void setStatus(boolean status) {
      this.status = status;
   }

   /**
    * @return the message
    */
   public String getMessage() {
      return this.message;
   }

   /**
    * @param message the message to set
    */
   public void setMessage(String message) {
      this.message = message;
   }

   /**
    * @return the data
    */
   public Object getData() {
      return data;
   }

   /**
    * @param data the data to set
    */
   public void setData(Object data) {
      this.data = data;
   }

public JSONArray getDatas() {
    return datas;
}

public void setDatas(JSONArray datas) {
    this.datas = datas;
}
}
