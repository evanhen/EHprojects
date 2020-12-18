#include <stdio.h>
#include <math.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct financialIdentity {
        
    double salary; // salary
    double savings; // savings account
    double checking; // checking account  
    double percent_to_savings; // percent to allocate to savings
    double savInterestRate; // savings interest rate
    double percent_to_checking; // percent to allocate to checking
    double debt; // debt amount
    double debtInterestRate; // debt interest rate
    double debtRequiredPayment; // 3% credit card fee
    double additionalPayment; // additional payment
    double loan; // loan amount
    double loanInterestRate; // loan interest rate
    int yearsWithDebt; // years a person has been in debt
    int yearsRented; // years a person has rented
    double debtPaid; // amount paid toward debt
    double housePrice; // house price
    double downPaymentPercent; // percent down payment
    double rentAmount; // rent price
  
} FI; // struct financialIdentity

void savingsPlacement (struct financialIdentity *person, double interestRate);
void debt (struct financialIdentity *person, double interestRate, double addlPay);
void rent (struct financialIdentity *person, double rentAmt);
void house (struct financialIdentity *person, double mortgageAmount, double interestRate, int mortgageTerm);
int *simulate (struct financialIdentity *person, double yearlySalary, bool financiallyLiterate);

void savingsPlacement (struct financialIdentity *person, double interestRate) { // function that updates savings each year
    
    person->savings = (person->savings * (1.0 + interestRate));	// updates savings each year
    
    
}

void debt (struct financialIdentity *person, double interestRate, double addlPay) {
    
    double diff2; // holds difference that must be charged to savings if checking funds are insufficient
    double payment = ((person->debt * person->debtRequiredPayment) + addlPay); // payment towards debt
    
    for (int n = 1; n <= 12; n++) { // for loop that simulates 12 months
        
        if (person->checking >= payment) { // if checking funds are greater than payment, charge to checking
                person->debt -= payment;	//pays each month
                person->debtPaid = person->debtPaid + payment;
                person->checking -= payment;
            
        } else { // else charge to savings
            diff2 = payment - person->checking;
        	person->checking = 0.0;
        	person->savings -= diff2;
        	person->debt -= payment;
        	person->debtPaid = person->debtPaid + payment;
                
            }
            
        }
    
    person->debt = (person->debt * interestRate);	// applies interest to debt annually
    person->yearsWithDebt = person->yearsWithDebt + 1; // increments yearsWithDebt
    
}

void rent (struct financialIdentity *person, double rentAmt) {
    
    double diff; // holds difference that checking can't pay
    

    if (person->checking >= rentAmt) { // if checking is greater than or equal to rent amount, charge checking
        
        person->checking -= rentAmt; 
        
    } else { // else, charge savings
        
        diff = rentAmt - person->checking;
        person->checking = 0.0;
        person->savings -= diff;
        
    }

}

void house (struct financialIdentity *person, double mortgageAmount, double interestRate, int mortgageTerm) {
    double discountFactor;
    double monthlyPayment;
    double diff;
    
    discountFactor = ((pow(1 + (person->loanInterestRate / 12.0), 360.0) - 1) / (pow(person->loanInterestRate * (1 + (person->loanInterestRate / 12.0)), 360.0))); //calculates discount factor         
    monthlyPayment = (person->housePrice * person->downPaymentPercent) / discountFactor; // monthly mortgage payment
    
    if( mortgageTerm == 1) { // if mortgageTerm == 1, loan is set to equal the mortgageAmount
        
        person->loan = mortgageAmount;
    }
     
    for (int m = 1; m <= 12; m++) { // for loop that simulates 12 months
        
        if (person->checking >= monthlyPayment) { // if checking greater or equal to monthlyPayment, charge checking
            
            person->checking = person->checking - monthlyPayment;
            
        } else {
            diff = monthlyPayment - person->checking; // holds difference checking can't pay
    	    person->checking = 0.0; 
    	    person->savings = person->savings - diff;
            
        }
        
        person->loan -= monthlyPayment; // monthlyPayment subtracted from loan
        person->loan = person->loan * person->loanInterestRate; // loan interest added
        
    }
}

int * simulate (struct financialIdentity *person, double yearlySalary, bool financiallyLiterate) {
    
    int wealth[40]; // array to store wealth
    wealth[0] = -25100; // wealth for first year
    printf("Wealth at Year #1: %d\n", wealth[0]); // print to console
    bool ownsHouse = false; // set false until person buys house
    double downPayment = (person->housePrice * person->downPaymentPercent); // down payment toward house
  
    for (int i = 1; i < 40; i++) { // for loop to simulate 40 years

      person->savings = (person->savings + (person->salary * person->percent_to_savings)); // update savings from salary each year
      person->checking = (person->checking + (person->salary * person->percent_to_checking)); // update checking from salary each year
      savingsPlacement (person, person->savInterestRate); // invoke savingsPlacement each year
      debt (person, person->debtInterestRate, person->additionalPayment); // invoke debt year
      
      double mortgageAmt = (person->housePrice - (person->housePrice * person->downPaymentPercent)); // calculate mortgageAmt
      
      if (person->savings >= downPayment && ownsHouse == false) { // if savings funds is greater than down payment and the person does not own a house, purchase a house/loan

              person->savings = person->savings - downPayment;
    	      ownsHouse = true; 
    	      person->loan = person->housePrice - downPayment;
          
      }
      
      else if (ownsHouse == false && person->savings < downPayment) { // else if person does not own house and savings are less than down payment, rent
          
          rent(person, person->rentAmount); // invoke rent function if person does not own a house and if their savings are less than the housePrice
          person->yearsRented = person->yearsRented++; // increment yearsRented
          
      }
      
  
  if (ownsHouse == true && person->loan > 0.0) {
      
      house(person, mortgageAmt, person->loanInterestRate, i); // invoke house function if person owns a house and still has loan to pay off
	  
	}
	
	wealth[i] = (int) ((person->savings + person->checking) - (person->debt - person->loan)); // calculates wealth
    
    printf("Wealth at Year #%d: %d\n", (i + 1), wealth[i]); // display wealth every year
      
  } // loop ends
  
    return wealth; //returns wealth 
}

int main () {
    
    FI *test; // pointer of FI type
    FI *test2; // pointer of FI type 
    
    FI tester_fl = { // struct of FI type
        59000.0,
        5000.0,
        0.0,
        0.2,
        0.07,
        0.3,
        30100.0,
        1.2,
        0.03,
        15.0,
        0.0,
        0.045,
        0,
        0,
        0.0,
        175000.0,
        0.20,
        850.0
        
        };
        
    FI tester_nfl = { // struct of FI type
        59000.0,
        5000.0,
        0.0,
        0.2,
        0.01,
        0.3,
        30100.0,
        1.2,
        0.03,
        1.0,
        0.0,
        0.05,
        0,
        0,
        0.0,
        175000.0,
        0.05,
        850.0
        
    };
        
        test = &tester_fl; // make pointer to tester_fl
        test2 = &tester_nfl; // make pointer to tester_nfl
        
        simulate (test, test->salary, true); // start simulation
        
        simulate (test2, test2->salary, false); // start simulation
    
    
    return -1;
}
