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
public class TaskStore 
{
    private ArrayList<Task> list;
    public TaskStore()
    {
        this.list = new ArrayList<Task>();
    }

   
    public void add(Task t)
    {
        if(!this.list.contains(t))
        {
            this.list.add(t);
        }
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
    
    public void print()
    {
        for(Task t : this.list)
        {
            System.out.println(t);
        }
    }
    
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
    
   /* public void taskByStatus(Task.Status status)
    {
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).getTaskStatus().equals(status))
            {
                System.out.println(this.list.get(i));
            }
        }
    }*/
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
   /* public ArrayList<Task> taskSortedStatus(TaskDate td)
    {
        ArrayList<Task> taskList = new ArrayList<Task>();
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).getDueOn().compareTo(td) < 0)
            {
                taskList.add(this.list.get(i));
            }
        }
        return taskList;
    }*/
    public ArrayList<Task> taskBeforeDue(TaskDate td)
    {
        ArrayList<Task> taskList = new ArrayList<Task>();
        for(int i = 0; i < this.list.size(); i++)
        {
            if(this.list.get(i).getDueOn().compareTo(td) < 0)
            {
                taskList.add(this.list.get(i));
            }
        }
        return taskList;
    }
    public ArrayList<Task> copyTaskStore(ArrayList<Task> t)
    {
        ArrayList<Task> copy = new ArrayList<Task>();
        for(int i = 0; i < t.size(); i++)
        {
            copy.add(t.get(i));
        }
        return copy;
    }
//    public void edit(String id, String i)
//    {
//        int position = searc
//    }
}
