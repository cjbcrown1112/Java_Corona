import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Promo {

    private static Connection con;


    private String promocode;
    private String details;
    private String shortcode;
    private LocalDateTime start;
    private LocalDateTime end;

    public Promo(String promocode, String details,
                 String shortcode, LocalDateTime start, LocalDateTime end) {
        this.promocode = promocode;
        this.details = details;
        this.shortcode = shortcode;
        this.start = start;
        this.end = end;
    }


    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getFormattedStart(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(this.start);
    }
    public String getFormattedEnd(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(this.end);
    }

    @Override
    public String toString() {
        return "Promo Code : " + promocode + "\nDetails : "
                + details + "Short Code : " +shortcode +
                "\nStart Date : " + start + "\nEnd Date : "
                + end;
    }
}
