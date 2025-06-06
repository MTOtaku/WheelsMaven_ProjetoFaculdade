package Wheels;

import java.util.Date;

public class Aluguel {
    private Date startDate = null;
    private Cliente cliente = null;
    private Bike bike = null;
    private int numberOfDays = 0;

    public Aluguel(Date sDate, int numDays, Bike bikeToHire, Cliente cust){
        startDate = sDate;
        numberOfDays = numDays;
        cliente = cust;
        bike = bikeToHire;

    }

    public Bike getBike(){
        return bike;
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }

}

