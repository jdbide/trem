package test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;
import javax.faces.convert.NumberConverter;
import javax.inject.Inject;

import org.junit.Test;

import com.avancial.app.data.controller.dao.implementation.TremasApplicationDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasApplicationDAO;
import com.avancial.app.data.databean.TremasApplication;

public class TremasApplicationDAOTest {

	@Resource(name = "tremasApplicationDAO")
    private TremasApplicationDAO dao;
	
	public void setDao(TremasApplicationDAOImpl dao) {
		this.dao = dao;
	}
	
	public TremasApplicationDAOTest() {
		this.dao = new TremasApplicationDAOImpl();
	}
	
	@Test
	public void test() {
/*		TremasApplication newInstance = new TremasApplication();
		
		newInstance.setActifApplication(true);
		newInstance.setCommentaireApplication("");
		newInstance.setDateCreateApplication(new Date());
		newInstance.setDateLastUpdateApplication(new Date());
		newInstance.setIdUtilisateurCreateApplication(1);
		newInstance.setIdUtilisateurLastUpdateApplication(1);
		newInstance.setLibelleApplication("ES");
		newInstance.setOrdreApplication(1);
		
		this.dao.insert(newInstance);
*/
		assertEquals(this.dao.count(),new Long(3));

		
		
	}

}
