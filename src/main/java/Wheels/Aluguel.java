package Wheels;

import java.util.Date;

public class Aluguel {
    private Date startDate = null;
    private Cliente cliente = null;
    private Bike bike = null;
    private int numberOfDays = 0;
    private int hireId = 0;

    private static int hireCount = 001;

    public Aluguel(Date sDate, int numDays, Bike bikeToHire, Cliente cust){
        startDate = sDate;
        numberOfDays = numDays;
        cliente = cust;
        bike = bikeToHire;
        hireId = hireCount++;
    }

    public Cliente getCustomer(){
        return cliente;
    }

    public Bike getBike(){
        return bike;
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }

    public Date getStartDate(){
        return startDate;
    }
}

