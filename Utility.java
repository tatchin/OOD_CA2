/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.util.Scanner;

/**
 *
 * @author toshiba
 */
public class Utility
{
    public static void noFoundMessage(boolean found)
    {
        if(!found)
        {
            System.out.println("No item found");
        }
    }
    
    public static void noFoundMessage(String item, boolean found)
    {
        if(!found)
        {
            System.out.println("'" + item + "' - No item found");
        }
    }
    
    public static void noFoundMessage(int found)
    {
        if(found == -1)
        {
            System.out.println("No item found");
        }
    }
    
    public static int inputRange(int min, int max)
    {
        Scanner kb = new Scanner(System.in);
        int value = -1;
        do
        {
            if (kb.hasNextInt())
            {
                value = kb.nextInt();
                if (value < min || value > max)
                {
                    System.out.println("Out of Range [" + min + " - " + max + "]");
                }
            }
            else
            {
                System.out.println("Numerical value only!");
                System.out.print("Option: ");
                String temp = kb.nextLine();
            }
        }while(value < min || value > max);
        return value;
    }
    
    public static String inputChar(String text1, String text2)
    {
        Scanner kb = new Scanner(System.in);
        String text = kb.next();
        boolean isChar;
        do
        {
            if(text.equalsIgnoreCase(text1) || text.equalsIgnoreCase(text2))
            {
                isChar = true;
            }
            else
            {
                isChar = false;
            }
        }while(!isChar);
        return text;
    }
    
    public static TaskDate inputDate(int date, int month, int year)
    {
        Scanner kb = new Scanner(System.in);
        if(kb.hasNextInt())
        {
            System.out.print("Enter date: ");
            date = kb.nextInt();
            System.out.print("Enter month: ");
            month = kb.nextInt();
            System.out.print("Enter year: ");
            year = kb.nextInt();
        }
        else
        {
            System.out.print("Invalid date");
        }
        return (new TaskDate(date, month, year));
    }
}
