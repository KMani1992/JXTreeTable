package treetabroot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.JXTreeTable;

public class TreeTableMainWithRoot extends JFrame {

    private JXTreeTable treeTable;
    
    public TreeTableMainWithRoot() {
        //sample doj
        final Date doj = Calendar.getInstance().getTime();

        List<Department> departmentList = new ArrayList<Department>();

        List<Employee> empList1 = new ArrayList<Employee>();
        empList1.add(new Employee(1, "Kiran", doj, "emp1.jpg",Boolean.TRUE));
        empList1.add(new Employee(2, "Prabhu", doj, "emp2.jpg",Boolean.TRUE));
        empList1.add(new Employee(3, "Murugavel", doj, "emp1.jpg",Boolean.FALSE));        
        departmentList.add(new Department(1, "Sales", empList1,Boolean.FALSE));

        List<Employee> empList2 = new ArrayList<Employee>();
        empList2.add(new Employee(4, "Deiveegan", doj, "emp2.jpg",Boolean.TRUE));
        empList2.add(new Employee(5, "Saravanan", doj, "emp1.jpg",Boolean.TRUE));
        departmentList.add(new Department(2, "Production", empList2,Boolean.TRUE));
        
        Organization organization = new Organization("ABC XYZ Corporation", departmentList,Boolean.TRUE);
        
        MyTreeTableModel myTreeTableModel = new MyTreeTableModel(organization);
        treeTable = new JXTreeTable(myTreeTableModel);
        
        treeTable.getColumnModel().getColumn(4).setCellRenderer(treeTable.getDefaultRenderer(Boolean.class));
        treeTable.getColumnModel().getColumn(4).setCellEditor(treeTable.getDefaultEditor(Boolean.class));
        
        //treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        treeTable.setRootVisible(true);
        treeTable.getColumnModel().getColumn(3).setCellRenderer(new PhotoRenderer());
        treeTable.setRowHeight(50);

        add(new JScrollPane(treeTable));
        
        treeTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                e.consume();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                e.consume();
            }
        });

        setTitle("JXTreeTable Example with Root");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TreeTableMainWithRoot();
            }
        });
    }

    
}