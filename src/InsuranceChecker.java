class InsuranceChecker {
    boolean flag = false;
    String icReturn = "";

    public boolean getFlag() {
        return flag;
    }

    public void setCarModel(boolean flag) {
        this.flag = flag;
    }

    public String checkInsurance(int carAge, int accidentHistory) {
        if (carAge <= 5 && accidentHistory == 0) {
            icReturn = "Full Coverage";
            System.out.println(icReturn);

        }
        return icReturn;

    }
}
class PartialCoverage extends InsuranceChecker {
    @Override
    public String checkInsurance(int carAge, int accidentHistory) {
        if (carAge >= 6 && carAge <= 10 || accidentHistory >= 1) {
            icReturn = "Partial Coverage";
            System.out.println(icReturn);
        }
        return icReturn;

    }
}
class BasicCoverage extends InsuranceChecker {
    @Override
    public String checkInsurance(int carAge, int accidentHistory) {
        if (carAge > 10) {
            icReturn = "Basic Coverage";
            System.out.println(icReturn);
        }
        return icReturn;
    }
}
