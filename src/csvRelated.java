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
        try (CSVReader csvReader = new CSVReader(new FileReader("approvedInsurance.csv"))) {
            List<String[]> rows = csvReader.readAll();  // Read all rows at once
            // Iterate and print each row
            for (String[] row : rows) {
                    for (String value : row) {
                        System.out.print(String.format("%-30s", value));
                    }
                System.out.println();  // Move to the next line
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}



class checkApproved extends approvedList{
    public boolean toApproved(String name, String age, String DE, String carmodel, String carage, String AH) {
        System.out.println(name + " Approved");
        boolean success = true;
        try (CSVWriter AWriter = new CSVWriter(new FileWriter("approvedInsurance.csv", true))) {
            String[] aI = {name, age, DE, carmodel, carage, AH};
            AWriter.writeNext(aI);  
            success = false;
        } catch (IOException i) {}
        return success; 
    }
}

class csvRelated extends checkApproved{

    public boolean checkName(String name) {
        boolean alreadyRegistered = false;
        try (CSVReader AReader = new CSVReader(new FileReader("approvedInsurance.csv"))) {
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
