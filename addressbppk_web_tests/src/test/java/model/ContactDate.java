package model;

public record ContactDate(String firstName, String middleName, String lastName) {
    public ContactDate(){
        this("","","");
    }

    public ContactDate withFirstName(String firstName) {
        return new ContactDate(firstName,this.middleName, this.lastName);
    }

    public ContactDate withMiddleName(String middleName) {
        return new ContactDate(this.firstName, middleName, this.lastName);
    }

    public ContactDate withLastName(String lastName) {
        return new ContactDate(this.firstName,this.middleName, lastName);
    }
}