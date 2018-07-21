import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;
/**
 * This class models the listing detail window which pops up when 
 * a row on the lsiting detail Jtable is clicked
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class ListingDetailsFrame 
{
    private JFrame frame;
    //the listing object
    private AirbnbListing listingObject;
    
    /**
     * Constructor for objects of class ListingDetailsFrame
     * uses the id and the computation class to get the listing object and displays its details in the window
     */
    public ListingDetailsFrame(String id)
    {
        //get the listing object from the csv reader, which matches the id of the row clicked in the Jtable
        listingObject = StartFrame.compute.getListingByID(id);
        //calls the method for creating window depending on the row clicked in the Jtable
        initComponents(listingObject.getId(),listingObject.getName(),listingObject.getHost_name(),
                        listingObject.getHost_id(),listingObject.getNeighbourhood(),listingObject.getLongitude(),
                        listingObject.getLatitude(),listingObject.getRoom_type(),listingObject.getPrice(),
                        listingObject.getMinimumNights(),listingObject.getNumberOfReviews(),
                        listingObject.getLastReview(),listingObject.getReviewsPerMonth(),
                        listingObject.getCalculatedHostListingsCount(),listingObject.getAvailability365());                          
    }
    /**
     * intitializes the components of the window, according to the listing object's details
     */
    private void initComponents(String id,String name,String hostName,String hostID,
                                String neighbourhoodName,double longitude,double latitude,
                                String type, int price, int minNights,int numberOfReviews,
                                String lastReview,double reviewPerMonth, int hostListingCount,
                                int availability) 
    {
        //the labels for the detail data 
        JLabel idDataLabel, nameDataLabel,hostNameDataLabel,hostIDDataLabel,
               neighbourhoodNameDataLabel,longitudeLabel,latitudeLabel,
               roomTypeDataLabel,priceDataLabel,minNightsDataLabel,
               numberOfReviewsLabel,lastReviewLabel,reviewPerMonthLabel,
               hostListingCountLabel,availabilityLabel;
        //the labels for the data columns
        JLabel idLabel, nameLabel,hostNameLabel,hostIDLabel,
               neighbourhoodNameLabel,longitudeDataLabel,
               latitudeDataLabel,roomTypeLabel,priceLabel,
               minNightsLabel,numberOfReviewsDataLabel,
               lastReviewDataLabel,reviewPerMonthDataLabel,
               hostListingCountDataLabel,availabilityDataLabel;
        //set layouts and the frame properties
        frame = new JFrame("Listing Detail");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(0,2,15,15));
        //the following rows initializes  
        //the labels with the appropriate data
        idDataLabel = new JLabel(id);
        idLabel = new JLabel("ID:");
        
        nameDataLabel = new JLabel(name);
        nameLabel = new JLabel("Name:");
        
        hostIDLabel = new JLabel("Host ID:");
        hostIDDataLabel =  new JLabel(hostID);
        
        hostNameLabel = new JLabel("Host Name:");
        hostNameDataLabel =  new JLabel(hostName);
        
        neighbourhoodNameLabel = new JLabel("Neighbourhood Name:");
        neighbourhoodNameDataLabel =  new JLabel(neighbourhoodName);
        
        roomTypeLabel = new JLabel("Room Type:");
        roomTypeDataLabel =  new JLabel(type);
        
        priceLabel = new JLabel("Price:");
        Integer priceData = price;
        priceDataLabel =  new JLabel(priceData.toString());
        
        minNightsLabel = new JLabel("Minumum number of nights:");
        Integer minNightsData = minNights;
        minNightsDataLabel =  new JLabel(minNightsData.toString());
        
        longitudeLabel = new JLabel("Longitude:");
        Double longitudeData = longitude;
        longitudeDataLabel =  new JLabel(longitudeData.toString());
        
        latitudeLabel = new JLabel("latitude:");
        Double latitudeData = latitude;
        latitudeDataLabel =  new JLabel(latitudeData.toString());
        
        
        numberOfReviewsLabel = new JLabel("Number of Reviews:");
        Integer numReviewData = numberOfReviews;
        numberOfReviewsDataLabel =  new JLabel(numReviewData.toString());
        
        lastReviewLabel = new JLabel("Last Review:");
        lastReviewDataLabel = new JLabel(lastReview);
        
        reviewPerMonthLabel = new JLabel("review per month:");
        Double reviewPerMonthData = reviewPerMonth;
        reviewPerMonthDataLabel =  new JLabel(reviewPerMonthData.toString());
        
        hostListingCountLabel = new JLabel("Host Listing Count:");
        Integer hostListingData = hostListingCount;
        hostListingCountDataLabel =  new JLabel(hostListingData.toString());
        
        availabilityLabel = new JLabel("Avaibility:");
        Integer avaibilityData = availability;
        availabilityDataLabel =  new JLabel(avaibilityData.toString());
        
        
        
        //the following lines 
        //adds the data labels and the display labels to the frame
        frame.add(idLabel);
        frame.add(idDataLabel);

        frame.add(nameLabel);
        frame.add(nameDataLabel);
        
        frame.add(hostNameLabel);
        frame.add(hostNameDataLabel);
        
        frame.add(hostIDLabel);
        frame.add(hostIDDataLabel);
        
        frame.add(neighbourhoodNameLabel);
        frame.add(neighbourhoodNameDataLabel);
        
        frame.add(roomTypeLabel);
        frame.add(roomTypeDataLabel);
        
        frame.add(priceLabel);
        frame.add(priceDataLabel);
        
        frame.add(longitudeLabel);
        frame.add(longitudeDataLabel);
        
        frame.add(latitudeLabel);
        frame.add(latitudeDataLabel);
        
        frame.add(minNightsLabel);
        frame.add(minNightsDataLabel);
        
         frame.add(numberOfReviewsLabel);
        frame.add(numberOfReviewsDataLabel);
        
        frame.add(lastReviewLabel);
        frame.add(lastReviewDataLabel);
        
        frame.add(reviewPerMonthLabel);
        frame.add(reviewPerMonthDataLabel);
        
        frame.add(hostListingCountLabel);
        frame.add(hostListingCountDataLabel);
        
        frame.add(availabilityLabel);
        frame.add(availabilityDataLabel);
        
        //packing and setting the visibility
        frame.pack();
        frame.setVisible(true);
    }

    
}
