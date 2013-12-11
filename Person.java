/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

/**
 *
 * @author localuser
 */
public class Person 
{
    private String name;
    private String id;
    private String email;
    private String tel;

    public Person(String name, String email, String tel) 
    {
        this.setId();
        this.name = name;
        this.email = email;
        this.tel = tel;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getId() 
    {
        return id;
    }

    public void setId() 
    {
        RandomGenerator randomNumber = new RandomGenerator();
        this.id = "EMP";
        this.id += randomNumber.noDuplicate(10000, 100000);
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getTel() 
    {
        return tel;
    }

    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    @Override
    public String toString() 
    {
        return "Employee Name : " + this.name 
                + "\nEmployee ID   : " + this.id 
                + "\nEmployee Email: " + this.email 
                + "\nEmployee Tel  : " + this.tel;
    }
    
    
}
