package bloagmaster;

import java.util.Date;

/**
 *
 * @author ranga
 */

public class Employee {

    private int id;
    private String name;
    private Date doj;
    
    private String photo;

    private boolean check;

    public Employee(int id, String name, Date doj, String photo,boolean check) {
        super();
        this.check=check;
        this.id = id;
        this.name = name;
        this.doj = doj;
        this.photo = photo;
    }
    
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
