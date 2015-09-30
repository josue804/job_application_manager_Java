/*Josue Valverde, CS202, Homework #5*/

package com.company;

/**
 * Created by Josue on 5/27/2015.
 */

//this class is the abstract base class of all the specific application classes
abstract class Application{

    //the following variables hold default some default qualifications that every application will have
    protected String name;
    protected String phone;
    protected String address;
    protected String email;
    protected Application next = null; //pointer to another application used by the job list

    //default constructor assigns default qualifications
    Application(){
        this.name = "Name: ";
        this.phone = "Phone: ";
        this.address = "Address: ";
        this.email = "Email: ";
    }

    //used to initialize the default qualifications to whatever is fed in
    //hopefully real information
    public void assignDefaults(String name, String phone, String address, String email){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    //functions to be used for dynamic binding
    public abstract boolean readQualification(); //adds a qualification
    public abstract boolean removeQualification(); //removes a qualification
    public abstract String getTitle(); //gets the name of the type of application
    public abstract void displayForm(); //displays the application and its forms

    public abstract int getApplicationType(); //retrives an int that specifies the type of application
    public abstract int getTotalPriority(); //returns an int that specifies how well the qualifications were met
    public abstract boolean newQualification(String qualification, int importance); //adds a new qualification (supervisor)
    public abstract boolean createQualifications(); //adds my pre-made qualifications, for ease of grading
    public abstract boolean saveApplication(); //writes the applications to text files
}
