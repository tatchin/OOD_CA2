/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author localuser
 */
public class Task
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
    public enum Status
    {
        Cancelled, Ongoing, Paused, Completed
    }
    
    public enum Notify
    {
        Yes, No
    }

    public Task(String name, Person taskLeader, TaskDate addedOn, TaskDate dueOn, 
            TaskDate completedOn, Status taskStatus, Notify notifyOverdue) 
    {
        RandomGenerator rg = new RandomGenerator();
        this.taskID = "EMP";
        this.taskID += rg.noDuplicate(1, 100000);
        this.name = name;
        this.taskLeader = taskLeader;
        this.addedOn = addedOn;
        this.dueOn = dueOn;
        this.completedOn = completedOn;
        this.taskTeamMember = new ArrayList<Person>();
        this.taskStatus = taskStatus;
        this.notifyOverdue = notifyOverdue;
    }
    
    public void addTaskMember(Person p)
    {
        this.taskTeamMember.add(p);
    }
    public Person getTaskLeader()
    {
        return taskLeader;
    }

    public void setTaskLeader(Person taskLeader)
    {
        this.taskLeader = taskLeader;
    }
    
    public String getTaskID() 
    {
        return taskID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public TaskDate getAddedOn() 
    {
        return addedOn;
    }

    public void setAddedOn(TaskDate addedOn) 
    {
        this.addedOn = addedOn;
    }

    public TaskDate getDueOn() 
    {
        return dueOn;
    }

    public void setDueOn(TaskDate dueOn) 
    {
        this.dueOn = dueOn;
    }

    public TaskDate getCompletedOn() 
    {
        return completedOn;
    }

    public void setCompletedOn(TaskDate completedOn) 
    {
        this.completedOn = completedOn;
    }

    public Status getTaskStatus()
    {
        return taskStatus;
    }

    public void setTaskStatus(Status taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Notify getNotifyOverdue() 
    {
        return notifyOverdue;
    }

    public void setNotifyOverdue(Notify notifyOverdue) 
    {
        this.notifyOverdue = notifyOverdue;
    }

    @Override
    public String toString() 
    {
        return "Task{" + "taskID=" + taskID + ", name=" + name + ", addedOn=" + addedOn + ", dueOn=" + dueOn + ", completedOn=" + completedOn + ", taskStatus=" + taskStatus + ", notifyOverdue=" + notifyOverdue + '}';
    }
    
    public int dayDifference()
    {
        Date dayComplete = this.completedOn.getTaskDate().getTime();
        Date dayDue = this.dueOn.getTaskDate().getTime();
        long dayCompleteTime = dayComplete.getTime();
        long dayDueTime = dayDue.getTime();
        long diffTime = dayDueTime - dayCompleteTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        int day = (int)diffDays;
        return day;
    }
    
}
