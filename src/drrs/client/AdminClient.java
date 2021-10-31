package drrs.client;

import drrs.utils.TextLogger;
import drrs.utils.TimeUtils;
import drrs.client.net.java.dev.jaxb.array.StringArray;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdminClient {
    static ICampus adminImpl;
    static CampusServerImplService service;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userName;
        service = new CampusServerImplService();
        while (true) {
            System.out.println("Welcome admin, enter your username. e.g(DVLA1111 or KKLA1111)");
            userName = input.nextLine().toUpperCase(Locale.ROOT);
            if (!Helpers.validateUserName(userName)) {
                System.out.println("Invalid username!");
                continue;
            }
            if (userName.contains("A")) {
                TextLogger logger = new TextLogger(userName + ".txt");
                String name = Helpers.getCampusName(userName);

                switch (name) {
                    case "DVL":
                        adminImpl = service.getCampusServerDVLImplPort();
                        break;
                    case "KKL":
                        adminImpl = service.getCampusServerKKLImplPort();
                        break;
                    case "WST":
                        adminImpl = service.getCampusServerImplPort();
                        break;
                    default:
                        System.out.println("Error connecting to server...");
                        return;
                }

                handleAdminInput(input, adminImpl, userName, logger);
                return;
            }
        }
    }

    private static void handleAdminInput(Scanner input, ICampus adminClient, String userName, TextLogger logger) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date currentDate = null;
        String userInput;

        while(true) {
            System.out.println("1: Create room\n2: Delete room\n3: Exit");
            userInput = input.nextLine();
            if (userInput.equals("3")) return;

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
            System.out.println("Enter timeslots separated by commas e.g 10:15-11:45,12:00-14:30");
            String adminTimeSlots = input.nextLine();

            ArrayList<String> adminSlots = new ArrayList<String>(Arrays.asList(adminTimeSlots.split(",")));
            ArrayList<String> validTimeSlots = new ArrayList<>();
            for (String slot : adminSlots) {
                try {
                    if (!TimeUtils.validateEndTime(slot)) {
                        System.out.println("End time can not be before start time: " + slot);
                    } else {
                        validTimeSlots.add(slot);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            String res = null;
//            String[] validTimeSlotsArr = new String[validTimeSlots.size()];
//            validTimeSlotsArr = validTimeSlots.toArray(validTimeSlotsArr);
            StringArray validTimeSlotsArr = new StringArray();
            validTimeSlotsArr.getItem().addAll(validTimeSlots);

            if (userInput.equalsIgnoreCase("1")) {
                res = adminClient.createRoom(roomNumber, date, validTimeSlotsArr);
                logger.log("record created for " + date + " in room " + roomNumber);
            } else if (userInput.equalsIgnoreCase("2")) {
                res = adminClient.deleteRoom(roomNumber, date, validTimeSlotsArr);
                logger.log("record deleted for " + date + " in room " + roomNumber);
            }

            System.out.println(res);
        }
    }
}
