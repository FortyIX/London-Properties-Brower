import java.util.*;
import javax.swing.table.AbstractTableModel;
/**
 * This is a TableViewController for Detail Table that shows when a house icon is clicked
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class DetailTableModel extends AbstractTableModel
{
    private static final int COLUMN_HOST_NAME=0;
    private static final int COLUMN_PRICE=1;
    private static final int COLUMN_MINIMUM_NIGHT=2;
    private static final int COLIMN_REVIEWS=3;
    private static final int ID=4;
    private String[] columnNames = {"Host name","price","Minimum night","Reviews per month","id"};
    private ArrayList<AirbnbListing> properties;
    
    
     public DetailTableModel(ArrayList<AirbnbListing> list){
         
         this.properties = list;
        
     }
      
     // count number of column
     @Override 
     public int getColumnCount(){
         return columnNames.length;
     }
     
     // count number of row
     @Override 
     public int getRowCount(){
         return properties.size();
     }
     
     // get names for all column
     @Override 
     public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
     }
     
     // return the type of each column 
     @Override 
     public Class<?> getColumnClass(int columnIndex){
         
         return getValueAt(0,columnIndex).getClass();
         
        }
        
     
     // get a certail detal from a certain entry
     @Override
     public Object getValueAt(int rowIndex,int columnIndex){
         
         //get the Airbnb Listing entry for selected row 
         AirbnbListing tmp = properties.get(rowIndex);
         
         switch(columnIndex){
             
             case COLUMN_HOST_NAME:
                  return tmp.getHost_name();
                  
                  
             case COLUMN_PRICE:
                  return tmp.getPrice();
                  
             case COLUMN_MINIMUM_NIGHT:
                  return tmp.getMinimumNights();
                  
             case COLIMN_REVIEWS:
                  return tmp.getNumberOfReviews();
                  
                  case ID:
                  return tmp.getId();
                  
            
            }
         
         return null;
         
      }
         
}