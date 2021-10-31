package drrs.server.campusservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ICampus {
    @WebMethod
    String createRoom(int roomNumber, String date, String[] timeSlots);
    @WebMethod
    String deleteRoom(int roomNumber, String date, String[] timeSlots);
    @WebMethod
    String bookRoom(String studentID, String campusName, int roomNumber, String date, String timeSlot);
    @WebMethod
    String cancelBooking(String bookingID);
    @WebMethod
    String getAvailableTimeSlot(String date);
    @WebMethod
    String changeReservation(String bookingID, String newCampusName, int newRoomNumber, String newTimeSlot);
}
