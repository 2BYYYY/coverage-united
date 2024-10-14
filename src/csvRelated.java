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



class registeredToAdd {
    public void registerChange(String insuranceID, String PN, String carModel, String TOI, String PM) {
        try (CSVReader csvReader = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv"))) {
            List<String[]> rows = csvReader.readAll();
            int countRows = 0;
            for (String[] row : rows) {
                if (row[1].equals(insuranceID)) {
                    countRows +=1;
                }
            }
            for (String[] row : rows) {
                if (row[0].equals("0") && row[1].equals(insuranceID)) {
                    try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/approvedInsurance.csv", true))) {
                        String[] updatedRow = {String.valueOf(countRows), insuranceID, PN, row[3], row[4], carModel, TOI, PM};
                        writer.writeNext(updatedRow);
                    } catch (IOException e) {}
                }
            }

        } catch (IOException | CsvException e) {}
    }
    public void registerChange(String insuranceID, String PN, String carModel, String carAge, String AH, String TOI, String PM) {
        try (CSVReader csvReader = new CSVReader(new FileReader("csvFiles/fullListOfInsurance.csv"))) {
            List<String[]> rows = csvReader.readAll();
            int countRows = 0;
            for (String[] row : rows) {
                if (row[1].equals(insuranceID)) {
                    countRows +=1;
                }
            }
            for (String[] row : rows) {
                if (row[0].equals("0") && row[1].equals(insuranceID)) {
                    try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/fullListOfInsurance.csv", true))) {
                        String[] updatedRow = {String.valueOf(countRows), insuranceID, row[2], row[3], row[4], carModel, carAge, AH, PN, TOI, PM};
                        writer.writeNext(updatedRow);
                    } catch (IOException e) {}
                }
            }

        } catch (IOException | CsvException e) {}
    }
}

class approvedList extends registeredToAdd{
    public void insuranceList(){
        Scanner sc = new Scanner(System.in);
        boolean insRunning = true;
        while (insRunning) {
            try (CSVReader csvReaderAI = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv")))  {
                List<String[]> rows = csvReaderAI.readAll();
                CSVReader csvReaderFLOI = new CSVReader(new FileReader("csvFiles/fullListOfInsurance.csv"));
                List<String[]> rowFLOI = csvReaderFLOI.readAll();
                System.out.println("================================================================================================================================================================");
                for (String[] row : rows) {
                        for (String value : row) {
                            System.out.print(String.format("%-20s", value));
                        }
                    System.out.println(); 
                }
                    System.out.println("[1] Search | [2] Delete | [3] Recently Deleted | [4] Get Customer information || [5] Exit");
                    System.out.print("Enter: ");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        boolean found = false;
                        System.out.print("Enter Insurance ID: ");
                        sc.nextLine(); // Consume newline
                        String searchID = sc.nextLine();
                    
                        for (String[] row : rows) { // Assuming 'rows' is the list of records from the CSV
                            // Check if the Insurance ID in the current row matches the search ID
                            if (row[1].equals(searchID)) {  
                                // Print the entire row with proper formatting
                                System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", 
                                    row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7])); // Adjust based on your CSV structure
                                found = true;
                            }
                        }
                        
