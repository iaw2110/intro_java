
/* Ivan Wolansky
   iaw2110
   Bookstore.java */

import java.util.Scanner;

public class Bookstore
{
    private void computeOrderPrice()
    {
        /* Read the total book price, quantity of books and define 
        variables */

        Scanner input = new Scanner(System.in);
        double subtotalBookPrice, shippingCharge;
        int numBooks;

	//read inputs from user P4.6(1)
	System.out.println("Please enter total book price: ");
	subtotalBookPrice = input.nextFloat();
	System.out.println("Please enter number of books: " );
	numBooks = input.nextInt();
	
	double TAX_PCT = 7.5;
	double SHIPPING_COST_PER_BOOK = 2.00;

	//calculate tax and shipping (2,3,4)
        shippingCharge = SHIPPING_COST_PER_BOOK * numBooks;
        double totalBookPrice = (1 + TAX_PCT/100.00) * subtotalBookPrice +
        shippingCharge;

	System.out.printf("The order price is: %.2f " , totalBookPrice);
        System.out.println();
    }
    public static void main (String[] args)
    {
	Bookstore b = new Bookstore();
	b.computeOrderPrice();
    }
}
