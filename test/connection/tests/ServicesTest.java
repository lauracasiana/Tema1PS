package connection.tests;

import org.junit.Test;

import com.teatrulnational.models.User;
import com.teatrulnational.services.UserManager;
//mrcl -> 445
//mria -> 435

public class ServicesTest {

	@Test
	public void testLogin() {
		UserManager um = new UserManager();

		User me;
		try {
			me = um.Login("mria", "435");
			System.out.println(me.getNume());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
