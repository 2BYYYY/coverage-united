import java.util.Scanner;


public class Questionnaire extends Customer{
    public void collectCustomerDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        setName(sc.nextLine());

        System.out.print("Enter customer age: ");
        setAge(sc.nextInt());

        System.out.print("Enter customer driving experience: ");
        setDE(sc.nextInt());

        sc.nextLine(); 

        System.out.print("Enter customer Car Model: ");
        setCarModel(sc.nextLine());

        System.out.print("Enter customer Car Age: ");
        setCarAge(sc.nextInt());

        System.out.print("Enter customer accident history: ");
        setAccidentHistory(sc.nextInt());
    }
}