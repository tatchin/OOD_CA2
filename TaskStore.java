 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author localuser
 */
public class TaskStore implements Serializable, Cloneable
{
    private ArrayList<Task> list;
    public TaskStore()
    {
        this.list = new ArrayList<Task>();
    }
   
    //(1)Add task to the Systems
    public void add(Task t)
    {
        if(!this.list.contains(t))
        {
            this.list.add(t);
        }
    }
    
    public int getSize()
    {
        return this.list.size();
    }
    
    public int search(String id)
    {
        int position = -1;
        for (int i = 0; i < this.list.size(); i++)
        {
            if (this.list.get(i).getTaskID().equalsIgnoreCase(id))
            {
                position =  i;
                break;
            }
        }
        return position;
    }
    //(2)Print task
    public void print()
    {
        for(int i = 0; i < this.list.size(); i++)
        {
            System.out.println("===========================================");
            System.out.println("\t\tTask " + (i + 1));
            System.out.println("===========================================");
            System.out.println(this.list.get(i));
            System.out.println("===========================================\n");
        }
    }
    //(2)Print task by leader Id
    public void printByLeaderId(String id)
    {   
        int found = -1;
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).getTaskLeader().getId().equalsIgnoreCase(id))
            {
                System.out.println(this.list.get(i));
                found = 1;
            }
        }
        Utility.noFoundMessage(found);
    }
  
    //(7)Return a list of all tasks with a user-defined status.
    public ArrayList<Task> taskByStatus(Task.Status status)
    {
        ArrayList<Task> taskList = new ArrayList<Task>();
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).getTaskStatus().equals(status))
            {
                taskList.add(this.list.get(i));
            }
        }
        return taskList;
    }
 
    //(8) Return a list of all tasks due within a date range.
    public ArrayList<Task> taskBeforeDue(TaskDate start, TaskDate end)
    {
        ArrayList<Task> taskList = new ArrayList<Task>();
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).getDueOn().compareTo(end) < 0 && 
                    this.list.get(i).getDueOn().compareTo(start) > 0)
            {
                taskList.add(this.list.get(i));
            }
        }
        return taskList;
    }
    
      //(9) Return a list of all tasks sorted by status
    public ArrayList<Task> taskSortedStatus()
    {
        ArrayList<Task> taskList = new ArrayList<Task>();
       
        for(int i = 0; i < this.list.size(); i++)
        {
            taskList.add(this.list.get(i));
        }
         TaskComparator tComparator = new TaskComparator(TaskComparator.SORT_ASCENDING);
        Collections.sort(taskList, tComparator);
       
        return taskList;
    }

    //(12) Return a list of all tasks completed by a user-defined team leader which were overdue by more than a user-defined number of days.
    public ArrayList<Task> getTaskListByLeaderIdAndByNumberOfDay(String id, int day)
    {
        ArrayList<Task> newTask = new ArrayList<Task>();
        for(int i = 0; i < this.list.size(); i++)
        {
            if(id.equalsIgnoreCase(this.list.get(i).getTaskLeader().getId())
                    && (this.list.get(i).getDayOverdue() > 0
                    && this.list.get(i).getDayOverdue() > day))
            {
                newTask.add(this.list.get(i));
            }
        }
        return newTask;   
    }
    

    //(10) Compare 2 lists for equality
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.list != null ? this.list.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final TaskStore other = (TaskStore) obj;
        if (this.list != other.list && (this.list == null || !this.list.equals(other.list))) {
            return false;
        }
        return true;
    }
    
    //(11) Copy a task store by implementing Cloneable
    @Override
    public TaskStore clone() 
    {  
        TaskStore taskStoreClone = null;  
        try 
        {  
           taskStoreClone = (TaskStore) super.clone();  
        } 
        catch (CloneNotSupportedException e) 
        {  
            e.printStackTrace();  
        }  
        return taskStoreClone;  
    }  
    
     //(14)Send a reminder email to the task team leader when a task the due for a task is within seven days.
    public void sendReminderEmail()
    {
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).dayDifference() <= -7)
            {
                MailUtility.send(this.list.get(i).getTaskLeader().getEmail(), "New Task"
                        , this.list.get(i).toEmail(), "text/plain");
            }
        }
    }
    
}
