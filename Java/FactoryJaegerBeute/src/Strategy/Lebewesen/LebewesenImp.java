package Strategy.Lebewesen;

import java.awt.Image;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;

import Main.AssetsManager;
import Strategy.Position;
import Strategy.eAktion;
import Strategy.BewegungsVerhalten.BewegungsVerhalten;


public abstract class LebewesenImp implements Lebewesen 
{
	protected ImageIcon _aussehen;
	protected BewegungsVerhalten _bewegungsVerhalten;
	protected AudioInputStream _laut;
	protected Position _position;
	
	protected double _jagdRadius;
	
	public double getJagdRadius() 
	{
		return _jagdRadius;
	}
	
	public abstract void aktion(eAktion aktion);

	public void bewege()
	{
		_position = _bewegungsVerhalten.bewege(_position);
	}
	
	protected LebewesenImp( final int imgKenny, final int soundKenny )
	{		
		ladeBild( imgKenny );	
		ladeLaut( soundKenny );
	}
	
	protected void ladeBild(final int img )
	{
		_aussehen = AssetsManager.getImage( img );
	}
	
	protected void ladeLaut( final int sound ) 
	{
		_laut = AssetsManager.getSound( sound );
	}

	public void erzeugeLaut()
	{
    	new Thread()
    	{
    	    public void run() 
    	    {
    	        try
    	        {
    	        	
    	            AudioFormat af = _laut.getFormat();
    	            int size = (int) (af.getFrameSize() * _laut.getFrameLength());
    	            byte[] audio = new byte[size];
    	            DataLine.Info info = new DataLine.Info(Clip.class, af, size);
    	            _laut.read(audio, 0, size);
    	            

	                Clip clip = (Clip) AudioSystem.getLine(info);
	                clip.open(af, audio, 0, size);
	                clip.start();
    	       
    	        }
    	        catch(Exception e)
    	        {
    	        	e.printStackTrace(); 
    	        }
    	    }
    	}.start();

    	AssetsManager.loadSounds();
	}

	public ImageIcon getAussehen()
	{		
		return new ImageIcon(_aussehen.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT));
	}


	public Position getPosition()
	{
		return _position;
	}

}