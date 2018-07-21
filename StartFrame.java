import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.NumberFormatException;
import java.util.*;
import java.io.IOException;
import javax.swing.event.*;
/**
 * This class represents the main frame of the entire program
 * to start the program, create an object of this class
 * 
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class StartFrame extends ViewController 
{
    //the computation object which facilates all the computations regarding the data
    static public ComputationModel compute;
    //primary panels for the GUI
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel topPanel2;
    private JPanel bottomPanel;

    //subpanels, which fit in the center of the main panel's border layout
    private JPanel welcomePanel;
    private JPanel mapPanel;
    private JPanel statisticsPanel;
    private JPanel finalPanel;

    //major components in the GUI
    private JButton scrollButtonRight;
    private JButton scrollButtonLeft;
    private JComboBox comboFrom;
    private JComboBox comboTo;

    // tell user program is traning data not frozen
    private JLabel loadingIndicator;

    //sub panel in statistics subpanel which enables navigation between the 8 different statistics.
    private JPanel statContentPane;
    //current indexes of the panels
    private int currentPanelIndex;
    private int currentStatisticsPanelIndex = 0;

    //array holding names of the borough
    private String[] arrayBo = new String[] {"Croydon", "sutton", "merton", "kingston", "richmond", "hounslow", 
            "hillingdon", "ealing ", "harrow", "brent", "barnet", "enfield", "haringey", "Waltham Forest",
            "redbridge", "havering", "barkngAndDagenham ", "bexley", "newham", "greenwich", "lewisham", 
            "southwark", "lambeth", "wordworth", "chelsea", "camden", "bromley", "westminister", "hammersmithAndFullham", 
            "islington", "hackney", "towerHamlets", "City"};

    // data holder for predictor
    private int pr,min,ava,bo;

    // data stored for error max
    private String errorMaxholder;
    /**
     * The constructor
     * calls method which creates the necessary panels required for the GUI initially
     */
    public StartFrame()
    {
        // init the loading indicator with content
        loadingIndicator=new JLabel("",SwingConstants.CENTER);

        
        compute = new ComputationModel();
        makeTopPanel();
        makeBottomPanel();
        makeWelcomePanel();
        makeMainPanel();
        currentPanelIndex = 1;
        makeFrame();

    }
    /**
     * Adds all the main panels to the main panel of the GUI, this includes the main center panel, top and bottom panels
     */
    private void makeMainPanel()
    {
        mainPanel = new JPanel(new BorderLayout(6,6));
        mainPanel.setPreferredSize(new Dimension(535,600));;
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);
        mainPanel.add(welcomePanel,BorderLayout.CENTER);
    }

    /**
     * Creates the top panel with two combo boxes for price selection
     */
    private void makeTopPanel()
    {
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //setting the border
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)));

        //arrays holding the prices to be displayed in the combobox
        String[] arrayFrom = new String[] {"-","5","10","15","20","25","30","35", "40","45","50","55","60","65","75","85","95", "115", "140", "165","190","215","240","265","290", "500","1000","2000","3000","4000","5000","6000","7000"};
        String[] arrayTo = new String[] {"-","150" ,"175", "200", "225","250","275","300","325","350","370","400","550","600","700","800","1050","1500","2500","3500","4500","5500","7500","15000"};

        //initializing the components for the top panel (ComboBox and the labels)
        comboFrom = new JComboBox(arrayFrom);
        JLabel labelFrom = new JLabel("From £: ");
        comboTo = new JComboBox(arrayTo);
        JLabel labelTo = new JLabel("To £: ");
        //adding the components to the top panel
        topPanel.add(labelFrom);
        topPanel.add(comboFrom);
        topPanel.add(labelTo);
        topPanel.add(comboTo);
        //the action listeners for the combobox
        comboFrom.addActionListener(event -> verifyComboInput());
        comboTo.addActionListener(event -> verifyComboInput());
    }

    /**
     * Validates the combo boxes and triggers new events if valid inputs are entered
     * into the combo boxes
     */
    private void verifyComboInput()
    {   //if combobox is not in the input selection stage
        if (comboFrom.getSelectedItem().equals("-") == false && comboTo.getSelectedItem().equals("-") ==false) 
        {
            String from = (String) comboFrom.getSelectedItem();
            String to = (String) comboTo.getSelectedItem();
            //if combo pair input is invalid
            if(Integer.parseInt(from)>Integer.parseInt(to))
            {
                JOptionPane.showMessageDialog(null, "The Selected Price Range Is Invalid");
                comboFrom.setSelectedItem("-");
                comboTo.setSelectedItem("-");
                scrollButtonLeft.setEnabled(false);
                scrollButtonRight.setEnabled(false);
                makeWelcomePanel();
                changeMainCenterPanel(welcomePanel);
                pack();
            }
            else
            {//combo input valid
                compute.setCurrentRange(Integer.parseInt(from),Integer.parseInt(to));
                compute.setCurrentAvaiableProperties();
                compute.setSizeForEachNeighbourHood();

                makeMapPanel();
                makeStatisticsPanel();
                //makeFinalPanel();
                if(!mainPanel.getComponent(2).getName().equals("Stats")){
                    changeMainCenterPanel(mapPanel);
                    scrollButtonLeft.setEnabled(false);
                    scrollButtonRight.setEnabled(true);
                    setSize(800,500);
                    setResizable(false);
                }else{
                    changeMainCenterPanel(statisticsPanel);
                    setResizable(true);
                }
                pack();
                currentPanelIndex=2;
            }
        }
        //if invalid input  of "-" in the price range is selected in combo
        else if (comboFrom.getSelectedItem().equals("-") == true || comboTo.getSelectedItem().equals("-") ==true)
        {
            scrollButtonLeft.setEnabled(false);
            scrollButtonRight.setEnabled(false);
            makeWelcomePanel();
            changeMainCenterPanel(welcomePanel);
            pack();
        }
    }

    /**
     * Create the welcome page and add a welcome image in the center
     */
    private void makeWelcomePanel()
    {
        OverlayLayout stackPane;
        welcomePanel = new JPanel();
        welcomePanel.setName("welcome");
        welcomePanel.setSize(800,700);
        stackPane = new OverlayLayout(welcomePanel);
        welcomePanel.setLayout(stackPane);
        try
        {
            //try loading the picture
            Image myPicture = ImageIO.read(new File("guiapps/welcome.png"));

            myPicture=myPicture.getScaledInstance(welcomePanel.getWidth()-10,welcomePanel.getHeight()-200,Image.SCALE_REPLICATE);
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            welcomePanel.add(picLabel,0);
        }
        catch(IOException e)
        {
            //first check if there is an IO exception
            System.out.println("Welcome image file not found");
            e.printStackTrace();
        }
        catch(Exception e)
        {
            //if there is any other unknown exception
            System.out.println("Unknown error!");
            e.printStackTrace();
        }
    }

    /**
     *  This function produces the panel for the properties type anaysizer, which is also 
     *  know as "the forth panel " or "the final panel "   
     *  
     */
    private void makeFinalPanel(){

        // set up the Classifier and train the classifier 
        Classifier cls = new Classifier();
        errorMaxholder = cls.getErrorMaxholder();

        //set up the master panel
        finalPanel = new JPanel(new  BorderLayout());
        finalPanel.setName("Final");
        finalPanel.setSize(535,600);

        //make a container at centre of the master panel 
        JPanel Components_Container= new JPanel(new GridBagLayout());

        /// make a BoxLayout to display components in stack fashion
        JPanel pageManager= new JPanel();
        pageManager.setLayout(new BoxLayout(pageManager,BoxLayout.Y_AXIS));

        // make a panel for titles to display
        JPanel titleLayoutManager= new JPanel();
        titleLayoutManager.setLayout(new BoxLayout(titleLayoutManager,BoxLayout.Y_AXIS));

        /// First row for neightbourhood
        //niBan (一番(いちばん))  means the second 
        JPanel ichiBanPanel = new JPanel(new GridLayout());
        JPanel innerIchiBanPanelManager = new JPanel(new GridLayout());

        JLabel labelBo = new JLabel("NeighbourHood");
        JComboBox comboBo = new JComboBox(arrayBo);

        ichiBanPanel.add(labelBo);
        ichiBanPanel.add(comboBo);

        /// Second row for Price
        //niBan (二番(にばん))  means the second 
        JPanel niBanPanel = new JPanel(new GridLayout());
        JLabel labelPr = new JLabel("Price");
        JTextField textPr = new JTextField(10);

        niBanPanel.add(labelPr);
        niBanPanel.add(textPr);

        //Third row for Minimun Night available
        //sanBan (三番(さんばん))  means the third 
        JPanel sanBanPanel = new JPanel(new GridLayout());
        JLabel labelMin = new JLabel("Minimum Night available");
        JTextField textMin = new JTextField(10);

        sanBanPanel.add(labelMin);
        sanBanPanel.add(textMin);

        //Forth row for Availability throuhout the year
        //yonBan (四番(よんばん))  means the forth  
        JPanel yonBanPanel = new JPanel(new GridLayout());
        JLabel labelAva = new JLabel("Availability");
        JTextField textAva= new JTextField(10);

        yonBanPanel.add(labelAva);
        yonBanPanel.add(textAva);

        //Fifth row for Availability throuhout the year
        //goBan (四番(ごばん))  means the fifth
        JPanel goBanPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submit = new JButton("Tell me");
        submit.addActionListener(

            action -> {

                if(ValidateInput(textPr.getText(),textMin.getText(),textAva.getText())){

                    try{

                        convertAllInput(textPr.getText(),textMin.getText(),textAva.getText(),comboBo);
                        classificationCompleteHandler(cls.Classify(bo,pr,min, ava));

                    }
                    catch(ClassifierNotConfigeredProperlyException e){
                        JOptionPane.showMessageDialog(mainPanel,
                            "It seems that Classifier have a problem,Please contact developers ",
                            "Fail to Classifier",
                            JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        );

        goBanPanel.add(submit);

        //sixthrow for Availability throuhout the year
        //rukoBan (六番(ろくばん))  means the sixth  
        JPanel rokuBanPanel = new JPanel();
        JLabel labWel = new JLabel("Properties-type Analyzer");
        labWel.setFont(new Font("Arial", Font.BOLD, 20));
        rokuBanPanel.add(labWel);

        //seventh row for Availability throuhout the year
        //nanaBan (七番(ななばん))  means the seventh  
        JPanel nanaBanPanel = new JPanel();
        JLabel labedes = new JLabel("<html>This AI program learn from the Airbnb dataset and can analysis all<br/> condtions you set  then tell you what type of properties would meet<br> your conditions </html> ");
        nanaBanPanel.add(labedes);

        JButton about= new JButton("How does it work ?");
        about.addActionListener(

            action -> {

                JOptionPane.showMessageDialog(mainPanel,
                    "<html>This program take all inputs into the trained classifier,The classifier has been <br/>trained with a huge amount of data of properties (~50000)"+
                    "using Decision tree learning<br/>(A Machine Learning algorithm). In Decision  tree learning,all leaves all representes all possible outcome<br/>"+
                    "The tree was generated by recursively spiliting based on the Information gain of each attributes.<br/> Please refer to the ID3 or C4.5 Algorithm "+
                    " if you want to learn more about how it works <br> when the program runs,the input goes into"+
                    "the tree and find a leaf where under all condtions <br/>Therefore, all input does not need to follow the data that actually exist, it can be any number"+
                    "<br/>As its a trained model </html>",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);

            });

        pageManager.add(ichiBanPanel);
        pageManager.add(niBanPanel);
        pageManager.add(sanBanPanel);
        pageManager.add(yonBanPanel);
        pageManager.add(goBanPanel);

        titleLayoutManager.add(rokuBanPanel);
        titleLayoutManager.add(nanaBanPanel);

        Components_Container.add(pageManager);

        finalPanel.add(titleLayoutManager,BorderLayout.NORTH);
        finalPanel.add(Components_Container,BorderLayout.CENTER);
        finalPanel.add(about,BorderLayout.SOUTH);

        //hide the loading bar
        loadingIndicator.setText("");
    }

    /**
     * 
     * This fuction convert all string (and selection) made in input fields into Integer which
     * can be accepted by the classifier
     * 
     * @param price input 
     * @param minimum available night input 
     * @param available night input
     * @param Bo the actual JcomboBox htat indicate the selection for neighbourhood
     * 
     * 
     */

    private void convertAllInput(String pr,String min ,String ava, JComboBox Bo){

        this.pr = Integer.parseInt(pr);
        this.min= Integer.parseInt(min);
        this.ava = Integer.parseInt(ava);
        this.bo=Bo.getSelectedIndex()+1;

    }

    /**
     * This function validates the input of user to ensure that they dont leave it blank 
     * or put some wired stuff into input fields
     * 
     * @param price input 
     * @param minimum available night input 
     * @param available night input
     * 
     * @return whether inputs are vailed or not
     * 
     */
    private boolean ValidateInput(String pr,String min ,String ava){

        boolean canClassify = false;

        if(!pr.trim().isEmpty() && !min.isEmpty()&& !ava.isEmpty()){

            if(pr.matches("^\\d+(\\.\\d+)?") && min.matches("^\\d+(\\.\\d+)?") && ava.matches("^\\d+(\\.\\d+)?")){

                canClassify = true;

            }

            else{

                JOptionPane.showMessageDialog(mainPanel,
                    "Only number is permitted for the value above",
                    "Invailed input",
                    JOptionPane.ERROR_MESSAGE);

            }
        }
        else{

            JOptionPane.showMessageDialog(mainPanel,
                "Values cannot be empty",
                "Invailed input",
                JOptionPane.ERROR_MESSAGE);

        }
        return canClassify;
    }

    /**
     * This method runs after the classification is finished, as there are some error on the 
     * origin returned value, so this function classify the returned value further base on 
     * which class returned value is closer.and then show the result in a alert
     * 
     * @param the result produced by classifier
     */

    private void classificationCompleteHandler(double[] result) throws ClassifierNotConfigeredProperlyException{

        Object[] options = {"Advanced Detail","Close" };

        String type = "ERROR";
        int biggestIndex = 10; 

        double biggestNumber = result[0];

        for(int i=0;i<=2;i++){

            if(result[i] >= biggestNumber){
                biggestNumber = result[i];
                biggestIndex = i;
            }

        }

        switch(biggestIndex){

            case 0:
            type = "Private home";
            break;

            case 1:
            type = "Entire home/apt";
            break;

            case 2:
            type = "Shared home";
            break;

            default:
            throw new ClassifierNotConfigeredProperlyException();

        }

        int in = JOptionPane.showOptionDialog(mainPanel,
                "The property is likely to be" + " "+type,
                "Result",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);

        if(in == JOptionPane.OK_OPTION ){
            JOptionPane.showMessageDialog(mainPanel,
                "<html>"+"Probability of Private home "+":"+" "+(int)(result[0]*100)+"%"+"("+result[0]+")"+"<br>"+
                ""+"Probability of Entire home/apt"+":"+" "+(int)(result[1]*100)+"%"+"("+result[1]+")"+"<br>"+
                ""+"Probability of Shared home "+":"+" "+(int)(result[2]*100)+"%"+"("+result[2]+")"+"<br><br><br>"
                +errorMaxholder+"",
                "Advanced Result",
                JOptionPane.INFORMATION_MESSAGE);

        }
    }

    /**
     * Creates the map panel with and background image with boundaries for all the 
     * neighbourhood in London. Then adds transaparent markers on the map for every
     * neighbourhood. The markers dynamically resize according to the number of properties 
     * in the price range selected by the combobox,
     * and have a minimum and maximum size to ensure visibility. 
     * A marker is not shown if there is no property in a specific neighbourhood
     */
    private void makeMapPanel()
    {
        //declaring the markers for the all the region
        Marker barkngAndDagenham;
        Marker barnet;
        Marker bexley;
        Marker brent;
        Marker bromley;
        Marker camden;
        Marker chelsea;
        Marker city;
        Marker croydon;
        Marker ealing;
        Marker enfield;
        Marker greenwich;
        Marker hackney;
        Marker hammersmithAndFullham;
        Marker haringey;
        Marker harrow;
        Marker havering;
        Marker hillingdon;
        Marker hounslow;
        Marker islington;
        Marker kingston;
        Marker lambeth;
        Marker lewisham;
        Marker merton;
        Marker newham;
        Marker redbridge;
        Marker richmond;
        Marker southwark;
        Marker sutton;
        Marker towerHamlets;
        Marker walthamForest;
        Marker westminister;
        Marker wordworth;
        //the labels and the pane
        JLabel boroughMap;
        JLayeredPane mainMapPanel;

        mapPanel = new JPanel(new BorderLayout(6,6));
        mapPanel.setName("Map");
        mapPanel.setSize(800,700);
        String fromString = (String) comboFrom.getSelectedItem();
        String toString = (String) comboTo.getSelectedItem();
        JLabel priceText = new JLabel("Price Range Selected: from  £"+fromString+" to  £" +toString);
        priceText.setFont(new Font("Arial", Font.BOLD, 20));
        mapPanel.add(priceText,BorderLayout.NORTH);
        mainMapPanel = new javax.swing.JLayeredPane();
        int from = Integer.parseInt(fromString);
        int to = Integer.parseInt(toString);
        //initializing the markers and the panels
        mainMapPanel = new javax.swing.JLayeredPane();
        croydon = new Marker(compute.getSizeForEachNeighbourHood().get("Croydon"),"Croydon");
        sutton = new Marker(compute.getSizeForEachNeighbourHood().get("Sutton"),"Sutton");
        merton = new Marker(compute.getSizeForEachNeighbourHood().get("Merton"),"Merton");
        kingston = new Marker(compute.getSizeForEachNeighbourHood().get("Kingston upon Thames"),"Kingston upon Thames");
        richmond = new Marker(compute.getSizeForEachNeighbourHood().get("Richmond upon Thames"),"Richmond upon Thames");
        hounslow = new Marker(compute.getSizeForEachNeighbourHood().get("Hounslow"),"Hounslow");
        hillingdon = new Marker(compute.getSizeForEachNeighbourHood().get("Hillingdon"),"Hillingdon");
        ealing = new Marker(compute.getSizeForEachNeighbourHood().get("Ealing"),"Ealing");
        harrow = new Marker(compute.getSizeForEachNeighbourHood().get("Harrow"),"Harrow");
        brent = new Marker(compute.getSizeForEachNeighbourHood().get("Brent"),"Brent");
        barnet = new Marker(compute.getSizeForEachNeighbourHood().get("Barnet"),"Barnet");
        enfield = new Marker(compute.getSizeForEachNeighbourHood().get("Enfield"),"Enfield");
        haringey = new Marker(compute.getSizeForEachNeighbourHood().get("Haringey"),"Haringey");
        walthamForest = new Marker(compute.getSizeForEachNeighbourHood().get("Waltham Forest"),"Waltham Forest");
        redbridge = new Marker(compute.getSizeForEachNeighbourHood().get("Redbridge"),"Redbridge");
        havering = new Marker(compute.getSizeForEachNeighbourHood().get("Havering"),"Havering");
        barkngAndDagenham = new Marker(compute.getSizeForEachNeighbourHood().get("Barking and Dagenham"),"Barking and Dagenham");
        bexley = new Marker(compute.getSizeForEachNeighbourHood().get("Bexley"),"Bexley");
        newham = new Marker(compute.getSizeForEachNeighbourHood().get("Newham"),"Newham");
        greenwich = new Marker(compute.getSizeForEachNeighbourHood().get("Greenwich"),"Greenwich");
        lewisham = new Marker(compute.getSizeForEachNeighbourHood().get("Lewisham"),"Lewisham");
        southwark = new Marker(compute.getSizeForEachNeighbourHood().get("Southwark"),"Southwark");
        lambeth = new Marker(compute.getSizeForEachNeighbourHood().get("Lambeth"),"Lambeth");
        wordworth = new Marker(compute.getSizeForEachNeighbourHood().get("Wandsworth"),"Wandsworth");
        chelsea = new Marker(compute.getSizeForEachNeighbourHood().get("Kensington and Chelsea"),"Kensington and Chelsea");
        camden = new Marker(compute.getSizeForEachNeighbourHood().get("Camden"),"Camden");
        bromley = new Marker(compute.getSizeForEachNeighbourHood().get("Bromley"),"Bromley");
        westminister = new Marker(compute.getSizeForEachNeighbourHood().get("Westminster"),"Westminster");
        hammersmithAndFullham = new Marker(compute.getSizeForEachNeighbourHood().get("Hammersmith and Fulham"),"Hammersmith and Fulham");
        islington =new Marker(compute.getSizeForEachNeighbourHood().get("Islington"),"Islington");
        hackney = new Marker(compute.getSizeForEachNeighbourHood().get("Hackney"),"Hackney");
        towerHamlets = new Marker(compute.getSizeForEachNeighbourHood().get("Tower Hamlets"),"Tower Hamlets");
        city = new Marker(compute.getSizeForEachNeighbourHood().get("City of London"),"City of London");
        boroughMap = new javax.swing.JLabel();

        //setting marker sizes for each of the markers, and also adding the action listeners
        //to pop up the listing window upon a marker being clicked
        croydon.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Croydon"));
        mainMapPanel.add(croydon);
        croydon.setBounds(270, 330,(int)croydon.getMarkerSize(),(int)croydon.getMarkerSize());
        croydon.addActionListener(event -> {new ListingFrame("Croydon",from,to);});

        sutton.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Sutton"));
        mainMapPanel.add(sutton);
        sutton.setBounds(200, 340,(int)sutton.getMarkerSize(),(int)sutton.getMarkerSize());
        sutton.addActionListener(event -> {new ListingFrame("Sutton",from,to);});

        merton.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Merton"));
        mainMapPanel.add(merton);
        merton.setBounds(190, 290,(int)merton.getMarkerSize(),(int)merton.getMarkerSize());
        merton.addActionListener(event -> {new ListingFrame("Merton",from,to);});

        kingston.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Kingston upon Thames"));
        mainMapPanel.add(kingston);
        kingston.setBounds(140, 300,(int)kingston.getMarkerSize(),(int)kingston.getMarkerSize());
        kingston.addActionListener(event -> {new ListingFrame("Kingston upon Thames",from,to);});

        richmond.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Richmond upon Thames"));
        mainMapPanel.add(richmond);
        richmond.setBounds(120, 250, (int)richmond.getMarkerSize(),(int)richmond.getMarkerSize());
        richmond.addActionListener(event -> {new ListingFrame("Richmond upon Thames",from,to);});

        hounslow.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Hounslow"));
        mainMapPanel.add(hounslow);
        hounslow.setBounds(80, 230, (int)hounslow.getMarkerSize(),(int)hounslow.getMarkerSize());
        hounslow.addActionListener(event -> {new ListingFrame("Hounslow",from,to);});

        hillingdon.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Hillingdon"));
        mainMapPanel.add(hillingdon);
        hillingdon.setBounds(30, 170,(int)hillingdon.getMarkerSize(),(int)hillingdon.getMarkerSize());
        hillingdon.addActionListener(event -> {new ListingFrame("Hillingdon",from,to);});

        ealing.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Ealing"));
        mainMapPanel.add(ealing);
        ealing.setBounds(110, 180, (int)ealing.getMarkerSize(),(int)ealing.getMarkerSize());
        ealing.addActionListener(event -> {new ListingFrame("Ealing",from,to);});

        harrow.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Harrow"));
        mainMapPanel.add(harrow);
        harrow.setBounds(90, 100, (int)harrow.getMarkerSize(),(int)harrow.getMarkerSize());
        harrow.addActionListener(event -> {new ListingFrame("Harrow",from,to);});

        brent.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Brent"));
        mainMapPanel.add(brent);
        brent.setBounds(140, 140, (int)brent.getMarkerSize(),(int)brent.getMarkerSize());
        brent.addActionListener(event -> {new ListingFrame("Brent",from,to);});

        barnet.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Barnet"));
        mainMapPanel.add(barnet);
        barnet.setBounds(180, 90, (int)barnet.getMarkerSize(),(int)barnet.getMarkerSize());
        barnet.addActionListener(event -> {new ListingFrame("Barnet",from,to);});

        enfield.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Enfield"));
        mainMapPanel.add(enfield);
        enfield.setBounds(260, 50, (int)enfield.getMarkerSize(),(int)enfield.getMarkerSize());
        enfield.addActionListener(event -> {new ListingFrame("Enfield",from,to);});

        haringey.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Haringey"));
        mainMapPanel.add(haringey);
        haringey.setBounds(240, 100, (int)haringey.getMarkerSize(),(int)haringey.getMarkerSize());
        haringey.addActionListener(event -> {new ListingFrame("Haringey",from,to);});

        walthamForest.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Waltham Forest"));
        mainMapPanel.add(walthamForest);
        walthamForest.setBounds(300, 110, (int)walthamForest.getMarkerSize(),(int)walthamForest.getMarkerSize());
        walthamForest.addActionListener(event -> {new ListingFrame("Waltham Forest",from,to);});

        redbridge.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Redbridge"));
        mainMapPanel.add(redbridge);
        redbridge.setBounds(360, 110, (int)redbridge.getMarkerSize(),(int)redbridge.getMarkerSize());
        redbridge.addActionListener(event -> {new ListingFrame("Redbridge",from,to);});

        havering.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Havering"));
        mainMapPanel.add(havering);
        havering.setBounds(450, 130, (int)havering.getMarkerSize(),(int)havering.getMarkerSize());
        havering.addActionListener(event -> {new ListingFrame("Havering",from,to);});

        barkngAndDagenham.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Barking and Dagenham"));
        mainMapPanel.add(barkngAndDagenham);
        barkngAndDagenham.setBounds(390, 160, (int)barkngAndDagenham.getMarkerSize(),(int)barkngAndDagenham.getMarkerSize());
        barkngAndDagenham.addActionListener(event -> {new ListingFrame("Barking and Dagenham",from,to);});

        bexley.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Bexley"));
        mainMapPanel.add(bexley);
        bexley.setBounds(400, 230, (int)bexley.getMarkerSize(),(int)bexley.getMarkerSize());
        bexley.addActionListener(event -> {new ListingFrame("Bexley",from,to);});

        newham.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Newham"));
        mainMapPanel.add(newham);
        newham.setBounds(330, 170,(int)newham.getMarkerSize(),(int)newham.getMarkerSize());
        newham.addActionListener(event -> {new ListingFrame("Newham",from,to);});

        greenwich.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Greenwich"));
        mainMapPanel.add(greenwich);
        greenwich.setBounds(340, 220, (int) greenwich.getMarkerSize(),(int) greenwich.getMarkerSize());
        greenwich.addActionListener(event -> {new ListingFrame("Greenwich",from,to);});

        lewisham.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Lewisham"));
        mainMapPanel.add(lewisham);
        lewisham.setBounds(300, 250,(int) lewisham.getMarkerSize(),(int) lewisham.getMarkerSize());
        lewisham.addActionListener(event -> {new ListingFrame("Lewisham",from,to);});

        southwark.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Southwark"));
        mainMapPanel.add(southwark);
        southwark.setBounds(260, 210, (int)southwark.getMarkerSize(),(int)southwark.getMarkerSize());
        southwark.addActionListener(event -> {new ListingFrame("Southwark",from,to);});

        lambeth.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Lambeth"));
        mainMapPanel.add(lambeth);
        lambeth.setBounds(240, 260,(int)lambeth.getMarkerSize(),(int)lambeth.getMarkerSize());
        lambeth.addActionListener(event -> {new ListingFrame("Lambeth",from,to);});

        wordworth.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Wandsworth"));
        mainMapPanel.add(wordworth);
        wordworth.setBounds(200, 250,(int) wordworth.getMarkerSize(),(int) wordworth.getMarkerSize());
        wordworth.addActionListener(event -> {new ListingFrame("Wandsworth",from,to);});

        chelsea.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Kensington and Chelsea"));
        mainMapPanel.add(chelsea);
        chelsea.setBounds(190, 200, (int)chelsea.getMarkerSize(),(int)chelsea.getMarkerSize());
        chelsea.addActionListener(event -> {new ListingFrame("Kensington and Chelsea",from,to);});

        camden.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Camden"));
        mainMapPanel.add(camden);
        camden.setBounds(210, 150, (int)chelsea.getMarkerSize(),(int)chelsea.getMarkerSize());
        camden.addActionListener(event -> {new ListingFrame("Camden",from,to);});

        bromley.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Bromley"));
        mainMapPanel.add(bromley);
        bromley.setBounds(340, 320, (int)bromley.getMarkerSize(),(int)bromley.getMarkerSize());
        bromley.addActionListener(event -> {new ListingFrame("Bromley",from,to);});

        westminister.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Westminster"));
        mainMapPanel.add(westminister);
        westminister.setBounds(210, 190, (int)westminister.getMarkerSize(),(int)westminister.getMarkerSize());
        westminister.addActionListener(event -> {new ListingFrame("Westminster",from,to);});

        hammersmithAndFullham.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Hammersmith and Fulham"));
        mainMapPanel.add(hammersmithAndFullham);
        hammersmithAndFullham.setBounds(170, 200,(int)hammersmithAndFullham.getMarkerSize(),(int)hammersmithAndFullham.getMarkerSize());
        hammersmithAndFullham.addActionListener(event -> {new ListingFrame("Hammersmith and Fulham",from,to);});

        islington.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Islington"));
        mainMapPanel.add(islington);
        islington.setBounds(250, 160, (int)islington.getMarkerSize(),(int)islington.getMarkerSize());
        islington.addActionListener(event -> {new ListingFrame("Islington",from,to);});

        hackney.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Hackney"));
        mainMapPanel.add(hackney);
        hackney.setBounds(280, 150, (int)hackney.getMarkerSize(),(int)hackney.getMarkerSize());
        hackney.addActionListener(event -> {new ListingFrame("Hackney",from,to);});

        towerHamlets.setMarkerSize(compute.getSizeForEachNeighbourHood().get("Tower Hamlets"));
        mainMapPanel.add(towerHamlets);
        towerHamlets.setBounds(290, 180, (int)towerHamlets.getMarkerSize(),(int)towerHamlets.getMarkerSize());
        towerHamlets.addActionListener(event -> {new ListingFrame("Tower Hamlets",from,to);});

        city.setMarkerSize(compute.getSizeForEachNeighbourHood().get("City of London"));
        mainMapPanel.add(city);
        city.setBounds(260, 190,(int)city.getMarkerSize(),(int)city.getMarkerSize());
        city.addActionListener(event -> {new ListingFrame("City of London",from,to);});
        //set the icon for the markers
        boroughMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiapps/boroughs.png"))); 
        mainMapPanel.add(boroughMap);
        boroughMap.setBounds(0, 0, 550, 431);

        mapPanel.add(mainMapPanel,BorderLayout.CENTER);
        pack();
    }

    /**
     * Panel containing a card layout to display all the statistics. Two large Jbuttons
     * on either side to allow navigation between diferrent stats and all main stats
     * are display in the center
     */
    private void makeStatisticsPanel()
    {
        //setting name and size of the panel
        statisticsPanel = new JPanel(new BorderLayout(6,6));
        statisticsPanel.setName("Stats");
        statisticsPanel.setSize(800,700);
        //the button components
        JButton buttonRight = new JButton(">");
        JButton buttonLeft = new JButton("<");
        buttonLeft.setEnabled(false);
        //setting up the card layout
        CardLayout cl = new CardLayout();
        statContentPane = new JPanel(cl);

        String from = (String) comboFrom.getSelectedItem();
        String to = (String) comboTo.getSelectedItem();
        //adding the number of properties statistics panel
        JPanel numberOfPropertiesPanel = new JPanel(new BorderLayout());
        JLabel statDisplayLabel1 = new JLabel("Number of available properties " + compute.getNumberOfAvailableProperties(Integer.parseInt(from),Integer.parseInt(to)));
        JButton help_btn1 = new JButton("What does this mean?");

        statDisplayLabel1.setFont(new Font("Arial", Font.BOLD, 15));
        numberOfPropertiesPanel.add(statDisplayLabel1, BorderLayout.CENTER);
        numberOfPropertiesPanel.add(help_btn1, BorderLayout.SOUTH);
        statContentPane.add(numberOfPropertiesPanel, "numberOfPropertiesPanel");
        
        JPanel numberOfEntiresPanel = new JPanel(new BorderLayout());
        JLabel statDisplayLabel2 = new JLabel("Number of entire homes and apartments " + compute.getNumberOfNonPrivate(Integer.parseInt(from),Integer.parseInt(to)));
        JButton help_btn2 = new JButton("What does this mean?");
        statDisplayLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        numberOfEntiresPanel.add(statDisplayLabel2,BorderLayout.CENTER);
        numberOfEntiresPanel.add(help_btn2,BorderLayout.SOUTH);
        statContentPane.add(numberOfEntiresPanel, "numberOfEntiresPanel");
        

        //adding the most expensive neighbourhood statistics panel
        JPanel mostExpensiveNeighbourhoodPanel = new JPanel(new BorderLayout());
        JLabel statDisplayLabel3 = new JLabel("The priciest neighbourhood is: " + compute.getPriciestNeighbourHood(Integer.parseInt(from),Integer.parseInt(to)));
        JButton help_btn3 = new JButton("What does this mean?");

        statDisplayLabel3.setFont(new Font("Arial", Font.BOLD, 15));
        mostExpensiveNeighbourhoodPanel.add(statDisplayLabel3,BorderLayout.CENTER);
        mostExpensiveNeighbourhoodPanel.add(help_btn3, BorderLayout.SOUTH);
        statContentPane.add(mostExpensiveNeighbourhoodPanel, "mostExpensiveNeighbourhoodPanel");
        //adding the median price statistics anel
        JPanel medianPricePanel = new JPanel(new BorderLayout());
        JLabel statDisplayLabel4 = new JLabel("The median price in the range is: " + compute.getMedianPrice(Integer.parseInt(from),Integer.parseInt(to)));
        JButton help_btn4 = new JButton("What does this mean?");

        statDisplayLabel4.setFont(new Font("Arial", Font.BOLD, 15));
        medianPricePanel.add(statDisplayLabel4, BorderLayout.CENTER);
        medianPricePanel.add(help_btn4, BorderLayout.SOUTH);
        statContentPane.add(medianPricePanel, "medianPricePanel");

        JPanel averageReviewPanel = new JPanel(new BorderLayout());
        JLabel averageReviewLabel = new JLabel("The average number of reviews in the price range is: " + compute.getAverageReviewScorePerProperties());
        JButton help_btn5 = new JButton("What does this mean?");

        averageReviewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        averageReviewPanel.add(averageReviewLabel, BorderLayout.CENTER);
        averageReviewPanel.add(help_btn5, BorderLayout.SOUTH);
        statContentPane.add(averageReviewPanel, "averageReviewPanel");

        JPanel atleast1reviewIn2017 = new JPanel(new BorderLayout());
        try{
            JLabel statDisplayLabel5 = new JLabel("Number of property with at least one review in 2017 is: " + compute.atleastOneReviewIn2017(Integer.parseInt(from),Integer.parseInt(to)));
            JButton help_btn6 = new JButton("What does this mean?");

            statDisplayLabel5.setFont(new Font("Arial", Font.BOLD, 15));
            atleast1reviewIn2017.add(statDisplayLabel5, BorderLayout.CENTER);
            atleast1reviewIn2017.add(help_btn6, BorderLayout.SOUTH);
            
            help_btn6.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the number of available properties,<br/>within the price range.</html>");

            }
        ); 
        }catch(Exception e){
            e.printStackTrace();
        }
        statContentPane.add(atleast1reviewIn2017, "numberOfPropertyWithAtLeast1reviewIn2017");

        JPanel inLongStayContainer = new JPanel(new BorderLayout());
        JPanel textContainer = new JPanel( new GridBagLayout());
        JPanel inLongStay = new JPanel();
       
        inLongStay.setLayout(new BoxLayout(inLongStay,BoxLayout.Y_AXIS));

        JLabel statDisplayLabel5 = new JLabel("Properties that Accepting"+ "\n"+ "long stay(>6 months) : "+" " + compute.CalLongestStay());
        JLabel statDisplayLabel6 = new JLabel(compute.CalLongestStay(Integer.parseInt(from),Integer.parseInt(to))+" "+" of them are in this price range");
        JButton help_btn7 = new JButton("What does this mean?");

        statDisplayLabel5.setFont(new Font("Arial", Font.BOLD, 15));
        statDisplayLabel6.setFont(new Font("Arial", Font.BOLD, 15));

        inLongStay.add(statDisplayLabel5);
        inLongStay.add(statDisplayLabel6);
        textContainer.add(inLongStay);
        
        inLongStayContainer.add(textContainer,BorderLayout.CENTER);
        inLongStayContainer.add(help_btn7,BorderLayout.SOUTH);
       

        inLongStay.add(statDisplayLabel5);
        inLongStay.add(statDisplayLabel6);
        
        inLongStayContainer.add(help_btn7,BorderLayout.SOUTH);
        statContentPane.add(inLongStayContainer,"Long Stay availabilty");

        String[] array = new String[]{"All","Barnet","Brent","Camben","City of London", "Ealing",
                "Greenwich","Hackney","Hammersmith and Fulham", "Haringey","Hounslow","Islington","Kensington and Chelsea",
                "Kingston upon Thames", "Lambeth","Merton","Newham","Richmond upon Thames","Southwark","Tower Hamlets",
                "Wandsworth","Westminster"};

        String[] columnNames = new String[]{"Host Name","Price","Discounted Price","Minimum Nights","ReviewsPerMonth"};       

        JPanel middleContainer = new JPanel( new GridBagLayout());
        JPanel discountsPanelBox = new JPanel();
        
        discountsPanelBox.setLayout(new BoxLayout(discountsPanelBox,BoxLayout.Y_AXIS));
        JPanel discountsPanel = new JPanel(new BorderLayout());
        JPanel choicePanel = new JPanel(new FlowLayout());
        JPanel resultsPanel = new JPanel(new FlowLayout());
        JTextField searchBar = new JTextField("",10);
        JComboBox dropdownBox = new JComboBox(array);
        JLabel statDisplayLabel8 = new JLabel("The cheapest discounted airbnb is: ");
        JLabel textBoxLabel = new JLabel("The type/size of airbnb:");
        JLabel result = new JLabel();
        JButton result_btn = new JButton("Enter");
        JButton help_btn8 = new JButton("What does this mean?");
        //JTable dataTable = new JTable(compute.getData(),columnNames);
        result_btn.setVisible(false);

        statDisplayLabel8.setFont(new Font("Arial", Font.BOLD, 15));
        //discountsPanel.add(choicePanel,BorderLayout.NORTH);
        discountsPanel.add(middleContainer, BorderLayout.CENTER);
        discountsPanel.add(help_btn8, BorderLayout.SOUTH);
        choicePanel.add(result_btn);
        choicePanel.add(textBoxLabel);
        choicePanel.add(searchBar);
        choicePanel.add(dropdownBox);
        resultsPanel.add(statDisplayLabel8);
        resultsPanel.add(result);
        //resultsPanel.add(dataTable);
        
        discountsPanelBox.add(choicePanel);
        discountsPanelBox.add(resultsPanel);
        
        middleContainer.add(discountsPanelBox);
        
        statContentPane.add(discountsPanel, "discountsPanel");

        JPanel mostTrustedPanel = new JPanel(new BorderLayout());
        JLabel mostTrustedLabel = new JLabel("The most trusted airbnb: \n" + compute.getMostTrustedAirbnb());
        JButton help_btn9 = new JButton("What does this mean?");
        mostTrustedLabel.setFont(new Font("Arial", Font.BOLD, 15));
        mostTrustedPanel.add(mostTrustedLabel, BorderLayout.CENTER);    
        mostTrustedPanel.add(help_btn9,BorderLayout.SOUTH);
        statContentPane.add(mostTrustedPanel, "numberOfEntiresPanel");


        currentStatisticsPanelIndex=0;
        cl.show(statContentPane, "numberOfPropertiesPanel");
        pack();
        //action listeners for the buttons
        help_btn2.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the number of entire homes and apartments ,<br/>within the price range of the data set.</html>");

            }
        );

        help_btn1.addActionListener(
            event->
            {
                 JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the number of available properties, according to the specified criteria<br/>within the price range.</html>");

            }
        );
        
        help_btn3.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the priciest neighbourhood <br/> out of all the neighbourhoods within the price range.</html>");

            }
        );
        
        help_btn4.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the median price from the list of airbnbs,<br/>within the price range.</html>");

            }
        );

        help_btn5.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the expected average amount of reviews per airbnb ,<br/>within the data set.</html>");

            }
        );
         

        help_btn7.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the number of available properties, according to the specified criteria<br/>within the price range.</html>");

            }
        );           
        
        help_btn8.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the cheapest discounted airbnb,<br/>within the data set that contains the criteria you enter in the textbox.</html>");

            }
        );

        
        help_btn9.addActionListener(
            event->
            {
                JOptionPane.showMessageDialog(mainPanel, "<html>This stats panel will find the cheapest discounted airbnb,<br/>within the data set that contains the criteria you enter in the textbox.</html>");

            }
        );
        
        result_btn.addActionListener(
            event->
            {

                if(dropdownBox.getSelectedItem().equals("All"))
                {

                    String ans = compute.getCheapestDiscountedAirbnbSearchAll(searchBar.getText());
                    result.setText(ans);
                }
                else{

                    System.out.println(searchBar.getText() + " : "+dropdownBox.getSelectedItem().toString());
                    String ans = compute.getCheapestDiscountedAirbnbSearch(searchBar.getText(), dropdownBox.getSelectedItem().toString());
                    result.setText(ans);
                }
                searchBar.setText("");
            }

        );

        searchBar.getDocument().addDocumentListener( new 

            DocumentListener()

            {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    update(e);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update(e);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    update(e);
                }

                private void update(DocumentEvent e) {
                    if(!searchBar.getText().isEmpty())
                    {
                        result_btn.setVisible(true);
                    }
                    else
                    {
                        result_btn.setVisible(false);
                    }
                }

            }
        );

        buttonRight.addActionListener
        (
            event -> 
            {
                cl.next(statContentPane);
                currentStatisticsPanelIndex++;
                //verifying if there are stat subpanels to navigate to
                buttonLeft.setEnabled(currentStatisticsPanelIndex>0);
                buttonRight.setEnabled(currentStatisticsPanelIndex < statContentPane.getComponentCount() - 1);
                setResizable(true);
                pack();
            }
        );
        buttonLeft.addActionListener
        (
            event -> 
            {

                cl.previous(statContentPane);
                currentStatisticsPanelIndex--;
                //verifying if there are stat subpanels to navigate to
                buttonLeft.setEnabled(currentStatisticsPanelIndex>0);
                buttonRight.setEnabled(currentStatisticsPanelIndex < statContentPane.getComponentCount() - 1);
                setResizable(false);
                pack();
            }
        );
        statisticsPanel.add(statContentPane,BorderLayout.CENTER);
        statisticsPanel.add(buttonRight,BorderLayout.EAST);
        statisticsPanel.add(buttonLeft,BorderLayout.WEST);
        pack();
    }

    /**
     * Create the bottom panel which contains two JButtons to allow navigation
     * between different panel. Also contains action listeners and logic for JButtons.
     */
    private void makeBottomPanel()
    {
        bottomPanel = new JPanel(new BorderLayout(6,6));
        //setting the border
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK)));
        scrollButtonRight = new JButton(" > ");
        scrollButtonRight.setEnabled(false);
        scrollButtonLeft = new JButton(" < ");
        scrollButtonLeft.setEnabled(false);
        bottomPanel.add(loadingIndicator,BorderLayout.CENTER);
        bottomPanel.add(scrollButtonRight,BorderLayout.EAST);
        bottomPanel.add(scrollButtonLeft,BorderLayout.WEST);
        scrollButtonRight.addActionListener
        (
            event -> 
            {//action listeners for proper navigation between the panels
                if(mainPanel.getComponent(2).getName().equals("Stats")){

                    //show the loading status 
                    loadingIndicator.setText("Training data, Please wait... ");
                    loadingIndicator.paintImmediately(loadingIndicator.getVisibleRect());

                    makeFinalPanel();
                    changeMainCenterPanel(finalPanel);
                    scrollButtonRight.setEnabled(false);
                    scrollButtonLeft.setEnabled(true);
                }else if(mainPanel.getComponent(2).getName().equals("Map")){
                    makeStatisticsPanel();
                    changeMainCenterPanel(statisticsPanel);
                    scrollButtonRight.setEnabled(true);
                    scrollButtonLeft.setEnabled(true);
                }
                pack();
                currentPanelIndex++;
            }
        );
        scrollButtonLeft.addActionListener
        (
            event -> 
            {   //action listeners for proper navigation between the panels
                if(mainPanel.getComponent(2).getName().equals("Stats")){
                    makeMapPanel();
                    changeMainCenterPanel(mapPanel);
                    scrollButtonRight.setEnabled(true);
                    scrollButtonLeft.setEnabled(false);
                }else if(mainPanel.getComponent(2).getName().equals("Map")){
                    scrollButtonRight.setEnabled(true);
                    scrollButtonLeft.setEnabled(false);
                }else{
                    makeStatisticsPanel();
                    changeMainCenterPanel(statisticsPanel);
                    scrollButtonRight.setEnabled(true);
                    scrollButtonLeft.setEnabled(true);
                }
                pack();
                currentPanelIndex=2;
            }
        );

    }

    /**
     * Changes the center panel of the main panel
     * @Param The panel to be changed to
     */
    private void changeMainCenterPanel(JPanel panel)
    {
        BorderLayout layout = (BorderLayout) mainPanel.getLayout();
        mainPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        mainPanel.add(panel,BorderLayout.CENTER);
        pack();
    }

    /**
     * Finalizes and packs the frame and then makes the main frame visible to the user
     */
    private void makeFrame()
    {
        setTitle("London Property Market");
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }
}