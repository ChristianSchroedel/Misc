/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe07.Model;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author christian
 */
public class GraphicModel 
{
    private ArrayList< ArrayList<Point> > points;
    private ArrayList<Point> line;
    
    public GraphicModel()
    {
        points = new ArrayList<>();
    }
    
    public void beginLine(Point p)
    {
        line = new ArrayList<>();
        line.add(p);
    }
    
    public void addPoint(Point p)
    {
        line.add(p);
    }
    
    public void endLine(Point p)
    {
        line.add(p);
        points.add(line);
    }
    
    public ArrayList<Point> getPoints(int index)
    {
        return points.get(index);
    }
    
    public ArrayList< ArrayList<Point> > getAllPoints()
    {
        return points;
    }
    
    public void savePoints(File file)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(points);
            oos.close();
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(GraphicModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(GraphicModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadPoints(File file)
    {
        try
        {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            points = (ArrayList<ArrayList<Point>>) ois.readObject();
            ois.close();
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(GraphicModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(GraphicModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(GraphicModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearPoints()
    {
        points.clear();
    }
}
