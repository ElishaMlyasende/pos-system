package Point.Of.Sale.POS.IMPLEMENT;


import Point.Of.Sale.POS.SERVICE.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SmsImpl implements SmsService {
    private final WebClient webClient;
    @Value("${sms.api.key}")
    private String apiKey;

    @Value("${sms.username}")
    private String username;
    @Value("${sms.sender_id}")
    private String from;
    @Value("${sms.url}")
    private String url;
    public  SmsImpl(WebClient webClient){
        this.webClient=webClient;
    }
    @Override
    public void sendSms(String sms, String phoneNumber) {
        MultiValueMap<String, String> formdata=new LinkedMultiValueMap<>();
            formdata.add("username",username);
            formdata.add("from",from);
            formdata.add("to",phoneNumber);
            formdata.add("message",sms);

            this.webClient
                    .post()
                    .uri(url)
                    .header("apiKey",apiKey)
                    .header("username",username)
                    .body(BodyInserters.fromFormData(formdata))
                    .retrieve()
                    .toBodilessEntity()
                    .subscribe(response->{
                        System.out.println("Message send successfull to ");

                    }, error->{
                        System.out.println("sms not sent successfully"+sms);
        });


    }
}
