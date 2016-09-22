/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe04.Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author nobody
 */
public class EditorModel
{
  private BufferedInputStream inputBuffer;
  private BufferedOutputStream outputBuffer;
  
  private String copiedText;
    
  public EditorModel()
  {

  }
  
  public void openFile(File file) throws FileNotFoundException
  {
      inputBuffer = new BufferedInputStream(new FileInputStream(file));
  }
  
  public String getHexData() throws IOException
  {
      StringBuilder hexString = new StringBuilder();
      byte[] b = new byte[1];
      int i = 0;
      
      while( inputBuffer.read(b) != -1 )
      {
          hexString.append(String.format("%02x", b[0]));
          hexString.append(" ");
                    
          i++;
          
          if( i % 8 == 0 )
            hexString.append("\n");
          else if( i % 4 == 0 )
            hexString.append(" ");
      }
      
      inputBuffer.close();
      
      return hexString.toString();
  }
  
  public void saveFile(File file, String content) throws FileNotFoundException, IOException
  {
      outputBuffer = new BufferedOutputStream(new FileOutputStream(file));
      outputBuffer.write(content.getBytes());
      outputBuffer.close();
  }
  
  public void copyText(String copiedText)
  {
      this.copiedText = copiedText;
  }
  
  public String pasteText()
  {
      return copiedText;
  }
}
