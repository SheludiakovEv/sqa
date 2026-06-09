package tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones(){

        var contacts = app.hmb().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);

        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.homepage())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }
}
