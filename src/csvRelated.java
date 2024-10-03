import java.util.Scanner;
import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

class approvedList {
    public void insuranceList(){
        Scanner sc = new Scanner(System.in);
        try (CSVReader csvReader = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv"))) {
            List<String[]> rows = csvReader.readAll();  
            for (String[] row : rows) {
                    for (String value : row) {
                        System.out.print(String.format("%-25s", value));
                    }
                System.out.println(); 
            }
            System.out.println("[1] Search | [2] Delete | [3] Exit");
            int choice = sc.nextInt();
                if (choice == 1) {
                    boolean found = false;
                        System.out.println("====================================================");
                        System.out.println("Enter Insurance ID");
                        System.out.println("====================================================");
                        sc.nextLine();
                        String searchID = sc.nextLine();
                        for (String[] row : rows) { // no need to call csvReader.readAll() again
                            if (row[0].equals(searchID)) {  
                                for (String value : row) {
                                    System.out.print(String.format("%-25s", value));
                                }
                                System.out.println();
                                found = true;
                                break; 
                            }
                        }
                        if (!found) {
                            System.out.println("Insurance ID not found.");
                        }
                } 
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}



class checkApproved extends approvedList{
    public boolean toApproved(String insuranceID, String name, String age, String DE, String carmodel, String carage, String AH) {
        System.out.println(name + " Approved");
        boolean success = true;
        try (CSVWriter AWriter = new CSVWriter(new FileWriter("csvFiles/approvedInsurance.csv", true))) {
            String[] aI = {insuranceID, name, age, DE, carmodel, carage, AH};
            AWriter.writeNext(aI);  
            success = false;
        } catch (IOException i) {}
        return success; 
    }
}

class csvRelated extends checkApproved{

    public boolean checkName(String name) {
        boolean alreadyRegistered = false;
        try (CSVReader AReader = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv"))) {
            String[] line;
            while ((line = AReader.readNext()) != null) {
                if (line[0].equalsIgnoreCase(name)) {
                    System.out.println("Already Registered");
                    alreadyRegistered = true;
                    break;  
                }
            }
        } catch (CsvValidationException | IOException e) {}
        return alreadyRegistered;  
    }
}
