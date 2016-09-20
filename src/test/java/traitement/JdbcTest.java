package traitement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.socle.utils.transcodageregimemotrice.UtilsTranscodageRegime;

@RunWith(Arquillian.class)
public class JdbcTest {

	@Test
	public void main() throws Exception {
		PreparedStatement ps = this.getConnectionFromDriverManager().prepareStatement("SELECT * FROM F$MDRP2.VMDCATH0");
		ResultSet rs = ps.executeQuery();
		
		byte[] is = null;
		StringBuilder sb;
		
		UtilsTranscodageRegime transco;
		
		Calendar calendrier = Calendar.getInstance();
		calendrier.set(2015, 11, 7);

		while(rs.next()) {
			sb = new StringBuilder();
			
			is = rs.getBytes("CATH_REGI");
			for (byte b : is) {
				sb.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
			}
			System.out.println(rs.getString("CATH_TRCH_NUM_TRA1"));
			System.out.println(rs.getString("CATH_SSIM"));
			System.out.println(sb.toString());
			
			transco = new UtilsTranscodageRegime(sb.toString(), calendrier.getTime());
			System.out.println(transco.executeTranscodage());
			transco.getListeJourPourRegime();
		}
		
	}

	/**
	 * Crée la connexion à partir d'un Driver Manager.
	 * @return
	 * @throws Exception
	 */
	private Connection getConnectionFromDriverManager() throws Exception {
		String DB_CONN_STRING = "jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;";
		String DRIVER_CLASS_NAME = "com.ibm.db2.jcc.DB2Driver";
		String USER_NAME = "ejmt013";
		String PASSWORD = "xa19de09";

		Class.forName(DRIVER_CLASS_NAME).newInstance();
		
		return DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
	}

	/**
	 * Crée la connexion à partir d'un data Source.
	 * @return
	 * @throws Exception
	 */
	private Connection getConnectionFromDataSource() throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		dataSource.setUsername("ejmt013");
		dataSource.setPassword("xa19de09");
		dataSource.setUrl("jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;");
		dataSource.setMaxActive(10);
		dataSource.setMaxIdle(5);
		dataSource.setInitialSize(5);
		dataSource.setValidationQuery("SELECT 1");
		
		return dataSource.getConnection();
	}
}
