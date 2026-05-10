package tests.contacts;

import model.ContactDate;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class RemoveContactsTests extends TestBase {

    @Test
    public void removeContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactDate("delete", "delete", "delete"));
        }
        app.contacts().removeContact();
    }
}