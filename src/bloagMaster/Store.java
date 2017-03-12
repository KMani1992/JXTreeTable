/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloagMaster;

import bloagmaster.Department;
import java.util.List;

/**
 *
 * @author mani
 */
public class Store {
    
    private String name;
    
    private boolean check;
    
    private List<bloagmaster.Department> depList;

    private int id;
    /**
     *
     * @param id
     * @param name
     * @param check
     * @param depList
     */
    public Store(int id, String name,List<bloagmaster.Department> depList,boolean check){
        this.id = id;
        this.name = name;
        this.check = check;
        this.depList = depList;
        
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

    public List<Department> getDepList() {
        return depList;
    }

    public void setDepList(List<Department> depList) {
        this.depList = depList;
    }
    
    
}
