package runner.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    //@Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String socialSecurity;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "user", cascade = PERSIST,fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn //sharing primary key with address since creating a new user requires address anyways
    private Address address;

    @OneToOne(mappedBy = "user", cascade = PERSIST,fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn //sharing primary key with user login since creating a new user requires a login anyways
    private Login login;

    //@JsonManagedReference
    @OneToMany(cascade= PERSIST,fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public User() {
    }

    public User(Long id, String firstName, String middleName, String lastName, String socialSecurity) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.socialSecurity = socialSecurity;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSocialSecurity() {
        return socialSecurity;
    }
    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String address) {
        this.email = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    public LocalDate getDateOfBirth() {   return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) {   this.dateOfBirth = dateOfBirth;  }
}