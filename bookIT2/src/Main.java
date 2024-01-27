import Entities.User;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean cond = true;
        User user;
        Scanner input = new Scanner(System.in);
        BookingOperations bookingOperations;
        UserOperations userOperations;

        while(cond == true) {
            int choice;
            System.out.println("-------------------------------\n|          MAIN MENU          |\n|1. Login                     |\n|2. Register                  |\n|0. Exit                      |\n-------------------------------");
            System.out.print("Selection from Menu: ");
            choice = input.nextInt();

            switch (choice) {
                case 1 :
                    System.out.println("\n\n-------------------------------------\n|               LOGIN               |");
                    Login loginForm = new Login();
                    user = loginForm.user;
                    if(user != null) {
                        System.out.println("| Succcessfully authenticated user  |\n-------------------------------------");
                        boolean userCondition = true;
                        int userChoice;
                        while(userCondition == true) {
                            System.out.println("\n\n--------------------------------\n|          USER MENU           |\n|1. Create Booking             |\n|2. View Bookings              |\n|3. View Profile               |\n|0. Logout                     |\n--------------------------------");
                            System.out.print("Selection from User Menu: ");
                            userChoice = input.nextInt();
                            switch (userChoice) {
                                case 1 :
                                    bookingOperations = new BookingOperations(userChoice,user.userId);
                                    break;
                                case 2 :
                                    bookingOperations = new BookingOperations(userChoice,user.userId);
                                    break;
                                case 3 :
                                    boolean profileCond = true;
                                    while(profileCond == true) {
                                        System.out.println("\n\n----------------------------------\n|         PROFILE SECTION        |\n|1. View Profile Details         |\n|2. Update Email                 |\n|3. Update Password              |\n|4. Update First Name            |\n|5. Update Last Name             |\n|6. Update Phone Number          |\n|0. Exit                         |\n----------------------------------");
                                        System.out.print("Selection from Profile Section: ");

                                        int profileChoice = input.nextInt();
                                        userOperations = new UserOperations(profileChoice,user.userId);
                                        if(profileChoice == 0) {
                                            profileCond = false;
                                        }
                                    }
                                    break;
                                case 0 :
                                    System.out.println("Successfully logged out\n");
                                    userCondition = false;
                                    break;
                                default :
                                    System.out.println("please select a number from the menu");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("authentication failed\n\n");
                    }
                    break;
                case 2 :
                    System.out.println("--------------------------------\n|         REGISTRATION         |");
                    Register myForm = new Register();
                    User newUser = myForm.user;
                    if(newUser != null) {
                        System.out.println("| Succcessfully registered user |\n---------------------------------\n\n");
                    } else {
                        System.out.println("Registration cancelled\n--------------------------------\n\n");
                    }
                    break;
                case 0 :
                    System.out.println("\nEXITING");
                    System.out.println("Thank you for using BookIT");
                    cond = false;
                    break;
                default :
                    System.out.println("Please select valid number");
                    break;
            }

        }


    }
}