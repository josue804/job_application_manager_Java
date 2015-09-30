//Josue Valverde, Homework #5, CS202
package com.company;
import java.util.Scanner;

/**
 * Created by Josue on 6/7/2015.
 */
//this class manages the entire program
//and also most of the input/output
public class ProgramInterface {

    private JobList allJobs; //list of job applications with list of qualifications
    private ApplicationTree [] tree; //array of trees of applications
    private Applicant allApplicants; //object for the aplicant interface
    private Application applicantBucket; //bucket that carries applications to the supervisor's BST

    //constructor
    ProgramInterface(){
        allJobs = null;
        tree = new ApplicationTree[3];
        tree[0] = new ApplicationTree();
        tree[1] = new ApplicationTree();
        tree[2] = new ApplicationTree();
        applicantBucket = null;
        allApplicants = new Applicant();
    }

    //the function that contains the entirety of the program
    public boolean runProgram() {

        Scanner input = new Scanner(System.in);
        int supervisorChoice = 0; //keeps track of what the supervisor wants to do
        int applicantChoice = 0;
        int applicationStackChoice = 0;
        boolean finishedApplication = false;

        //initializes the job application list and also builds one for ease of grading
        if (allJobs == null) {
            allJobs = new JobList(); //Job list that manages the program
            allJobs.buildTemplates(); //Function that builds templates for the purpose of program testing
        }

        //main-menu loop
        while(applicantChoice != 3) {
            System.out.println("-----------MAIN MENU-----------");
            System.out.println("|1.I am the Supervisor        |");
            System.out.println("|2.I am an Applicant          |");
            System.out.println("|3.Quit Program               |");
            System.out.println("-------------------------------");
            System.out.print("Please enter your choice: ");
            applicantChoice = input.nextInt();

            if (applicantChoice == 1) {

                //supervisor-menu loop
                while (supervisorChoice != 3) {
                    System.out.println("---------------SUPERVISOR MENU----------------");
                    System.out.println("|1.Display Application Templates             |");
                    System.out.println("|2.Display All Applications                  |");
                    System.out.println("|3.Quit Supervisor Program                   |");
                    System.out.println("----------------------------------------------");
                    System.out.print("Please enter your choice: ");
                    supervisorChoice = input.nextInt();

                    //the following if statements make sure that what the user wants to do is executed
                    if (supervisorChoice == 1) {
                        allJobs.displayAll();
                    }

                    //displays the tree of applications that the supervisor wishes to view
                    //the Web Developer one can be loaded easily by the grader
                    //the others you can use the functions from program#4 to create applications for
                    //all trees work exactly the same
                    else if(supervisorChoice == 2){
                        System.out.println("-----------------APPLICATION STACK------------------");
                        System.out.println("|1.Display Web Developer Applications (pre-loaded) |");
                        System.out.println("|2.Display Software Engineer Applications          |");
                        System.out.println("|3.Display Network Administrator Applications      |");
                        System.out.println("|4.Back to Supervisor Menu                         |");
                        System.out.println("----------------------------------------------------");
                        System.out.print("Please enter your choice: ");
                        applicationStackChoice = input.nextInt();

                        if(applicationStackChoice < 4) tree[applicationStackChoice - 1].displayHighToLow();

                    }
                }
            }

            //if it's an applicant using the program...
            else if(applicantChoice == 2){
                finishedApplication = runApplicant();

                //if the applicant finished their application
                //it is in the bucket and can be inserted into the appropriate tree
                if(finishedApplication){

                    if(applicantBucket.getApplicationType() == 1){
                        tree[0] = tree[0].addApplication(applicantBucket);
                        applicantBucket = null;
                    }

                    else if(applicantBucket.getApplicationType() == 2){
                        tree[1] = tree[1].addApplication(applicantBucket);
                        applicantBucket = null;
                    }

                    else if(applicantBucket.getApplicationType() == 3){
                        tree[2] = tree[2].addApplication(applicantBucket);
                        applicantBucket = null;
                    }

                }
            }

            supervisorChoice = 0;
        }

        return true;
    }

    //runs the applicant interface
    //returns true if the user is ready to submit their application
    //false otherwise
    public boolean runApplicant(){

        Scanner input = new Scanner(System.in);
        int applicantChoice = 0;
        int newApplication = 0;
        boolean existingApplication = false;
        String name;
        String password;

        //applicant menu
        while(applicantChoice != 4) {
            System.out.println("----------APPLICANT MENU---------");
            System.out.println("|1.Create a new application     |");
            System.out.println("|2.Edit my application          |");
            System.out.println("|3.Turn in my application       |");
            System.out.println("|4.Quit Applicant Program       |");
            System.out.println("---------------------------------");
            System.out.print("Please enter your choice: ");
            applicantChoice = input.nextInt();

            //if block to make a new application
            if(applicantChoice != 4) {
                input.nextLine();

                //user creates a name/password or inputs to access their files
                System.out.print("Please enter your name: ");
                name = input.nextLine();

                System.out.print("Please enter a password: ");
                password = input.nextLine();

                //which application they wish to create
                if (applicantChoice == 1) {

                    System.out.println("------------NEW APPLICATION------------");
                    System.out.println("|Fill out which application?          |");
                    System.out.println("|1.Web Developer                      |");
                    System.out.println("|2.Software Engineer                  |");
                    System.out.println("|3.Network Administrator              |");
                    System.out.println("|4.Back to Applicant Menu             |");
                    System.out.println("---------------------------------------");
                    System.out.print("Please enter your choice: ");
                    newApplication = input.nextInt();

                    if (newApplication != 4) {
                        existingApplication = allApplicants.createApplication(name, password, newApplication);

                        //if they already have an application, return
                        if (existingApplication) {
                            System.out.println("-->You already have an application");
                            System.out.println("-->Returning to Menu");
                        }

                        else System.out.println("-->Your application has been created");
                    }

                    else System.out.println("-->Going back to Applicant Menu");
                }

                //if the user wants to edit a preexisting application, the correct function is called with their
                //pass and username
                else if (applicantChoice == 2){
                    existingApplication = allApplicants.editApplication(name, password);

                    //if no application is found, notify the user
                    if(!existingApplication){
                        System.out.println("-->No application under that Name/Password");
                    }
                }

                //if the user is ready to submit, calls the appropriate function
                else if (applicantChoice == 3) {
                    if (applicantBucket != null) {
                        applicantBucket = null;
                    }

                    applicantBucket = allApplicants.createApplicationObject(name, password, applicantBucket);

                    if(applicantBucket == null) {
                        System.out.println("-->Incorrect User/Pass");
                        return false;
                    }

                    System.out.println("--->APPLICATION SUBMITTED<---");
                    return true;
                }
            }
        }
        return false;
    }


}
