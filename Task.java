/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author localuser
 */
public class Task implements Serializable
{
    private String taskID;
    private String name;
    private Person taskLeader;
    private TaskDate addedOn;
    private TaskDate dueOn;
    private TaskDate completedOn;
    private ArrayList<Person> taskTeamMember;
    private Status taskStatus;
    private Notify notifyOverdue;
    private int dayOverdue;
    public enum Status
    {
        CANCELLED(0), ONGOING(1), PAUSED(2), COMPLETED(3);
        private int value;
        private Status(int value)
        {
           this.value = value; 
        }
        
        private int getValue()
        {
            return value;
        }
        
        public String toString()
        {
            if(this.ordinal() == 0)
            {
                return "Cancelled";
            }
            else if(this.ordinal() == 1)
            {
                return "Ongoing";
            }
            else if(this.ordinal() == 2)
            {
                return "Paused";
            }
            else
            {
                return "Completed";
            }
        }
    }
    
    public enum Notify
    {
        YES(0), NO(1);
        private int value;
        private Notify(int value)
        {
            this.value = value;
        }
        
        private int getValue()
        {
            return value;
        }
        
        public String toString()
        {
            if(this.ordinal() == 0)
            {
                return "Yes";
            }
            else
            {
                return "No";
            }
        }
        
    }

    public Task(String name, Person taskLeader, TaskDate addedOn, TaskDate dueOn, 
            TaskDate completedOn, Status taskStatus, Notify notifyOverdue) 
    {
        RandomIDGenerator rg = new RandomIDGenerator();
        this.taskID = "TSK";
        this.taskID += rg.noDuplicateID(10000, 100000);
        this.name = name;
        this.taskLeader = taskLeader;
        this.addedOn = addedOn;
        this.dueOn = dueOn;
        this.completedOn = completedOn;
        this.taskTeamMember = new ArrayList<Person>();
        this.taskStatus = taskStatus;
        this.notifyOverdue = notifyOverdue;
        this.dayOverdue = this.dayDifference();
    }
    
    /**
    * Add team members into teamMemberList
    *
    * @param p  the Person of Object
    */
    public void addTaskMember(Person p)
    {
        if(!this.taskLeader.equals(p) && !this.taskTeamMember.contains(p))
        {
            this.taskTeamMember.add(p);
        }
    }
    
     /**
    * Print the Member names inside the taskTeamMember ArrayList
    *
    * @return output - String value
    */
    private String printTeamMemberName()
    {
        String output = "";
        for(Person p : this.taskTeamMember)
        {
            output += p.getName() + " ";
        }
        return output;
    }
    
     /**
    * Remove the Member names By member Id from ArrayList
    *
    * @param id the string of Person ID
    * @return boolean - false if remove nothing
    */
    public boolean removeTeamMember(String id)
    {
        for(int i = 0; i < this.taskTeamMember.size(); i++)
        {
            if(id.equalsIgnoreCase(this.taskTeamMember.get(i).getId()))
            {
                this.taskTeamMember.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public int getDayOverdue()
    {
        return this.dayOverdue;
    }
    
    public Person getTaskLeader()
    {
        return this.taskLeader;
    }
    
    public void setTaskLeader(Person taskLeader)
    {
        this.taskLeader = taskLeader;
    }
    
    public String getTaskID() 
    {
        return this.taskID;
    }

    public String getName() 
    {
        return this.name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public TaskDate getAddedOn() 
    {
        return this.addedOn;
    }

    public void setAddedOn(TaskDate addedOn) 
    {
        this.addedOn = addedOn;
    }

    public TaskDate getDueOn() 
    {
        return this.dueOn;
    }

    public void setDueOn(TaskDate dueOn) 
    {
        this.dueOn = dueOn;
    }

    public TaskDate getCompletedOn() 
    {
        return this.completedOn;
    }

    public void setCompletedOn(TaskDate completedOn) 
    {
        this.completedOn = completedOn;
    }

    public Status getTaskStatus()
    {
        return this.taskStatus;
    }

    public void setTaskStatus(Status taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Notify getNotifyOverdue() 
    {
        return this.notifyOverdue;
    }

    public void setNotifyOverdue(Notify notifyOverdue) 
    {
        this.notifyOverdue = notifyOverdue;
    }
    
    @Override
    public String toString() 
    {
        //String leftAlignFormat = "| %-15s | %-20s |%n";
       // System.out.format("+-----------------+------+%n");
        //System.out.printf("| Attribute       | ID   |%n");
        //System.out.format("+-----------------+------+%n");
        String dueDay = " ";
        if(this.isOverdue())
        {
            dueDay += "(" + this.dayOverdue + " day(s) overdue)";
        }
        
        return    "Name           : " + this.name 
                + "\nTask ID        : " + this.taskID 
                + "\nTask Leader    : " + this.taskLeader.getName()
                + "\nTask Team      : " + this.printTeamMemberName()
                + "\nAdded On       : " + this.addedOn 
                + "\nDue On         : " + this.dueOn 
                + "\nCompleted On   : " + this.completedOn + dueDay
                + "\nStatus         : " + this.taskStatus 
                + "\nNotify Overdue : " + this.notifyOverdue;
        //System.out.format("+-----------------+------+%n");
    }
    
   
    public String toEmail() 
    {
        //String leftAlignFormat = "| %-15s | %-20s |%n";
       // System.out.format("+-----------------+------+%n");
        //System.out.printf("| Attribute       | ID   |%n");
        //System.out.format("+-----------------+------+%n");
        String dueDay = " ";
        if(this.isOverdue())
        {
            dueDay += "(" + this.dayOverdue + " day(s) overdue)";
        }
        
        return    "Name                 " + this.name 
                + "\nTask ID              " + this.taskID 
                + "\nTask Leader      " + this.taskLeader.getName()
                + "\nTask Team        " + this.printTeamMemberName()
                + "\nAdded On          " + this.addedOn 
                + "\nDue On              " + this.dueOn 
                + "\nCompleted On   " + this.completedOn + dueDay
                + "\nStatus                " + this.taskStatus 
                + "\nNotify Overdue   " + this.notifyOverdue;
        //System.out.format("+-----------------+------+%n");
    }
    
     /**
    * Get the days difference between completed date and due date
    *
    * 
    * @return day - days difference
    */
    public int dayDifference()
    {
        Date dayComplete = this.completedOn.getTaskDate().getTime();
        Date dayDue = this.dueOn.getTaskDate().getTime();
        long dayCompleteTime = dayComplete.getTime();
        long dayDueTime = dayDue.getTime();
        long diffTime = dayCompleteTime - dayDueTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        int day = (int)diffDays;
        return day;
    }
    
   public boolean isOverdue()
   {
       if(this.dayOverdue > 0)
       {
           return true;
       }
       else
       {
           return false;
       }
   }
    
}
