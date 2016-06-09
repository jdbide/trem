package data.model.databean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestImport {
   // @Inject
   // @Socle_PUSocle
   // private EntityManager ems;
   //
   // @Inject
   // @Socle_PUExterne
   // private EntityManager eme;

   public void testImportTMDGADS() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Class.forName("com.ibm.db2.jcc.DB2Driver");
         System.out.println("Driver O.K.");
         String urlSocle = "jdbc:mysql://caliban/tremas";
         String urlDb2 = "jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;";
         String userSocle = "dbad_tremas";
         String userDb2 = "SGAV026";
         String passwdSocle = "!tremas-12";
         String passwdDb2 = "bitchs39";
         Connection connSocle = DriverManager.getConnection(urlSocle, userSocle, passwdSocle);
         Connection connDb2 = DriverManager.getConnection(urlDb2, userDb2, passwdDb2);
         System.out.println("Connexions effective !");

         Statement stateDb2 = connDb2.createStatement();
         Statement stateSocle = connSocle.createStatement();
         ResultSet select = stateDb2.executeQuery("SELECT * FROM F$MDRP1.TMDGADS");
         ResultSetMetaData metaSelect = select.getMetaData();
         int compt = 0;

         StringBuilder sqlInsert = new StringBuilder();
         StringBuilder sqlInsertBis = new StringBuilder();
         sqlInsert.append("INSERT INTO tremas_import_tmdgads1 (");

         for (int i = 1; i <= metaSelect.getColumnCount(); i++) {
            sqlInsert.append(metaSelect.getColumnName(i).toUpperCase());
            if (i != metaSelect.getColumnCount())
               sqlInsert.append(",");
         }
         sqlInsert.append(") VALUES (");

         while (select.next()) {
            compt++;
            sqlInsertBis.setLength(0);
            for (int i = 1; i <= metaSelect.getColumnCount(); i++) {
               sqlInsertBis.append("'" + select.getObject(i).toString() + "'");
               if (i != metaSelect.getColumnCount())
                  sqlInsertBis.append(",");
            }
            if (compt % 1000 == 0) {
               sqlInsertBis.append(")");
               stateSocle.executeUpdate(sqlInsert + "" + sqlInsertBis);
               sqlInsertBis.setLength(0);
            } else
               sqlInsertBis.append("),(");
         }
         if (compt % 1000 != 0) {
            sqlInsertBis.setLength(sqlInsertBis.length() - 2);
            stateSocle.executeUpdate(sqlInsert + "" + sqlInsertBis);
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
