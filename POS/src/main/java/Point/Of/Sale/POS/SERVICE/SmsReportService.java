package Point.Of.Sale.POS.SERVICE;

import Point.Of.Sale.POS.ENTITY.Invetory;
import Point.Of.Sale.POS.REPOSITORY.InvetoryRepository;
import Point.Of.Sale.POS.REPOSITORY.SalesRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsReportService {
    private final InvetoryRepository invetoryRepository;
    private final SalesRepository salesRepository;
    private final SmsService smsService;
    public  SmsReportService(InvetoryRepository invetoryRepository,
                             SmsService smsService,
                             SalesRepository salesRepository
    ){
        this.invetoryRepository=invetoryRepository;
        this.smsService=smsService;
        this.salesRepository=salesRepository;
    }
    @Scheduled(cron = "0 0 0 * * *")
     void sendDailySalesReport(){
        Double dailySales=salesRepository.sumTodaySales();
        Double weeklySales=salesRepository.sumWeeklySales();
        Double monthlySalesReport=salesRepository.sumMonthlySales();
        Double yearlySalesReport=salesRepository.sumYearlySales();
        String sms=String.format(
                        "RIPOTI YA MAUZO:\n"+
                        "Leo : TSH %.2f\n"+
                        "Wiki hii: TSH %.2f\n"+
                        "Mwezi huu : TSH %.2f\n"+
                        "Mwaka huu : TSH %.2f",
                         dailySales,weeklySales,monthlySalesReport,yearlySalesReport);
        smsService.sendSms(sms,"255779913771");
       System.out.println("sms imetumwa kikamilifu"+" "+ sms);

    }
    @Scheduled(initialDelay = 5000, fixedDelay = 3600000)
    void test(){
        sendDailySalesReport();
    }
    @Scheduled(fixedRate = 3600000)
    public void autoCheckLowStock() {
        // Inapata list ya Invetory kwa kutumia repository
        List<Invetory> lowStockItems = invetoryRepository.stockLevelAlert();

        for (Invetory item : lowStockItems) {
            sendAlertMessage(item);
        }
    }
    private void sendAlertMessage(Invetory product) {
        String message = "TAHADHARI: Bidhaa " + product.getProduct().getProductName() +
                " imefikia kiwango cha chini";
        System.out.println("Inatuma Message... " + message);
        try {
            smsService.sendSms(message, "255779913771");
        } catch (Exception e) {
            System.out.println("Kosa la kutuma SMS: " + e.getMessage());
        }
    }

}
