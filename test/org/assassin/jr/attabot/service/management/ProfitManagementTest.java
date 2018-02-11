package org.assassin.jr.attabot.service.management;

import org.junit.Test;

public class ProfitManagementTest {

	@Test
	public void testInsert() {
		
		ProfitManagement pm = ProfitManagement.getInstance();
		pm.addProfit("2018-02-09", "C1", 0.1, 0);
		pm.save();
		
		pm.addProfit("2018-02-09", "C1", 0.3, 0);
		pm.addProfit("2018-02-09", "C2", 0.4, 0);
		pm.save();
		
		pm.addProfit("2018-02-10", "C1", 0.3, 0);
		pm.addProfit("2018-02-10", "C2", 0.4, 0);
		pm.save();
		
		
		pm.addProfit("2018-02-11", "C1", 0.3, 0);
		pm.addProfit("2018-02-11", "C2", 0.4, 0);
		pm.save();
		
		
		pm.addProfit("2018-02-12", "C1", 0.3, 0);
		pm.save();
		pm.addProfit("2018-02-12", "C2", 0.4, 0);
		pm.save();
		
		pm.addProfit("2018-02-13", "C1", 0.3, 0);
		pm.save();
		pm.addProfit("2018-02-13", "C2", 0.4, 0);
		pm.save();

	}

}
