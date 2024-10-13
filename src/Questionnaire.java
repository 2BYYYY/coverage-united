import java.util.Scanner;

public class Questionnaire extends Customer{
    csvRelated cr = new csvRelated();
    public void collectCustomerDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        setName(sc.nextLine());

        System.out.print("Enter customer age: ");
        setAge(sc.nextInt());

        System.out.print("Enter customer driving experience: ");
        setDE(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter customer plate number: ");
        setPlateNumber(sc.nextLine());

        System.out.print("Enter customer Car Model: ");
        setCarModel(sc.nextLine());

        System.out.print("Enter customer Car Age: ");
        setCarAge(sc.nextInt());

        System.out.print("Enter customer accident history: ");
        setAccidentHistory(sc.nextInt());
        
    }
    public void regCollectCustomerDetails() {
        csvRelated cv = new csvRelated();
        InsuranceChecker fCov = new InsuranceChecker();
        InsuranceChecker pCov = new PartialCoverage();
        InsuranceChecker bCov = new BasicCoverage();
        PremiumCalculator pCal = new PremiumCalculator();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter InsuranceID: ");
        int insueID = sc.nextInt();
        if(cr.checkInsID(String.valueOf(insueID)) == true){
            sc.nextLine();

            System.out.print("Enter customer Plate Number:");
            String PN = sc.nextLine();

            System.out.print("Enter customer Car Model: ");
            String CCM = sc.nextLine();

            System.out.print("Enter customer Car Age: ");
            int CCA = sc.nextInt();

            System.out.print("Enter customer accident history: ");
            int CSH = sc.nextInt();

            System.out.print("Enter customer Driving Experience: ");
            int DE = sc.nextInt();

            String FullCov = fCov.checkInsurance(CCA, CSH);
            String PartialCov = pCov.checkInsurance(CCA, CSH);
            String BasicCov = bCov.checkInsurance(CCA, CSH);

            cv.registerChange(String.valueOf(insueID),
                                PN,
                                CCM,
                                FullCov + PartialCov + BasicCov,
                                Integer.toString(pCal.calculatePremium(15000, CSH, DE)));
            cv.registerChange(String.valueOf(insueID),
                                PN,
                                CCM,
                                String.valueOf(CCA),
                                String.valueOf(CSH),
                                FullCov + PartialCov + BasicCov,
                                Integer.toString(pCal.calculatePremium(15000, CSH, DE)));
            System.out.println("Customer's Premium is: " + 
                                pCal.calculatePremium(15000, 
                                CSH, 
                                DE));
        } else {
            System.out.println("Insurance ID not found");
        }
    }
}
