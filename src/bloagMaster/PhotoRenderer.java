package bloagmaster;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ranga
 */
public class PhotoRenderer extends JCheckBox
                           implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
                            JTable table, Object photo,
                            boolean isSelected, boolean hasFocus,
            
                            int row, int column) {
        
        
        if(photo != null) {
            
            //ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/" + photo));
            //setIcon(imageIcon);
        }
        else {
            setIcon(null);
        }
        return this;
    }
}