class InsuranceChecker {
    boolean flag = false;
    String icReturn = "";
    String cov = "";

    public boolean getFlag() {
        return flag;
    }

    public void setCarModel(boolean flag) {
        this.flag = flag;
    }

    public String getCov() {
        return cov;
    }
    public void setCov(String cov){
        this.cov = cov;
    }

    public String checkInsurance(int carAge, int accidentHistory) {
        setCarModel(false);
        if (carAge <= 5 && accidentHistory == 0) {
            icReturn = "Full Coverage";
            System.out.println(icReturn);
            System.out.println("You're eligible for Full Coverage. Full coverage is the most comprehensive option, combining liability, collision, and comprehensive insurance. It protects you from a wide range of risks, including damages to your own vehicle in accidents, theft, vandalism, and natural disasters.");
            setCarModel(true);
            setCov(icReturn);
            getCov();
        }
        return icReturn;

    }
}
class PartialCoverage extends InsuranceChecker {
    @Override
    public String checkInsurance(int carAge, int accidentHistory) {
        if(getFlag() == false)
        if (carAge >= 6 && carAge <= 10 || accidentHistory >= 1) {
            icReturn = "Partial Coverage";
            System.out.println(icReturn);
            System.out.println("You're eligible for Partial Coverage. Partial coverage provides a balance of protection and affordability. It includes liability insurance and covers some damages to your vehicle, like those caused by collisions. However, it may not cover other incidents such as theft or weather-related damage.");
            setCarModel(true);
            setCov(icReturn);
            getCov();
        }
        return icReturn;

    }
}
class BasicCoverage extends InsuranceChecker {
    @Override
    public String checkInsurance(int carAge, int accidentHistory) {
        if(getFlag() == false){
        if (carAge > 10) {
            icReturn = "Basic Coverage";
            System.out.println(icReturn);
            System.out.println("You're eligible for Basic Coverage. Basic coverage is the minimum level of insurance, covering liability for damage or injuries you cause to others in an accident. It doesn't include coverage for your own vehicle. This is typically recommended for older cars with low market value, where the cost of repairs could exceed the value of the vehicle.");
            setCarModel(true);
            setCov(icReturn);
            getCov();
        }
        }
        return icReturn;
    }
}
