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
        String homepage)
{
    public ContactDate(){
        this("","","","", "", "", "", "", "");
    }

    public ContactDate withId(String id) {
        return new ContactDate(id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage);
    }

    public ContactDate withFirstName(String firstName) {
        return new ContactDate(this.id, firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage);
    }

    public ContactDate withMiddleName(String middleName) {
        return new ContactDate(this.id, this.firstName, middleName, this.lastName, this.photo, this.home, this.mobile, this.work, this.homepage);
    }

    public ContactDate withLastName(String lastName) {
        return new ContactDate(this.id, this.firstName,this.middleName, lastName, this.photo, this.home, this.mobile, this.work, this.homepage);
    }

    public ContactDate withPhoto(String photo) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, photo, this.home, this.mobile, this.work, this.homepage);
    }

    public ContactDate withHome(String home) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, home, this.mobile, this.work, this.homepage);
    }
    public ContactDate withMobile(String mobile) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, mobile, this.work, this.homepage);
    }
    public ContactDate withWork(String work) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, work, this.homepage);
    }
    public ContactDate withHomepage(String homepage) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, this.photo, this.home, this.mobile, this.work, homepage);
    }
}