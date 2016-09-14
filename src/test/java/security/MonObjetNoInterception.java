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

@Interceptors(SecurityInterceptorRoleTest.class)
@Role(values = { "Admin MOE" })
public class MonObjetNoInterception implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public String maMethode() {
      return "Interception!";
   }

}
