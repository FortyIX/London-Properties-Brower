
/**
 * Represents one listing of a property for rental on Airbnb.
 * This is essentially one row in the data table. Each column
 * has a corresponding field.
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */

public class AirbnbListing {
    /**
     * The id and name of the individual property
     */
    private String id;
    private String name;
    /**
     * The id and name of the host for this listing.
     * Each listing has only one host, but one host may
     * list many properties.
     */
    private String host_id;
    private String host_name;

    /**
     * The grouped location to where the listed property is situated.
     * For this data set, it is a london borough.
     */
    private String neighbourhood;

    /**
     * The location on a map where the property is situated.
     */
    private double latitude;
    private double longitude;

    /**
     * The type of property, either "Private room" or "Entire Home/apt".
     */
    private String room_type;

    /**
     * The price per night's stay
     */
    private int price;
    private double averagePricePerNight;
    private double discountedPrice = -1.0;

    /**
     * The minimum number of nights the listed property must be booked for.
     */
    private int minimumNights;
    private int numberOfReviews;

    /**
     * The date of the last review, but as a String
     */
    private String lastReview;
    private double reviewsPerMonth;

    /**
     * The total number of listings the host holds across AirBnB
     */
    private int calculatedHostListingsCount;
    /**
     * The total number of days in the year that the property is available for
     */
    private int availability365;

    public AirbnbListing(String id, String name, String host_id,
    String host_name, String neighbourhood, double latitude,
    double longitude, String room_type, int price,
    int minimumNights, int numberOfReviews, String lastReview,
    double reviewsPerMonth, int calculatedHostListingsCount, int availability365) {
        this.id = id;
        this.name = name;
        this.host_id = host_id;
        this.host_name = host_name;
        this.neighbourhood = neighbourhood;
        this.latitude = latitude;
        this.longitude = longitude;
        this.room_type = room_type;
        this.price = price; 
        this.averagePricePerNight = price/minimumNights;
        this.minimumNights = minimumNights;
        this.numberOfReviews = numberOfReviews;
        this.lastReview = lastReview;
        this.reviewsPerMonth = reviewsPerMonth;
        this.calculatedHostListingsCount = calculatedHostListingsCount;
        this.availability365 = availability365;

    }
    /**
     * @return the id for the airbnb listing
     */
    public String getId() {
        return id;
    }
    /**
     * @return the name for the airbnb listing
     */
    public String getName() {
        return name;
    }
    /**
     * @return the host id for the airbnb listing
     */
    public String getHost_id() {
        return host_id;
    }
    /**
     * @return the hostname for the airbnb listing
     */
    public String getHost_name() {
        return host_name;
    }
    /**
     * @return the neighborhood name for the airbnb listing
     */
    public String getNeighbourhood() {
        return neighbourhood;
    }
    /**
     * @return the latitude for the airbnb listing
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * @return the longitude for the airbnb listing
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * @return the room type for the airbnb listing
     */
    public String getRoom_type() {
        return room_type;
    }
    /**
     * @return the price for the airbnb listing
     */
    public int getPrice() {
        return price;
    }
    /**
     * @return the average price for the airbnb listing
     */
    public double getAveragePrice(){
        return averagePricePerNight;
    }
    /**
     * @return the min nights for the airbnb listing
     */
    public int getMinimumNights() {
        return minimumNights;
    }
    /**
     * @return the number of reviews for the airbnb listing
     */
    public int getNumberOfReviews() {
        return numberOfReviews;
    }
    /**
     * @return the last review for the airbnb listing
     */
    public String getLastReview() {
        return lastReview;
    }
    /**
     * @return the reviews per month for the airbnb listing
     */
    public double getReviewsPerMonth() {
        return reviewsPerMonth;
    }
    /**
     * @return the host listing count for the airbnb listing
     */
    public int getCalculatedHostListingsCount() {
        return calculatedHostListingsCount;
    }
    /**
     * @return the availability for the airbnb listing
     */
    public int getAvailability365() {
        return availability365;
    }
    
    public double getDiscountedPrice()
    {
        return discountedPrice;
    }
    
    public void setDiscountedPrice(double newPrice)
    {
        this.discountedPrice = newPrice;
    }
    
    @Override
    public String toString() {
        return "AirbnbListing{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", host_id='" + host_id + '\'' +
        ", host_name='" + host_name + '\'' +
        ", neighbourhood='" + neighbourhood + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", room_type='" + room_type + '\'' +
        ", price=" + price +
        ", minimumNights=" + minimumNights +
        ", numberOfReviews=" + numberOfReviews +
        ", lastReview='" + lastReview + '\'' +
        ", reviewsPerMonth=" + reviewsPerMonth +
        ", calculatedHostListingsCount=" + calculatedHostListingsCount +
        ", availability365=" + availability365 +
        '}';
    }

    public String getString() {
        return  id + "," + 
        name + "," + 
        host_id + "," + 
        host_name + "," + 
        neighbourhood + "," + 
        latitude + "," +
        longitude + "," +
        room_type + "," + 
        price + "," +
        minimumNights + "," +
        numberOfReviews + "," +
        lastReview + "," +
        reviewsPerMonth + "," +
        calculatedHostListingsCount + "," +
        availability365;
    }
}
