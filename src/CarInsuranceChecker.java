import java.util.Scanner;
import java.util.Random;

class printables{
    Questionnaire qu = new Questionnaire();
    public printables(Questionnaire qu) {
        this.qu = qu;
    }
    public boolean approved() {
        if (qu.getAge() < 18) {
            System.out.println(qu.getName() + " Not Approved");
            return true;
        } else {
            boolean alreadyRegistered = false; 
            csvRelated cv = new csvRelated();
            alreadyRegistered = cv.checkName(qu.getName());

            if (!alreadyRegistered) {
                Random rd = new Random();
                int firstSection = qu.getName().length();
                int secondSection = qu.getCarAge();
                int thirdSection = rd.nextInt(1000000);
                qu.setInsuranceID(firstSection + secondSection + thirdSection);
                cv.toApproved(
                    "0",
                    String.valueOf(qu.getInsuranceID()),
                    qu.getPlateNumber(), 
                    String.valueOf(qu.getName()), 
                    String.valueOf(qu.getAge()), 
                    qu.getCarModel(), 
                    "Partial Coverage", 
                    "10000");
                cv.toApproved(
                    "0",
                    String.valueOf(qu.getInsuranceID()),
                    qu.getName(), 
                    String.valueOf(qu.getAge()), 
                    String.valueOf(qu.getDE()), 
                    qu.getCarModel(), 
                    String.valueOf(qu.getCarAge()), 
                    String.valueOf(qu.getAccidentHistory()),
                    qu.getPlateNumber(),
                    "Partial Coverage",
                    "10000");
            }
        }
        return false;
    }
}


public class CarInsuranceChecker {
    public static void main(String[] args) {
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
            System.out.println("[1] Registered");
            System.out.println("[2] Not yet Registered");
            int answer2 = sc.nextInt();
            if (answer2 == 1){
                questionnaire.regCollectCustomerDetails();

            } else if (answer2 == 2) {
            questionnaire.collectCustomerDetails();
            printables pr = new printables(questionnaire);
                if(pr.approved() != true){
                    System.out.println("Customer's Premium is: " + premCalc.calculatePremium(15000, questionnaire.getAccidentHistory(), questionnaire.getDE()));
                    fullCov.checkInsurance(questionnaire.getCarAge(), questionnaire.getAccidentHistory());
                    partialCov.checkInsurance(questionnaire.getCarAge(), questionnaire.getAccidentHistory());
                    basicCov.checkInsurance(questionnaire.getCarAge(), questionnaire.getAccidentHistory());
                }
            }  
        } else if (answer == 2){
            csvRelated cr = new csvRelated();
            cr.insuranceList();
        }
        sc.close();
    }
}