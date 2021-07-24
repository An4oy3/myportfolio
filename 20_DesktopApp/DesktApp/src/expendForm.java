import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class expendForm {
    private JPanel expendPanel;
    private JButton expendButton;
    private JTextField fullNameField;
    private JLabel fullNameLabel;


    public expendForm(String fullName){
        fullNameField.setText(fullName);

        expendButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                expendPanel.removeAll();
                expendPanel.revalidate();
                expendPanel.add(new MainForm().getMainPanel());

            }
        });


    }


    public JPanel getExpendPanel(){
        return expendPanel;
    }
}
