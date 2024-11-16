// Class representing a person with first name, last name and email
public class Person {
    // Declaring the instance variables
    private String firstName;
    private String surname;
    private String email;

    // Constructor to initialize the Person object with the provided values
    public Person(String name,String surname, String email){
        this.firstName =name;
        this.surname=surname;
        this.email=email;
    }
    // Getter method to get first name
    public String getFirstName() {
        return firstName;
    }
    // Getter method to get surname name
    public String getSurname(){
        return surname;
    }
    // Getter method to get email
    public String getEmail(){
        return email;
    }
    // Setter method to set first name
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    // Setter method to set surname
    public void setSurname(String surname){
        this.surname = surname;
    }
    // Setter method to set email
    public void setEmail(String email) {
        this.email = email;
    }

    // Override the toString() method to provide a string representation of the Person object
    public String toString() {
        return "First Name: '" + firstName.toUpperCase() + '\'' + ", Surname: '" + surname.toUpperCase() + '\'' + ", Email: '" + email + '\'';
    }

    // Method to print the person's information
    public void printInformationOfPerson(){
        System.out.println("First Name: "+ firstName.toUpperCase());
        System.out.println("Surname: "+surname.toUpperCase());
        System.out.println("Email: "+email);
    }
}