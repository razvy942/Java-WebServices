package drrs.client;

import drrs.utils.TextLogger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class StudentClient {
    static ICampus studentImpl;
    static CampusServerImplService service;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userName;
        service = new CampusServerImplService();

        while (true) {
            System.out.println("Welcome student, enter your username. e.g(DVLS1111 or KKLS1111)");
            userName = input.nextLine().toUpperCase(Locale.ROOT);
            if (!Helpers.validateUserName(userName)) {
                System.out.println("Invalid username!");
                continue;
            }
            if (userName.contains("S")) {
                TextLogger logger = new TextLogger(userName + ".txt");
                String name = Helpers.getCampusName(userName);

                switch (name) {
                    case "DVL":
                        studentImpl = service.getCampusServerDVLImplPort();
                        break;
                    case "KKL":
                        studentImpl = service.getCampusServerKKLImplPort();
                        break;
                    case "WST":
                        studentImpl = service.getCampusServerImplPort();
                        break;
                    default:
                        System.out.println("Error connecting to server...");
                        return;
                }

                handleStudentInput(input, studentImpl, userName, logger);
                return;
            }
        }
    }

    private static void handleStudentInput(Scanner input, ICampus studentClient, String userID, TextLogger logger) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date currentDate = null;
        String userInput;

        while (true) {
            StringBuilder logText = new StringBuilder();
            System.out.println("1: Book room\n2: Get available timeslots\n3: Cancel booking\n4: Change booking\n5: Exit");
            userInput = input.nextLine();
            if (userInput.equals("5")) return;

            if (userInput.equals("1")) {
                System.out.println("Choose your campus: " +
                        "DVL" + ", " + "WST" + ", " + "KKL");
                String campusName = input.nextLine().toUpperCase(Locale.ROOT);
                if (!campusName.equals("DVL") && !campusName.equals("WST") && !campusName.equals("KKL")) {
                    System.out.println("Invalid campus name!");
                    continue;
                }

                System.out.println("Enter date in following format dd-mm-yyyy...");
                String date = input.nextLine();

                try {
                    currentDate = df.parse(date);
                    Date todayDate = new Date();
                    while (!df.format(currentDate).equals(df.format(todayDate)) && currentDate.before(todayDate)) {
                        System.out.println("Date can not be before today...");
                        System.out.println("Enter date in following format dd-mm-yyyy...");
                        date = input.nextLine();
                        currentDate = df.parse(date);
                    }
                } catch (ParseException e) {
                    System.out.println("Wrong date format: " + e.getMessage());
                    continue;
                }

                System.out.println("Enter room number...");
                int roomNumber;
                try {
                    roomNumber = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("PLease enter a number for room: " + e.getMessage());
                    continue;
                }

                System.out.println("Choose timeslot e.g 10:15-11:45");
                String timeSlot = input.nextLine();

                String res = null;
                res = studentClient.bookRoom(userID, campusName, roomNumber, date, timeSlot);

                logText.append("Student booked room: " + roomNumber + " at campus " +
                        campusName + " for date " + date + " for time " + timeSlot + "\n");
                logText.append(res != null ? "Server success: " + res : "Server failed");

                logger.log(logText.toString());
                System.out.println(res);
            } else if (userInput.equals("2")) {
                System.out.println("Enter date in following format dd-mm-yyyy...");
                String date = input.nextLine();

                try {
                    currentDate = df.parse(date);
                    Date todayDate = new Date();
                    while (!df.format(currentDate).equals(df.format(todayDate)) && currentDate.before(todayDate)) {
                        System.out.println("Date can not be before today...");
                        System.out.println("Enter date in following format dd-mm-yyyy...");
                        date = input.nextLine();
                        currentDate = df.parse(date);
                    }
                } catch (ParseException e) {
                    System.out.println("Wrong date format: " + e.getMessage());
                    continue;
                }
                String res = studentClient.getAvailableTimeSlot(date).trim();
                logger.log("Student sent getAvailableTimeSlot request. Server response: " + res);
                System.out.println(res);
            } else if (userInput.equals("3")) {
                System.out.println("Enter booking ID to cancel");
                String bookingID = input.nextLine();
                String res = studentClient.cancelBooking(userID + "-" + bookingID);

                logText.append("Student canceled booking for booking ID: " + bookingID + "\n");
                logText.append(res != null ? "Server success: " + res : "Server failed");
                logger.log(logText.toString());

                System.out.println(res);
            } else if (userInput.equals("4")) {
                System.out.println("Enter booking ID to modify");
                String bookingID = input.nextLine();
                System.out.println("Enter new campus name: " + "DVL" + ", " + "WST" + ", " + "KKL");
                String newCampusName = input.nextLine();
                if (!newCampusName.equals("DVL") && !newCampusName.equals("WST") && !newCampusName.equals("KKL")) {
                    System.out.println("Invalid campus name!");
                    continue;
                }
                System.out.println("Enter new room number");
                int newRoomNumber;
                try {
                    newRoomNumber = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("PLease enter a number for room: " + e.getMessage());
                    continue;
                }
                System.out.println("Enter new time slot e.g.10:15-11:45");
                String newTimeSlot = input.nextLine();

                String res = studentClient.changeReservation(userID + "-" + bookingID, newCampusName, newRoomNumber, newTimeSlot);
                logText.append("Student modified booking for booking ID: " + bookingID + "\n");
                logText.append(res != null ? "Server success: " + res : "Server failed");
                logger.log(logText.toString());

                System.out.println(res);
            }
        }
    }

}
