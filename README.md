# _Job Application Manager/ Job Application Submitter_

##### _This program allows a hiring manager to create job applications. An applicant can then complete and submit a job application to a specified position. Finally, the hiring manager can see all submitted applications in descending order by how suitable the candidate is according to the manager's specifications., June 20, 2015_

#### By _**Josue Valverde**_

## Description

_I created two data structures to manage this program's data: _
  _1. A binary search tree where each node is an application and its value is determined by how well it scored based on the manager's specifications._
  _2. A linear linked list of linear linked lists where each node of the pimary LLL is a type of job application (e.g. Software Engineer application). Each one of these nodes then points to the questions that the manager adds to the applicaion--each one of these questions also has a priority, which helps in ranking applicants._

## Setup

* `$ git clone` this repository
* `$ cd job_application_manager_Java/src/com/company/`
* `$ javac Main.java` to compile program
* `$ java Main` to run program

## Technologies Used

*_IntelliJ IDEA 14.1.3_
*_Written in Java_

### Legal

Copyright (c) 2015 **_Josue Valverde_**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
