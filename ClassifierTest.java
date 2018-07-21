import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClassifierTest.
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class ClassifierTest
{
    /**
     * Default constructor for test class ClassifierTest
     */
    public ClassifierTest()
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

    /**
     * Ensures that the classifier initializes and works as expected
     */
    @Test
    public void Test_For_Classification()
    {
        Classifier cla = new Classifier();
        double[] a = cla.Classify(241, 6356, 324, 45);
        assertNotEquals(a,0.0);//Should never equal 0 with the above input no matter the conditions
        
    }
}
