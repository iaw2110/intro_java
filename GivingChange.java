/* Ivan Wolansky
   iaw2110
   GivingChange.java */

import java.util.Scanner;

public class GivingChange
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int amountDue, amountReceived;

        //Get the amount due from the program user
        System.out.println("Please enter the amount due in pennies:");
        amountDue = input.nextInt();
        
        //Get the amount received from the program user
        System.out.println("Please enter the amount received in pennies:");
        amountReceived = input.nextInt();

        /* Calculate the amount that needs to be returned and account for
           customer needing more money */
	int difference = (amountReceived - amountDue);
        if (amountDue > amountReceived)
        {
            System.out.println("Oops, you need more money!");
        }
        // Calculate the number of each type of currency returned
        else
        {
            int dollars = difference / 100;
            System.out.println(dollars + " " + "dollars");
            difference = difference % 100;

            int quarters = difference / 25;
            System.out.println(quarters + " " + "quarters");
            difference = difference % 25;

            int dimes = difference / 10;
            System.out.println(dimes + " " + "dimes");
            difference = difference % 10;

            int nickels = difference / 5;
            System.out.println(nickels + " " + "nickels");
            difference = difference % 5;

            int pennies = difference;
            System.out.println(pennies + " " + "pennies");
        }
    }
}
