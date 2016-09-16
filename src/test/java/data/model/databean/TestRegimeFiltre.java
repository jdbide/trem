package data.model.databean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.socle.utils.ListUtils;

import junit.framework.Assert;

public class TestRegimeFiltre {

   @Test
   public void main() {
      Calendar calendar14 = Calendar.getInstance();
      calendar14.set(Calendar.DAY_OF_MONTH, 14);
      Calendar calendar15 = Calendar.getInstance();
      calendar15.set(Calendar.DAY_OF_MONTH, 15);
      Calendar calendar16 = Calendar.getInstance();
      calendar16.set(Calendar.DAY_OF_MONTH, 16);
      Calendar calendar17 = Calendar.getInstance();
      calendar17.set(Calendar.DAY_OF_MONTH, 17);
      Calendar calendar18 = Calendar.getInstance();
      calendar18.set(Calendar.DAY_OF_MONTH, 18);

      List<Date> dates1 = new ArrayList<>();
      dates1.add(calendar14.getTime());
      dates1.add(calendar15.getTime());
      dates1.add(calendar16.getTime());
      dates1.add(calendar17.getTime());
      dates1.add(calendar18.getTime());
      List<Date> dates2 = new ArrayList<>();
      dates2.add(calendar14.getTime());
      dates2.add(calendar15.getTime());
      dates2.add(calendar16.getTime());
      dates2.add(calendar17.getTime());
      dates2.add(calendar18.getTime());
      List<Date> dates3 = new ArrayList<>();
      dates3.add(calendar14.getTime());
      dates3.add(calendar15.getTime());
      dates3.add(calendar16.getTime());
      dates3.add(calendar17.getTime());
      dates3.add(calendar18.getTime());
      List<Date> dates4 = new ArrayList<>();
      dates4.add(calendar14.getTime());
      dates4.add(calendar15.getTime());
      dates4.add(calendar16.getTime());
      dates4.add(calendar17.getTime());
      dates4.add(calendar18.getTime());
      
      List<Date> expected = new ArrayList<>();
      
      Regime regime1 = new Regime();
      regime1.setListeJours(dates1);
      regime1.filtreDates(null, calendar15.getTime());
      expected.clear();
      expected.add(calendar14.getTime());
      expected.add(calendar15.getTime());
      Assert.assertTrue("Test null - dateFin", ListUtils.compareLists(expected, regime1.getListeJours()));

      Regime regime2 = new Regime();
      regime2.setListeJours(dates2);
      regime2.filtreDates(calendar16.getTime(), null);
      expected.clear();
      expected.add(calendar16.getTime());
      expected.add(calendar17.getTime());
      expected.add(calendar18.getTime());
      Assert.assertTrue("Test dateDebut - null", ListUtils.compareLists(expected, regime2.getListeJours()));
      
      Regime regime3 = new Regime();
      regime3.setListeJours(dates3);
      regime3.filtreDates(calendar15.getTime(), calendar17.getTime());
      expected.clear();
      expected.add(calendar15.getTime());
      expected.add(calendar16.getTime());
      expected.add(calendar17.getTime());
      Assert.assertTrue("Test dateDebut - dateFin", ListUtils.compareLists(expected, regime3.getListeJours()));
      
      Regime regime4 = new Regime();
      regime4.setListeJours(dates4);
      regime4.filtreDates(null, null);
      expected.clear();
      expected.add(calendar14.getTime());
      expected.add(calendar15.getTime());
      expected.add(calendar16.getTime());
      expected.add(calendar17.getTime());
      expected.add(calendar18.getTime());
      Assert.assertTrue("Test null - null", ListUtils.compareLists(expected, regime4.getListeJours()));
   }
}
