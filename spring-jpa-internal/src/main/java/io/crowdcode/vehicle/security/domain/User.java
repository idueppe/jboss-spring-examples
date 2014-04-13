package io.crowdcode.vehicle.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Account")
@NamedQueries({@NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.FIND_BY_ROLES, query = "SELECT u FROM User u WHERE u.role = :role"),
        @NamedQuery(
                        name = User.FIND_NO_CUSTOMER_MEMBER_BY_COMPANY_NAME, 
                        query="SELECT u FROM User u, FleetGroup g " +
                        	"WHERE u.role = io.crowdcode.vehicle.security.domain.Role.CUSTOMER " +
                        	"AND g.fleet.companyName = :companyName " +
                        	"AND NOT u MEMBER OF g.users ")
})
public class User {

    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_ALL = "User.findByAll";
    public static final String FIND_BY_ROLES = "User.findByRole";
    public static final String FIND_NO_CUSTOMER_MEMBER_BY_COMPANY_NAME = "User.findNoCumstomerMembersByCompanyName";


    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String surename;
    private String firstname;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

}
