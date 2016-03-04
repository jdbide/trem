package test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;
import javax.faces.convert.NumberConverter;
import javax.inject.Inject;

import org.junit.Test;

import com.avancial.app.data.controller.dao.implementation.TremasApplicationDAO;
import com.avancial.app.data.controller.dao.inter.ITremasApplicationDAO;
import com.avancial.app.data.databean.TremasApplication;

public class TremasApplicationDAOTest {

	@Resource(name = "tremasApplicationDAO")
    private ITremasApplicationDAO dao;
	
	public void setDao(TremasApplicationDAO dao) {
		this.dao = dao;
	}
	
	public TremasApplicationDAOTest() {
		this.dao = new TremasApplicationDAO();
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
