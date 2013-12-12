/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;
import java.io.Serializable;

/**
 *
 * @author localuser
 */
public class Person implements Serializable
{
    private String name;
    private String id;
    private String email;
    private String tel;

     /**
    * Person that employee in company
    * 
    * @param name - employee name
    * @param email - employee email
    * @param tel - employee phone number
    */
    public Person(String name, String email, String tel) 
    {
        this.setId();
        this.name = name;
        this.email = email;
        this.tel = tel;
    }

    /**
    * Get Person name
    * 
    * @return employee name 
    */
    public String getName() 
    {
        return name;
    }

    /**
    * Set Person name
    * 
    * @param name employee name
    */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
    * Get Person ID
    * 
    * @return employee ID 
    */
    public String getId() 
    {
        return id;
    }

    /**
    * Set the employee id by randomGenerator
    */
    public void setId() 
    {
        RandomIDGenerator randomNumber = new RandomIDGenerator();
        this.id = "EMP";
        this.id += randomNumber.noDuplicateID(10000, 100000);
    }

    /**
    * Get Person Email
    * 
    * @return employee Email 
    */
    public String getEmail() 
    {
        return email;
    }

    /**
    * Set Person Email
    * 
    * @param email employee email 
    */
    public void setEmail(String email) 
    {
        this.email = email;
    }

    /**
    * Get Person phone number
    * 
    * @return employee tel 
    */
    public String getTel() 
    {
        return tel;
    }

    /**
    * Set Person phone number
    * 
    * @param tel employee phone number 
    */
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

   /**
    * Set String value of Person
    * 
    * @return String 
    */
    @Override
    public String toString() 
    {
        return "Employee Name : " + this.name 
                + "\nEmployee ID   : " + this.id 
                + "\nEmployee Email: " + this.email 
                + "\nEmployee Tel  : " + this.tel;
    }
}
