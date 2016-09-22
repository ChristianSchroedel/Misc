/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe05b.View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
public class BanditViewTest
{
    
    public BanditViewTest()
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
     * Test of showWinDialog method, of class BanditView.
     */
    @Test
    public void testShowWinDialog()
    {
        System.out.println("showWinDialog");
        BanditView instance = new BanditView();
        instance.showWinDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showLoseDialog method, of class BanditView.
     */
    @Test
    public void testShowLoseDialog()
    {
        System.out.println("showLoseDialog");
        BanditView instance = new BanditView();
        instance.showLoseDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectIcon method, of class BanditView.
     */
    @Test
    public void testSelectIcon()
    {
        System.out.println("selectIcon");
        int number = 0;
        BanditView instance = new BanditView();
        ImageIcon expResult = null;
        ImageIcon result = instance.selectIcon(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class BanditView.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        BanditView.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBtnStart method, of class BanditView.
     */
    @Test
    public void testGetBtnStart()
    {
        System.out.println("getBtnStart");
        BanditView instance = new BanditView();
        JButton expResult = null;
        JButton result = instance.getBtnStart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBtnStop method, of class BanditView.
     */
    @Test
    public void testGetBtnStop()
    {
        System.out.println("getBtnStop");
        BanditView instance = new BanditView();
        JButton expResult = null;
        JButton result = instance.getBtnStop();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLbSlot1 method, of class BanditView.
     */
    @Test
    public void testGetLbSlot1()
    {
        System.out.println("getLbSlot1");
        BanditView instance = new BanditView();
        JLabel expResult = null;
        JLabel result = instance.getLbSlot1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLbSlot2 method, of class BanditView.
     */
    @Test
    public void testGetLbSlot2()
    {
        System.out.println("getLbSlot2");
        BanditView instance = new BanditView();
        JLabel expResult = null;
        JLabel result = instance.getLbSlot2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLbSlot3 method, of class BanditView.
     */
    @Test
    public void testGetLbSlot3()
    {
        System.out.println("getLbSlot3");
        BanditView instance = new BanditView();
        JLabel expResult = null;
        JLabel result = instance.getLbSlot3();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLbCredits method, of class BanditView.
     */
    @Test
    public void testGetLbCredits()
    {
        System.out.println("getLbCredits");
        BanditView instance = new BanditView();
        JLabel expResult = null;
        JLabel result = instance.getLbCredits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
