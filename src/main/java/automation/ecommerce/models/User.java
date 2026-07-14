package automation.ecommerce.models;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobile;

    public User(
            String firstName,
            String lastName,
            String email,
            String password,
            String address,
            String country,
            String state,
            String city,
            String zipcode,
            String mobile) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getMobile() {
        return mobile;
    }
}