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
public class TaskStore implements Serializable, Cloneable
{
    private ArrayList<Task> list;
    public TaskStore()
    {
        this.list = new ArrayList<Task>();
    }
    
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
        for(int i = 0; i < this.list.size(); i++)
        {
            System.out.println("===========================================");
            System.out.println("\t\tTask " + (i + 1));
            System.out.println("===========================================");
            System.out.println(this.list.get(i));
            System.out.println("===========================================\n");
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
    
//    public ArrayList<Task> copyTaskStore(ArrayList<Task> t)
//    {
//        ArrayList<Task> copy = new ArrayList<Task>();
//        for(int i = 0; i < t.size(); i++)
//        {
//            copy.add(t.get(i));
//        }
//        return copy;
//    }
    
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
    
    ///PROBLEM!!!!!!!!!
    public  boolean equalLists(Object obj)
    {     
        TaskStore ts = (TaskStore)obj;
        if(this.list.isEmpty() && ts.list.isEmpty())
        {
            return true;
        }
//        if(this.list.size() != ts.list.size())
//        {
//            return false;
//        }
        if(this.list.equals(ts.list))
        {
            return true;
        }
//        for (Task t : ts.list) 
//        {
//            if(!this.list.contains(t))
//            {
//                return true;
//            }
//        }
        return false;
    }
//    public void edit(String id, String i)
//    {
//        int position = searc
//    }
}
