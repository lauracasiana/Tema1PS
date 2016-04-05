package connection.tests;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.teatrulnational.database.Connect;
import com.teatrulnational.database.SpectacolDAO;
import com.teatrulnational.database.TicketDAO;
import com.teatrulnational.database.UserDAO;
import com.teatrulnational.models.Bilet;
import com.teatrulnational.models.Spectacol;
import com.teatrulnational.models.User;

public class DBConnectionTest {

	private Connect c;
	private Connection conn;

	@Before
	public void init() {
		c = new Connect();
		conn = c.getConn();
	}

	@Test
	public void select() {
		try {
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet res = st.executeQuery("SELECT * FROM user");
			int i = 0;

			while (res.next()) {
				int id = res.getInt("idUser");
				String name = res.getString("nume");
				String username = res.getString("username");
				String parola = res.getString("parola");
				String rol = res.getString("role");
				System.out.println(name + " " + username + " " + parola + "\n");
				i++;
			}
			Assert.assertEquals(2, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void Login() {
		UserDAO usrsrv = new UserDAO();
		try {
			User me = usrsrv.Login("admin", "admin");
			Assert.assertEquals("Laura", me.getNume());
			User manu = usrsrv.Login("angajat", "123");
			Assert.assertEquals("Manuela", manu.getNume());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void addAngajat() {
		UserDAO usrsrv = new UserDAO();
		try {
			User usr = new User("Maria", "mria", "435", "angajat");
			Assert.assertEquals(true, usrsrv.addAngajat(usr));
			User manu = usrsrv.Login("mria", "435");
			Assert.assertEquals("Maria", manu.getNume());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void seeAngajati() {
		UserDAO usrsrv = new UserDAO();
		ArrayList<User> useri = usrsrv.seeAllAngajat();
		for (User user : useri) {
			System.out.println(user.getNume());
		}
	}

	@Test
	public void addAndDeleteSpectacol() {
		Date x = Date.valueOf("2016-02-12");
		Spectacol spec = new Spectacol("Ceaiul", "Marta", "Loea", x, 4);
		SpectacolDAO specdao = new SpectacolDAO();
		try {
			Assert.assertEquals(true, specdao.addSpectacol(spec));
			Assert.assertEquals(true, specdao.deleteSpectacol(spec.getTitlu()));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void updateSpectacol() {
		Date x = Date.valueOf("2016-03-12");
		Spectacol spec = new Spectacol("Idiotul", "Marta3", "Loea2", x, 5);
		SpectacolDAO specdao = new SpectacolDAO();
		try {
			Assert.assertEquals(true, specdao.updateSpectacol(spec));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void addTicket() {
		Date x = Date.valueOf("2016-03-12");
		Spectacol spec = new Spectacol("Idiotul", "Marta3", "Loea2", x, 5);
		TicketDAO td = new TicketDAO();
		Bilet b = new Bilet(spec, 1, 1);
		try {
			Assert.assertEquals(true, td.addTicket(b));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@Test 
	public void seeAllTickets(){
		TicketDAO td = new TicketDAO();
		ArrayList<Bilet> bilete = td.seeAllTickets();
		for (Bilet bilet : bilete) {
			System.out.println(bilet.getSpectacol().getTitlu()+" "+bilet.getNumar());
		}
	}

	@After
	public void distroy() {
		c.DisConect();
	}

}
