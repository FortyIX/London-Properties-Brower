import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * This is a customized version of the JButton which not only have all feature JButton have,but also being able
 * to store its sizing information and set the icon when initialing
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class Marker extends JButton
{
    //size of the markers
    private double size;
    //icons for the markers
    private ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/guiapps/house-icon.png"));

    public Marker(double size)
    {
        super();
        this.size=size;
        //Minimum size is 10 and maximum size is 30
        if(this.size <= 10){
            this.size=10;
        } else if(this.size>=30){
            this.size=30;
        } else{
            this.size=size;
        }
        setIcon((int)size);
        //Makes the buttons transparent and removes button borders
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        if(size==0){
            setVisible(false);
        }
    }

    /**
     * An alternative constructor for this object which sets a default hover text
     */
    public Marker(double size, String tooltiptext){
        super();
        this.size=size;
        if(this.size <= 10){
            this.size=10;
        } else if(this.size>=30){
            this.size=30;
        } else{
            this.size=size;
        }
        setIcon((int)size);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setToolTipText(tooltiptext);//Adds hover text to buttons to clarify what borough its referring to
        if(size==0){
            setVisible(false);
        }
    }

    /**
     * @return Returns the marker size
     */
    public double getMarkerSize(){

        if(this.size <= 10){
            return 10;
        } else if(this.size>=30){
            return 30;
        } else{
            return this.size;
        }

    }

    /**
     * Sets the marker size. If its not between 10 and 30 a default minimum of maximum value is chosen.
     * 
     * @param New marker size 
     */
    public void setMarkerSize(double size){

        if(this.size <= 10){
            this.size=10;
        } else if(this.size>=30){
            this.size=30;
        } else{
            this.size=size;
        }

    }

    /**
     * Size changer for the pic
     */
    private void setIcon(int x)
    {
        if(this.size <= 10){
            x=10;
        } else if(this.size>=30){
            x=30;
        } else{
            x=x;
        }

        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(x, x,  java.awt.Image.SCALE_SMOOTH);   

        icon = new ImageIcon(newimg);  // transform it back

        this.setIcon(icon);
    }

}