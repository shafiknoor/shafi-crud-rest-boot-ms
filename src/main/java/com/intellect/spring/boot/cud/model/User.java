package com.intellect.spring.boot.cud.model;

public class User {
	 
    private long id;
    private String fName;
    private String lName;
    private String email;
    private long pinCode;
    private String birthDate;
    private boolean isActive;
    
    
 
    public User(long id, String fName, String lName, String email, long pinCode, String birthDate, boolean isActive) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pinCode = pinCode;
		this.birthDate = birthDate;
		this.isActive = isActive;
	}

	public User(){
        id=0;
    }
     

     
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    
 
    public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }



	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", pinCode=" + pinCode
				+ ", birthDate=" + birthDate + ", isActive=" + isActive + "]";
	}
 
    
 
 
}
