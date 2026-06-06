package manager;

import org.openqa.selenium.support.ui.Select;
import model.ContactDate;
import model.GroupDate;
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
        //selectContact(contact);
    }

    public void createContactInGroup(ContactDate contact, GroupDate group){
        openCreationPage();
        fillContactForm(contact);
        selectGroup(group);
        //selectContact(contact);
        openHomePage();
    }

    private void selectGroup(GroupDate group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactDate contact){
        highLightСontact();
        clickDeteButtonContact();
        clickHomePage();
    }

    public void RemoveGroupFromContact(ContactDate contact, GroupDate group) {
        openHomePage();
        selectGroupFilter(group);
        selectContact(contact);
        removeFromGroup();
        openHomePage();
    }


    private void selectGroupFilter(GroupDate group) {
        click(By.cssSelector(String.format("select[name='group'] option[value='%s']", group.id())));
    }

    private void removeFromGroup() {
        click(By.name("remove"));
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

    private void selectContact(ContactDate contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
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

    public void AddGroupToContact(ContactDate contact, GroupDate group) {
        openHomePage();
        selectContact(contact);
        selectAddGroup(group);
        addToGroup();
        openHomePage();
    }

    private void selectAddGroup(GroupDate group) {
        click(By.cssSelector(String.format("select[name='to_group'] option[value='%s']", group.id())));
    }

    private void addToGroup() {
        click(By.name("add"));
    }
}