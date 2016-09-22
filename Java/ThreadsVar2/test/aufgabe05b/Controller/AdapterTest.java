/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe05b.Controller;

import aufgabe05b.Model.Application;
import aufgabe05b.Model.SlotThread;
import aufgabe05b.View.BanditView;
import java.awt.event.ActionEvent;
import java.util.Observable;
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
public class AdapterTest
{
    
    public AdapterTest()
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
     * Test of update method, of class Adapter.
     */
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Observable arg0 = new SlotThread();
        Object arg1 = new Object();
        Adapter instance = new Adapter(new BanditView(), new Application());
        instance.update(arg0, arg1);
    }
    
}
