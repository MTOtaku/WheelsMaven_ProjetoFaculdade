package Wheels;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String name;
    private String postcode;
    private String telephone;
    private String email;
    private int customerID;

    private static int customerCount = 1;

    // Lista pública e estática acessível de qualquer parte do sistema
    public static List<Cliente> listaClientes = new ArrayList<>();

    public Cliente(String cName, String pcode, String tel, String email){
        this.name = cName;
        this.postcode = pcode;
        this.telephone = tel;
        this.email = email;
        this.customerID = customerCount++;
    }

    public void setCustomerID(int id) {
        this.customerID = id;
        if (id >= customerCount) {
            customerCount = id + 1;
        }
    }

    public int getCustomerNumber(){
        return customerID;
    }

    public String getName(){
        return name;
    }

    public String getPostcode(){
        return postcode;
    }

    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }

    public String toCSV() {
        return customerID + "," + name + "," + postcode + "," + telephone + "," + email;
    }

    @Override
    public String toString() {
        return customerID + "," + name;
    }
}