                        // If no matching records are found, print a message
                        if (!found) {
                            System.out.println("Insurance ID not found.");
                        }
                    } else if(choice == 2){
                            boolean found2 = false;
                            System.out.println("[1] Full delete");
                            System.out.println("[2] Car delete");
                            int fOc = sc.nextInt();
                            sc.nextLine(); 
                            System.out.print("Enter Insurance ID: ");
                            String searchName = sc.nextLine();
                            List<String[]> filteredRows = new ArrayList<>();
                            List<String[]> filteredRowsFLOI = new ArrayList<>();
        
                            if (fOc == 1) {
                                for (String[] row : rows) {
                                    if (!row[1].equals(searchName)) {
                                        filteredRows.add(row); 
                                    } else {
                                        found2 = true; 
                                    }
                                }
        
                                for (String[] row : rowFLOI) {
                                    if (!row[1].equals(searchName)) {
                                        filteredRowsFLOI.add(row);
                                    } else {
                                        List<String[]> recentDel = new ArrayList<>();
                                        recentDel.add(row);
                                        try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/recentlyDeleted.csv", true))) {
                                            writer.writeAll(recentDel);
                                        }
                                        found2 = true;  
                                    }
                                }
        
                            } else if (fOc == 2) {
                                System.out.println("Enter Plate Number (TO DELETE): ");
                                String plateNumber = sc.nextLine();
        
                                for (String[] row : rows) {
                                    if (!(row[1].equals(searchName) && row[2].equals(plateNumber))) {
                                        filteredRows.add(row);
                                    } else {
                                        found2 = true;
                                    }
                                }
        
                                for (String[] row : rowFLOI) {
                                    if (!(row[1].equals(searchName) && row[8].equals(plateNumber))) {
                                        filteredRowsFLOI.add(row);
                                    } else {
                                        List<String[]> recentDel = new ArrayList<>();
                                        recentDel.add(row);
                                        try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/recentlyDeleted.csv", true))) {
                                            writer.writeAll(recentDel);
                                        }
                                        found2 = true; 
                                    }
                                }
                            }
                            if (!found2) {
                                System.out.println("Name not found in the list.");
                            } else {
                                try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/approvedInsurance.csv"))) {
                                    writer.writeAll(filteredRows);
                                }
                                try (CSVWriter writer2 = new CSVWriter(new FileWriter("csvFiles/fullListOfInsurance.csv"))) {
                                    writer2.writeAll(filteredRowsFLOI);
                                }
        
                                System.out.println("Record(s) successfully deleted.");
                            }
                        } else if (choice == 3){
                            CSVReader RDReader = new CSVReader(new FileReader("csvFiles/recentlyDeleted.csv"));
                            List<String[]> rdRows = RDReader.readAll();  
                            for (String[] row : rdRows) {
                                    for (String value : row) {
                                        System.out.print(String.format("%-20s", value));
                                    }
                                System.out.println(); 
                            } 
                        } else if(choice == 4){
                            List<String[]> getRowsFLOI = new ArrayList<>();
                            System.out.print("Enter Insurance ID: ");
                            int inID = sc.nextInt();
                            for (String[] row : rowFLOI) {
                                if (row[1].equals(String.valueOf(inID))) {
                                    getRowsFLOI.add(row); 
                                }
                            }
                            try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/"+ inID + ".csv", true))) {
                                String[] header = {"Index","InsuranceID","Name","Age","Driving Experience","Car Model","Car Age","Accident History","Plate Number","Type Of Insurance","Premium Amount"};
                                writer.writeNext(header);
                                writer.writeAll(getRowsFLOI);
                            } catch(IOException e){
                                System.out.println("Error reading CSV file: " + e.getMessage());
                            }
                        } else if(choice == 5){
                            break;
                        } else {
                            System.out.println("================================");
                            System.out.println("Try Again");
                            System.out.println("================================");
                        }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }
    }
}

class checkApproved extends approvedList{
    public void toApproved(String index, String insuranceID, String PL, String name, String age, String carmodel, String TOI, String pAccount) {
        System.out.println("=====================");
        System.out.println(name + " Approved");
        System.out.println("=====================");
        try (CSVWriter AWriter = new CSVWriter(new FileWriter("csvFiles/approvedInsurance.csv", true))) {
            String[] aI = {index, insuranceID, PL, name, age, carmodel, TOI, pAccount};
            AWriter.writeNext(aI);  
        } catch (IOException i) {}
    }
    public void toApproved(String number, String insuranceID, String name, String age, String DE, String carmodel, String carage, String AH, String plateNumber, String TOI, String premAcc) {
        try (CSVWriter AWriter = new CSVWriter(new FileWriter("csvFiles/fullListOfInsurance.csv", true))) {
            String[] aI2 = {number, insuranceID, name, age, DE, carmodel, carage, AH, plateNumber, TOI, premAcc};
            AWriter.writeNext(aI2);  
        } catch (IOException i) {}
    }
}

class csvRelated extends checkApproved{
    public boolean checkPN(String PN) {
        boolean alreadyRegistered = false;
        try (CSVReader AReader = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv"))) {
            String[] line;
            while ((line = AReader.readNext()) != null) {
                if (line[2].equalsIgnoreCase(PN)) {
                    System.out.println("Already Registered");
                    alreadyRegistered = true;
                    break;  
                }
            }
        } catch (CsvValidationException | IOException e) {}
        return alreadyRegistered;  
    }
    public boolean checkInsID(String ID){
        boolean registeredID = false;
        try (CSVReader AReader = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv"))) {
            List<String[]> rows = AReader.readAll();  
            for (String[] row : rows) {
                if (row[1].equals(ID)){
                    registeredID = true;
                }
            }
        } catch (IOException | CsvException e) {}
        return registeredID;  
    }
}
