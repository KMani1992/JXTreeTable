package treetabroot;

import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

public class MyTreeTableModel extends AbstractTreeTableModel {

    private final static String[] COLUMN_NAMES = {"Id", "Name", "Doj", "Photo", "Select"};

    private final Organization organization;

    /**
     *
     * @param organization
     */
    public MyTreeTableModel(Organization organization) {
        super(organization);
        this.organization = organization;
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
    public Object getValueAt(Object node, int column) {
        if (node instanceof Organization) {
            if (1 == column) {
                return ((Organization) node).getName();
            } else if (4 == column) {
                return ((Organization) node).isCheck();
            }
        }
        if (node instanceof Department) {
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

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof Organization) {
            Organization org = (Organization) parent;
            return org.getDepartmentList().get(index);
        } else {
            Department dept = (Department) parent;
            return dept.getEmployeeList().get(index);
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Organization) {
            Organization org = (Organization) parent;
            return org.getDepartmentList().size();
        } else {
            Department dept = (Department) parent;
            return dept.getEmployeeList().size();
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof Organization) {
            Organization org = (Organization) parent;
            Department dept = (Department) child;
            return org.getDepartmentList().indexOf(dept);
        } else {
            Department dept = (Department) parent;
            Employee emp = (Employee) child;
            return dept.getEmployeeList().indexOf(emp);
        }
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        if (node instanceof Organization && (0 == column || 2 == column)) {
            return false;
        } else if (node instanceof Department && column == 2) {
            return false;
        }

        return true;
    }

    private boolean isFullyCheckedDep(List<Department> depList, boolean sel) {
        if (!sel) {
            for (Department dep : depList) {

                boolean empFullyChk = isFullyChecked(dep.getEmployeeList(), sel);
                if (empFullyChk) {
                    if (!dep.isCheck()) {
                        return false;
                    }
                } else {
                    return empFullyChk;
                }
            }
        }
        return true;
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

    private boolean isPartialChecked(List<Employee> empList) {
        for (Employee emp : empList) {

            if (emp.isCheck()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void setValueAt(Object value, Object node, int column) {
        String strValue;
        if (value instanceof String) {
            strValue = (String) value;
        } else {
            strValue = "";
        }
        if (node instanceof Organization) {
            Organization org = (Organization) node;
            switch (column) {
                case 1:
                    org.setName(strValue);
                    break;
                case 4:
                    List<treetabroot.Department> emplist = org.getDepartmentList();
                    boolean val = Boolean.valueOf(value.toString());

                    val = val == isFullyCheckedDep(emplist, val);

                    for (treetabroot.Department dep : emplist) {
                        List<Employee> employeeList = dep.getEmployeeList();
                        for (treetabroot.Employee emp : employeeList) {
                            emp.setCheck(val);
                        }

                        dep.setCheck(val);
                    }
                    org.setCheck(val);

                    break;
            }
        } else if (node instanceof Department) {
            Department dept = (Department) node;
            switch (column) {
                case 0:
                    dept.setId(Integer.valueOf(strValue));
                    break;
                case 1:
                    dept.setName(strValue);
                    break;
                case 4:

                    List<treetabroot.Employee> emplist = dept.getEmployeeList();
                    boolean childChecked = Boolean.valueOf(value.toString());

                    boolean rootChecked = false;

                    childChecked = childChecked == isFullyChecked(emplist, childChecked);

                    for (treetabroot.Employee emp : emplist) {
                        emp.setCheck(childChecked);
                    }

                    dept.setCheck(childChecked);

                    if (!childChecked) {

                        List<Department> departmentList = organization.getDepartmentList();
                        for (Department tmpDep : departmentList) {

                            if (dept != tmpDep) {
                                List<Employee> emplist1 = tmpDep.getEmployeeList();

                                rootChecked = isPartialChecked(emplist1);

                            }

                        }
                    } else {
                        rootChecked = childChecked;
                    }
                    organization.setCheck(rootChecked);

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
                    boolean leafChecked = Boolean.valueOf(value.toString());
                    boolean childChecked;
                    boolean rootChecked = false;
                    emp.setCheck(leafChecked);

                    List<Department> departmentList = organization.getDepartmentList();
                    for (Department dep : departmentList) {

                        List<Employee> emplist = dep.getEmployeeList();

                        int indexOf = emplist.indexOf(emp);

                        if (indexOf > -1) {
                            childChecked = isPartialChecked(emplist);

                            dep.setCheck(childChecked);

                            if (childChecked) {
                                rootChecked = true;
                                break;
                            }
                        } else {
                            childChecked = isPartialChecked(emplist);

                            if (childChecked) {
                                rootChecked = true;
                            }
                        }
                    }
                    organization.setCheck(rootChecked);
                    break;
            }
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Employee;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return super.getColumnClass(column);
    }
}
