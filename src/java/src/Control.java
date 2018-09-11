package src;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Control extends javax.swing.JFrame {

    OrderManager om = new OrderManager();
    MenuManager mm = new MenuManager();
    ItemManager im = new ItemManager();
    DiscountManager dm = new DiscountManager();

    Menu menuSelected = null;
    Item menuItemSelected = null;
    Item itemSelected = null;
    Discount discountSelected = null;
    Discount menuDiscountSelected = null;
    Order orderSelected = null;
    Order historySelected = null;

    DefaultListModel menuListModel;
    DefaultListModel menuItemListModel;
    DefaultListModel itemListModel;
    DefaultListModel orderListModel;
    DefaultListModel discountListModel;
    DefaultListModel menuDiscountListModel;
    DefaultListModel historyListModel;

    JFrame frame = new JFrame();

    String name = "Name";
    int maxTables = 99;
    
    public Control() {
        initComponents();

        menuListModel = new DefaultListModel();
        menuItemListModel = new DefaultListModel();
        itemListModel = new DefaultListModel();
        orderListModel = new DefaultListModel();
        discountListModel = new DefaultListModel();
        menuDiscountListModel = new DefaultListModel();
        historyListModel = new DefaultListModel();

        menuList.setModel(menuListModel);
        menuItemList.setModel(menuItemListModel);
        itemList.setModel(itemListModel);
        orderList.setModel(orderListModel);
        discountList.setModel(discountListModel);
        menuDiscountList.setModel(menuDiscountListModel);
        historyList.setModel(historyListModel);

        ArrayList<Menu> tempMenuList = mm.getMenus();
        for (Menu m : tempMenuList) {
            menuListModel.addElement(m.getName());
        }
        
        Thread t = new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        refreshGUI();
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();

        ArrayList<String> dotw = new ArrayList<>();
        dotw.add("Monday");dotw.add("Tuesday");dotw.add("Wednesday");dotw.add("Thursday");
        dotw.add("Friday");dotw.add("Saturday");dotw.add("Sunday");
        DefaultComboBoxModel d = new DefaultComboBoxModel();
        
        daysActiveComboBox.setModel(d);
        for (String s : dotw) {
            d.addElement(s);
        }

    }

    private void initLists() {

        // Menus
        ArrayList<Menu> tempMenuList = mm.getMenus();
        for (Menu m : tempMenuList) {
            menuListModel.addElement(m.getName());
        }

        //Menu Items
        if (menuSelected != null) {
            ArrayList<Item> items = menuSelected.getItems();
            for (Item i : items) {
                menuItemListModel.addElement(i.getName());
            }
        }

        // Items
        ArrayList<Item> items = im.getItems();
        for (Item i : items) {
            itemListModel.addElement(i.getName());
        }

        // Current Orders
        ArrayList<Order> orders = om.getOrders();
        for (Order o : orders) {
            orderListModel.addElement(o.toSimpleString());
        }

        // Menu Discounts
        if (menuSelected != null) {
            ArrayList<Discount> discounts = mm.getMenuDiscounts(menuSelected);
            for (Discount d : discounts) {
                menuDiscountListModel.addElement(d.toString());
            }
        }

        // Discounts
        ArrayList<Discount> discounts = dm.getDiscounts();
        for (Discount d : discounts) {
            discountListModel.addElement(d.toString());
        }
        
        // History
        ArrayList<Order> history = om.getOrderHistory();
        for (Order o : history) {
            historyListModel.addElement(o.toSimpleString());
        }

        venueNameLabel.setText(name);
        venueTableMaxLabel.setText(Integer.toString(maxTables));
        pendingOrdersLabel.setText(Integer.toString(orders.size()));
        
    }

    private void refreshGUI() {
        menuListModel.clear();
        menuItemListModel.clear();
        itemListModel.clear();
        orderListModel.clear();
        discountListModel.clear();
        menuDiscountListModel.clear();
        historyListModel.clear();

        initLists();
        updateMenuSelected();
    }

    private void updateMenuSelected(){
        if(menuSelected != null){
            menuSelectedLabel.setText(menuSelected.getName());
            menuActiveCheckBox.setSelected(menuSelected.isActive());
        } else {
            menuSelectedLabel.setText("None");
        }
        
        
    }
    
    private boolean isNotNull(boolean menu, boolean menuItem, boolean item, boolean discount, boolean menuDiscount) {
        if ((menu && menuSelected == null) || (menuItem && menuItemSelected == null) || (item && itemSelected == null)
                                           || (discount &&  discountSelected == null) || (menuDiscount && menuDiscountSelected == null)) {
             JOptionPane.showMessageDialog(frame, "Please select valid items.");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        restaurantNameField = new javax.swing.JTextField();
        maxTableField = new javax.swing.JTextField();
        updateRestaurantInfoButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        addMenuButton = new javax.swing.JButton();
        daysActiveComboBox = new javax.swing.JComboBox<>();
        menuActiveCheckBox = new javax.swing.JCheckBox();
        menuNameField = new javax.swing.JTextField();
        updateMenuJsonButton = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        removeMenuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        addItemButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        menuItemList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        removeItemFromMenuButton = new javax.swing.JButton();
        removeItemButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        itemIdField = new javax.swing.JTextField();
        itemNameField = new javax.swing.JTextField();
        itemCostField = new javax.swing.JTextField();
        itemDescriptionField = new javax.swing.JTextField();
        itemAllergensField = new javax.swing.JTextField();
        itemTagsField = new javax.swing.JTextField();
        addItemToMenuButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        menuDiscountList = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        discountList = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        addDiscountToMenuButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        discountFirstTagField = new javax.swing.JTextField();
        removeDiscountFromMenu = new javax.swing.JButton();
        discountSecondTagField = new javax.swing.JTextField();
        addDiscountButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        discountReductionField = new javax.swing.JTextField();
        removeDiscountButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JList<>();
        printOrderButton = new javax.swing.JButton();
        clearOrderButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        historyList = new javax.swing.JList<>();
        printTicketButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        venueNameLabel = new javax.swing.JLabel();
        venueTableMaxLabel = new javax.swing.JLabel();
        pendingOrdersLabel = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        menuSelectedLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
        });

        jLabel14.setText("Info");

        jLabel15.setText("Name");

        jLabel23.setText("Max Tables");

        updateRestaurantInfoButton.setText("Update Info");
        updateRestaurantInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRestaurantInfoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(updateRestaurantInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel23))
                            .addGap(11, 11, 11)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(restaurantNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maxTableField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel14))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(restaurantNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(maxTableField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateRestaurantInfoButton)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venue", jPanel2);

        addMenuButton.setText("+");
        addMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMenuButtonActionPerformed(evt);
            }
        });

        daysActiveComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateMenuJsonButton.setText("Update Menu JSON");
        updateMenuJsonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMenuJsonButtonActionPerformed(evt);
            }
        });

        jLabel19.setText("Name");

        jLabel20.setText("Active");

        jLabel21.setText("Days Active");

        removeMenuButton.setText("-");
        removeMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMenuButtonActionPerformed(evt);
            }
        });

        menuList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        menuList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(menuList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(menuActiveCheckBox))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(daysActiveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(menuNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addMenuButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateMenuJsonButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(removeMenuButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(496, 496, 496))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(menuNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(menuActiveCheckBox)
                            .addComponent(jLabel20))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(daysActiveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMenuButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeMenuButton)
                            .addComponent(updateMenuJsonButton))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menus", jPanel1);

        addItemButton.setText("+");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        itemList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        itemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemListMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(itemList);

        jLabel2.setText("Menu Items");

        menuItemList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        menuItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuItemListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(menuItemList);

        jLabel3.setText("Items");

        removeItemFromMenuButton.setText("-");
        removeItemFromMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemFromMenuButtonActionPerformed(evt);
            }
        });

        removeItemButton.setText("-");
        removeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("ID");

        jLabel9.setText("Name");

        jLabel10.setText("Cost");

        jLabel11.setText("Desc");

        jLabel12.setText("Allergens");

        jLabel13.setText("Tags");

        addItemToMenuButton.setText("+");
        addItemToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemToMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(itemAllergensField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addComponent(itemTagsField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addComponent(itemDescriptionField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addComponent(itemIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(itemCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(itemNameField))))
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeItemFromMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(addItemToMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane3)
                                    .addComponent(removeItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(7, 7, 7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(itemIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(itemCostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(itemDescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(itemAllergensField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(itemTagsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addItemButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(addItemToMenuButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeItemFromMenuButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeItemButton)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Items", jPanel3);

        jLabel6.setText("Discounts");

        menuDiscountList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        menuDiscountList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuDiscountListMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(menuDiscountList);

        discountList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        discountList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                discountListMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(discountList);

        jLabel16.setText("First Tag");

        jLabel17.setText("Second Tag");

        addDiscountToMenuButton.setText("+");
        addDiscountToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDiscountToMenuButtonActionPerformed(evt);
            }
        });

        jLabel18.setText("Reduction");

        removeDiscountFromMenu.setText("-");
        removeDiscountFromMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDiscountFromMenuActionPerformed(evt);
            }
        });

        addDiscountButton.setText("+");
        addDiscountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDiscountButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Menu Discounts");

        removeDiscountButton.setText("-");
        removeDiscountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDiscountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(discountSecondTagField))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(26, 26, 26)
                        .addComponent(discountReductionField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addDiscountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(32, 32, 32)
                        .addComponent(discountFirstTagField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addDiscountToMenuButton)
                            .addComponent(removeDiscountFromMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane5)
                    .addComponent(removeDiscountButton, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addGap(109, 109, 109))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(addDiscountToMenuButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeDiscountFromMenu))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(discountFirstTagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(discountSecondTagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(discountReductionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeDiscountButton)
                    .addComponent(addDiscountButton))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Discounts", jPanel4);

        jLabel24.setText("Current Orders");

        orderList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        orderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                orderListMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(orderList);

        printOrderButton.setText("Print Ticket");
        printOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printOrderButtonActionPerformed(evt);
            }
        });

        clearOrderButton.setText("Clear");
        clearOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearOrderButtonActionPerformed(evt);
            }
        });

        jLabel25.setText("Order History");

        historyList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        historyList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                historyListMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(historyList);

        printTicketButton.setText("Print Ticket");
        printTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTicketButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(printTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(printOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel24))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printOrderButton)
                    .addComponent(clearOrderButton)
                    .addComponent(printTicketButton))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Orders", jPanel5);

        jLabel4.setText("Venue Name:");

        jLabel7.setText("Number of Tables: ");

        jLabel22.setText("Pending Orders:");

        venueNameLabel.setText("venueName");

        venueTableMaxLabel.setText("maxTable");

        pendingOrdersLabel.setText("pendingOrders");

        jLabel26.setText("Menu Selected: ");

        menuSelectedLabel.setText("menuSelected");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pendingOrdersLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(venueTableMaxLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(102, 102, 102)
                                .addComponent(venueNameLabel)))
                        .addGap(173, 173, 173)
                        .addComponent(jLabel26)
                        .addGap(77, 77, 77)
                        .addComponent(menuSelectedLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(venueNameLabel)
                    .addComponent(jLabel26)
                    .addComponent(menuSelectedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(venueTableMaxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(pendingOrdersLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addItemToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemToMenuButtonActionPerformed
        if (isNotNull(true, false, true, false, false)) {
            menuSelected.addItem(itemSelected);
            boolean success = mm.appendItem(menuSelected, itemSelected);
            if(!success){
                JOptionPane.showMessageDialog(frame, "Item is already on menu.");
            }
        }

        refreshGUI();
    }//GEN-LAST:event_addItemToMenuButtonActionPerformed

    private void removeItemFromMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemFromMenuButtonActionPerformed
        if (isNotNull(true, true, false, false, false)) {
            mm.removeItem(menuSelected, menuItemSelected);
            menuSelected.removeItem(menuItemSelected);
        }
        
        refreshGUI();
    }//GEN-LAST:event_removeItemFromMenuButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        mm.removeItem(menuSelected, menuItemSelected);
        menuSelected.removeItem(menuItemSelected);

        im.removeItem(itemSelected);
        refreshGUI();
    }//GEN-LAST:event_removeItemButtonActionPerformed

    private void removeMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMenuButtonActionPerformed
        int i = menuList.getSelectedIndex();
        mm.removeMenu(mm.getMenus().get(i));
        menuListModel.removeElementAt(i);
        //refreshGUI();
    }//GEN-LAST:event_removeMenuButtonActionPerformed

    private void addDiscountToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDiscountToMenuButtonActionPerformed
        mm.addDiscount(menuSelected, discountSelected);
        refreshGUI();
    }//GEN-LAST:event_addDiscountToMenuButtonActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        try {
            ArrayList<Item> items = im.getItems();
            String name = itemNameField.getText();
            
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    JOptionPane.showMessageDialog(frame, "Warning. Duplicate names are allowed. Check item IDs for reference.");
                }
            }
            
            String desc = itemDescriptionField.getText();
            Double cost = 0.00;
            cost = Double.parseDouble(itemCostField.getText());
            String allergensString = itemAllergensField.getText();
            String tagsString = itemTagsField.getText();

            String[] allergens = allergensString.split(";");
            String[] tags = tagsString.split(";");

            int id = 0;

            for (Item item : items) {
                if (item.getId() > id) {
                    id = item.getId();
                }
            }
            id++;

            Item i = new Item(id, name, desc, cost, allergens, tags);
            im.addItem(i);
            itemListModel.addElement(i.getName());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid cost.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_addItemButtonActionPerformed

    private void addMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMenuButtonActionPerformed
        String name = menuNameField.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(frame, "Please input valid menu name.");
        } else {
            boolean active = menuActiveCheckBox.isSelected();
            Menu m = new Menu(name, active);
            mm.addMenu(m);
            menuItemListModel.addElement(m.getName());
        }
        
        refreshGUI();
    }//GEN-LAST:event_addMenuButtonActionPerformed

    private void addDiscountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDiscountButtonActionPerformed
        String firstTag = discountFirstTagField.getText();
        String secondTag = discountSecondTagField.getText();
        Double reduction = Double.parseDouble(discountReductionField.getText());
        Discount d = new Discount(0, firstTag, secondTag, reduction);
        dm.addDiscount(d);
        refreshGUI();
    }//GEN-LAST:event_addDiscountButtonActionPerformed

    private void removeDiscountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDiscountButtonActionPerformed
        dm.removeDiscount(discountSelected);
        refreshGUI();
    }//GEN-LAST:event_removeDiscountButtonActionPerformed

    private void removeDiscountFromMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDiscountFromMenuActionPerformed
        if (menuDiscountSelected != null && menuSelected != null) {
            mm.removeDiscount(menuSelected, menuDiscountSelected);
        } else {
            System.out.println("is NULL");
        }
        refreshGUI();
    }//GEN-LAST:event_removeDiscountFromMenuActionPerformed

    private void clearOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearOrderButtonActionPerformed
        om.clearOrder(orderSelected);
        refreshGUI();
    }//GEN-LAST:event_clearOrderButtonActionPerformed

    private void updateMenuJsonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMenuJsonButtonActionPerformed
        mm.activateMenu(menuSelected);
        mm.updateMenuJSON(menuSelected, menuSelected.getItems());
        refreshGUI();
    }//GEN-LAST:event_updateMenuJsonButtonActionPerformed

    private void updateRestaurantInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRestaurantInfoButtonActionPerformed
        String mtf = maxTableField.getText();
        String vn = restaurantNameField.getText();
        if(!vn.equals("")){
            try{
                Integer.parseInt(mtf);
                mm.updateRestaurantJSON(vn,mtf);

                maxTables = Integer.parseInt(mtf);
                name = vn;
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Please input a number for the max number of tables.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please input a valid name.");
        }
        refreshGUI();
    }//GEN-LAST:event_updateRestaurantInfoButtonActionPerformed

    private void printOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printOrderButtonActionPerformed
        String print = om.printOrder(orderSelected);
        JOptionPane.showMessageDialog(frame, print);
    }//GEN-LAST:event_printOrderButtonActionPerformed

    private void printTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTicketButtonActionPerformed
        String print = om.printOrder(historySelected);
        JOptionPane.showMessageDialog(frame, print);
    }//GEN-LAST:event_printTicketButtonActionPerformed

    private void menuListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListMousePressed
        menuSelected = mm.getMenus().get(menuList.getSelectedIndex());
        menuNameField.setText(menuSelected.getName());
        
        menuItemSelected = null;
        menuDiscountSelected = null;
        
        updateMenuSelected();
    }//GEN-LAST:event_menuListMousePressed

    private void menuItemListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemListMousePressed
        try{
        menuItemSelected = menuSelected.getItems().get(menuItemList.getSelectedIndex());
        } catch (Exception e){
        }
    }//GEN-LAST:event_menuItemListMousePressed

    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed
        refreshGUI();
    }//GEN-LAST:event_jTabbedPane1MousePressed

    private void orderListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderListMousePressed
        try{
            orderSelected = om.getOrders().get(orderList.getSelectedIndex());
        } catch (ArrayIndexOutOfBoundsException e){
        }
        
        refreshGUI();
    }//GEN-LAST:event_orderListMousePressed

    private void historyListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyListMousePressed
        try{
            historySelected = om.getOrderHistory().get(historyList.getSelectedIndex());
        } catch (ArrayIndexOutOfBoundsException e){
        }
        
        refreshGUI();
    }//GEN-LAST:event_historyListMousePressed

    private void itemListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListMousePressed
        itemSelected = im.getItems().get(itemList.getSelectedIndex());
        itemIdField.setText(Integer.toString(itemSelected.getId()));
        itemNameField.setText(itemSelected.getName());
        itemDescriptionField.setText(itemSelected.getDesc());
        DecimalFormat df = new DecimalFormat("#.00");
        itemCostField.setText(df.format(itemSelected.getCost()));
        itemAllergensField.setText(itemSelected.getAllergensCSV());
        itemTagsField.setText(itemSelected.getTagsCSV());
    }//GEN-LAST:event_itemListMousePressed

    private void menuDiscountListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDiscountListMousePressed
        menuDiscountSelected = menuSelected.getDiscounts().get(menuDiscountList.getSelectedIndex());
        refreshGUI();
    }//GEN-LAST:event_menuDiscountListMousePressed

    private void discountListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discountListMousePressed
        discountSelected = dm.getDiscounts().get(discountList.getSelectedIndex());
        discountFirstTagField.setText(discountSelected.getFirstTag());
        discountSecondTagField.setText(discountSelected.getSecondTag());
        discountReductionField.setText(Double.toString(discountSelected.getReduction()));

        refreshGUI();
    }//GEN-LAST:event_discountListMousePressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Control().setVisible(true);
            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDiscountButton;
    private javax.swing.JButton addDiscountToMenuButton;
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton addItemToMenuButton;
    private javax.swing.JButton addMenuButton;
    private javax.swing.JButton clearOrderButton;
    private javax.swing.JComboBox<String> daysActiveComboBox;
    private javax.swing.JTextField discountFirstTagField;
    private javax.swing.JList<String> discountList;
    private javax.swing.JTextField discountReductionField;
    private javax.swing.JTextField discountSecondTagField;
    private javax.swing.JList<String> historyList;
    private javax.swing.JTextField itemAllergensField;
    private javax.swing.JTextField itemCostField;
    private javax.swing.JTextField itemDescriptionField;
    private javax.swing.JTextField itemIdField;
    private javax.swing.JList<String> itemList;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JTextField itemTagsField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField maxTableField;
    private javax.swing.JCheckBox menuActiveCheckBox;
    private javax.swing.JList<String> menuDiscountList;
    private javax.swing.JList<String> menuItemList;
    private javax.swing.JList<String> menuList;
    private javax.swing.JTextField menuNameField;
    private javax.swing.JLabel menuSelectedLabel;
    private javax.swing.JList<String> orderList;
    private javax.swing.JLabel pendingOrdersLabel;
    private javax.swing.JButton printOrderButton;
    private javax.swing.JButton printTicketButton;
    private javax.swing.JButton removeDiscountButton;
    private javax.swing.JButton removeDiscountFromMenu;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JButton removeItemFromMenuButton;
    private javax.swing.JButton removeMenuButton;
    private javax.swing.JTextField restaurantNameField;
    private javax.swing.JButton updateMenuJsonButton;
    private javax.swing.JButton updateRestaurantInfoButton;
    private javax.swing.JLabel venueNameLabel;
    private javax.swing.JLabel venueTableMaxLabel;
    // End of variables declaration//GEN-END:variables
}
