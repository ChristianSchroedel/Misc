/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe05b.Model;

import aufgabe05b.Controller.Adapter;
import aufgabe05b.View.BanditView;
import java.util.Observable;
import java.util.Observer;
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
public class ApplicationTest
{
    
    public ApplicationTest()
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
     * Test of setObservers method, of class Application.
     */
    @Test
    public void testSetObservers()
    {
        System.out.println("setObservers");
        Observer o = new Adapter(new BanditView(), new Application());
        Application instance = new Application();
        instance.setObservers(o);
    }

    /**
     * Test of getSlotNumbers method, of class Application.
     */
    @Test
    public void testGetSlotNumbers()
    {
        System.out.println("getSlotNumbers");
        Application instance = new Application();
        int[] expResult = {0, 0, 0};
        int[] result = instance.getSlotNumbers();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of startSlotMachine method, of class Application.
     */
    @Test
    public void testStartSlotMachine()
    {
        System.out.println("startSlotMachine");
        Application instance = new Application();
        instance.startSlotMachine();
    }

    /**
     * Test of stopSlotMachine method, of class Application.
     */
    @Test
    public void testStopSlotMachine()
    {
        System.out.println("stopSlotMachine");
        Application instance = new Application();
        instance.stopSlotMachine();
    }

    /**
     * Test of isSlotMachineRunning method, of class Application.
     */
    @Test
    public void testIsSlotMachineRunning()
    {
        System.out.println("isSlotMachineRunning");
        Application instance = new Application();
        boolean expResult = false;
        boolean result = instance.isSlotMachineRunning();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCredits method, of class Application.
     */
    @Test
    public void testSetCredits()
    {
        System.out.println("setCredits");
        int credits = 0;
        Application instance = new Application();
        instance.setCredits(credits);
    }

    /**
     * Test of getCredits method, of class Application.
     */
    @Test
    public void testGetCredits()
    {
        System.out.println("getCredits");
        Application instance = new Application();
        int expResult = 50;
        int result = instance.getCredits();
        assertEquals(expResult, result);
    }
    
}
