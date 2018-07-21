import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.*;
import java.text.*;
/**
 * The computational engine that will hold all the important 
 * functions for use in statistics and in the map panel.
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class ComputationModel
{
    private ArrayList<AirbnbListing> properties;
    private ArrayList<AirbnbListing> currentAproperties;
    private static final String ROOM_TYPE_NON_PRIVATE = "Private room";
    private static final double RESIZING_FACTOR = 0.04;
    private HashMap<String,Double> sizeLib = new HashMap<String,Double>();

    private int currentLowerLimit = 0;
    private int currentUpperLimit = 0;

    /**
     * Constructor for objects of class Computation
     */
    public ComputationModel()
    {
        properties = new ArrayList<AirbnbListing>();
        AirbnbDataLoader reader = new AirbnbDataLoader();
        properties.addAll(reader.load());
        getNeighbourhoods();
    }

    /**
     * Calculates the number of available properties within the price range.
     * @param from is a int value, holding the lower boundary price.
     * @param to is a int value, holding the upper boundary price.
     * 
     * @return the number of available properties within the price range.
     */
    public long getNumberOfAvailableProperties(int from,int to)
    {    
        return properties.stream()
        .filter(airbnb -> airbnb.getAvailability365()>0 && airbnb.getPrice() >=from && airbnb.getPrice() <=to)
        .count();
    }

    /**
     * Calculates the number of non private property in the given range
     * @Param the range over which to carry out this operation
     * @Return the number of the non private property over the range 
     */
    public long getNumberOfNonPrivate(int from,int to)
    {
        return properties.stream()
        .filter(airbnb -> !airbnb.getRoom_type().equals(ROOM_TYPE_NON_PRIVATE) && airbnb.getPrice() >=from && airbnb.getPrice() <=to)
        .count();
    }

    /**
     * Gets the neighbourhood listing for one specific neighbourhood
     * @Param the neightbourhood name
     * @Return an arraylist containing the all listing instances in that neighbourhood
     */
    public ArrayList<AirbnbListing> getNeighbourhoodListings(String neighbourhood)
    {

        return properties.stream()
        .filter(listing -> listing.getNeighbourhood().equals(neighbourhood))
        .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Calculates the price range for the neighbourhood listing
     * @Param the name of the neighbourhood, the price boundaries
     * @Return the range of prices for the neighbourhood in the given price range
     */
    public long getNeighbourhoodListingsPriceRangeSize(String neighbourhood, int from, int to)
    {

        return properties.stream()
        .filter(listing -> listing.getNeighbourhood().equals(neighbourhood) && listing.getPrice() >=from && listing.getPrice() <=to)
        .count();
    }

    /**
     * Calculates the price range for the neighbourhood listing and then creates an arraylist accordingly
     * @Param the name of the neighbourhood, the price boundaries
     * @Return an arraylist for the range of prices in the given neighbourhood
     */
    public ArrayList<AirbnbListing> getNeighbourhoodListingsPriceRange(String neighbourhood, int from, int to)
    {

        return properties.stream()
        .filter(listing -> listing.getNeighbourhood().equals(neighbourhood) && listing.getPrice() >=from && listing.getPrice() <=to)
        .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns the size of a given neighbourhood by calculating the length of the listings arraylist
     */
    public int getNeighbourSize(String neighbourhood)
    {
        ArrayList<AirbnbListing> x = getNeighbourhoodListings(neighbourhood);
        return x.size();
    }

    public int getAverageReviewScorePerProperties()
    {
        int temp = 0;
        int count=0;

        for(AirbnbListing x: properties)
        {
            temp+= x.getNumberOfReviews();
            count ++;
        }
        //System.out.println(temp+" : "+count);
        return temp/count;
    }

    public Object[][] getData()
    {
        ArrayList<AirbnbListing> list =  getDiscountedProperties();
        Object[][] tmp = new Object[list.size()][5];

        for(int i=0; i<list.size()-1;i++)
        {

            tmp[i][0]=list.get(i).getHost_name();
            tmp[i][1]=list.get(i).getPrice();
            tmp[i][2]=list.get(i).getDiscountedPrice();
            tmp[i][3]=list.get(i).getMinimumNights();

        }
        return tmp;
    }

    /**
     * Prints all data in the airbnblistings according to id and neighbourhood
     */
    public void printAirbnbDetails(String id, String neighbourhood)
    {
        System.out.println(Arrays.toString(getAirbnbDetails(id, neighbourhood)));
    }

        /**
         *  
         */
    public ArrayList<AirbnbListing> getAirbnbSearchCriteria(String criteria, String neighbourhood)
    {
        ArrayList<AirbnbListing> list = new ArrayList<AirbnbListing>();
        
       //adds all the properties to a new list that contaim the criteria in their name field and are in the same neighbourhood. 
        for(AirbnbListing x: getNeighbourhoodListings(neighbourhood))
        {
            if(x.getName().toLowerCase().contains(criteria.toLowerCase().trim()))
            {
                list.add(x);
            }
        }

        
        // when the list is empty it returns null, else returns the list.
        if(list.isEmpty())
        {
            return null;
        }
        else{
            return list;
        }
    }

    /**
     * Applies discounts to all applicable properties
     */
    public void setAirbnbDiscountedPrices()
    {
        for(AirbnbListing x: getDiscountedProperties()){
            double discount = (getDiscount(x.getId(), x.getNeighbourhood())/100.0);
            if(discount < 0)
            {
                discount = 0.0;
            }

            //System.out.println(getDiscount(x.getId(), x.getNeighbourhood())+" : "+discount);
            double newPrice =x.getPrice()*(1- discount);
            x.setDiscountedPrice(newPrice);
            //System.out.println(x.getPrice()+" : "+ x.getDiscountedPrice());
        }
    }

    /**
     * @return  the discount for a specific neighbourhood id
     */
    public int getDiscount(String id, String neighbourhood)
    {
        int discount=0; 
        if(getAirbnbDetails(id,neighbourhood) == null)
        {
            return -1;
        }
        String[] name = getAirbnbDetails(id,neighbourhood);

        for(int i=0; i< name.length;i++)
        {
            if(name[i].contains("%")&& !name[i].equals("%"))
            {
                String strDiscount = name[i];
                strDiscount = strDiscount.replaceAll("-","");
                strDiscount = strDiscount.replaceAll("%","");
                strDiscount = strDiscount.replaceAll("!","");
                strDiscount = strDiscount.replaceAll("!","");

                try{
                    discount = Integer.valueOf(strDiscount);
                    //System.out.println(discount);
                }catch(NumberFormatException ex){ // handle your exception
                    //System.out.println("Error:" + ex); 
                    return -1;
                }

                return discount;

            }
        }
        return -1;
    }

    /**
     * @return a list of all discounted airbnb instances
     */
    public ArrayList<AirbnbListing> getDiscountedProperties()
    {
        ArrayList<AirbnbListing> list = new ArrayList<AirbnbListing>();

        for(AirbnbListing x: properties)
        {
            if(x.getName().toLowerCase().contains("%") && !x.getName().toLowerCase().contains("up to"))
            {
                list.add(x);
            }
        }

        if(list.isEmpty())
        {
            return null;
        }
        else{
            return list;
        }
    }

    /**
     * @return a list of all discounted airbnb listings instances witha given criteria
     */
    public ArrayList<AirbnbListing> getDiscountedPropertiesSearch(String criteria)
    {
        ArrayList<AirbnbListing> list = new ArrayList<AirbnbListing>();

        for(AirbnbListing x: properties)
        {
            if(x.getName().toLowerCase().contains("%") && !x.getName().toLowerCase().contains("up to") && x.getName().toLowerCase().contains(criteria.toLowerCase()))
            {
                list.add(x);
            }
        }

        if(list.isEmpty())
        {
            return null;
        }
        else{
            return list;
        }
    }

    /**
     * Returns a string containing the cheapest airbnb discounted property matching the criteria
     */
    public String getCheapestDiscountedAirbnbSearch(String criteria, String neighbourhood)
    {
        double tempPrice=10000000.0;
        String tempAirbnb = "";
        String tempNeighbourhood ="";
        setAirbnbDiscountedPrices();
        ArrayList<AirbnbListing> list = getAirbnbSearchCriteria(criteria,neighbourhood);

        if(list != null)
        {
            for(AirbnbListing x : list)
            {
                if(x.getDiscountedPrice() < tempPrice && x.getDiscountedPrice() > 0.0)
                {
                    tempPrice = x.getDiscountedPrice();
                    tempAirbnb = x.getHost_name();
                    tempNeighbourhood = x.getNeighbourhood();
                    //System.out.println( "tempHost: " + tempAirbnb + "tempNeighbourhood: " + tempNeighbourhood +"tempPrice: " + tempPrice + "tempName: " + x.getName() );
                }
            }
            if(tempPrice != 10000000.0)
            {
                return tempAirbnb +":"+ tempNeighbourhood +":£" +tempPrice;
            }
            else
            {
                return "No such Airbnb matches your criteria.";
            }
        }
        else
        {
            return"No such Airbnb matches your criteria.";
        }
    }

    /**
     * Retruns the most trusted airbnb instance from the entire csv file
     */
    public String getMostTrustedAirbnb()
    {
        int  tempReviews =0;
        String tempAirbnb = "";
        String tempNeighbourhood ="";
        int tempPrice = 0;
        for(AirbnbListing x: properties)
        {
            if(x.getNumberOfReviews() > tempReviews)
            {
                tempReviews = x.getNumberOfReviews();
                //System.out.println(tempReviews);
                tempAirbnb = x.getHost_name();
                tempNeighbourhood = x.getNeighbourhood();
                tempPrice = x.getPrice();
                //System.out.println(tempAirbnb +":"+ tempNeighbourhood +":£" +tempPrice);
            }
        }
        return tempAirbnb +":"+ tempNeighbourhood +":£" +tempPrice;
    }

    /**
     * Returns a string containing the cheapest airbnb discounted property matching the criteria
     */
    public String getCheapestDiscountedAirbnbSearchAll(String criteria)
    {
        double tempPrice=10000000.0;
        String tempAirbnb = "";
        String tempNeighbourhood ="";
        setAirbnbDiscountedPrices();
        ArrayList<AirbnbListing> list = getDiscountedPropertiesSearch(criteria);

        if(list != null)
        {
            for(AirbnbListing x : list)
            {
                if(x.getDiscountedPrice() < tempPrice && x.getDiscountedPrice() > 0.0)
                {
                    tempPrice = x.getDiscountedPrice();
                    tempAirbnb = x.getHost_name();
                    tempNeighbourhood = x.getNeighbourhood();
                    //System.out.println( "tempHost: " + tempAirbnb + "tempNeighbourhood: " + tempNeighbourhood +"tempPrice: " + tempPrice + "tempName: " + x.getName() );
                }
            }
            if(tempPrice != 10000000.0)
            {
                return tempAirbnb +":"+ tempNeighbourhood +":£" +tempPrice;
            }
            else
            {
                return "No such Airbnb matches your criteria.";
            }
        }
        else
        {
            return"No such Airbnb matches your criteria.";
        }
    }

    /**
     * Runs a search for the cheapest airbnb property under the given criteria
     */
    public String getCheapestAirbnbSearch(String criteria, String neighbourhood)
    {
        int temp=10000000;
        String tempAirbnb = "";
        ArrayList<AirbnbListing> list = getAirbnbSearchCriteria(criteria,neighbourhood);
        if(list != null)
        {
            for(AirbnbListing x : list)
            {
                if(x.getPrice() < temp)
                {
                    temp = x.getPrice();
                    tempAirbnb = x.getHost_name();
                }
            }
            return tempAirbnb +":"+ temp;
        }
        else
        {
            return"No such Airbnb matches your criteria.";
        }
    }

    /**
     * Returns an array containing all the airbnb listing instances in a given id
     * and neighbourhood
     */
    public String[] getAirbnbDetails(String id,String neighbourhood)
    {

        for(AirbnbListing x: getNeighbourhoodListings(neighbourhood))
        {
            if(x.getId().equals(id))
            {
                return x.getName().split(" ");
            }

        }
        return null;
    }

    /**
     * Calculates the average price per night for a specified neighbourhood.
     * 
     * @param neighbourhood - is a String, the name for the 
     * neighbourhood to find the average price per night.
     * @param from is a int value, holding the lower boundary price.
     * @param to is a int value, holding the upper boundary price.
     * 
     * @return the average price per night for a neighbourhood if valid, 
     * else if no listings exists, will return a rogue value of -1.
     */
    public double averageNeighbourHoodPrice(String neighbourhood, int from, int to)
    {
        double cumulativeTotal = 0;
        int count =0;
        DecimalFormat df = new DecimalFormat("#.##");
        for (AirbnbListing x : getNeighbourhoodListings(neighbourhood))
        {
            if(x.getPrice() >=from && x.getPrice() <=to)
            {
                cumulativeTotal += x.getAveragePrice();
                count++;
            }
        }

        double total = cumulativeTotal/count;
        df.format(total);
        if(Double.isNaN(total))
        {
            return -1;
        }
        else{
            return total;
        }
    }

    /**
     * Returns the median price for airbnb property in the given range
     */
    public double getMedianPrice(int from,int to)
    {
        double median;
        ArrayList<Double> listing = new ArrayList<>();
        for(AirbnbListing x : properties)
        {
            if(x.getPrice() >= from && x.getPrice()<=to)
            {
                double y = (double) x.getPrice();
                listing.add(y);
            }
        }
        Collections.sort(listing);
        if (listing.size() % 2 == 0)
        {
            median = ((double)listing.get(listing.size()/2) + (double)listing.get(listing.size()/2 -1))/2;
        }
        else
        {
            median = (double) listing.get(listing.size()/2);
        }
        return median;
    }

    /**
     * Returns a hashset all the neighbourhoods
     */
    public HashSet<String> getNeighbourhoods()
    {

        return properties.stream()
        .map(listings -> listings.getNeighbourhood())
        .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Prints all neighbourhoods in the csv file
     */
    public void printNeighbourHoods()
    {
        getNeighbourhoods().forEach(neighbourhood -> System.out.println(neighbourhood));
    }

    /**
     * Calculates the most expensive neighbourhood to stay in for the specified price range.
     * @param from is a int value, holding the lower boundary price.
     * @param to is a int value, holding the upper boundary price.
     * 
     * @return the most expensive neighbourhood to stay in for the specified price range.
     */
    public String getPriciestNeighbourHood(int from, int to)
    {
        double temp=0;
        String priciestNeighbourhood="";
        if(from > to || to < from)
        {
            return null;
        }

        for(String x : getNeighbourhoods())
        {
            if(averageNeighbourHoodPrice(x,from,to)>temp)
            {
                temp = averageNeighbourHoodPrice(x,from,to);
                priciestNeighbourhood = x;

            }

        }
        return priciestNeighbourhood;
    }

    /**
     * Returns the number of properties that have at least one review in 2017
     * 
     * @param from is a int value, holding the lower boundary price.
     * @param to is a int value, holding the upper boundary price.
     * 
     * @return the number of properties that have at least one review in 2017
     */
    public int atleastOneReviewIn2017(int from, int to) throws ParseException{
        DateFormat format = new SimpleDateFormat("dd-MM-yy");
        int numIn2017 = 0;
        for(AirbnbListing x : properties){
            if(x.getPrice() >= from && x.getPrice()<=to)
            {
                String lastreview = x.getLastReview();
                if(!lastreview.isEmpty()){
                    Date date = format.parse(lastreview);
                    Calendar calender = Calendar.getInstance();
                    calender.setTime(date);
                    if(calender.get(Calendar.YEAR)==2017){
                        numIn2017++;
                    }
                }
            }
        }
        return numIn2017;
    }

    /**
     * Extract all propertise withn a certain price range into a 2D array that is used for populating
     * data for the table which shown when a icon is clicked 
     * 
     * @param name of the neighbourhood
     */
    public Object[][] extractData(String nei){
        // first get all propertise within a certain price range
        ArrayList<AirbnbListing> list = new ArrayList<>();
        list = getCurrentNeighbourhoodListings(nei);
        // the 2D array that will be returned 
        Object[][] tmp = new Object[list.size()][5];

        for(int i=0;i<list.size()-1;i++){

            tmp[i][0]=list.get(i).getHost_name();
            tmp[i][1]=list.get(i).getPrice();
            tmp[i][2]=list.get(i).getReviewsPerMonth();
            tmp[i][3]=list.get(i).getMinimumNights();
            tmp[i][4]=list.get(i).getId();

        }
        return tmp;
    }

    /**
     * Set all location icon with a size
     * 
     */
    public void setSizeForEachNeighbourHood(){

        for(String i : getNeighbourhoods())
        {

            sizeLib.put(i,getCurrentNeighbourSize(i)*RESIZING_FACTOR);

        }

    }

    /**
     * 
     * get the dictionary which store all sizes information
     */
    public HashMap<String,Double> getSizeForEachNeighbourHood(){

        return sizeLib;
    }

    /**
     * Return all available properties in a certain range of price defined by end users
     * 
     * @param upper limit of the range
     * @param lower limit of the range
     * @return all properties within this range as List
     */
    public ArrayList<AirbnbListing> getAvailableProperties(int from,int to)
    {

        ArrayList<AirbnbListing> list = new ArrayList<>();
        if(from > to)
        {
            return null;
        }
        for(AirbnbListing airbnb : properties){

            if(airbnb.getAvailability365()>0 && airbnb.getPrice() >=from && airbnb.getPrice() <=to){

                list.add(airbnb);   

            }

        }

        return list;
    }

    /**
     * Return the list of properties of a certain neighbourhood when a range is decided by end users
     * 
     * @param the name of the neighbourhood
     * @return A list contains all available properties in that neighbourhood
     * 
     */
    public ArrayList<AirbnbListing> getCurrentNeighbourhoodListings(String neighbourhood)
    {

        ArrayList<AirbnbListing> toReturn = new ArrayList<>();
        if(!neighbourhood.equals("")){
            for(AirbnbListing x : currentAproperties)
            {
                if(x.getNeighbourhood().equals(neighbourhood))
                {
                    toReturn.add(x);
                }
            }
            return toReturn;
            
        }
        else
        {
            return null;
        }
    }

    /**
     * Return the size of list of properties of a certain neighbourhood when a range is decided by end users
     * 
     * @param the name of the neighbourhood and the money range
     * @return the size of a list contains all available properties in that neighbourhood
     * 
     */
    public int getCurrentNeighbourSize(String neighbourhood)
    {
        ArrayList<AirbnbListing> x = getCurrentNeighbourhoodListings(neighbourhood);
        return x.size();
    }

    /**
     * Set the current selected range for search, Do remember to run this method when new range is selected
     * 
     * @param upper limit for the range
     * @param lower limit for the range
     */
    public void setCurrentRange(int from,int to){

        this.currentLowerLimit=from;
        this.currentUpperLimit=to;
    }

    /**
     * Set the available properties under the selected range
     */
    public void setCurrentAvaiableProperties(){

        this.currentAproperties = getAvailableProperties(currentLowerLimit,currentUpperLimit);
        //RISK OF CRASH DO NOT RUN THIS PRINTLN System.out.println("data stored is "+currentAproperties);
    }

    /**
     * Count the properties that accepting long term stay(longer than 6 months)
     * @return number of the properties
     */

    public int CalLongestStay(){
        int counter=0;
        for(AirbnbListing a : properties){
            if(a.getAvailability365()>=180){
                counter++;
            }
        }

        return counter;
    }

    /**
     * Count the properties that accepting long term stay(longer than 6 months)
     * @param upper limit for the range
     * @param lower limit for the range
     */
    public int CalLongestStay(int from,int to){
        int counter=0;
        for(AirbnbListing a : properties){
            if(a.getAvailability365()>=180 && a.getPrice()>=from && a.getPrice()<=to){
                counter++;
            }
        }

        return counter;

    }

    /**
     * Returns a specific airbnb listing by its id
     */
    public AirbnbListing getListingByID(String id)
    {
        for(AirbnbListing x:properties)
        {
            if(x.getId().equals(id))
            {
                return x;
            }
        }
        return null;
    }

}