# Gumtree coding challenge

## The task

Your task is to develop a small java application. We need you to build your application in your own GitHub repository.  Please do not fork our repository to create your project.  Once you are done, send us a link to your repository.

Please allow yourself at least 1 hour of uninterrupted time for this task, but feel free to spend as much time on the task as you like and make the solution and the code as perfect as you like.

## The application

Your application needs to read the attached AddressBook file and answer the following questions:

1. How many males are in the address book?
2. Who is the oldest person in the address book?
3. How many days older is Bill than Paul?

Some insights into what we'll be looking for (and what we will not):

- Feel free to use any dependency management and build tools eg maven or gradle
- Please do not use database, we are more interested in your Java skills than your SQL skills
- Feel free to commit as often as you'd like. The more commits the better! Please commit at least once within the first hour
- It's better to complete 1 task than to start 3
- Feel free to use any java libraries you feel appropriate
- We will be looking at how you approach the task (e.g. how you break it into sub-tasks) and how you structure your code to answer the questions

Good Luck!


# My solution

## Assumptions (requirements that were not clear based on the specs)

1. People with same data should be considered as duplicates, so there
   was no need to store them twice.
2. When trying to know who is the oldest person, if address book is empty it will return NoSuchElementException.
3. When trying to get 'how many days older', if one of the given names does not match at least any of the existing
   first names (not including last name), a PersonNotFoundException will be raised.
4. If the second person is older than the first one, then a negative number will be returned.
5. All the fields are mandatory. App will ignore those elements whose name is empty and will not run if any of
   the rest of the elements are not valid.
6. Address book has to be immutable and records cannot be added or modified.