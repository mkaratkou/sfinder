package job.stafffinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import job.stafffinder.validation.UserPhoneNumberProvided;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static job.stafffinder.validation.ValidationMessages.REQUIRED_FIELD;
import static job.stafffinder.validation.ValidationMessages.INVALID_NAME;
import static job.stafffinder.validation.ValidationMessages.INVALID_EMAIL;


@UserPhoneNumberProvided
public class User {

    private Long id;
    @NotBlank(message = REQUIRED_FIELD)
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotNull(message = REQUIRED_FIELD)
    @Pattern(regexp="(?=.*[a-z])(?=.*[A-Z]).{2,50}", message = INVALID_NAME)
    private String firstName;

    @NotNull(message = REQUIRED_FIELD)
    @Pattern(regexp="(?=.*[a-z])(?=.*[A-Z]).{2,50}", message = INVALID_NAME)
    private String lastName;

    @NotBlank(message = REQUIRED_FIELD)
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

