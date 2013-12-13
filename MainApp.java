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
public class MainApp 
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
    
//        this.mainMenu();
//        this.employeeMenu();
//        TaskDate d1 = new TaskDate(6,1,2014);
//        System.out.print(d1.toString());
        PersonStore personStore = new PersonStore();
        int choice;
        do
        {
            this.mainMenu();
            System.out.print("Option: ");
            choice = Utility.inputRange(0, 2);
            if (choice == 1)
            {
                int empChoice;
                do
                {
                    this.employeeMenu();
                    System.out.print("Option: ");
                    empChoice = Utility.inputRange(0, 4);
                
                    if (empChoice == 1)
                    {
                        System.out.print("Enter employee name: ");
                        String name = kb.nextLine();
                        System.out.print("Enter employee email: ");
                        String email = kb.nextLine();
                        System.out.print("Enter employee telephone: ");
                        String tel = kb.nextLine();
                        Person person = new Person(name, email, tel);
                        personStore.add(person);
                        System.out.println("\n" + person);
                    }
                    else if (empChoice == 2)
                    {
                        System.out.print("Enter Employee ID: ");
                        String id = kb.nextLine();
                        if(personStore.search(id) != -1)
                        {
                            this.empEditMenu();
                            System.out.print("Option: ");
                            int editChoice = Utility.inputRange(0, 3);
                            switch(editChoice)
                            {
                                case 1:
                                    System.out.print("Enter new Employee Name: ");
                                    String name = kb.nextLine();
                                    personStore.editByName(id, name);
                                    break;
                                case 2:
                                    System.out.print("Enter new Employee Email: ");
                                    String email = kb.nextLine();
                                    personStore.editByEmail(id, email);
                                    break;
                                case 3:
                                    System.out.print("Enter new Employee Tel: ");
                                    String tel = kb.nextLine();
                                    personStore.editByName(id, tel);
                                    break;
                                default:
                                    break;
                            }  
                        }
                        else
                        {
                            System.out.println("Invalid Employee ID!");
                        }
                    }
                    else if (empChoice == 3)
                    {
                        this.empDelMenu();
                        System.out.print("Option: ");
                        int delChoice = Utility.inputRange(0, 2);
                        if (personStore.getSize() == 0 && delChoice != 0)
                        {
                           System.out.println("Do not have any employee data!"); 
                        }
                        else
                        {
                            switch(delChoice)
                            {
                                case 1:
                                    System.out.print("Delete Employee ID: ");
                                    String id = kb.nextLine();
                                    if(personStore.search(id) != -1)
                                    {
                                        personStore.delete(id);
                                        System.out.println("Delete Completed (" + id + ")");
                                    }
                                    else
                                    {
                                        System.out.println("Invalid Employee ID!");
                                    }
                                    break;
                                case 2:
                                    System.out.print("Are you sure delete all Employees [Y/N]? ");
                                    String answer = Utility.inputChar("y", "n");
                                    if(answer.equalsIgnoreCase("y"))
                                    {
                                        personStore.deleteAll();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    else if (empChoice == 4)
                    {
                        if(personStore.getSize() != 0)
                        {
                            this.empPrintMenu();
                            System.out.print("Option: ");
                            int printChoice = Utility.inputRange(0, 4);
                            switch(printChoice)
                            {
                                case 1:
                                    System.out.print("[Print Name]Enter Employee Name: ");
                                    String name = kb.nextLine();
                                    personStore.printByName(name);
                                    break;
                                case 2:
                                    String email = kb.nextLine();
                                    personStore.printByEmail(email);
                                    break;
                                case 3:
                                    String tel = kb.nextLine();
                                    personStore.printByTel(tel);
                                    break;
                                case 4:   
                                    personStore.print();
                                    break;
                                default:
                                    break;
                            }
                        }
                        else
                        {
                            System.out.println("Do not have any employee data!"); 
                        }
                    }
                    else
                    {
                        System.out.println("Back To Main Menu");
                    }
                }while(empChoice != 0);
            }
            else if (choice == 2)
            {
                int taskChoice;
                do
                {
                    this.taskMenu();
                    TaskStore taskStore = new TaskStore();
                    System.out.print("Option: ");
                    taskChoice = Utility.inputRange(0, 4);
                    if (taskChoice == 1)
                    {
                        System.out.print("Enter Task Name: ");
                        String name = kb.nextLine();
                        this.teamLeaderMenu();
                        for(int i = 0; i < personStore.getSize(); i++)
                        {
                            System.out.println("\t[" + (i + 1) + "] " + personStore.printName(i));
                        }
                        System.out.println("======================================");
                        System.out.print("Option: ");
                        int taskLeaderIndex = Utility.inputRange(1, personStore.getSize());
                        this.teamMemberMenu();
                        
                        for(int i = 0; i < taskStore.getSize(); i++)
                        {
                            
                        }
                        
                        
                        
                        String id;
                        do
                        {
                            System.out.print("Enter Task Team ID (press \"q\" to quit): ");
                            id = kb.nextLine(); //validation
                        }while(id.equalsIgnoreCase("q"));
                        System.out.print("Enter Task Added Date: ");
                        System.out.print("\nDate: ");
                        int date = kb.nextInt();
                        System.out.print("\nMonth: ");
                        int month = kb.nextInt();
                        System.out.print("\nYear: ");
                        int year = kb.nextInt();
                        String email = kb.nextLine();
                       // Person person = new Person(name, email, tel);
                       //personStore.add(person);
                        //System.out.println("\n" + person);
                    }
                    else if (taskChoice == 2)
                    {
                        this.taskEditMenu();
                    }
                    else if (taskChoice == 3)
                    {
                        this.taskDelMenu();
                    }
                    else if (taskChoice == 4)
                    {
                        this.taskPrintMenu();
                    }
                    else
                    {
                        System.out.println("Back To Main Menu");
                    }
                }while(taskChoice != 0);
            }
            else
            {
                System.out.println("Quit...");
            }
        }while(choice != 0);
       
        //System.out.print(l);
    }
    
    public void mainMenu()
    {
        System.out.println("======================================");
        System.out.println("\tApple Co. Tasks System");
        System.out.println("======================================");
        System.out.println("\t[1] Employee");
        System.out.println("\t[2] Task");
        System.out.println("\t[0] Quit");
        System.out.println("======================================");
        
    }
    
    public void employeeMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Employee Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Add");
        System.out.println("\t[2] Edit");
        System.out.println("\t[3] Delete");
        System.out.println("\t[4] Print");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void empDelMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Employee Delete Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Delete By Id");
        System.out.println("\t[2] Delete All");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void empPrintMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Employee Print Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Print By Name");
        System.out.println("\t[2] Print By Email");
        System.out.println("\t[3] Print By Tel");
        System.out.println("\t[4] Print All");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void empEditMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Employee Edit Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Edit By Name");
        System.out.println("\t[2] Edit By Email");
        System.out.println("\t[3] Edit By Tel");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void taskMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Task Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Add");
        System.out.println("\t[2] Edit");
        System.out.println("\t[3] Delete");
        System.out.println("\t[4] Print");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void taskPrintMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Task Print Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Print By Leader Id");
        System.out.println("\t[2] Print By Task Status");
        System.out.println("\t[3] Print By Before Due Within A Date Range");
        System.out.println("\t[4] Print All Task");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
     
    public void taskEditMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Task Edit Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Edit By");
        System.out.println("\t[2] Edit");
        System.out.println("\t[3] Delete");
        System.out.println("\t[4] Print");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void taskDelMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Task Delete Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Edit By");
        System.out.println("\t[2] Edit");
        System.out.println("\t[3] Delete");
        System.out.println("\t[4] Delete All");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void taskAddMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Task Add Menu");
        System.out.println("======================================");
        System.out.println("\t[1] Add Task Name");
        System.out.println("\t[2] Add Task Leader");
        System.out.println("\t[3] Add Task Team Member");
        System.out.println("\t[4] Added On");
        System.out.println("\t[5] Due On");
        System.out.println("\t[6] Completed On");
        System.out.println("\t[7] Add Status");
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void statusMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Status Menu");
        System.out.println("======================================");
        System.out.println("\t[1] " + Task.Status.CANCELLED.toString());
        System.out.println("\t[2] " + Task.Status.COMPLETED.toString());
        System.out.println("\t[3] " + Task.Status.ONGOING.toString());
        System.out.println("\t[4] " + Task.Status.PAUSED.toString());
        System.out.println("\t[0] Back");
        System.out.println("======================================");
    }
    
    public void teamMemberMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Team Member Menu");
        System.out.println("======================================");
    }
    
    public void teamLeaderMenu()
    {
        System.out.println("======================================");
        System.out.println("\t   Team Member Menu");
        System.out.println("======================================");
    }
}
