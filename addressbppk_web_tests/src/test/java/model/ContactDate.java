package model;

public record ContactDate(
        String id,
        String firstName,
        String middleName,
        String lastName,
        String photo,
        String home,
        String mobile,
        String work,
        String homepage,
        String address,
        String email,
        String email2,
        String email3)
{
    public ContactDate(){
        this("","","","", "", "", "", "", "","","","","");
    }

    public ContactDate withId(String id) {
        return new ContactDate(id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }

    public ContactDate withFirstName(String firstName) {
        return new ContactDate(this.id, firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }

    public ContactDate withMiddleName(String middleName) {
        return new ContactDate(this.id, this.firstName, middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }

    public ContactDate withLastName(String lastName) {
        return new ContactDate(this.id, this.firstName,this.middleName, lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }

    public ContactDate withPhoto(String photo) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }

    public ContactDate withHome(String home) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }
    public ContactDate withMobile(String mobile) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, mobile, this.work, this.homepage, this.address, this.email, this.email2, this.email3);
    }
    public ContactDate withWork(String work) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, work, this.homepage, this.address, this.email, this.email2, this.email3);
    }
    public ContactDate withHomepage(String homepage) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, homepage, this.address, this.email, this.email2, this.email3);
    }

    public ContactDate withAddress(String address) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, address, this.email, this.email2, this.email3);
    }

    public ContactDate withEmail(String email) {
        return new ContactDate(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, email, this.email2, this.email3);
    }

    public ContactDate withEmail2(String email2) {
        return new ContactDate(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, email2, this.email3);
    }

    public ContactDate withEmail3(String email3) {
        return new ContactDate(this.id, this.firstName, this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage, this.address, this.email, this.email2, email3);
    }
}