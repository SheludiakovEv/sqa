package manager;


import model.ContactDate;
import org.openqa.selenium.By;

public class ContactHelper  extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactDate contact){
        openCreationPage();
        fillContactForm(contact);
        selectContact();
        openHomePage();
    }

    public void removeContact(){
        highLightСontact();
        clickDeteButtonContact();
        clickHomePage();
    }

    public void openCreationPage(){
        if (!manager.isElementPresent(By.name("Send e-Mail"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage(){
        click(By.linkText("home page"));
    }

    private void fillContactForm(ContactDate contact) {
        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstName());
        click(By.name("middlename"));
        type(By.name("middlename"), contact.firstName());
        click(By.name("theform"));
        click(By.name("lastname"));
        type(By.name("lastname"), contact.lastName());
    }

    private void selectContact() {
        click(By.cssSelector("input:nth-child(71)"));
    }

    private void highLightСontact() {
        click(By.name("selected[]"));
    }

    private void clickDeteButtonContact() {
        click(By.name("delete"));
    }

    private void clickHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isContactPresent() {
        openCreationPage();
        return manager.isElementPresent(By.name("selected[]"));
    }
}