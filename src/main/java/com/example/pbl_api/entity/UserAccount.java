package com.example.pbl_api.entity;

import com.example.pbl_api.model.UserAccountModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false)
	    private Long id;

	    @Column(name = "username", nullable = false, unique = true)
	    private String username;

	    @Column(name = "password", nullable = false)
	    private String password;

		@Column(name = "provider")
		private String provider;

		@OneToOne(mappedBy = "userAccount")
		private User user;


	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "user_role",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles;


	public UserAccount(String username, String password, List<Role> roles,String provider) {
		this.username = username;
		this.password = password;
		this.roles= new HashSet<>(roles);
		this.provider=provider;
	}

	public UserAccount() {

	}

	public void editAccount(UserAccountModel userAccountModel){
		this.username = userAccountModel.getUsername();
		this.password = userAccountModel.getPassword();
//		this.roles= new HashSet<>(roles);
	}


	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }



}
