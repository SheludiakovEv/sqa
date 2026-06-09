package tests.contacts;

import model.ContactDate;
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
}