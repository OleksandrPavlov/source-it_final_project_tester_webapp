package tester.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class AccountModel implements Serializable {

	private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String email;
    private Timestamp created;
    private Boolean active;
    private String password;
    private List<String> role;
    
    public AccountModel(){
    	
    }
    
	public AccountModel(long id, String firstName, String lastName, String middleName, String login, String email,
			Timestamp created, Boolean active, String password,List<String> role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.login = login;
		this.email = email;
		this.created = created;
		this.active = active;
		this.password = password;
		this.role=role;
	}
	
	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role=role;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return firstName+" "+lastName;
	}
	
    
    
}
