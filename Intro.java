/**
Intro.java
@author Ethan Avera
@since 11/24/23
This class controls the directions of the game.
*/

public class Intro {
    /*
    This method prints out the instructions/directions for the program
    It does not have any parameters, and has no return value
    */
    public void directions() {
        System.out.println("There are a few command words necessary to use this program:");
        System.out.println(" - To search for a guest, type \"s\" or \"search\"");
        System.out.println(" - To add a guest, type \"a\" or \"add\"");
        System.out.println(" - To remove a user, type \"r\" or \"remove\"");
        System.out.println(" - To view the roster of a specific table, type \"tr\" or \"view table roster\"");
        System.out.println(" - To view the roster of all tables, type \"atr\" or \"view all table rosters\"");
        System.out.println(" - To view the roster of a company, type \"cr\" or \"view company roster\"");
        System.out.println(" - To view the roster of all companies, type \"acr\" or \"view all company rosters\"");
        System.out.println(" - To exit the program, type \"q\" or \"quit\"\n");
    }    
}
