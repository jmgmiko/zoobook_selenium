package Miko.SeleniumFramework;

import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class MainTest
{

	@Test
    public void wholeTest() {
    	try {
    		TestScript script = new TestScript();        	
			script.readTestScript();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
