import com.mysql.cj.log.Log;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmSManager implements ManagerInterface {
    private SMS sms;
    private static Connection con;
    final private static Logger logger = Logger.getLogger(SmSManager.class.getName());

    @Override
    public boolean insertSMS() {
        return false;
    }

    public static boolean checkValidity(String sender_msisdn, String receiver_msisdn, String body) {

        Statement statement = null;
        ResultSet resultSet = null;
        boolean valid = false;

        con = DatabaseLab.connect(con);

        try {
            if (!con.isClosed()) {
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM sms " +
                                "WHERE " +
                                "sender_msisdn = ? AND " +
                                "receiver_msisdn = ? AND " +
                                "body = ? AND " +
                                "transaction_time between ? AND ? " +
                                "ORDER BY transaction_time DESC " +
                                "LIMIT 1 ");

                ps.setString(1, sender_msisdn);
                ps.setString(2, receiver_msisdn);
                ps.setString(3, body);


                //for the time
                LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
                LocalDateTime m15BeforeNow = LocalDateTime.now(ZoneOffset.UTC).minus(15, ChronoUnit.MINUTES);
                DateTimeFormatter formattedNow = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                DateTimeFormatter formattedHBN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                ps.setString(4, m15BeforeNow.format(formattedNow));
                ps.setString(5, now.format(formattedHBN));

                ResultSet rs = ps.executeQuery();
                System.out.println(ps.toString());

                if (rs.isBeforeFirst()) {
                    System.out.println(rs);
                    valid = true;
                }

            } else {
                logger.log(Level.SEVERE, "Connection to the Database FAILED");
            }

            //Disconnect
            DatabaseLab.disconnect(con);
            if (con.isClosed()) {
                logger.log(Level.INFO, "Disconnected from the database");
            } else {
                logger.log(Level.WARNING, "Connection to database might still be open");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "Connected to Database", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valid;
    }

    @Override
    public String retrieveSMSByDate(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public String retrieveSMSByPromoCode() {
        return null;
    }

    @Override
    public String retrieveSMSByMsisdn(String msisdn) {
        return null;
    }

    @Override
    public String retrieveSMSByMsisdn(String[] msisdn) {
        return null;
    }

    @Override
    public String retrieveSMSBySent() {
        return null;
    }

    @Override
    public String retrieveSMSByReceive() {
        return null;
    }


}
