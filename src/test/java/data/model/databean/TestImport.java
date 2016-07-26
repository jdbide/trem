package data.model.databean;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.traitement.TraitementImportDb2Motrice;
import com.avancial.app.traitement.TraitementImportJeuDonnees;
import com.avancial.app.utilitaire.MapTraitementImportBrut;
import com.avancial.app.utilitaire.SchemaMotrice;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class TestImport {    
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
                .addClass(JeuDonneeService.class).addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }
    
    @Inject
    @Socle_PUSocle
    EntityManager entityManagerSocle;

    EntityManager entityManagerDb2;
    @Test
    public void testImportTMDVOIT() {
        try {
            this.entityManagerSocle.clear();
//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.ibm.db2.jcc.DB2Driver");
//            System.out.println("Driver O.K.");
//            String urlSocle = "jdbc:mysql://caliban/tremas";
//            String urlDb2 = "jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;";
//            String userSocle = "dbad_tremas";
            String userDb2 = "ejmt013";
//            String passwdSocle = "!tremas-12";
            String passwdDb2 = "Isab1000";
//            Connection connSocle = DriverManager.getConnection(urlSocle, userSocle, passwdSocle);
////            Connection connDb2 = DriverManager.getConnection(urlDb2, userDb2, passwdDb2);
//            System.out.println("Connexions effective !");

            try {
                this.entityManagerDb2 = EntityManagerFactoryProviderDb2
                        .getInstance(userDb2, passwdDb2)
                        .createEntityManager();
            }
            catch (Throwable ex) {
                throw ex;
            }
            
            TraitementImportDb2Motrice traitementImportDb2Motrice = new TraitementImportDb2Motrice(this.entityManagerSocle, this.entityManagerDb2, "F$MDRP2");
            traitementImportDb2Motrice.execute();

//            Statement stateDb2 = connDb2.createStatement();
//            Statement stateSocle = connSocle.createStatement();
//            ResultSet select1 = stateDb2.executeQuery("SELECT * FROM F$MDRP2.TMDKAPP");
//            ResultSet select2 = stateDb2.executeQuery("SELECT * FROM F$MDRP2.TMDVOIT");
//            ResultSetMetaData metaSelect1 = select1.getMetaData();
//            ResultSetMetaData metaSelect2 = select2.getMetaData();
//            MapTraitementImportBrut mapTraitementImportBrut = new MapTraitementImportBrut();
//            int compt = 0;
//
//            StringBuilder sqlInsert = new StringBuilder();
//            StringBuilder sqlInsertBis = new StringBuilder();
//            sqlInsert.append("INSERT INTO tremas_import_tmdkapp (");
//
//            for (int i = 1; i <= metaSelect1.getColumnCount(); i++) {
//                sqlInsert.append(metaSelect1.getColumnName(i).toUpperCase());
//                if (i != metaSelect1.getColumnCount())
//                    sqlInsert.append(",");
//            }
//            sqlInsert.append(") VALUES (");
//
//            while (select1.next()) {
//                compt++;
//                sqlInsertBis.setLength(0);
//                for (int i = 1; i <= metaSelect1.getColumnCount(); i++) {
//                    if (!mapTraitementImportBrut.containsKey(metaSelect1.getColumnName(i)))
//                        sqlInsertBis.append("'" + select1.getObject(i).toString() + "'");
//                    else
//                        sqlInsertBis.append("'").append(mapTraitementImportBrut.get(metaSelect1.getColumnName(i))
//                                .execute(select1.getObject(i).toString()).replaceAll("'", "''")).append("'");
//                    if (i != metaSelect1.getColumnCount())
//                        sqlInsertBis.append(",");
//                }
//                if (compt % 250 == 0) {
//                    sqlInsertBis.append(")");
//                    stateSocle.executeUpdate(sqlInsert + "" + sqlInsertBis);
//                    sqlInsertBis.setLength(0);
//                }
//                else
//                    sqlInsertBis.append("),(");
//            }
//            if (compt % 250 != 0) {
//                sqlInsertBis.setLength(sqlInsertBis.length() - 2);
//                stateSocle.executeUpdate(sqlInsert + "" + sqlInsertBis);
//            }
//
//            compt = 0;
//            sqlInsert.append("INSERT INTO tremas_import_tmdvoit (");
//
//            for (int i = 1; i <= metaSelect2.getColumnCount(); i++) {
//                sqlInsert.append(metaSelect2.getColumnName(i).toUpperCase());
//                if (i != metaSelect2.getColumnCount())
//                    sqlInsert.append(",");
//            }
//            sqlInsert.append(") VALUES (");
//
//            while (select2.next()) {
//                compt++;
//                sqlInsertBis.setLength(0);
//                for (int i = 1; i <= metaSelect2.getColumnCount(); i++) {
//                    if (!mapTraitementImportBrut.containsKey(metaSelect2.getColumnName(i)))
//                        sqlInsertBis.append("'" + select2.getObject(i).toString() + "'");
//                    else
//                        sqlInsertBis.append("'").append(mapTraitementImportBrut.get(metaSelect2.getColumnName(i))
//                                .execute(select1.getObject(i).toString()).replaceAll("'", "''")).append("'");
//                    if (i != metaSelect2.getColumnCount())
//                        sqlInsertBis.append(",");
//                }
//                if (compt % 250 == 0) {
//                    sqlInsertBis.append(")");
//                    stateSocle.executeUpdate(sqlInsert + "" + sqlInsertBis);
//                    sqlInsertBis.setLength(0);
//                }
//                else
//                    sqlInsertBis.append("),(");
//            }
//            if (compt % 250 != 0) {
//                sqlInsertBis.setLength(sqlInsertBis.length() - 2);
//                stateSocle.executeUpdate(sqlInsert + "" + sqlInsertBis);
//            }
//
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
