package bloagmaster;

import java.util.Iterator;
import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author ranga
 */
public class NoRootTreeTableModel extends AbstractTreeTableModel {

    private final static String[] COLUMN_NAMES = {"Id", "Name", "Doj", "Photo", "Check"};

    private List<bloagMaster.Store> departmentList;

    public NoRootTreeTableModel(List<bloagMaster.Store> departmentList) {
        super(new Object());
        this.departmentList = departmentList;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        if (node instanceof Department && column == 2) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Employee;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Department) {
            Department dept = (Department) parent;
            return dept.getEmployeeList().size();
        }
        return departmentList.size();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof Department) {
            Department dept = (Department) parent;
            return dept.getEmployeeList().get(index);
        }
        return departmentList.get(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Department dept = (Department) parent;
        Employee emp = (Employee) child;
        return dept.getEmployeeList().indexOf(emp);
    }

    @Override
    public Object getValueAt(Object node, int column) {
         if (node instanceof bloagMaster.Store) {
            bloagMaster.Store dept = (bloagMaster.Store) node;
            switch (column) {
                case 0:
                    return 1;

                case 1:
                    return dept.getName();
                case 4:
                    return dept.isCheck();
            }
        }
        else if (node instanceof Department) {
            Department dept = (Department) node;
            switch (column) {
                case 0:
                    return dept.getId();

                case 1:
                    return dept.getName();
                case 4:
                    return dept.isCheck();
            }
        } else if (node instanceof Employee) {
            Employee emp = (Employee) node;
            switch (column) {
                case 0:
                    return emp.getId();
                case 1:
                    return emp.getName();
                case 2:
                    return emp.getDoj();
                case 3:
                    return emp.getPhoto();
                case 4:

                    return emp.isCheck();
            }
        }
        return null;
    }

    
    private boolean isFullyChecked(List<Employee> empList, boolean sel) {
        if (!sel) {
            for (Employee emp : empList) {

                if (!emp.isCheck()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
    
    
    @Override
    public void setValueAt(Object value, Object node, int column) {
        System.out.println("v " + value);
        String strValue = "";
        if (value instanceof String) {
            strValue = (String) value;
        }
        if (node instanceof Department) {

            Department dept = (Department) node;

            switch (column) {
                case 0:
                    dept.setId(Integer.valueOf(strValue));
                    break;
                case 1:
                    dept.setName(strValue);
                    break;
                case 4:
                    
                    List<Employee> emplist = dept.getEmployeeList();
                    boolean val = Boolean.valueOf(value.toString());
                    
                    val=val==isFullyChecked(emplist,val);
                    
                    for (Employee emp : emplist) {
                        emp.setCheck(val);
                    }
                    dept.setCheck(val);

                    break;
            }
        } else if (node instanceof Employee) {
            Employee emp = (Employee) node;
            switch (column) {
                case 0:
                    emp.setId(Integer.valueOf(strValue));
                    break;
                case 1:
                    emp.setName(strValue);
                    break;
                case 4:
                    boolean val = Boolean.valueOf(value.toString());

                    emp.setCheck(val);
//                    for (Department dep : departmentList) {
//
//                        List<Employee> emplist = dep.getEmployeeList();
//                        int indexOf = emplist.indexOf(emp);
//                        if (indexOf > -1) {
//                            int cnt = 0;
//                            for (Employee emp1 : emplist) {
//                                if (emp1.isCheck()) {
//                                    cnt++;
//                                    break;
//                                }
//                            }
//                            if (cnt == 0) {
//                                dep.setCheck(false);
//                            } else {
//                                dep.setCheck(true);
//                            }
//                            break;
//                        }
//                    }

                    break;
            }
        }
    }
}
