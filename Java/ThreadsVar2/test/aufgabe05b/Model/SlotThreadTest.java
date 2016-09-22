/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe05b.Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author christian
 */
public class SlotThreadTest
{
    
    public SlotThreadTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of start method, of class SlotThread.
     */
    @Test
    public void testStart()
    {
        System.out.println("start");
        SlotThread instance = new SlotThread();
        instance.start();
    }

    /**
     * Test of stop method, of class SlotThread.
     */
    @Test
    public void testStop()
    {
        System.out.println("stop");
        SlotThread instance = new SlotThread();
        instance.stop();
    }

    /**
     * Test of getSlotZahl method, of class SlotThread.
     */
    @Test
    public void testGetSlotZahl()
    {
        System.out.println("getSlotZahl");
        SlotThread instance = new SlotThread();
        int expResult = 0;
        int result = instance.getSlotZahl();
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class SlotThread.
     */
    @Test
    public void testRun()
    {
        System.out.println("run");
        SlotThread instance = new SlotThread();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
