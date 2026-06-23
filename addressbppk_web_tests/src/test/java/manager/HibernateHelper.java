package manager;

import io.qameta.allure.Step;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactDate;
import model.GroupDate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase{

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory =
                new Configuration()
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        // PostgreSQL
                        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=CONVERT_TO_NULL")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                        .buildSessionFactory();
    }

    static List<GroupDate> convertList(List<GroupRecord> records){
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupDate convert(GroupRecord record) {
        return new GroupDate("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupDate data) {
        var id = data.id();
        if("".equals(id)){
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    @Step
    public List<GroupDate> getGroupList(){
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord",GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*)from GroupRecord",long.class).getSingleResult();
        });
    }

    @Step
    public void createGroup(GroupDate groupDate) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupDate));
            session.getTransaction().commit();
        });
    }

    static List<ContactDate> convertContactList(List<ContactRecord> records){
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactDate convert(ContactRecord record) {
        return new ContactDate().withId("" + record.id)
                .withFirstName(record.firstname)
                .withMiddleName(record.middlename)
                .withLastName(record.lastname)
                .withPhoto("")
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work)
                .withHomepage(record.homepage)
                .withAddress(record.address)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3);
    }

    private static ContactRecord convert(ContactDate data) {
        var id = data.id();
        if("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id),
                data.firstName(),
                data.middleName(),
                data.lastName(),
                data.photo(),
                data.home(),
                data.mobile(),
                data.work(),
                data.homepage(),
                data.address(),
                data.email(),
                data.email2(),
                data.email3()
        );
    }

    public List<ContactDate> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*)from ContactRecord",long.class).getSingleResult();
        });
    }

//    public List<ContactDate> getContactsInGroup(GroupDate group) {
//        return sessionFactory.fromSession(session -> {
//            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
//        });
//    }

    public List<ContactDate> getContactsInGroup(GroupDate group) {
        return sessionFactory.fromSession(session -> {
            var query = session.createQuery(
                    "select c from GroupRecord g join g.contacts c where g.id = :groupId",
                    ContactRecord.class
            );
            query.setParameter("groupId", Integer.parseInt(group.id()));
            return convertContactList(query.list());
        });
    }

    public void createContact(ContactDate contactDate) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactDate));
            session.getTransaction().commit();
        });
    }
}