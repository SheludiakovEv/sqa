package model;

public record ContactDate(String id, String firstName, String middleName, String lastName, String photo) {
    public ContactDate(){
        this("","","","", "");
    }

    public ContactDate withId(String id) {
        return new ContactDate(id, this.firstName,this.middleName, this.lastName, this.photo);
    }

    public ContactDate withFirstName(String firstName) {
        return new ContactDate(this.id, firstName,this.middleName, this.lastName, this.photo);
    }

    public ContactDate withMiddleName(String middleName) {
        return new ContactDate(this.id, this.firstName, middleName, this.lastName, this.photo);
    }

    public ContactDate withLastName(String lastName) {
        return new ContactDate(this.id, this.firstName,this.middleName, lastName, this.photo);
    }

    public ContactDate withPhoto(String photo) {
        return new ContactDate(this.id, this.firstName,this.middleName, this.lastName, photo);
    }
}