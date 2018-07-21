import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

/**
 * this class represents the window which pops up when a marker in a particular region is clicked on the map
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
public class ListingFrame extends ViewController
{
    //panels for the 
    private JPanel mainPanel;
    private JPanel topPanel;
    private JScrollPane scrollPane;
    private JTextField searchBar;
    private JButton btn_clear;
    //components
    private JComboBox comboOrder;
    private JTable dataTable;

    private Object[][] data;
    /**
     * Constructor for objects of class ListingFrame
     * 
     * @Param neighbourhoodName, the name of the neighbourhood, from and to the price range
     */
    public ListingFrame(String neighbourhoodName, int from, int to)
    {

        makeMainPanel(neighbourhoodName, from, to);
        makeFrame(neighbourhoodName);

    }
    
    /**
     * Creates frame with no price range
     */
    private void makeFrame(String name)
    {
        setTitle("Detailed Listing of " + name);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }
    
    /**
     * Creates the main panel the specified price range
     * 
     * @Param neighbourhoodName, the name of the neighbourhood, from and to the price range
     */
    private void makeMainPanel(String neighbourhood,int from, int to)
    {

        mainPanel = new JPanel(new BorderLayout(6,6));
        mainPanel.setPreferredSize(new Dimension(800,600));
        makeTopPanel(neighbourhood,from,to);
        makeTable(neighbourhood,to,from);
        scrollPane = new JScrollPane(dataTable);
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(scrollPane,BorderLayout.CENTER);

    }
    
    /**
     * Creates the top panel the specified price range
     * 
     * @Param neighbourhoodName, the name of the neighbourhood, from and to the price range
     */
    private void makeTopPanel(String name,int from, int to)
    {
        
        btn_clear = new JButton("Clear");
        btn_clear.setVisible(false);
        
        searchBar = new JTextField("",20);
        searchBar.setForeground(Color.BLACK);

        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)));

        String[] array = new String[] {"Host Name","Price","Review Score"};
        comboOrder = new JComboBox(array);
        JLabel displayLabel = new JLabel("sort by : ");
        JLabel searchBarLabel = new JLabel("Search by host name :");
        topPanel.add(displayLabel);
        topPanel.add(comboOrder);
        topPanel.add(searchBarLabel);
        topPanel.add(searchBar);
        topPanel.add(btn_clear);

        searchBar.getDocument().addDocumentListener( new DocumentListener() {
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
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( dataTable.getModel()); 

                    String text = searchBar.getText();
                    if (text.trim().length() == 0) {
                        btn_clear.setVisible(false);
                        dataTable.setRowSorter(null);
                    } else {

                        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+ searchBar.getText()));
                        dataTable.setRowSorter(sorter);
                        btn_clear.setVisible(true);
                    }
                    
                }
            });

        comboOrder.addActionListener(event -> sort());
        
        btn_clear.addActionListener(event -> clear());
        
    }
    
    /**
     * Clears the panel
     */
    private void clear()
    {
        searchBar.setText("");
    }
    
    /**
     * Sorts the table content according to user selection
     */
    private void sort()
    {

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(dataTable.getModel());
        dataTable.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexToSort = 0;

        switch((String) comboOrder.getSelectedItem()){
            case "Host Name":
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();
            break;

            case "Price":
            columnIndexToSort = 1;
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();
            break;

            case "Review Score":
            columnIndexToSort = 3;
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();
            break;

        }

    }
    
    /**
     * Creates a table with property details according to price selection
     * 
     * @Param neighbourhoodName, the name of the neighbourhood, from and to the price range
     */
    public void makeTable(String neighbourhood,int from, int to)
    {
        DetailTableModel table =  new DetailTableModel(StartFrame.compute.getCurrentNeighbourhoodListings(neighbourhood));
        dataTable = new JTable(table);
        sort();
        ListSelectionModel mod = dataTable.getSelectionModel();

        dataTable.getColumnModel().getColumn(4).setMinWidth(0);
        dataTable.getColumnModel().getColumn(4).setMaxWidth(0);
        dataTable.getColumnModel().getColumn(4).setWidth(0);

        //dataTable = new JTable(compute.getData(neighbourhood,from,to,15), columnNames);
        dataTable.show();
        scrollPane = new JScrollPane(dataTable);
        mod.addListSelectionListener(new ListSelectionListener(){

                public void valueChanged(ListSelectionEvent e){
                    if(!mod.isSelectionEmpty())
                    {
                        if (!e.getValueIsAdjusting()) {//This line prevents double events
                            String id = (String) dataTable.getValueAt(dataTable.getSelectedRow(), 4);

                            ListingDetailsFrame detailFrame = new ListingDetailsFrame(id);
                        }

                    }
                }
            });
    }
}
