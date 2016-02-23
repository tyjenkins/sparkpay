import com.payments.config.WebConfig;
import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jenkins on 23/02/2016.
 */
@Configuration
@ComponentScan({ "com.payments" })
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        new WebConfig(ctx.getBean(AccountService.class), ctx.getBean(TransactionService.class));
        ctx.registerShutdownHook();
    }

}
