package manager;


import model.ContactDate;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    public void removeContact(ContactDate contact){
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
        if (!manager.isElementPresent(By.name("Send e-Mail"))) {
            click(By.linkText("home"));
        }
    }

    private void fillContactForm(ContactDate contact) {
        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstName());
        click(By.name("middlename"));
        type(By.name("middlename"), contact.middleName());
        click(By.name("lastname"));
        type(By.name("lastname"), contact.lastName());
        attach(By.name("photo"), contact.photo());
    }

    private void selectContact() {
        click(By.xpath("//input[@value=\'Enter\']"));
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
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
        removeSelectedContacts();
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    public List<ContactDate> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactDate>();
        var spans = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var span : spans){

            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");

            var firstname = span.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastname = span.findElement(By.cssSelector("td:nth-child(2)")).getText();

            contacts.add(new ContactDate().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public void modifyContact(ContactDate contacts, ContactDate modifiedContact) {
        openHomePage();
        selectContactToEdit(contacts);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void selectContactToEdit(ContactDate contacts) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", contacts.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }
}