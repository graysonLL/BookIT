import Entities.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean cond = true;
        User user;
        Scanner input = new Scanner(System.in);
        BookingOperations bookingOperations;

        while(cond == true) {
            int choice;
            System.out.println("   MAIN MENU\n1. Login\n2. Register\n0. Exit");
            System.out.print("Selection from the Menu: ");
            choice = input.nextInt();

            switch (choice) {
                case 1 :
                    System.out.println("LOGIN\n");
                    Login loginForm = new Login();
                    user = loginForm.user;
                    if(user != null) {
                        System.out.println("Succcessful authentication of "+user.firstName+" "+user.lastName);
                        System.out.println("userId: "+user.userId);
                        boolean userCondition = true;
                        int userChoice;
                        while(userCondition == true) {
                            System.out.println("\n\nUSER MENU\n1. Create Booking\n2. View Bookings\n3. View Profile\n0. Logout");
                            userChoice = input.nextInt();
                            switch (userChoice) {
                                case 1 :
                                    bookingOperations = new BookingOperations(userChoice,user.userId);
                                    break;
                                case 2 :
                                    bookingOperations = new BookingOperations(userChoice,user.userId);
                                    break;
                                case 3 :
                                    System.out.println("Profile Section");
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
                        System.out.println("authentication cancelled");
                    }
                    break;
                case 2 :
                    System.out.println("REGISTRATION\n");
                    Register myForm = new Register();
                    User newUser = myForm.user;
                    if(newUser != null) {
                        System.out.println("Succcessful registration of "+newUser.firstName+" "+newUser.lastName);
                    } else {
                        System.out.println("Registration cancelled");
                    }
                    break;
                case 0 :
                    System.out.println("Exit");
                    cond = false;
                    break;
                default :
                    System.out.println("Please select valid number");
                    break;
            }

        }


    }
}