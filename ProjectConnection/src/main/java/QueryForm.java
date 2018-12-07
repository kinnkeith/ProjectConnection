import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryForm extends JFrame{
    private JPanel mainJPanel;
    private JButton searchProductsButton;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;

    public static void main(String[] args) {
        //making the form functional
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                QueryForm mainFrame = new QueryForm();
                mainFrame.setBounds(500, 100, 300, 300);
                mainFrame.setVisible(true);
            }
        });
    }

    public QueryForm() {
        final ProjApp projApp = new ProjApp();

        setTitle("Product Search App");
        setContentPane(mainJPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                projApp.search();
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                projApp.add();

            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                projApp.edit();
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                projApp.delete();
            }
        });
    }
}
