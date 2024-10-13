import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

class printables extends PremiumCalculator {
    Questionnaire qu = new Questionnaire();
    InsuranceChecker fCov = new InsuranceChecker();
    InsuranceChecker pCov = new PartialCoverage();
    InsuranceChecker bCov = new BasicCoverage();

    public printables(Questionnaire qu) {
        this.qu = qu;
    }

    public printables(InsuranceChecker fCov, InsuranceChecker pCov, InsuranceChecker bCov) {
        this.fCov = fCov;
        this.pCov = pCov;
        this.bCov = bCov;
    }

    public boolean approved() {
        csvRelated cv = new csvRelated();
    
        // Check if the user is under 18
        if (qu.getAge() < 18) {
            System.out.println(qu.getName() + " Not Approved (Underage)");
            return false;
        }
    
        // Check if the plate number is already registered
        boolean alreadyRegistered = cv.checkPN(qu.getPlateNumber());
        if (alreadyRegistered == true) {
            System.out.println(qu.getName() + " Not Approved (Plate Number Already Registered)");
            return false;
        }
    
        // If neither condition is met, proceed with the approval process
        Random rd = new Random();
        int firstSection = qu.getName().length();
        int secondSection = qu.getCarAge();
        int thirdSection = rd.nextInt(1000000);
        qu.setInsuranceID(firstSection + secondSection + thirdSection);
    
        String FullCov = fCov.checkInsurance(qu.getCarAge(), qu.getAccidentHistory());
        String PartialCov = pCov.checkInsurance(qu.getCarAge(), qu.getAccidentHistory());
        String BasicCov = bCov.checkInsurance(qu.getCarAge(), qu.getAccidentHistory());
    
        // Approve and register customer, premium calculation only for approved
        cv.toApproved(
            "0",
            String.valueOf(qu.getInsuranceID()),
            qu.getPlateNumber(),
            String.valueOf(qu.getName()),
            String.valueOf(qu.getAge()),
            qu.getCarModel(),
            FullCov + PartialCov + BasicCov,
            Integer.toString(calculatePremium(15000, qu.getAccidentHistory(), qu.getDE())));
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
                //
                FullCov + PartialCov + BasicCov,
                //
                Integer.toString(calculatePremium(15000, qu.getAccidentHistory(), qu.getDE())));
    
        return true; // Customer approved
    }
    
}


public class CarInsuranceChecker {
    public static void main(String[] args) {
        Boolean running = true;
        while (running) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("=====================");
                System.out.println("|| COVERAGE UNITED ||");
                System.out.println("=====================");
                System.out.println("[1] Insurance Checker || [2] View Insurance List || [3] Exit");
                System.out.print("Enter: ");
                Questionnaire questionnaire = new Questionnaire();
                PremiumCalculator premCalc = new PremiumCalculator();
                int answer = sc.nextInt();

                if (answer == 1) {
                    boolean runningOne = true;
                    while (runningOne) {
                        try {
                            System.out.println("=======================");
                            System.out.println("|| Insurance Checker ||");
                            System.out.println("=======================");
                            System.out.println("[1] Old Customer || [2] New Customer || [3] Go Back");
                            System.out.print("Enter: ");
                            int answer2 = sc.nextInt();

                            if (answer2 == 1) {
                                questionnaire.regCollectCustomerDetails();
                            } else if (answer2 == 2) {
                                questionnaire.collectCustomerDetails();
                                printables pr = new printables(questionnaire);
                                // Only calculate and display the premium if the customer is approved
                                if (pr.approved()) {
                                    System.out.println("Customer's Premium is: " + 
                                        premCalc.calculatePremium(15000, 
                                        questionnaire.getAccidentHistory(), 
                                        questionnaire.getDE()));
                                }
                            } else if (answer2 == 3) {
                                break;
                            } else {
                                System.out.println("================================");
                                System.out.println("Choose from the choices please");
                                System.out.println("================================");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("================================");
                            System.out.println("Choose from the choices please");
                            System.out.println("================================");
                            sc.nextLine();
                        }
                    }
                } else if (answer == 2) {
                    csvRelated cr = new csvRelated();
                    cr.insuranceList();
                } else if (answer == 3) {
                    running = false;
                } else {
                    System.out.println("================================");
                    System.out.println("Choose from the choices please");
                    System.out.println("================================");
                }
            } catch (InputMismatchException e) {
                System.out.println("================================");
                System.out.println("Choose from the choices please");
                System.out.println("================================");
            }
        }
    }
}
