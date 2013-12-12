/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author TatChin Tee
 */
public class SecondApp 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        MainApp theApp = new MainApp();
        theApp.start();
    }
    
    public void start()
    {
        Scanner kb = new Scanner(System.in);
        Person p1 = new Person("ada","sdad","asfa");
        Person p2 = new Person("22","222","222");
        Person p3 = new Person("sdad","222","222");
        Person p4 = new Person("gg","222","222");
        
        Task t1 = new Task("Desc of Task", p1, new TaskDate(1,2,2013), new TaskDate(1,3,2013),
                new TaskDate(5,2,2013), Task.Status.ONGOING, Task.Notify.YES);
        Task t3 = new Task("Desc of Task", p1, new TaskDate(1,2,2013), new TaskDate(1,3,2013),
                new TaskDate(5,2,2013), Task.Status.ONGOING, Task.Notify.YES);
        Task t4 = new Task("Desc of Task", p1, new TaskDate(1,2,2013), new TaskDate(1,3,2013),
                new TaskDate(5,2,2013), Task.Status.ONGOING, Task.Notify.NO);
        t1.addTaskMember(p3);
        Task t2 = new Task("Desc of Task", p2, new TaskDate(2,2,2013), new TaskDate(2,3,2013),
                new TaskDate(7,3,2013), Task.Status.PAUSED, Task.Notify.NO);
        t2.addTaskMember(p3);
        TaskStore t = new TaskStore();
        TaskStore s = new TaskStore();
        s.add(t1);
        s.add(t2);
        s.add(t3);
        t.add(t1);
        t.add(t2);
        t.add(t4);
        TaskStore e = t.clone();
        t.print();
        TaskComparator tComparator 
                = new TaskComparator(TaskComparator.SORT_ASCENDING);
       // Collections.sort(s, tComparator);
       // MailUtility.send("teetatchin@gmail.com", "New Task", t2.toEmail(), "text/plain");
        //System.out.println(t.equalLists(s));
        System.out.println(t2);
        SerializationUtility.save("c:/temp/", "taskStore.txt", t);
	TaskStore taskStore = (TaskStore)SerializationUtility.load("c:/temp/", 
				"taskStore.txt");
        taskStore.print();
        //e.print();
    }
}