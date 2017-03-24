package ca.ubc.it.as.udetective.ui.model;

import java.io.Serializable;

public class Admin implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private static final String TO_STRING_DELIMITER = ":";
	
    //Unique ID
    private int id;

    //CWL user name
    private String cwlName;

    //First name
    private String firstName;

    // Last name
    private String lastName;

    // Middle name
    private String middleName;

    // Email
    private String email;

        
    // Empty constructor
    public Admin()
    {
    }

    /**
     * Constructor with required parameters
     * @param id
     * @param cwlName
     * @param firstName
     * @param lastName
     * @param email
     */
    public Admin(int id, String cwlName, String firstName, String lastName, String email)
    {
        this.id        = id;
        this.cwlName   = cwlName;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }
    
    /**
     * Constructor with required parameters
     * @param id
     * @param cwlName
     * @param firstName
     * @param lastName
     * @param middleName
     * @param email
     */
    public Admin(int id, String cwlName, String firstName, String lastName, String middleName, String email)
    {
        this.id         = id;
        this.cwlName    = cwlName;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.middleName = middleName;
        this.email      = email;
    }
    
    public int getId() {
        return this.id;
    }

    public String getCwlName() 
    {
        return this.cwlName;
    }

    public void setCwlName(String cwlName) 
    {
        this.cwlName = cwlName;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public String getMiddleName() 
    {
        return middleName;
    }

    public void setMiddleName(String middleName) 
    {
        this.middleName = middleName;
    }

    public String getEmail() 
    {
        return this.email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return getId()        + TO_STRING_DELIMITER + getCwlName()    + TO_STRING_DELIMITER + 
               getFirstName() + TO_STRING_DELIMITER + getMiddleName() + TO_STRING_DELIMITER +
               getLastName()  + TO_STRING_DELIMITER + getEmail();
    }
}