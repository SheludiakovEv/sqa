package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;

    public String firstname;
    public String middlename;
    public String lastname;
    public String photo;
    public String home;
    public String mobile;
    public String work;
    public String homepage;
    public String address;
    public String email;
    public String email2;
    public String email3;

    public ContactRecord(){}

    public ContactRecord(int id, String firstname, String middlename, String lastname, String photo,
                         String home, String mobile, String work, String homepage, String address,
                         String email, String email2, String email3) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.photo = photo;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.homepage = homepage;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
    }
}