import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sample {

    private static Connection con;
    final private static Logger logger = Logger.getLogger(Sample.class.getName());

    public static void main(String[] args) {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean valid = false;

        con = DatabaseLab.connect(con);

        try {
            if(!con.isClosed()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO promo_transaction (sms_id, promo_id, status, first_name, last_name) " +
                                "VALUES (?,?,?,?,?)");

                ps.setString(1, "456");
                ps.setString(2, "234");
                ps.setString(3, "approved");
                ps.setString(4, "CJ");
                ps.setString(5, "Crown");

                int row = ps.executeUpdate();
                if (row > 0) {
                    logger.log(Level.INFO, "Promo Accepted");
                    valid = true;
                } else {
                    logger.log(Level.WARNING, "Promo Failed");
                }
            } else {
                logger.log(Level.SEVERE, "FAILED to connect to Database");
            }

            DatabaseLab.disconnect(con);
            if(con.isClosed()) {
                logger.log(Level.INFO, "Disconnected from the Database");
            } else {
                logger.log(Level.WARNING, "You are Still Connected to Database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "Connected to Database", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
