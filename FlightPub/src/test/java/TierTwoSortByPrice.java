/**
* @author Cliff Ng
* @version 1.0, 5/11/2019
*/

import org.junit.Assert;
import org.junit.Test;
import seng3150.group1.models.FlightLegContainer;
import seng3150.group1.models.FlightOptionContainer;
import seng3150.group1.models.SearchCriteria;

import java.util.*;

public class TierTwoSortByPrice {

    @Test
    public void sortByPrice() {
        FlightLegContainer flightOne,flightTwo,flightThree,flightFour,flightFive,flightSix,flightSeven,flightEight,flightNine;
        flightOne = new FlightLegContainer(3439.95,150,3.0,false,"AC786","Madrid","Miami","Air Canada","767-400","2018-09-27 08:00:00","2018-09-27 10:30:00","PME","AC");
        flightTwo = new FlightLegContainer(1522.00,90,1.0,false,"AA1735","Madrid","Miami","American Airlines","A380","2018-09-27 06:30:00","2018-09-27 08:00:00","PME","AA");
        flightThree = new FlightLegContainer(5291.82,220,2.0,false,"BA7312","Madrid","Miami","British Airways","A340-500","2018-09-27 08:30:00","2018-09-27 12:10:00","PME","BA");
        flightFour = new FlightLegContainer(4598.02,150,3.0,false,"AC786","Madrid","Miami","Air Canada","767-400","2018-09-27 08:00:00","2018-09-27 10:30:00","BUS","AC");
        flightFive = new FlightLegContainer(7165.63,90,4.0,false,"EK409","Madrid","Miami","Qatar Airways","A380","2018-09-27 06:30:00","2018-09-27 08:00:00","ECO","EK");
        flightSix = new FlightLegContainer(8079.37,150,6.5,false,"LH790","Madrid","Miami","Lufthansa","A380","2018-09-27 08:30:00","2018-09-27 11:00:00","FIR","LH");
        flightSeven = new FlightLegContainer(6650.07,220,2.5,false,"BA7312","Madrid","Miami","British Airways","A340-500","2018-09-27 08:30:00","2018-09-27 12:10:00","BUS","BA");
        flightEight = new FlightLegContainer(9039.14,220,7.5,false,"AF5028","Madrid","Miami","Air France","757-200","2018-09-27 08:00:00","2018-09-27 11:40:00","FIR","AF");
        flightNine = new FlightLegContainer(2640.03,90,1.0,false,"AA1735","Madrid","Miami","American Airlines","A380","2018:09-27 06:30:00","2018-09-27 08:00:00","BUS","AA");

        List<FlightLegContainer> listOne = new LinkedList<>();
        listOne.add(flightOne);

        List<FlightLegContainer> listTwo = new LinkedList<>();
        listTwo.add(flightTwo);

        List<FlightLegContainer> listThree = new LinkedList<>();
        listThree.add(flightThree);

        List<FlightLegContainer> listFour = new LinkedList<>();
        listFour.add(flightFour);

        List<FlightLegContainer> listFive = new LinkedList<>();
        listFive.add(flightFive);

        List<FlightLegContainer> listSix = new LinkedList<>();
        listSix.add(flightSix);

        List<FlightLegContainer> listSeven = new LinkedList<>();
        listSeven.add(flightSeven);

        List<FlightLegContainer> listEight = new LinkedList<>();
        listEight.add(flightEight);

        List<FlightLegContainer> listNine = new LinkedList<>();
        listNine.add(flightNine);

        FlightOptionContainer flightOptionOne= new FlightOptionContainer(listOne);
        FlightOptionContainer flightOptionTwo= new FlightOptionContainer(listTwo);
        FlightOptionContainer flightOptionThree= new FlightOptionContainer(listThree);
        FlightOptionContainer flightOptionFour= new FlightOptionContainer(listFour);
        FlightOptionContainer flightOptionFive= new FlightOptionContainer(listFive);
        FlightOptionContainer flightOptionSix= new FlightOptionContainer(listSix);
        FlightOptionContainer flightOptionSeven= new FlightOptionContainer(listSeven);
        FlightOptionContainer flightOptionEight= new FlightOptionContainer(listEight);
        FlightOptionContainer flightOptionNine= new FlightOptionContainer(listNine);


        LinkedList<FlightOptionContainer> madridToMiamiFlights = new LinkedList<>();
        madridToMiamiFlights.add(flightOptionOne);
        madridToMiamiFlights.add(flightOptionTwo);
        madridToMiamiFlights.add(flightOptionThree);
        madridToMiamiFlights.add(flightOptionFour);
        madridToMiamiFlights.add(flightOptionFive);
        madridToMiamiFlights.add(flightOptionSix);
        madridToMiamiFlights.add(flightOptionSeven);
        madridToMiamiFlights.add(flightOptionEight);
        madridToMiamiFlights.add(flightOptionNine);

        //set all sliders to not important
        SearchCriteria noneImportant = new SearchCriteria("1","1","1","1");

        LinkedList<FlightOptionContainer> noneImportantList =FlightOptionContainer.rankAndSortFlights(madridToMiamiFlights,noneImportant);


        boolean sorted=true;
        double previousPrice=0;

        //check if list is sorted
        //System.out.println("None Important List: "+"size is "+noneImportantList.size());
        for(int i=0;i<noneImportantList.size();i++) {
            //System.out.println(noneImportantList.get(i).getLegs().get(0).getPrice());

            double price = noneImportantList.get(i).getLegs().get(0).getPrice();

            if(i>0) {
                previousPrice = noneImportantList.get(i - 1).getLegs().get(0).getPrice();
            }

            if(price<previousPrice) {
                sorted=false;
                break;
            }
        }

        Assert.assertEquals(false,sorted);

        //set price slider to very important, facilitating sort by price
        SearchCriteria priceImportant = new SearchCriteria("5","1","1","1");

        LinkedList<FlightOptionContainer> priceImportantList =FlightOptionContainer.rankAndSortFlights(madridToMiamiFlights,priceImportant);


        sorted=true;
        previousPrice=0;

        //check if list is sorted
        System.out.println("Sorted By Price: ");
        for(int i=0;i<priceImportantList.size();i++) {
            System.out.println(priceImportantList.get(i).getLegs().get(0).getPrice());
            double price = priceImportantList.get(i).getLegs().get(0).getPrice();

            if(i>0) {
                previousPrice = priceImportantList.get(i - 1).getLegs().get(0).getPrice();
            }

            if(price<previousPrice) {
                sorted=false;
                break;
            }
        }

        Assert.assertEquals(true,sorted);
      //  for(int i=0;i<noneImportantList.size();i++) {
        //    System.out.println(noneImportantList.get(i).getLegs().get(0).getPrice());
        //}

        //FlightOptionContainer.rankAndSortFlights(madridToMiami,)

    }
}
