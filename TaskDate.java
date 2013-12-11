/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author TatChin Tee
 */
public class TaskDate implements Comparable
{
    private Calendar taskDate;
    public TaskDate(int date, int month, int year)
    {
        this.taskDate = Calendar.getInstance();
        this.taskDate.set(year, month-1, date);
    }

    public Calendar getTaskDate()
    {
        return taskDate;
    }
    
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(taskDate.getTime());
    }
    
    @Override
    public int compareTo(Object obj)
    {
        if(obj != null)
        {
            return 0;
        }
        
        if(this.getClass() != obj.getClass())
        {
            return 0;
        }
        
        TaskDate other = (TaskDate)obj;
        if(this.getTaskDate().before(other.getTaskDate()))
        {
            return -1;
        }
        else if(this.getTaskDate().after(other.getTaskDate()))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    
}
