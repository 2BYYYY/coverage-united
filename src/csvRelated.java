import java.util.Scanner;
import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
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
            System.out.println("[1] Search | [2] Delete | [3] Recently Deleted | [4] Exit");
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
                } else if(choice == 2){
                    boolean found2 = false;
                        System.out.println("Enter Username/Email (TO DELETE): ");
                        sc.nextLine();
                        String searchName = sc.nextLine();
                        List<String[]> filteredRows = new ArrayList<>();
                        List<String[]> recentDel = new ArrayList<>();
                        for (String[] row : rows) {
                            if (!row[0].equals(searchName)) { 
                                filteredRows.add(row);
                                found2 = true;
                            } else if (row[0].equals(searchName)){
                                recentDel.add(row);
                                CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/recentlyDeleted.csv",true));
                                writer.writeAll(recentDel); 
                                writer.close();
                            }
                        }
                        
                        if (!found2) {
                            System.out.println("Name not found in the leaderboards.");
                        } else {
                            CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/approvedInsurance.csv"));
                            writer.writeAll(filteredRows); 
                            writer.close();
                        }

                } else if (choice == 3){
                    CSVReader RDReader = new CSVReader(new FileReader("csvFiles/recentlyDeleted.csv"));
                    List<String[]> rdRows = RDReader.readAll();  
                    for (String[] row : rdRows) {
                            for (String value : row) {
                                System.out.print(String.format("%-25s", value));
                            }
                        System.out.println(); 
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
