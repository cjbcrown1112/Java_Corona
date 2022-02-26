import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    final private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        /*
        SMS sms = new SMS();
        sms.sendSmS("09776279410", "09216255060", "#1PIZZA");

        Promo.createPromo("#1PIZZA", "1234555",
                "PISO NA LANG ANG PIZZA!!!", "2022-02-01 10:00:00", "2022-06-30 23:59:00");

        boolean a = Promo.checkShortCode("1234555");
        System.out.println(a);

         */



        //User Input
        Scanner input = new Scanner(System.in);

        boolean programOn = true;
        String sender_msisdn = "";

        while (programOn) {
            logger.log(Level.INFO, "Promo Registration");
            System.out.println("Promo Registration\n");

            while (sender_msisdn.isEmpty())
            {
                System.out.println("Enter Phone Number : ");
                sender_msisdn = input.nextLine();
            }

            System.out.println("Enter Message to Recipient: ");
            String message = input.nextLine();

            System.out.println("Send to : ");
            String receiver_msisdn = input.nextLine();

        }

    }
}
