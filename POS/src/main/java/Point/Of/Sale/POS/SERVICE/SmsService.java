package Point.Of.Sale.POS.SERVICE;

import org.springframework.stereotype.Service;

@Service
public interface SmsService {
    public void sendSms(String sms, String phoneNumber);
}
