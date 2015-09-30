//Josue Valverde, Homework #5, CS202
package com.company;

/**
 * Created by Josue on 6/9/2015.
 */
//class manages a tree of applications
//three of these trees exist, one for each type of application
public class ApplicationTree extends treeNode{

    private treeNode root;

    //constructor
    ApplicationTree(){
        root = null;
    }

    //adds the fed in application to the tree by calling a private recursive function
    public ApplicationTree addApplication(Application toAdd){
        root = addApplication(toAdd, root);
        return this;
    }

    //called by the function above
    private treeNode addApplication(Application toAdd, treeNode root){

        if(root == null){
            root = new treeNode();
            root.setApplicationData(toAdd);
            return root;
        }

        //total priority is used make the BST
        //so that it is easily displayed in terms of most to least qualified candidates
        if(toAdd.getTotalPriority() >= root.getTotalPriority()){
            root.left = addApplication(toAdd, root.getLeft());
        }

        else if(toAdd.getTotalPriority() < root.getTotalPriority()){
            root.right = addApplication(toAdd, root.getRight());
        }

        return root;
    }

    //displays candidates from highest qualifications to lowest qualifications met
    //through a recursive call
    public void displayHighToLow(){
        displayHighToLow(root);
    }

    //recursive call for function above
    private void displayHighToLow(treeNode root){

        if(root == null) return;

        displayHighToLow(root.getLeft());
        root.display();
        System.out.println("TOTAL PRIORITY: " + root.getTotalPriority()); //displays how well the applicant met qualifications
        displayHighToLow(root.getRight());

    }

}
