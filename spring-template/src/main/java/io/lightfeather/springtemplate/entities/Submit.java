package io.lightfeather.springtemplate.entities;

public class Submit {
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String supervisor;

    public Submit(String firstName,
                  String lastName,
                  String email,
                  String phone,
                  String supervisor) {

        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.phone      = phone;
        this.supervisor = supervisor;

    }

}
