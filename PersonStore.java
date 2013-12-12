/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author localuser
 */
public class PersonStore implements Serializable
{
    private ArrayList<Person> list;
    
    public PersonStore()
    {
        this.list = new ArrayList<Person>();
    }
    
    public void add(Person p)
    {
        this.list.add(p);
    }
    
    public int storeSize()
    {
        return this.list.size();
    }
    public int search(String id)
    {
        int position = -1;
        for(int i = 0; i < this.list.size(); i++)
        {
            if (this.list.get(i).getId().equals(id))
            {
                position = i;
                break;
            }
        }
        return position;
    }
    
    public boolean searchName(String name)
    {
        boolean done = false;
        int i = 0;
        while(done && i < this.list.size())
        {
            if (this.list.get(i).getName().equals(name))
            {
                done = true;
            }
        }
        return done;
    }
     
    public boolean searchEmail(String email)
    {
        boolean done = false;
        int i = 0;
        while(done && i < this.list.size())
        {
            if (this.list.get(i).getEmail().equals(email))
            {
                done = true;
            }
        }
        return done;
    }
     
    public boolean searchTel(String tel)
    {
        boolean done = false;
        int i = 0;
        while(done && i < this.list.size())
        {
            if (this.list.get(i).getTel().equals(tel))
            {
                done = true;
            }
        }
        return done;
    }
      
    public boolean editByName(String id, String name)
    {
        int position = search(id);
        if(position != -1)
        {
            this.list.get(position).setName(name);
            return true;
        }
        return false;            
    }
    
    public boolean editByEmail(String id, String email)
    {
        int position = search(id);
        if(position != -1)
        {
            this.list.get(position).setEmail(email);
            return true;
        }
        return false;
    }
    
    public boolean editByTel(String id, String tel)
    {
        int position = search(id);
        if(position != -1)
        {
            this.list.get(position).setTel(tel);
            return true;
        }
        return false;
    }
    
    public boolean delete(String id)
    {
        int position = search(id);
        if(position != 1)
        {
            this.list.remove(position);
            return true;
        }
        return false;
    }
    
    public void deleteAll()
    {
       this.list.removeAll(this.list);
    }
    
    public void printAll()
    {
        for(Person p : this.list)
        {
            System.out.println(p);
        }
    }
    
    public void printByName(String name)
    {
        if(searchName(name))
        {
            for(int i = 0; i < this.list.size(); i++)
            {
                if(this.list.get(i).getName().equalsIgnoreCase(name))
                {
                    System.out.println(this.list.get(i));
                }
            }
        }
        else
        {
            System.out.println("Error [" + name + "] no FOUND!");
        }
    }
    
    public void printByEmail(String email)
    {
        if(searchEmail(email))
        {
            for(int i = 0; i < this.list.size(); i++)
            {
                if(this.list.get(i).getEmail().equalsIgnoreCase(email))
                {
                    System.out.println(this.list.get(i));
                }
            }
        }
        else
        {
            System.out.println("Error [" + email + "] no FOUND!");
        }
    }
      
    public void printByTel(String tel)
    {
        if(searchTel(tel))
        {
            for(int i = 0; i < this.list.size(); i++)
            {
                if(this.list.get(i).getTel().equalsIgnoreCase(tel))
                {
                    System.out.println(this.list.get(i));
                }
            }
        }
        else
        {
            System.out.println("Error [" + tel + "] no FOUND!");
        }
    }
}

