/**
 * 
 */
package security;

import java.io.Serializable;

import javax.interceptor.Interceptors;

import com.avancial.socle.resources.annotations.Role;

/**
 * @author bruno.legloahec
 *
 */

@Interceptors(SecurityInterceptorRoleAdmin.class)
@Role(values = { "Admin MOE" })
public class MonObjetInterception implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public String maMethode() {
      return "Interception!";
   }

}
