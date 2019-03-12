package by.training.lihodievski.final_project.command;

import java.util.HashMap;
import java.util.Map;

public class Respond {

    public static final String PAGE = "page";
    public static final String REDIRECT = "redirect";
    public static final String AJAX = "ajax";
    private String status;
    private String value;


    public Respond(String status) {
        this.status = status;
    }

    public Respond(String status, String value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
