/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;
import java.util.HashSet;

/**
 *
 * @author TatChin Tee
 */
public class RandomGenerator 
{
    private HashSet<Integer> generatedSet;
    
    public RandomGenerator()
    {
        this.generatedSet = new HashSet<Integer>();
    }
    
    public int noDuplicate(int min, int max)
    {
        int maxSize = max - min + 1;
        if (generatedSet.size() == maxSize)
        {
            return min - 1;
        }
        
        int number = 0;
        Integer intObject = null;
        
        do
        {
            number = duplicate(min, max);
            intObject = new Integer(number);
        }while (generatedSet.contains(intObject));
        
        generatedSet.add(intObject);
        return number;
    }
    
    public int duplicate(int min, int max)
    {
        return (int)Math.round((max - min) * Math.random() + min);
    }
    
    public int size()
    {
        return generatedSet.size();
    }

    public void reset()
    {
        generatedSet.clear();
    }
}
