//Josue Valverde, Homework #5, CS202
package com.company;

/**
 * Created by Josue on 6/9/2015.
 */
//manages the data for a tree node in the tree of applications
//mostly setters and getters
public class treeNode {

    protected treeNode left;
    protected treeNode right;
    protected Application applicationData; //its data is of data type application

    //constructor
    treeNode(){
        left = null;
        right = null;
        applicationData = null;
    }

    //sets the application data to reference the fed in application data
    public void setApplicationData(Application applicationData) {
        this.applicationData = applicationData;
    }

    //displays its application data
    public void display(){
        if(applicationData == null) return;

        applicationData.displayForm();
    }

    //returns the left reference
    public treeNode getLeft() {
        return left;
    }

    //returns the right reference
    public treeNode getRight() {
        return right;
    }

    //catches and returns the total priority (how well the application meets qualifications)
    //of the current application object
    public int getTotalPriority(){
        return applicationData.getTotalPriority();
    }
}
