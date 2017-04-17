package leena.contactdbapp;

/**
 * Created by admin on 08-11-2016.
 */

public class Contact {

    Long id;
    String name;
    String phoneNumber;
    String emailID;

    public Contact(Long id,String name, String phoneNumber, String emailID)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }

    public Contact(String name, String phoneNumber, String emailID)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
