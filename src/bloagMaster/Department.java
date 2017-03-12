package bloagmaster;

import java.util.List;

/**
 *
 * @author ranga
 */

public class Department {

    private int id;
    private String name;
    private List<Employee> employeeList;
    private boolean check;
    
    

    public Department(int id, String name,List<Employee> empList,boolean check) {
        super();
        this.check=check;
        this.id = id;        
        this.name = name;
        this.employeeList = empList;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}