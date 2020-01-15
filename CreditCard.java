/* Ivan Wolansky
 * iaw2110
   CreditCard */
public class CreditCard {
	
	private String creditCardNumber; // the actual credit card number
	private int errorCode; // the code that is displayed when there is an error
	
	public CreditCard(String card) {
		creditCardNumber = card; // initialization of variables
		errorCode = 0;
	}
	public boolean isValid() { /* tells if creditCardNumber is 
								  valid if errorCode is 0 */
		return errorCode == 0;
	}
	private void check1() { /* checks if the first test for a valid 
							   creditCardNumber passes */
		if (errorCode != 0)
			return; /* the purpose of this is in each check is to return a 
				specific errorCode; if it is not 0 it returns immediately */
		int first = Integer.parseInt(creditCardNumber.substring(0, 1));
		if (first != 4) /* when the first digit is not 4 then the
		 				   error 1 is displayed */
			errorCode = 1;
	}
	private void check2() { /* checks if the second test for a valid 
							   creditCardNumber passes */
		if (errorCode != 0)
			return;
		int fourth = Integer.parseInt(creditCardNumber.substring(3, 4));
		int fifth = Integer.parseInt(creditCardNumber.substring(4, 5));
		if (fourth <= fifth) /* when the fourth digit is not greater 
								than the fifth then error 2 is displayed */
			errorCode = 2;
	}
	private void check3() { /* checks if the third test for a 
							   valid creditCardNumber passes */
		if (errorCode != 0)
			return;
		int first = Integer.parseInt(creditCardNumber.substring(0, 1));
		int fifth = Integer.parseInt(creditCardNumber.substring(4, 5));
		int ninth = Integer.parseInt(creditCardNumber.substring(8, 9));
		if (first * fifth * ninth != 24) /* when the product of digit 1, 5, 
							and 9 is not 24, then error 3 is displayed */
			errorCode = 3;
	}
	private void check4() { /* checks if the fourth test for a 
							   valid creditCardNumber passes */
		if (errorCode != 0)
			return;
		int sum = 0;
		for (int k = 0; k < creditCardNumber.length(); k++)
			sum = sum + Character.getNumericValue(creditCardNumber.charAt(k));
		if (sum % 4 != 0) /* when the sum of the digits is not 
							 divisible by 4, then error 4 is displayed */
			errorCode = 4;
	}
	private void check5() { /* checks if the fifth test 
							   for a valid creditCardNumber passes */
		if (errorCode != 0)
			return;
		int sumFirstFourDigits = 0;
		int sumLastFourDigits = 0;
		for (int i = 0; i < 4; i++)
			sumFirstFourDigits += 
			Character.getNumericValue(creditCardNumber.charAt(i));
		for (int i = 8; i < 12; i++)
			sumLastFourDigits += 
			Character.getNumericValue(creditCardNumber.charAt(i));
		if (sumFirstFourDigits != sumLastFourDigits - 1) /* when the sum of the
		 						first four digits is not less than the sum
		 						of the last four digits error 5 is displayed*/
			errorCode = 5;
	}
	private void check6() { /* checks if the sixth test for 
							a valid creditCardNumber passes */
		if (errorCode != 0)
			return;
		int firstAndSecond = 
				Integer.parseInt(creditCardNumber.substring(0, 2));
		int seventhAndEighth = 
				Integer.parseInt(creditCardNumber.substring(6, 8));
		if (firstAndSecond + seventhAndEighth != 100)
			errorCode = 6;
	}
	public void check() { /* checks if all the tests for a valid 
							creditCardNumber pass */
		check1();
		check2(); 
		check3(); 
		check4(); 
		check5(); 
		check6();
	}
	public int getErrorCode() { // gets the type of error
		return errorCode;
	}
	
} // end class