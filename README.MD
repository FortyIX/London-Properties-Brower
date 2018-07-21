

# London Properties Map
 ![GitHub last commit](https://img.shields.io/github/last-commit/google/skia.svg)  ![GitHub last commit](https://img.shields.io/badge/Team-Mercuial-red.svg)

This is the Assignment 4 for Programming practise and Application (4CS1PPA), Developed by Team Mercuial 

![Image](http://fuzhang.io/storage/welcome.png)

</br>

## Installation 

```shell
 git clone https://github.kcl.ac.uk/k1763499/Mercuial_Assignment

```

> For MacOS users, if the error such as "XXX" is missing or "XXX" is not found occurs when you run the project in BlueJ, Please restart the BlueJ and retry. There is a bug in MacOS BlueJ

</br>

This project runs without any need for JDK or BlueJ. Just doube click the jar file.

## Features

- Aritifical Intellgence (Machine Learning)
- Clickable icon on each borough
- Resizable icon representing numbers of properties in each borough 
- Median price of all properties
- The most expensive place to rent and other useful information which gives you suggestions

</br>

## Task allocation 
| Members      | Tasks          | 
| ------------- |:-------------:| 
| Fu Zhang     | Designed the Classifier which enable users to know what type of properties they will get by inputing the conditions they want using Decision Tree learning algorithm. Completed the forth panel with the classifer. Resizing of the markers on the map according to number of properties in the region in a certain price range selected by user. Implemented the marker class, Classifier class and ClassifierNotConfigeredProperlyException class in full,Created the detailTableModel Class which is a TableViewController of the detail data which shows the list of all available properties within a borough. In addition, implemented Calculation of the number the properties that allows long term stay as well as the number of long term enabled properties in the current price range therefore implementing 2 statistics.Designed the welcome image for our Github Repo| 
| Michael Jones     | Event Handle of the the clickable icon on each borough, ensuring proper display of data listing on the ListingFrame window upon click of markers on the map. implementation of the test class as well as 2 statistics. Refactoring code   |   
| Naharul Hayat| Base implementation of the GUI design for the panels and the frames and the design of several classes. Validation of combo input. Implementing the card layouts of entire stat panel for proper navigation. Also, Ensuring proper navigation between the 3 base panels (welcome,map,statistics) and at the same time ensuring dynamic update of contents according to combo input. Implemented the ListingDetailsFrame class in full, moreover, implemented event handling of Jtable, to display the full details of a record in a new window, if a row in Jtable is clicked. Finally, implemented 2 statistics, one calculating median, and another calculating total number of properties in the price range.   | 
| Noyan Raquib |  Initial implementation of the combo boxes and the welcome page layout. Added the base implementation of the map panel, including the background image, initial positioning of all the icons and the initial size of all the icons, also added tooltips to all icons on the map. Implemented two statistics on the stats page (One for the number of entire homes and apartments and another for the number of property with at least one review in 2017) and made stats page dynimically update whenever a new value from the combo box is selected. Set the font and size of all text in the map and stats panel. Made all icons transparent to avoid overlapping issues. Generated numerical data for machine learning algorithm.| 



</br>


## AUTHORS 
(C)2018 Fu Zhang, Michael Jones,A K M Naharul Hayat and Noyan Raquib



