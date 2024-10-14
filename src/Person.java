public class Person {
    private String name;
    private int age;
    private int drivingExperience;
    private int insuranceID;

    Person() {
        this.name = "Person";
        this.age = 0;
        this.drivingExperience = 1;
        this.insuranceID = 0;
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getDE() {
        return drivingExperience;
    }

    public void setInsuranceID(int insuranceID) {
        this.insuranceID = insuranceID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDE(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

}
