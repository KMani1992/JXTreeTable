package treetabroot;

import java.util.List;

public class Department implements Entity {

    private int id;
    private String name;
    private List<Employee> employeeList;
    private boolean check;

    /**
     *
     * @param id
     * @param name
     * @param empList
     * @param check
     */
    public Department(int id, String name, List<Employee> empList,boolean check) {
        super();
        this.id = id;        
        this.name = name;
        this.employeeList = empList;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    
    
}