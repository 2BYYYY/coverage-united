class InsuranceChecker {
    boolean flag = false;
    public boolean getFlag(){
        return flag;
    }
    public void setCarModel(boolean flag){
        this.flag = flag;
    }
    void checkInsurance(int carAge, int accidentHistory){
        if(carAge <= 5 && accidentHistory == 0){
            System.out.println("Full Coverage");
        }
    }
}

class PartialCoverage extends InsuranceChecker{
    @Override void checkInsurance(int carAge, int accidentHistory){
        if(carAge >= 6 && carAge <= 10 || accidentHistory >=1){
            System.out.println("Partial Coverage");
        }
    }
}

class BasicCoverage extends InsuranceChecker{
    @Override void checkInsurance(int carAge, int accidentHistory){
        if(carAge > 10){
            System.out.println("Basic Coverage");
        }
    }
}
