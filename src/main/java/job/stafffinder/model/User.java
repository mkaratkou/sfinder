package job.stafffinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import static job.stafffinder.validation.Patterns.NAME_PATTERN;

public class User {

    private Long id;
    @NotBlank
    private String email;
    @NotBlank(message = "error.invalid.name")
    @Pattern(regexp=NAME_PATTERN, message = "error.invalid.name")
    private String firstName;
    @NotBlank(message = "error.invalid.name")
    @Pattern(regexp=NAME_PATTERN, message = "error.invalid.name")
    private String lastName;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String landlinePhoneNumber;
    private String mobilePhoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLandlinePhoneNumber() {
        return landlinePhoneNumber;
    }

    public void setLandlinePhoneNumber(String landlinePhoneNumber) {
        this.landlinePhoneNumber = landlinePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

}

