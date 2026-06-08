package tests.contacts;

import model.ContactDate;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class RemoveContactsTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactDate("", "delete", "delete", "delete",""));
        }
        var oldContacts = app.hmb().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hmb().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts.size(), oldContacts.size()-1);
    }

    @Test
    public void canRemoveContactInGroup() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactDate("", "delete", "delete", "delete",""));
        }
        var oldContacts = app.hmb().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hmb().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts.size(), oldContacts.size()-1);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactDate("", "ff", "ff", "ff",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

    @Test
    public void CanRemoveGroupToContact() {

        //Проверяем наличие контактов. если нет, то создаём
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactDate("", "delete", "delete", "delete",""));
        }


        // Проверяем наличие групп. создаём если нет
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupDate("", "ff", "ff", "ff"));
        }


        var rnd = new Random();
        //Выбор случайной группы
        var groupList = app.hmb().getGroupList();
        var indexGroup = rnd.nextInt(groupList.size());
        var selectedGroup = groupList.get(indexGroup);


        //Получаем список контактов в группе ДО добавления
        var oldContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);


        // Если в группе нет контактов — создаём и добавляем
        if (oldContactsInGroup.isEmpty()) {
            if (!app.contacts().isContactPresent()) {
                app.contacts().createContact(new ContactDate("", "delete", "delete", "delete",""));
            }

            //Выбор случайного контакта
            var contactList = app.hmb().getContactList();
            var indexContact = rnd.nextInt(contactList.size());
            var selectedContact = contactList.get(indexContact);

            // Добавляем контакт в группу
            app.contacts().AddGroupToContact(selectedContact, selectedGroup);

            // Обновляем список контактов в группе
            oldContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);
        }

        // выбираем случайный контакт из группы
        var selectedContact = oldContactsInGroup.get(rnd.nextInt(oldContactsInGroup.size()));


        app.contacts().RemoveGroupFromContact(selectedContact, selectedGroup);



        var newContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);


        Comparator<ContactDate> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContactsInGroup.sort(compareById);

        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.remove(selectedContact);
        expectedList.sort(compareById);


        Assertions.assertEquals(newContactsInGroup, expectedList);
    }
}