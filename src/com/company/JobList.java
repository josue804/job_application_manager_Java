/*Josue Valverde, CS202, Homework #5*/

package com.company;
import java.util.Scanner;

/**
 * Created by Josue on 5/27/2015.
 */

//This class manages the list of 3 different job applications
public class JobList {

    private Application head; //grabs hold of the list
    private Scanner input = new Scanner(System.in);

    //default constructor
    JobList() {
        this.head = null;
    }

    //function that adds the appropriate job application to the list
    public int addJob(int choice) {

        int quit = 0; //keeps track of if the user wishes to add more qualifications or quit
        Application temp = head; //temporary pointer used for adding application nodes

        //adds a Web Developer to the job list
        if (choice == 1) {

            //if there is no list, add it to the head
            if (this.head == null) {
                head = new WebDeveloper();
                temp = head;
            }

            //otherwise see if the application exists
            //if so: add a new qualification to it
            //if not: create the new application and add a qualification to it
            else {
                temp = recursiveFind(temp, "Web Developer");

                if(temp == null){
                    temp = new WebDeveloper();
                    temp.next = head;
                    head = temp;
                }
            }
        }

        //adds a Software Engineer to the job list
        else if (choice == 2) {

            //otherwise, see if the application exists
            //if so: add a new qualification to it
            //if not: create the new application and add a qualification to it
            if (this.head == null) {
                head = new SoftwareEngineer();
                temp = head;
            }


            else {
                temp = recursiveFind(temp, "Software Engineer");

                if(temp == null){
                    temp = new SoftwareEngineer();
                    temp.next = head;
                    head = temp;
                }
            }
        }

        //same as the previous two "if" statements, except with a Network Administrator position
        else if (choice == 3) {
            if (this.head == null) {
                head = new NetworkAdministrator();
                temp = head;
            }

            else {
                temp = recursiveFind(temp, "Network Administrator");

                if(temp == null){
                    temp = new NetworkAdministrator();
                    temp.next = head;
                    head = temp;
                }
            }
        }

        //loop to continue adding qualifications to the current application
        while (quit != 2) {
            temp.readQualification();
            System.out.println("-----------------------------");
            System.out.println("|1.Add another qualification|");
            System.out.println("|2.Back to Main Menu        |");
            System.out.println("----------------------------|");
            System.out.print("Please enter your choice: ");
            quit = input.nextInt();
        }

        //displays the modified job application
        temp.displayForm();

        //since temp might have the new list, reset head to temp
        head = temp;

        return 0;
    }

    //removes a specified qualification from a specified job application
    public boolean removeQualification(int choice){

        //if there is no list, return false
        if(head == null) return false;

        Application temp = head; //assign temp to head so that head doesn't get mucked with

        //the following "if statements find if the desired application exists
        if(choice == 1){
            temp = recursiveFind(temp,"Web Developer");
        }

        else if(choice == 2){
            temp = recursiveFind(temp, "Software Engineer");
        }

        else if(choice == 3){
            temp = recursiveFind(temp, "Network Administrator");
        }


        //if it is not found, returns false
        if(temp == null) return false;

        //if it does exist, run the function that asks the user what qualificaiton they want to remove
        else{
            return temp.removeQualification();
        }
    }

    //finds the application specified by the user, recursively
    private Application recursiveFind(Application temp, String applicationType){

        //if not found, return a null pointer
        if(temp == null) return temp;

        //if found, return a pointer to the application
        if(temp.getTitle().equalsIgnoreCase(applicationType)){
            return temp;
        }

        //recursive call
        return recursiveFind(temp.next, applicationType);
    }

    //displays all applications and their contents
    public void displayAll(){

        Application temp = head;
        while(temp != null){
            System.out.println("");
            temp.displayForm();
            temp = temp.next;
        }
    }

    //builds a linear linked list of template with my pre-made qualifications, for ease of grading
    public boolean buildTemplates(){

        Application temp;

        temp = new WebDeveloper();
        temp.createQualifications();
        head = temp;

        temp = new SoftwareEngineer();
        temp.createQualifications();
        temp.next = head;
        head = temp;

        temp = new NetworkAdministrator();
        temp.createQualifications();
        temp.next = head;
        head = temp;

        return true;
    }
}