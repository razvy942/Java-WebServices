package drrs.client;

public class Helpers {
    static boolean validateUserName(String userName) {
        if (!userName.startsWith("DVL")
                && !userName.startsWith("KKL")
                && !userName.startsWith("WST")
        )
            return false;
        return true;
    }

    static String getCampusName(String userID) {
        if (userID.startsWith("DVL")) {
            return "DVL";
        } else if (userID.startsWith("KKL")){
            return "KKL";
        } else if (userID.startsWith("WST")) {
            return "WST";
        }
        return "";
    }
}
