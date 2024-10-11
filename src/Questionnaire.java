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

            cv.registerChange(String.valueOf(insueID),
                                PN,
                                CCM,
                                "A",
                                "B");
            cv.registerChange(String.valueOf(insueID),
                                PN,
                                CCM,
                                String.valueOf(CCA),
                                String.valueOf(CSH),
                                "A",
                                "B");

        } else {
            System.out.println("Insurance ID not found");
        }
    }
}
