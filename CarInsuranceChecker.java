import java.util.Scanner;

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
                readerRunning = cv.toApproved(cu.getName(), String.valueOf(cu.getAge()), String.valueOf(cu.getDE()), cu.getCarModel(), String.valueOf(cu.getCarAge()), String.valueOf(cu.getAccidentHistory()));
            }
        }
        return readerRunning;
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
        int answer = sc.nextInt();
        if (answer == 1) {
            questionnaire.collectCustomerDetails();
            printables pr = new printables(questionnaire);
            pr.approved();
        } else if (answer == 2){
            csvRelated cr = new csvRelated();
            cr.insuranceList();
        }
    }
}