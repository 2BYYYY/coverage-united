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
        try (CSVReader csvReaderAI = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv")))  {
            List<String[]> rows = csvReaderAI.readAll();
            CSVReader csvReaderFLOI = new CSVReader(new FileReader("csvFiles/fullListOfInsurance.csv"));
            List<String[]> rowFLOI = csvReaderFLOI.readAll();
            for (String[] row : rows) {
                    for (String value : row) {
                        System.out.print(String.format("%-15s", value));
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
                    System.out.println("[1] Full delete");
                    System.out.println("[2] Car delete");
                    int fOc = sc.nextInt();
                    sc.nextLine();  // Consume the newline
                    System.out.println("Enter Username/Email (TO DELETE): ");
                    String searchName = sc.nextLine();
                    List<String[]> filteredRows = new ArrayList<>();
                    List<String[]> filteredRowsFLOI = new ArrayList<>();

                    if (fOc == 1) {
                        // Full delete
                        for (String[] row : rows) {
                            if (!row[1].equals(searchName)) {
                                filteredRows.add(row);  // Keep rows that don't match
                            } else {
                                found2 = true;  // Mark as found when deleted
                            }
                        }

                        for (String[] row : rowFLOI) {
                            if (!row[1].equals(searchName)) {
                                filteredRowsFLOI.add(row);  // Keep rows that don't match
                            } else {
                                // Add deleted row to "recently deleted" file
                                List<String[]> recentDel = new ArrayList<>();
                                recentDel.add(row);
                                try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/recentlyDeleted.csv", true))) {
                                    writer.writeAll(recentDel);
                                }
                                found2 = true;  // Mark as found when deleted
                            }
                        }

                    } else if (fOc == 2) {
                        // Car delete (deleting by plate number)
                        System.out.println("Enter Plate Number (TO DELETE): ");
                        String plateNumber = sc.nextLine();

                        for (String[] row : rows) {
                            if (!(row[1].equals(searchName) && row[2].equals(plateNumber))) {
                                filteredRows.add(row);  // Keep rows that don't match
                            } else {
                                found2 = true;  // Mark as found when deleted
                            }
                        }

                        for (String[] row : rowFLOI) {
                            if (!(row[1].equals(searchName) && row[8].equals(plateNumber))) {
                                filteredRowsFLOI.add(row);  // Keep rows that don't match the criteria
                            } else {
                                List<String[]> recentDel = new ArrayList<>();
                                recentDel.add(row);
                                try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/recentlyDeleted.csv", true))) {
                                    writer.writeAll(recentDel);
                                }
                                found2 = true;  // Mark as found when deleted
                            }
                        }
                    }

                    // Check if any record was found and deleted
                    if (!found2) {
                        System.out.println("Name not found in the list.");
                    } else {
                        // Write the updated approvedInsurance.csv
                        try (CSVWriter writer = new CSVWriter(new FileWriter("csvFiles/approvedInsurance.csv"))) {
                            writer.writeAll(filteredRows);
                        }

                        // Write the updated fullListOfInsurance.csv
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
    public void toApproved(String index, String insuranceID, String PL, String name, String age, String carmodel, String TOI, String pAccount) {
        System.out.println(name + " Approved");
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
    public boolean checkName(String name) {
        boolean alreadyRegistered = false;
        try (CSVReader AReader = new CSVReader(new FileReader("csvFiles/approvedInsurance.csv"))) {
            String[] line;
            while ((line = AReader.readNext()) != null) {
                if (line[1].equalsIgnoreCase(name)) {
                    System.out.println("Already Registered");
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
