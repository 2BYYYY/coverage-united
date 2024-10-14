public class Customer extends Person {
    private String carModel;
    private int carAge;
    private int accidentHistory;
    private String plateNumber;

    Customer() {
        this.carAge = 0;
        this.carModel = "carModel";
        this.accidentHistory = 0;
        this.plateNumber = "plateNumber";
    }

    public String getCarModel() {
        return carModel;
    }

    public int getCarAge() {
        return carAge;
    }

    public int getAccidentHistory() {
        return accidentHistory;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarAge(int carAge) {
        this.carAge = carAge;
    }

    public void setAccidentHistory(int accidentHistory) {
        this.accidentHistory = accidentHistory;
    }
}
