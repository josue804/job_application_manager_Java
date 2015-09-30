//Josue Valverde, CS202, Homework #5

package com.company;

/**
 * Created by Josue on 5/27/2015.
 */

//this class manages qualification nodes and their data
//should have used another class to manage the list, but ran out of time
public class QualificationNode{

    private QualificationNode next;
    private String qualification;
    private int priority = 0;

    //constructor, unused but necessary
    QualificationNode(){
        this.next = null;
    }

    //constructor with arguments, creates a new qualification node with a the specified qualification and priority
    QualificationNode(String qualification, int priority){
        this.qualification = qualification;
        this.priority = priority;
        this.next = null;
    }

    //adds a new qualification to the list of qualification nodes, recursively
    public QualificationNode addQualification(String qualification, int priority, QualificationNode head){

        //there there is no list, add there and return
        if(head == null){
            head = new QualificationNode(qualification,priority);
            return head;
        }

        //otherwise, if the current node has less priority than the one being added, add the new node there
        //and return the new list
        else if(priority > head.getPriority()){
            QualificationNode temp = head;
            head = new QualificationNode(qualification,priority);
            head.next = temp;
            return head;
        }

        //recursive call, with head catching the new list
        head.next = addQualification(qualification, priority, head.getNext());

        //returns the new list
        return head;
    }

    //removes a qualification specified by the user
    public QualificationNode removeQualification(String qualification, QualificationNode head){

        //if there is no list or the qualification is not found, return a null pointer
        if(head == null) return head;

        //if there is a match, let go of the node and reattach the list
        //return the new list
        else if(head.getQualification().equalsIgnoreCase(qualification)){
            System.out.println("-->Qualification found and removed");
            QualificationNode temp = head.next;
            head = temp;
            return head;
        }

        //traverse the list recursively
        head.next = removeQualification(qualification, head.next);

        //return a reference to the new list
        return head;
    }

    //returns a value that represents by how much this particular application
    //meets the qualifications
    //head is used to recurse through the list
    public int getTotalPriority(QualificationNode head){

        if(head == null) return 0;

        String meet = head.qualification;
        if(meet.contains(" YES")){
            return getTotalPriority(head.getNext()) + head.getPriority();
        }

        else
            return getTotalPriority(head.getNext());
    }

    //displays the qualification AND its priority. Didn't implement but thought it be something that could be
    //necessary in the future
    public void display(){
        System.out.println("Qualification: " + this.qualification + "|Priority: " + priority);
    }

    //displays this node's qualification
    public void displayForm(){
        System.out.println(this.qualification);
    }

    //returns the next pointer
    public QualificationNode getNext() {
        return next;
    }

    //returns the node's priority
    public int getPriority() {
        return priority;
    }

    //returns the node's qualification
    public String getQualification(){
        return qualification;
    }

}
