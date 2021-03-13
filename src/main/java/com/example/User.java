package com.example;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name= "users") 

public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "ID")

    private long ID;
    @Column(name = "username",unique=true)
    private String username;
    
    @Override
	public String toString() {
		return "User [ID=" + ID + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	@Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;


    public User() {
        
    }
    
    public User(long id, String username, String email, String password) {
            this.ID = id;
            this.username = username;
            this.email = email;
            this.password = password;
    }
    

    public long getID() {return this.ID; }
    public String getUsername() { return this.username;}
    public String getEmail() { return this.email;}
    public String getPassword() { return this.password;}
    
    public void setID(long id) { this.ID = id;}
    public void setUsername(String username) { this.username = username;}
    public void setEmail(String email) { this.email = email;}
    public void setPassword(String password) { this.password = password;}
}