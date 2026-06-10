package tests.contacts;

import common.CommonFunctions;
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
    void testEmailAddress() {

//        var contacts = app.hmb().getContactList();
//        var expected = contacts.stream().collect(Collectors.toMap(ContactDate::id, contact ->
//                Stream.of(contact.email(), contact.email2(), contact.email3())
//                        .filter(s -> s != null && !"".equals(s))
//                        .collect(Collectors.joining("\n"))
//        ));
//        var emailAddress = app.contacts().getEmail();
//        Assertions.assertEquals(expected, emailAddress);
//    }
        if (app.hmb().getContactCount() == 0) {
            app.hmb().createContact(new ContactDate()
                    .withFirstName("First name Test ")
                    .withEmail(CommonFunctions.randomString(5))
                    .withEmail2(CommonFunctions.randomString(7))
                    .withEmail3(CommonFunctions.randomString(10))
            );
            app.contacts().openHomePage();
        }


// Получаем актуальный список контактов
        var contacts = app.hmb().getContactList();

// Находим в списке именно наш созданный контакт (например, последний или по имени)
        var contact = contacts.get(contacts.size() - 1);

// Формируем ожидаемую строку из имейлов НАШЕГО контакта
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

        var emails = app.contacts().getEmail(contact);
        Assertions.assertEquals(expected, emails);
    }
}