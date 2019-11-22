// Class containing static methods to generate and send an email receipt to a user.

package seng3150.group1.helpers;

import seng3150.group1.entities.User;
import seng3150.group1.models.FlightLegContainer;
import seng3150.group1.models.FlightOptionContainer;

import java.util.LinkedList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "flightpub3160@gmail.com";
    private static final String SMTP_AUTH_PWD  = "flightpube1";

    // Function to send an email containing the receipt for a booking to a user given the list of flights they booked.
    public static void emailReceipt(User user, LinkedList<FlightOptionContainer> flights) {

        if (user.getEmail() ==null)
            return;

        String recipientEmail = user.getEmail();

        StringBuilder messageBody = new StringBuilder();

        messageBody.append("<div style=\"background: #1e365c; color: white;\">");
        messageBody.append("<img src=\"http://flightpub.clickk.com.au/logo.png\" style=\"display: block; margin: 0 auto; width: 200px;\">");
        messageBody.append("</div>");

        messageBody.append("<div style=\"background: #e96736; color: white; padding: 35px;\">");

        messageBody.append("<h1> Thank you for your booking with FlightPub, " + user.getFirstname() + "!</h1> " + "<br/>");
        messageBody.append("</div>");

        messageBody.append("<div style=\"padding: 35px;\">");
        messageBody.append("<h2>Your flights are </h2>" + "<br/>");
        messageBody.append("<br/>");
        int flightNumber = 1;

        messageBody.append("<div style=\"border: 1px black solid;\">");
        for (FlightOptionContainer foc : flights) {
            messageBody.append("<h3>Flight " + flightNumber + "</h3>");
            int legNumber = 1;
            boolean lightBack = false;
            for (FlightLegContainer leg : foc.getLegs()) {

                if (lightBack)
                    messageBody.append("<div style=\"background:#ffffff; padding:10px\">");
                else
                    messageBody.append("<div style=\"background:#d3d3d3; padding:10px\">");

                messageBody.append("Leg " + legNumber + ": ");
                messageBody.append(leg.getFlyingFrom() + " &#x2192; " + leg.getFlyingTo() + "<br/>");
                messageBody.append("Depart: " + leg.getDepartureDate() + " Arrive: " + leg.getArrivalDate() + "<br/>");
                messageBody.append("$" + leg.getPrice());
                messageBody.append("<br/></div>");
                lightBack = !lightBack;
                legNumber++;
            }
            flightNumber++;
        }
        messageBody.append("</div>");

        messageBody.append("</div>");
        messageBody.append("<div style=\"background:#1e365c; color:white; padding:35px; text-align:center\">");
        messageBody.append("<br/>FlightPub Pty Ltd<br/>");
        messageBody.append("1 University Drive Callaghan<br/>");
        messageBody.append("A mile higher than the rest...");
        messageBody.append("</div>");

        sendEmail(recipientEmail, "Congratulations on your booking!", messageBody.toString());

    }

    // Given the recipient's address, subject and message body, send an email to a user.
    private static void sendEmail(String recipient, String subject, String messageBody) {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        try {
            Transport transport = session.getTransport();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_AUTH_USER));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody, "UTF-8", "html");

            //send the message
            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

            System.out.println("Email receipt sent successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
