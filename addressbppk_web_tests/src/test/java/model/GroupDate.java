package model;

public record GroupDate(String id, String name, String header, String footer) {

    public GroupDate(){
        this("", "","","");
    }

    public GroupDate withId(String id) {
        return new GroupDate(id, this.name, this.header, this.footer);
    }

    public GroupDate withName(String name) {
        return new GroupDate(this.id, name,this.header, this.footer);
    }


    public GroupDate withHeader(String header) {
        return new GroupDate(this.id, this.name, header, this.footer);
    }

    public GroupDate withFooter(String footer) {
        return new GroupDate(this.id, this.name,this.header, footer);
    }
}