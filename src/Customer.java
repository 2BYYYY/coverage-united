public class Customer extends Person{
    private String carModel;
    private int carAge;
    private int accidentHistory;

    public String getCarModel(){
        return carModel;
    }
    public int getCarAge(){
        return carAge;
    }
    public int getAccidentHistory(){
        return accidentHistory;
    }
    public void setCarModel(String carModel){
        this.carModel = carModel;
    }
    public void setCarAge(int carAge){
        this.carAge = carAge;
    }
    public void setAccidentHistory(int accidentHistory){
        this.accidentHistory = accidentHistory;
    }
}
