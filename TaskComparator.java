/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.util.Comparator;

/**
 *
 * @author TatChin Tee
 */
public class TaskComparator implements Comparator<Task>
{
    private static byte sortOrder;
    public static final byte SORT_ASCENDING = -1;
    public static final byte SORT_DESCENDING = 1;
    
    public TaskComparator(byte theSortOrder)
    {
       sortOrder = theSortOrder;
    }

    @Override
    public int compare(Task t1, Task t2) 
    {
        return sortOrder * t1.getTaskStatus().compareTo(t2.getTaskStatus());
    }
}
