/*Josue Valverde, CS202, Homework #5*/
/*Purpose: This program allows an applicant to fill out a job application for one of three positions,
Web Developer, Software Engineer, and Network Administrator. Once the application is up to the user's standards,
the application can be inserted into the supervisor's.... BST, where the supervisor can view which applicants have
best met the qualifications
 */

package com.company;

//Main simply calls the runprogram function
public class Main {

    public static void main(String[] args) {
        ProgramInterface program = new ProgramInterface();
        program.runProgram();
    }
}
