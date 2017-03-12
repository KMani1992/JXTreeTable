package treetabroot;

import java.util.List;

public class Organization  implements Entity {

    private String name;
    private List<Department> departmentList;
    
    private boolean check;

    /**
     *
     * @param name
     * @param departmentList
     * @param check
     */
    public Organization(String name, List<Department> departmentList,boolean check) {
        this.name = name;
        this.departmentList = departmentList;
        this.check = check;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    
    

}
