package Wheels;

import java.util.Date;

public class IssueBikeUI {
    //set up the member variables
    private Bike chosenBike = null;
    private Cliente cliente = null;
    private Payment payment = null;
    private Aluguel aluguel = null;
    private int numberOfDays = 0;

    public void showBikeDetails(int bikeNum){
        //find the bike by its number
        chosenBike = Bike.buscarPorNumero(bikeNum);
        if(chosenBike != null){
            chosenBike.mostrarDetalhes();
        }
    }

    public void calculateCost(int numDays){
        //set the member variable so it can be used later
        numberOfDays = numDays;
        //then ask the bike for the cost
        chosenBike.calcularCusto(numDays);
    }

    public void createCustomer(String name, String postcode, String telephone, String email){
        //create a customer and associated aluguel and payment
        cliente = new Cliente(name, postcode, telephone, email);
        payment = new Payment(cliente);
        aluguel = new Aluguel(new Date(), numberOfDays, chosenBike, cliente);
    }

    public void calculateTotalPayment(){
        //get the total payment from the payment object
        payment.calcularTotalPagamento(aluguel);
    }
}
