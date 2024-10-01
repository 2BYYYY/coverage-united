class InsuranceChecker {
    void FullCoverage(int carAge, int accidentHistory){
        if(carAge <= 5 && accidentHistory == 0){
            System.out.println("Full Coverage");
        }
    }

    void PartialCoverage(int carAge, int accidentHistory){
        if(carAge >= 6 && carAge <= 10 || accidentHistory <=1){
            System.out.println("Partial Coverage");
        }
    }

    void BasicCoverage(int carAge, int accidentHistory){
        if(carAge > 10){
            System.out.println("Basic Coverage");
        }
    }
}
