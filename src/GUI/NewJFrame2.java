/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

/**
 *
 * @author ADMIN
 */
public class NewJFrame2 extends javax.swing.JFrame {

    Color defualtColor, ClickColor;

    public NewJFrame2() {
        initComponents();
        setLocationRelativeTo(null);

        defualtColor = new Color(41, 182, 246);
        ClickColor = new Color(144, 202, 249);

        initMoving(NewJFrame2.this);
    }
    private int x, y;

    public void initMoving(JFrame frame) {
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

        });
        menu.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Menu1 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        Menu2 = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        Menu4 = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        Menu3 = new javax.swing.JPanel();
        lb2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lb4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lb5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(41, 182, 246));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu1.setBackground(new java.awt.Color(41, 182, 246));
        Menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Menu1MousePressed(evt);
            }
        });

        lb.setBackground(new java.awt.Color(41, 182, 246));
        lb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bulleted_list_30px.png"))); // NOI18N
        lb.setText("    Menu 1");

        javax.swing.GroupLayout Menu1Layout = new javax.swing.GroupLayout(Menu1);
        Menu1.setLayout(Menu1Layout);
        Menu1Layout.setHorizontalGroup(
            Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        Menu1Layout.setVerticalGroup(
            Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(Menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 220, 50));

        Menu2.setBackground(new java.awt.Color(41, 182, 246));
        Menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Menu2MousePressed(evt);
            }
        });

        lb1.setBackground(new java.awt.Color(41, 182, 246));
        lb1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb1.setForeground(new java.awt.Color(255, 255, 255));
        lb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bulleted_list_30px.png"))); // NOI18N
        lb1.setText("    Menu 1");

        javax.swing.GroupLayout Menu2Layout = new javax.swing.GroupLayout(Menu2);
        Menu2.setLayout(Menu2Layout);
        Menu2Layout.setHorizontalGroup(
            Menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        Menu2Layout.setVerticalGroup(
            Menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(Menu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        Menu4.setBackground(new java.awt.Color(41, 182, 246));
        Menu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Menu4MousePressed(evt);
            }
        });

        lb3.setBackground(new java.awt.Color(41, 182, 246));
        lb3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb3.setForeground(new java.awt.Color(255, 255, 255));
        lb3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bulleted_list_30px.png"))); // NOI18N
        lb3.setText("    Menu 1");

        javax.swing.GroupLayout Menu4Layout = new javax.swing.GroupLayout(Menu4);
        Menu4.setLayout(Menu4Layout);
        Menu4Layout.setHorizontalGroup(
            Menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        Menu4Layout.setVerticalGroup(
            Menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(Menu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, 50));

        Menu3.setBackground(new java.awt.Color(41, 182, 246));
        Menu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Menu3MousePressed(evt);
            }
        });

        lb2.setBackground(new java.awt.Color(41, 182, 246));
        lb2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb2.setForeground(new java.awt.Color(255, 255, 255));
        lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bulleted_list_30px.png"))); // NOI18N
        lb2.setText("    Menu 1");

        javax.swing.GroupLayout Menu3Layout = new javax.swing.GroupLayout(Menu3);
        Menu3.setLayout(Menu3Layout);
        Menu3Layout.setHorizontalGroup(
            Menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        Menu3Layout.setVerticalGroup(
            Menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(Menu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        jPanel9.setBackground(new java.awt.Color(41, 182, 246));

        lb4.setBackground(new java.awt.Color(41, 182, 246));
        lb4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb4.setForeground(new java.awt.Color(255, 255, 255));
        lb4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bulleted_list_30px.png"))); // NOI18N
        lb4.setText("    Menu 1");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, -1));

        jPanel8.setBackground(new java.awt.Color(41, 182, 246));

        lb5.setBackground(new java.awt.Color(41, 182, 246));
        lb5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb5.setForeground(new java.awt.Color(255, 255, 255));
        lb5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_bulleted_list_30px.png"))); // NOI18N
        lb5.setText("    Menu 1");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        menu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 221, 770));

        jPanel3.setBackground(new java.awt.Color(41, 182, 246));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_cancel_48px.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 20, 50, 40));

        menu.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 1480, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 764, Short.MAX_VALUE)
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menu.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 1260, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Menu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu1MousePressed
        Menu1.setBackground(ClickColor);
        Menu2.setBackground(defualtColor);
        Menu3.setBackground(defualtColor);
        Menu4.setBackground(defualtColor);


    }//GEN-LAST:event_Menu1MousePressed

    private void Menu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu2MousePressed
        Menu1.setBackground(defualtColor);
        Menu2.setBackground(ClickColor);
        Menu3.setBackground(defualtColor);
        Menu4.setBackground(defualtColor);
    }//GEN-LAST:event_Menu2MousePressed

    private void Menu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu3MousePressed
        Menu1.setBackground(defualtColor);
        Menu2.setBackground(defualtColor);
        Menu3.setBackground(ClickColor);
        Menu4.setBackground(defualtColor);
    }//GEN-LAST:event_Menu3MousePressed

    private void Menu4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu4MousePressed
        Menu1.setBackground(defualtColor);
        Menu2.setBackground(defualtColor);
        Menu3.setBackground(defualtColor);
        Menu4.setBackground(ClickColor);
    }//GEN-LAST:event_Menu4MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void Menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu1MouseClicked
        NewJInternalFrame menu1 = new NewJInternalFrame();
        jDesktopPane1.add(menu1).setVisible(true);

    }//GEN-LAST:event_Menu1MouseClicked

    private void Menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu2MouseClicked
        NewJInternalFrame1 menu2 = new NewJInternalFrame1();
        jDesktopPane1.add(menu2).setVisible(true);
    }//GEN-LAST:event_Menu2MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu1;
    private javax.swing.JPanel Menu2;
    private javax.swing.JPanel Menu3;
    private javax.swing.JPanel Menu4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables

}
