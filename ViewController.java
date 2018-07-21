import javax.accessibility.AccessibleContext;
import javax.swing.JFrame;

/**
 * Controls the action of all GUI components in the programme.
 * All GUI interfaces should inherit this method.
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class ViewController extends JFrame 
{
 public ViewController()
 {
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 }

}