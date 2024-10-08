import java.util.Scanner;
import java.util.Random;

class printables{
    Customer cu = new Customer();
    public printables(Customer cu) {
        this.cu = cu;
    }
    public boolean approved() {
        boolean readerRunning = true;
        if (cu.getAge() < 18) {
            System.out.println(cu.getName() + " Not Approved");
        } else {
            boolean alreadyRegistered = false; 
            csvRelated cv = new csvRelated();
            alreadyRegistered = cv.checkName(cu.getName());

            if (!alreadyRegistered) {
                Random rd = new Random();
                int firstSection = cu.getName().length();
                int secondSection = cu.getCarAge();
                int thirdSection = rd.nextInt(1000000);
                cu.setInsuranceID(firstSection + secondSection + thirdSection);
                readerRunning = cv.toApproved(
                    String.valueOf(cu.getInsuranceID()),
                    cu.getName(), 
                    String.valueOf(cu.getAge()), 
                    String.valueOf(cu.getDE()), 
                    cu.getCarModel(), 
                    String.valueOf(cu.getCarAge()), 
                    String.valueOf(cu.getAccidentHistory()));
                    //ADD MAYBE PLATENUMBER
            }
        }
        return readerRunning;
    }
}


public class CarInsuranceChecker {
    public static void main(String[] args) {
        Customer cu = new Customer();
        Scanner sc = new Scanner(System.in);
        System.out.println("=====================");
        System.out.println("|| COVERAGE UNITED ||");
        System.out.println("=====================");
        System.out.println("[1] Insurance Checker");
        System.out.println("[2] View Insurance List");
        System.out.println("[3] Exit");
        System.out.println("What do you want to do? ");
        Questionnaire questionnaire = new Questionnaire();
        PremiumCalculator premCalc = new PremiumCalculator();
        InsuranceChecker fullCov = new InsuranceChecker();
        InsuranceChecker partialCov = new PartialCoverage();
        InsuranceChecker basicCov = new BasicCoverage();
        int answer = sc.nextInt();
        if (answer == 1) {
            questionnaire.collectCustomerDetails();
            printables pr = new printables(questionnaire);
            pr.approved();
            System.out.println("Customer's Premium is: " + premCalc.calculatePremium(15000, cu.getAccidentHistory(), cu.getDE()));
            fullCov.checkInsurance(cu.getCarAge(), cu.getAccidentHistory());
            partialCov.checkInsurance(cu.getCarAge(), cu.getAccidentHistory());
            basicCov.checkInsurance(cu.getCarAge(), cu.getAccidentHistory());
        } else if (answer == 2){
            csvRelated cr = new csvRelated();
            cr.insuranceList();
        }
        sc.close();
    }
}