/**
 * 
 */
package security;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.security.SecurityInterceptor;

/**
 * @author bruno.legloahec
 *
 */
public class SecurityInterceptorRoleAdmin extends SecurityInterceptor {

   /**
    * On surchage pour cause de tests
    */
   @Override
   @PostConstruct
   public void init() {
      this.listRoles = new ArrayList<>();
      RoleDataBean role = new RoleDataBean();
      role.setTechnicalNameRole("Admin MOE");
      this.listRoles.add(role);
   }

}
