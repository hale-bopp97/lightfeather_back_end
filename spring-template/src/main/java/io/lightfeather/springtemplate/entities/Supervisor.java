package io.lightfeather.springtemplate.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Supervisor {
    public String jurisdiction;
    public String firstName;
    public String lastName;

    public String getFormatted() {

        return this.jurisdiction +
                " - " +
                this.lastName +
                ", " +
                this.firstName;

    }

    public Boolean isValid() {

        Pattern pattern = Pattern.compile("([0-9])");
        Matcher matcher = pattern.matcher(this.jurisdiction);

        return !matcher.find();

    }

}
