package email;

import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;

public class EmailSender {
    public static void sendEmail(String recipient, String lastCoordinates, String lastCoordinatesDate) {

        Courier.init("pk_prod_X8MHBNWVN7M26TG9GXNBHBV2ZYBY");

        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", recipient);
        message.setTo(to);
        message.setTemplate("2CZXFR52RE43WMKXNV7JPFE0H68A");

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("lastCoordinates", lastCoordinates);
        data.put("lastCoordinatesDate", lastCoordinatesDate);

        message.setData(data);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}