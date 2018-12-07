/**
 * Author: Keith Koinange
 **/
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ProjApp extends JFrame{

    public JPanel mainPanel, bottomPanel;
    private JLabel lblProdSKU, lblProduct, lblProdDesc, lblPrice, lblDiscount;
    private JTextField txtFieldID, txtFieldProdDesc, txtFieldProd, txtFieldPrice,txtFieldDiscount;
    private JButton btnSearch, btnAdd, btnDelete, btnEdit;
    private JScrollPane scrollPane;
    private JTable productTable;
    private String inProdSKU, inProd, inProdDesc, inPrice, inDiscount = "0";

    private SQLConn sqlConn= new SQLConn();

    public ProjApp() {
        setBounds(250, 100, 700, 450);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sqlConn.connectToDB();

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Products> productsList = null;
                    inProdSKU = txtFieldID.getText();

                    if (inProdSKU != null && inProdSKU.trim().length() > 0) {
                        productsList = sqlConn.searchProdID(inProdSKU);

                    } else {
                        productsList = sqlConn.searchAllProducts();
                    }
                    // create the model and update the "table"
                    ProductTableModel model = new ProductTableModel(productsList);
                    productTable.setModel(model);
                    /*if(model==null)
                    {
                        JOptionPane.showMessageDialog(null, "Product not found", "FAILED!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Product Search Success", "SUCCESS!", JOptionPane.NO_OPTION);
*/
                    //System.exit(0);

                } catch (Exception ex1) {
                    System.out.println(ex1);
                }clearTxtFields();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Products> productsList = null;
                    inProdSKU = txtFieldID.getText();
                    inProd = txtFieldProd.getText();
                    inProdDesc = txtFieldProdDesc.getText();
                    inPrice = txtFieldPrice.getText();
                    inDiscount = txtFieldDiscount.getText();

                    if (inProdSKU != null && inProdSKU.trim().length() > 0) {
                        if (inProdDesc != null && inProdDesc.trim().length() > 0) {
                            if (inProd != null && inProd.trim().length() > 0) {
                                if (inPrice != null && inPrice.trim().length() > 0) {
                                    // create the model and update the "table"
                                    productsList = sqlConn.addProducts(inProdSKU, inProdDesc, inProd, inPrice, inDiscount);
                                    ProductTableModel model = new ProductTableModel(productsList);
                                    productTable.setModel(model);
                                    JOptionPane.showMessageDialog(null, "Product Added", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                                } else
                                    JOptionPane.showMessageDialog(null, "Price Field Empty!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, "Product Field Empty!", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "Product Description Field Empty!", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "Product ID Field Empty!", "ERROR!!!!", JOptionPane.ERROR_MESSAGE);

                } catch (Exception ex1) {
                    ex1.getMessage();
                    System.out.println(ex1);
                }//clearTxtFields();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Products> productsList = null;
                    inProdSKU = txtFieldID.getText();
                    inProd = txtFieldProd.getText();
                    inProdDesc = txtFieldProdDesc.getText();
                    inPrice = txtFieldPrice.getText();
                    inDiscount = txtFieldDiscount.getText();

                    if (inProdSKU != null && inProdSKU.trim().length() > 0) {
                        if (inProdDesc != null && inProdDesc.trim().length() > 0) {
                            if (inProd != null && inProd.trim().length() > 0) {
                                if (inPrice != null && inPrice.trim().length() > 0) {
                                    // create the model and update the "table"
                                    productsList = sqlConn.updateProducts(inProdSKU, inProdDesc, inProd, inPrice, inDiscount);
                                    ProductTableModel model = new ProductTableModel(productsList);
                                    productTable.setModel(model);
                                    JOptionPane.showMessageDialog(null, "Product Edit Complete", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                                } else
                                    JOptionPane.showMessageDialog(null, "Price Field Empty!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, "Product Field Empty!", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "Product Description Field Empty!", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "Product ID Field Empty!", "ERROR!!!!", JOptionPane.ERROR_MESSAGE);
                    //System.exit(0);

                } catch (Exception ex1) {
                    System.out.println(ex1);
                }//clearTxtFields();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Products> productsList = null;
                    inProdSKU = txtFieldID.getText();

                    if (inProdSKU != null && inProdSKU.trim().length() > 0) {
                        productsList = sqlConn.deleteProducts(inProdSKU);
                        // create the model and update the "table"
                        ProductTableModel model = new ProductTableModel(productsList);
                        productTable.setModel(model);
                        JOptionPane.showMessageDialog(null, "Product Deleted", "SUCCESS!", JOptionPane.NO_OPTION);

                    } else {
                        JOptionPane.showMessageDialog(null, "Product ID Field Empty!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                    //System.exit(0);

                } catch (Exception ex1) {
                    System.out.println(ex1);
                }clearTxtFields();
            }
        });
    }

    public void search()
    {
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);

        txtFieldProd.setEnabled(false);
        txtFieldProdDesc.setEnabled(false);
        txtFieldPrice.setEnabled(false);
        txtFieldDiscount.setEnabled(false);

        setTitle("Search Product");
        setVisible(true);
    }
    public void add ()
    {
        //btnSearch.setEnabled(false);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);

        setTitle("Add Product");
        setVisible(true);
    }
    public void edit ()
    {
        //btnSearch.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(false);

        setTitle("Edit Product");
        setVisible(true);
    }

    public void delete ()
    {
        btnAdd.setEnabled(false);
        //btnSearch.setEnabled(false);
        btnEdit.setEnabled(false);

        txtFieldProd.setEnabled(false);
        txtFieldProdDesc.setEnabled(false);
        txtFieldPrice.setEnabled(false);
        txtFieldDiscount.setEnabled(false);

        setTitle("Delete Product");
        setVisible(true);
    }

    public void clearTxtFields ()
    {
        txtFieldID.setText("");
        txtFieldProdDesc.setText("");
        txtFieldProd.setText("");
        txtFieldPrice.setText("");
        txtFieldDiscount.setText("");
    }
}
