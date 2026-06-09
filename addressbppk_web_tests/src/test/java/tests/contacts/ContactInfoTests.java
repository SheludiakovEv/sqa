package tests.contacts;

import model.ContactDate;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones(){

        var contacts = app.hmb().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactDate::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.homepage())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress(){

        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactDate()
                    .withFirstName("First_name")
                    .withAddress("Address"));
        }

        var contacts = app.hmb().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactDate::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var address = app.contacts().getAddress();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmailAddress(){

        var contacts = app.hmb().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactDate::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emailAddress = app.contacts().getEmail();
        Assertions.assertEquals(expected, emailAddress);
    }
}