import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.text.*;

/**
 * The test class ComputationModelTest.
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class ComputationModelTest
{
    /**
     * Default constructor for test class ComputationModelTest
     */
    public ComputationModelTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    /**
     * Test to ensure most expensive neighbourhood functions correctly
     */
    public void Test_Priciest_Neighbourhood()
    {
        ComputationModel computat1 = new ComputationModel();
        assertNotNull(computat1.getPriciestNeighbourHood(0, 3500));
        assertNotNull(computat1.getPriciestNeighbourHood(100, 200));
    }

    /**
     * Test to ensure average review functions correctly
     */
    @Test
    public void Test_AverageReviewScore()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(12,computat1.getAverageReviewScorePerProperties());
    }

    @Test
     /**
     * Test to ensure most trusted airbnb functions correctly
     */
    public void Test_MostTrustedAirbnb()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals("Love LONDON:Westminster:£80",computat1.getMostTrustedAirbnb());
    }

    @Test
    public void Test_AverageNeighbourHoodPerNight_Null_Value()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(-1, computat1.averageNeighbourHoodPrice("Barking and Dagenham", 500, 600), 0.1);
        assertEquals(-1, computat1.averageNeighbourHoodPrice("Brent", 1000, 1500), 0.1);
    }

    @Test
    public void Test_NumberOfNonPrivate()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(0, computat1.getNumberOfNonPrivate(0, 0));
        assertEquals(12976, computat1.getNumberOfNonPrivate(0,100));
    }

    @Test
    public void Test_Discounts()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(-1, computat1.getDiscount("1311071", "Westminster"));
        assertEquals(-1, computat1.getDiscount("1945165", "Camden"));
        assertEquals(-1, computat1.getDiscount("", "Camden"));
        assertEquals(-1, computat1.getDiscount("1945165", ""));
        assertEquals(-1, computat1.getDiscount("", ""));
        assertEquals(20, computat1.getDiscount("326534", "Westminster"));
        assertEquals(-1, computat1.getDiscount("14841113", "Camden"));
        assertEquals(30, computat1.getDiscount("15678330", "Westminster"));
        assertEquals(-1, computat1.getDiscount("11189565", "Camden"));   
    }

    @Test
    public void Test_NeighbourHoodSize()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(142, computat1.getNeighbourSize("Barking and Dagenham"));
        assertEquals(3761, computat1.getNeighbourSize("Camden"));
        assertEquals(1003, computat1.getNeighbourSize("Ealing"));
        assertEquals(0, computat1.getNeighbourSize(""));
    }  

    @Test
    public void Test_NeighbourhoodListingsPriceRangeSize()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(11, computat1.getNeighbourhoodListingsPriceRangeSize("Barking and Dagenham", 100, 200));
        assertEquals(553, computat1.getNeighbourhoodListingsPriceRangeSize("Croydon", 0, 5000));
        assertEquals(0, computat1.getNeighbourhoodListingsPriceRangeSize("Newham", 0, 0));
        assertEquals(0, computat1.getNeighbourhoodListingsPriceRangeSize("", 100, 200));
    }

    @Test
    /**
     * Test to ensure constructor for computational model intializes without errors
     */
    public void Test_getTableData()
    {
        ComputationModel computat1 = new ComputationModel();
    }

    @Test
    public void Test_getNeighbourhood_size()
    {
        ComputationModel computat2 = new ComputationModel();
        assertEquals(553, computat2.getNeighbourSize("Croydon"));
        assertEquals(142, computat2.getNeighbourSize("Barking and Dagenham"));
        assertEquals(1151, computat2.getNeighbourSize("Newham"));
    }

    @Test
    public void Test_cheapestDiscountAirbnbSearch()
    {
        ComputationModel computat2 = new ComputationModel();
        assertEquals("Galin:Newham:£24.0", computat2.getCheapestDiscountedAirbnbSearchAll("bedroom"));
        assertEquals("No such Airbnb matches your criteria.", computat2.getCheapestDiscountedAirbnbSearchAll("daedwfe"));
        assertEquals("No such Airbnb matches your criteria.", computat2.getCheapestDiscountedAirbnbSearch("daedwfe","Newham"));
        assertEquals("No such Airbnb matches your criteria.", computat2.getCheapestDiscountedAirbnbSearch("ergjiefejwwo2od34","Camden"));

    }

    @Test
    public void testatleast1reviewin2017()
    {
        try{
            ComputationModel computat1 = new ComputationModel();
            assertEquals(19971, computat1.atleastOneReviewIn2017(0, 2000));
            assertEquals(280, computat1.atleastOneReviewIn2017(300, 400));
            assertEquals(0, computat1.atleastOneReviewIn2017(-100, 0));
            assertEquals(0, computat1.atleastOneReviewIn2017(8000, 11000));
        }catch(ParseException e){
            fail("Parse exception has occured");
        }
    }

    @Test
    public void longeststaytest()
    {
        ComputationModel computat2 = new ComputationModel();
        assertEquals(21558, computat2.CalLongestStay());
    }

    @Test
    /**
     * Test to ensure the non private property count functions correctly
     */
    public void testNonPrivateMethod()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(27597, computat1.getNumberOfNonPrivate(0, 500));
        assertEquals(366, computat1.getNumberOfNonPrivate(400, 500));
        assertEquals(8, computat1.getNumberOfNonPrivate(2000, 3000));
        assertEquals(0, computat1.getNumberOfNonPrivate(-100, 0));
        assertEquals(0, computat1.getNumberOfNonPrivate(80000, 100000));
    }

    @Test
    /**
     * Test to ensure the total count of all available property function correctly
     */
    public void Test_NumberOfAvailableProperties()
    {
        ComputationModel computat1 = new ComputationModel();
        assertEquals(11157, computat1.getNumberOfAvailableProperties(100, 200));
        assertEquals(30746, computat1.getNumberOfAvailableProperties(40, 200));
        assertEquals(0, computat1.getNumberOfAvailableProperties(0, 0));
        assertEquals(0, computat1.getNumberOfAvailableProperties(-100, 0));
        assertEquals(0, computat1.getNumberOfAvailableProperties(8000, 10000));
    }
}

