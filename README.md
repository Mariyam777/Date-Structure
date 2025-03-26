# Date Structure Program
A Java program that implements a date management system with various date operations including validation, day of week calculation, date difference calculation and sorting.

# Features
 - Date validation (handles leap years and month lengths correctly)
 - Day of week calculation 
 - Date difference calculation in days
 - Date sorting functionality
 - Interactive console menu system

#Program Options
When running the program, you'll see a menu with these options:

Add new date - Enter a day, month, and year to create and store a new date
Get day of the week - View what day of the week a stored date falls on
Calculate difference - Find the number of days between two stored dates
Print all dates - Display all stored dates in sorted order
Exit - Quit the program

#Implementation Details
Key Components:

-Date Class: Handles all date-related operations including:
  *Validation (isValidDate())
  *Day of week calculation (getDayOfWeek())
  *Date difference calculation (calculateDifference())
  *Natural ordering (Comparable interface implementation)

-Main Class: Provides the user interface and manages the collection of dates
