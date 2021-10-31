package drrs.server.campusservice;

import javax.xml.ws.Endpoint;

public class ServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8080/WST", new CampusServerImpl("WST"));
        System.out.println("WSTCampus Server ready and waiting...");
        Endpoint.publish("http://127.0.0.1:8080/DVL", new CampusServerImpl("DVL"));
        System.out.println("DVLCampus Server ready and waiting...");
        Endpoint.publish("http://127.0.0.1:8080/KKL", new CampusServerImpl("KKL"));
        System.out.println("KKLCampus Server ready and waiting...");
    }
}
