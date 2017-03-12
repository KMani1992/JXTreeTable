package bloagmaster;

import eu.floraresearch.lablib.gui.checkboxtree.CheckboxTree;
import eu.floraresearch.lablib.gui.checkboxtree.TreeCheckingModel;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javafx.scene.paint.Stop;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author ranga
 */

public class TreeTableMain extends JFrame 
{

    private JXTreeTable treeTable;
    
    //private CheckboxTree tree;

    public TreeTableMain() 
    {
        
        List<bloagMaster.Store> storeList=new ArrayList();
        
        
        //sample doj
        final Date doj = Calendar.getInstance().getTime();        
        List<Department> departmentList = new ArrayList<Department>();

        //create and add the first department with its list of Employee objects
        List<Employee> empList1 = new ArrayList<Employee>();
        empList1.add(new Employee(1, "Kiran", doj, "emp1.jpg",Boolean.TRUE));
        empList1.add(new Employee(2, "Prabhu", doj, "emp2.jpg",Boolean.TRUE));
        empList1.add(new Employee(3, "Murugavel", doj, "emp1.jpg",Boolean.FALSE));        
        departmentList.add(new Department(1, "Sales", empList1,Boolean.TRUE));

        //create and add the second department with its list of Employee objects
        List<Employee> empList2 = new ArrayList<Employee>();
        empList2.add(new Employee(4, "Deiveegan", doj, "emp2.jpg",Boolean.TRUE));
        empList2.add(new Employee(5, "Saravanan", doj, "emp1.jpg",Boolean.TRUE));
        departmentList.add(new Department(2, "Production", empList2,Boolean.TRUE));
        
        storeList.add(new bloagMaster.Store(1,"Store1", departmentList, Boolean.TRUE));
        
        //we use a no root model
        NoRootTreeTableModel noRootTreeTableModel = new NoRootTreeTableModel(storeList);
//        tree=new CheckboxTree(noRootTreeTableModel);
//         this.tree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);
//        this.tree.setRootVisible(true);
//        this.tree.setEnabled(true);
        //this.tree.expandAll();
        treeTable = new JXTreeTable(noRootTreeTableModel);
        
//        treeTable.setTreeCellRenderer(tree.getCellRenderer());
        
        
        
        //treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        treeTable.setRootVisible(false);
        treeTable.getColumnModel().getColumn(4).setCellRenderer(treeTable.getDefaultRenderer(Boolean.class));
        treeTable.getColumnModel().getColumn(4).setCellEditor(treeTable.getDefaultEditor(Boolean.class));
        treeTable.setRowHeight(50);
        
        treeTable.setFillsViewportHeight(true);
        treeTable.setFont(new Font("Arial", Font.PLAIN, 13));
//        treeTable.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                //System.out.println("hello");
//            }
//        });
      

        add(new JScrollPane(treeTable));

        setTitle("JXTreeTable Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {        
                new TreeTableMain();
            }
        });
    }
}
