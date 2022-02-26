import java.time.LocalDateTime;

public interface ManagerInterface {

    public boolean insertSMS();
    public String retrieveSMSByDate(LocalDateTime dateTime);
    public String retrieveSMSByPromoCode();
    public String retrieveSMSByMsisdn(String msisdn);
    public String retrieveSMSByMsisdn(String[] msisdn);
    public String retrieveSMSBySent();
    public String retrieveSMSByReceive();


}
