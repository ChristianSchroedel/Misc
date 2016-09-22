package Main;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public final class AssetsManager 
{	
	public final static int IMG_WOODS = 0;
	public final static int IMG_STEPPE = 1;
	public final static int IMG_TIGER = 2;
	public final static int IMG_HUMAN = 3;
	public final static int IMG_KENNY = 4;
	public final static int IMG_STERBEN = 5;
	public final static int IMG_WOODS2 = 6;
	public final static int IMG_STEPPE2 = 7;
	public final static int IMG_REH	= 8;
	
	public final static int SOUND_TIGER = 0;
	public final static int SOUND_KENNY = 1;
	public final static int SOUND_REH = 2;
	public final static int SOUND_MENSCH = 3;
	
	private static ArrayList<ImageIcon> images;
	private static ArrayList<AudioInputStream> sounds;
	
	public static void loadSounds() 
	{
		sounds = new ArrayList<>();
		
		try 
		{
			sounds.add(AudioSystem.getAudioInputStream(AssetsManager.class.getResource("/content/tiger.wav")));
			sounds.add(AudioSystem.getAudioInputStream(AssetsManager.class.getResource("/content/kenny.wav")));
			sounds.add(AudioSystem.getAudioInputStream(AssetsManager.class.getResource("/content/reh.wav")));
			sounds.add(AudioSystem.getAudioInputStream(AssetsManager.class.getResource("/content/mensch.wav")));
		} 
		catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadImages()
	{
		images = new ArrayList<>();
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/wald.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/steppe.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/tiger.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/mensch_wald.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/kenny.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/sterben.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/wald2.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/steppe2.png")));
		images.add(new ImageIcon(AssetsManager.class.getResource("/content/reh.png")));
	}
	
	public final static ImageIcon getImage(int index)
	{
		return images.get(index);
	}
	
	public final static AudioInputStream getSound(int index) 
	{
		return sounds.get(index);
	}
}
