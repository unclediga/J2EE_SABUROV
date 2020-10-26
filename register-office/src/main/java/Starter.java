import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.unclediga.saburov.register.rest.MarriageController;
import ru.unclediga.saburov.register.view.MarriageRequest;

public class Starter {
    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
        final MarriageController controller = context.getBean("controller", MarriageController.class);
        controller.findMarriageCertificate(new MarriageRequest());
    }
}
