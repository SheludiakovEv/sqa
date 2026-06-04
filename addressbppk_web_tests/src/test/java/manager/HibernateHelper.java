package manager;

import manager.hbm.GroupRecord;
import model.GroupDate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase{

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory =
                new Configuration()
//                        .addAnnotatedClass(Book.class)
                        .addAnnotatedClass(GroupRecord.class)
                        // PostgreSQL
                        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                        .buildSessionFactory();
    }

    static List<GroupDate> convertList(List<GroupRecord> records){
        List<GroupDate> result = new ArrayList<>();
        for (var record : records){
            result.add(convert(record));
        }
        return result;
    }

    private static GroupDate convert(GroupRecord record) {
        return new GroupDate("" + record.id, record.name, record.header, record.footer);
    }

    public List<GroupDate> getGroupList(){
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord",GroupRecord.class).list();
        }));
    }
}
