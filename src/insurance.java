// import java.util.Scanner;

// class printables{
//     Scanner sc = new Scanner(System.in);
//     private String name;
//     private int age;
//     printables(String name, int age){
//         this.name = name;
//         this.age = age;
//     }
//     public String getName(){
//         return name;
//     }
//     public int getAge(){
//         return age;
//     }
//     public boolean approved() {
//         boolean readerRunning = true;
//         if (age < 18) {
//             System.out.println(name + " Not Approved");
//         } else {
//             boolean alreadyRegistered = false; 
//             csvRelated cv = new csvRelated();
//             alreadyRegistered = cv.checkName(name);

//             if (!alreadyRegistered) {
//                 readerRunning = cv.toApproved(name, String.valueOf(age));
//                 cv.insuranceList();
//             }
//         }
//         return readerRunning;
//     }
// }
// public class insurance {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String userName = "";
//         int userAge = 0;
//         boolean running = true;
//         while (running) {
//             System.out.print("name: ");
//             userName = sc.nextLine();
//             System.out.println();
//             System.out.print("age: ");
//             userAge = sc.nextInt();
//             sc.nextLine();
//             printables na = new printables(userName, userAge);
//             running = na.approved();
//         }
//         printables na = new printables(userName, userAge);
//         System.out.println("Congratualtions!! your account is:");
//         System.out.println("name: " + na.getName());
//         System.out.println("age: " + na.getAge());
//     }
// }
    