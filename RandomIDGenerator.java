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
public class RandomIDGenerator 
{
    private HashSet<Integer> generatedSet;
    
    public RandomIDGenerator()
    {
        this.generatedSet = new HashSet<Integer>();
    }
    
    public int noDuplicateID(int min, int max)
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
            number = duplicateID(min, max);
            intObject = new Integer(number);
        }while(generatedSet.contains(intObject));
        
        generatedSet.add(intObject);
        return number;
    }
    
    public int duplicateID(int min, int max)
    {
        return (int)Math.round((max - min) * Math.random() + min);
    }
    
    public int getSize()
    {
        return generatedSet.size();
    }

    public void reset()
    {
        generatedSet.clear();
    }
}
