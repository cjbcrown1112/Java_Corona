import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMS {

    private static Connection con;
    final private static Logger logger = Logger.getLogger(SMS.class.getName());

    protected String msisdn;
    protected String recipients;
    protected String sender;
    protected String shortcode;
    protected String transactionid;
    protected String txt;
    protected LocalDateTime timestamp;

    //Constructor for users
    public SMS() {

        this.msisdn = msisdn;
        this.sender = sender;
        this.recipients = recipients;
        this.transactionid = transactionid;
        this.shortcode = shortcode;
        this.txt = txt;
        this.timestamp = timestamp;


    }

    //Constructor for the System
    public SMS(String msisdn, String recipients,
               String sender, String shortcode,
               String transactionid,
               LocalDateTime timestamp) {

        this.msisdn = msisdn;
        this.sender = sender;
        this.recipients = recipients;
        this.transactionid = transactionid;
        this.shortcode = shortcode;
        this.timestamp = timestamp;

    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormattedTimestamp(){
        return this.timestamp.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public boolean sms_checker(HashMap<String, String> map1, HashMap<String, String> map2) {

        if (!map1.containsKey("Mobile number") || !map2.containsKey("Mobile number") ||
                !map1.containsKey("Message") || !map2.containsKey("Message") ||
                !map1.containsKey("Short Code") || !map2.containsKey("Short Code")) {
            return false;
        }

        return map1.equals(map2);
    }

    public static boolean sendSmS(String sender_msisdn, String receiver_msisdn, String text) {
        boolean sent = false;
        try {

            con = DatabaseLab.connect(con);

            if (!con.isClosed()) {
                logger.log(Level.INFO, "Connected to Database");

                //statement
                PreparedStatement sqlQuery = con.prepareStatement("INSERT INTO sms (sender_msisdn, receiver_msisdn, body) VALUES (?,?,?)");
                sqlQuery.setString(1, sender_msisdn);
                sqlQuery.setString(2, receiver_msisdn);
                sqlQuery.setString(3, text);


                //execute statement
                int row = sqlQuery.executeUpdate();
                if (row > 0) {
                    logger.log(Level.INFO, "SMS sent");
                    sent = true;
                } else {
                    logger.log(Level.WARNING, "SMS FAILED TO SEND");
                }

            } else {
                logger.log(Level.INFO, "Connection to the database FAILED");
            }

            //Disconnect from database
            DatabaseLab.disconnect(con);
            if (con.isClosed()) {
                logger.log(Level.INFO, "Disconnected from Database");
            } else {
                logger.log(Level.WARNING, "You are Still Connected to Database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "Connected to Database", e);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return sent;
        }
    }

}
