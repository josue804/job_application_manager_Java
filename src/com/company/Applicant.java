//Josue Valverde, Homework #5, CS202

package com.company;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Josue on 6/9/2015.
 */

//this class everything that an applicant can do, which is:
//1.create a new application
//2.edit a pre-existing applications (even if the program was terminated, they are saved as txt files)
//3.turn in their application in which case it goes to the supervisor's bst
public class Applicant {

    //creates a new application for the current user
    //their name and password are attached to the application so only they can edit them
    //type specifies what application they want to fill out
    public boolean createApplication(String name, String password, int type){

        //variables to hold user choices
        Scanner input = new Scanner(System.in);
        File newApplication = new File(name + ".txt");
        String source = null;
        String line = null;
        String userAnswer = null;
        String applicationType = null;

        //check to see if there is already a file under their name, if so, return
        //only allowed to have 1 application
        if(newApplication.exists())
            return true;

        //the following statements check for which type of application the user wants to fill out
        if(type == 1){
            source = "WebDeveloperTemplate.txt";
            applicationType = "Web Developer";
        }

        else if (type == 2){
            source = "SoftwareEngineerTemplate.txt";
            applicationType = "Software Engineer";
        }

        else {
            source = "NetworkAdministratorTemplate.txt";
            applicationType = "Network Administrator";
        }


        //directions
        System.out.println("-->PLEASE answer all QUESTIONS with a YES or NO<---");

        //write into a file named after their name
        //file also contains password
        String fileName = name + ".txt";
        try{
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(source));

            bufferedWriter.write(password);
            bufferedWriter.newLine();
            bufferedWriter.write(applicationType);

            while((line = bufferedReader.readLine()) != null){
                bufferedWriter.newLine();
                bufferedWriter.write(line);
                System.out.print(line + " ");
                userAnswer = input.nextLine();
                bufferedWriter.write(" " + userAnswer);
                bufferedWriter.newLine();
                if((line = bufferedReader.readLine()) != null) bufferedWriter.write(line);
            }

            bufferedWriter.close();
        }

        catch(IOException ex){
            System.out.println("Could not write to file'" + fileName + "'");
        }

        return false;
    }

    //allows the user to edit their applications, provided their name and password
    //are the same as the file they are looking for
    public boolean editApplication(String name, String password){

        Scanner input = new Scanner(System.in);
        File editApplication = new File(name + ".txt");
        File oldFile = new File("old.txt");
        String line = null;
        String edited = null;
        int pos = 1;
        int editLine = 0;

        if(!editApplication.exists()){
            return false;
        }

        String fileName = name + ".txt";

        //finds the line they want to edit, marks it, and rewrites the corrected version onto another text file
        //which is also under their name
        //old file is deleted
        try{
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            if(!password.equalsIgnoreCase(bufferedReader.readLine())) return false;

            System.out.println("--->Here is your current " + bufferedReader.readLine() + " Application---");
            while((line = bufferedReader.readLine()) != null){
                System.out.println(pos + "." + line);
                bufferedReader.readLine();
                ++pos;
            }

            System.out.print("--->Which line would you like to edit? ");
            editLine = input.nextInt();
            editLine = (2 * editLine) + 1;

            bufferedWriter.close();
            bufferedReader.close();

            editApplication.renameTo(oldFile); //renames old file to "oldfile.txt"

            BufferedWriter newWriter = new BufferedWriter(new FileWriter(fileName, true)); //writer for new file
            BufferedReader reader = new BufferedReader(new FileReader(oldFile)); //reader for old file


            for(int i = 0; i < editLine - 1; ++i){
                line = reader.readLine();
                newWriter.write(line);
                newWriter.newLine();
            }

            //places a marker on the line they wish to edit
            //we come back to this once they fill out the new information
            reader.mark(0);
            line = reader.readLine();
            String [] toEdit = line.split(":");
            edited = toEdit[0] + ": ";
            System.out.print(edited);
            input.nextLine();
            line = input.nextLine();
            reader.reset();
            newWriter.write(edited + line);
            reader.readLine();
            newWriter.newLine();

            while((line = reader.readLine()) != null){
                newWriter.write(line);
                newWriter.newLine();
            }

            newWriter.close();
            reader.close();

            oldFile.delete(); //deletes the old file

        }

        catch(IOException ex){
            System.out.println("Failed to edit your application");
            return false;
        }

        return true;
    }

    //initializes the passed in bucket to the values in the text file specified
    //by the name and password
    public Application createApplicationObject(String name, String password, Application bucket){

        Scanner input = new Scanner(System.in);
        File editApplication = new File(name + ".txt");
        String line = null;
        String priority = null;
        int type = 3;
        int pos = 1;
        int choice = 1;

        //if no file is found under the specified user/pass, returns null bucket
        if(!editApplication.exists()){
            return bucket;
        }

        String fileName = name + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            if (!password.equalsIgnoreCase(bufferedReader.readLine())) return bucket;

            System.out.println("--->Here is your current " + bufferedReader.readLine() + " Application---");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(pos + "." + line);
                bufferedReader.readLine();
                ++pos;
            }

            System.out.println("---Submit?---");
            System.out.println("|1.Yes      |");
            System.out.println("|2.No       |");
            System.out.println("-------------");
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();

            if(choice == 2) return bucket;

            //if user confirms that they want to submit
            //initializes the application data structure to the values in the uesr's text file
            else{
                BufferedReader dataReader = new BufferedReader(new FileReader(fileName));

                dataReader.readLine();
                line = dataReader.readLine();

                if(line.equalsIgnoreCase("Web Developer")){
                    bucket = new WebDeveloper();

                    String newName = dataReader.readLine();
                    dataReader.readLine();
                    String newPhone = dataReader.readLine();
                    dataReader.readLine();
                    String newAddress = dataReader.readLine();
                    dataReader.readLine();
                    String newEmail = dataReader.readLine();
                    bucket.assignDefaults(newName, newPhone, newAddress, newEmail);

                    while((priority = dataReader.readLine()) != null && priority.length() > 0){
                        line = dataReader.readLine();
                        int newPriority = Integer.parseInt(priority);
                        bucket.newQualification(line, newPriority);
                    }

                    return bucket;
                }
            }
        }

        catch(IOException ex){
            System.out.println("No application under that Name/Password");
            return bucket;
        }



        return bucket; //returns a bucket that is referencing a "specified application" data structure
    }
}
