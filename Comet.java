/* Ivan Wolansky
   iaw2110
   Comet */

import java.util.Scanner;

public class Comet
{
    public static void main(String[] args)
    {
    double G = 6.67E-11; // Gravitational Constant
    double M = 1.3E22;   // Mass of Halley's Comet in kilograms
    double R = 1.153E6;  // Radius of Halley's Comet in meters
    
        Scanner input = new Scanner(System.in);
        // Define the equation for escape velocity from physics
        double escapeVelocity = Math.sqrt(2.0 * G * M / R);

        System.out.println("Enter launch velocity in miles per hour");
        
        double launchVelocity = input.nextDouble();
        /* The launch velocity must be converted from miles per hour to 
           meters per second */
        launchVelocity = launchVelocity * 0.44704;

        if(launchVelocity < escapeVelocity)
        {
            System.out.println("Astronaut will come back to the surface!");
        }
        else
        {
            /* Calculate the mass of the comet needed to stay on the 
               surface given a certain launch velocity */
            double newM;
            newM = (Math.pow(launchVelocity , 2) * R) / (2.0 * G);
            // Had to make separate strings to not exceed 80 characters
            String comet = "Comet must have a mass of";
            System.out.print(comet + " ");
            System.out.printf("%5.2E" , newM);
            System.out.println(" " + "kg to return to the surface.");

            double difference = (newM - M);
            String change = "The comet must be";
            System.out.print(change + " ");
            System.out.printf("%5.2E" , difference);
            System.out.println(" " + "kg more massive");
        }
    }
}
