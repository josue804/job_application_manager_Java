/*Josue Valverde, CS202, Homework #5*/

package com.company;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Josue on 6/1/2015.
 */
//The following class is a child of the application base class and manages a Web Developer application
public class WebDeveloper extends Application{

    private String title = "Web Developer";
    private QualificationNode head = null;
    private int applicationType = 1;

    //constructor
    public WebDeveloper(){
        this.head = null;
    }


    //reads a qualification from the user and calls the function to insert it into a QualificationNode
    public boolean readQualification(){
        Scanner input = new Scanner(System.in);
        String qualification;
        int importance = 0;

        //reads in the user's qualification
        System.out.print("Please enter a new qualification: ");
        qualification = input.nextLine();
        System.out.print("On the scale of 1 - 10 (least to most), how important is this qualification? ");
        importance = input.nextInt();

        //calls the function to add a qualification
        return newQualification(qualification, importance);
    }


    //adds a new qualification to the list
    public boolean newQualification(String qualification, int importance){

        //if there is no list, add to the head
        if(head == null){
            head = new QualificationNode(qualification, importance);
            return true;
        }

        //otherwise, call the function to add a qualification to the application recursively
        //catch this new list with head
        head = head.addQualification(qualification,importance, head);

        return true;
    }


    //removes the qualification that the user specifies
    public boolean removeQualification() {

        if(head == null) return false;

        Scanner input = new Scanner(System.in);
        String choice;

        System.out.println("--------------------------------------------");
        System.out.println("|Here is " + title + " current application |");
        displayForm();

        System.out.println("-->Which qualification would you like to remove? ");
        choice = input.nextLine();

        //calls the function to remove a qualification recursively
        head = head.removeQualification(choice, head);

        return true;
    }


    //displays the current form's qualifications
    public void displayForm(){
        QualificationNode temp = head;

        System.out.println("-------------------------------------\\");
        System.out.println("|" + getTitle() +  " Application|");
        System.out.println("-------------------------------------/");
        System.out.println(name + "\n" + phone +"\n" + address + "\n" + email);

        while(temp != null){
            temp.displayForm();
            temp = temp.getNext();
        }
    }


    //returns the title of the current application
    public String getTitle() {
        return title;
    }

    //returns the application type
    //in this case, 1 means we are at a web developer object
    public int getApplicationType() {
        return applicationType;
    }

    //creates preloaded qualifications for ease of grading
    public boolean createQualifications(){

        newQualification("Are you familiar with Ruby: ", 8);
        newQualification("Are you familiar with Bootstrap: ", 10);
        newQualification("Do you have a Web Development Portfolio: ", 10);
        newQualification("Do you wear glasses: ", 2);
        newQualification("Are you a good team player: ", 5);

        return saveApplication();
    }


    //writes the applications to a text file which is used as the template
    //to all other qualifications
    public boolean saveApplication(){
        QualificationNode temp = head;
        String fileName = "WebDeveloperTemplate.txt";

        try{
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int lines = 0;

            while(reader.readLine() != null) ++lines;
            if(lines > 4)
                return false;

            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write(phone);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write(address);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write(email);

            while(temp != null) {
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(temp.getPriority()));
                bufferedWriter.newLine();
                bufferedWriter.write(temp.getQualification());
                temp = temp.getNext();
            }

            bufferedWriter.close();
        }

        catch(IOException ex){
            System.out.println("Could not write to file'" + fileName + "'");
            return false;
        }

        return true;
    }


    //catches the value that determines how closely this application matches the qualifications
    public int getTotalPriority() {
        return head.getTotalPriority(head);
    }
}
