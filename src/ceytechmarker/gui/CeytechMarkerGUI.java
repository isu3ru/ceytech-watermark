/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker.gui;

import ceytechmarker.enums.DocumentType;
import ceytechmarker.enums.WatermarkText;
import ceytechmarker.enums.WatermarkType;
import ceytechmarker.markers.ExcelWatermark;
import ceytechmarker.markers.PDFWatermark;
import ceytechmarker.markers.WordWatermark;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author isuru
 */
public class CeytechMarkerGUI extends javax.swing.JFrame {

    private String sourceFilePath;
    private String destinationFilePath;
    private DocumentType documentType;
    private WatermarkType watermarkType;

    /**
     * Creates new form CeytechMarkerGUI
     */
    public CeytechMarkerGUI() {
        initComponents();
        populateWatermarkTextSelection();
        populateDocumentTypeSelection();
        populateWatermarkTypeSelection();
    }

    private void populateWatermarkTextSelection() {
        WatermarkText[] texts = WatermarkText.values();
        DefaultComboBoxModel wtcModel = new DefaultComboBoxModel<>(texts);
        watermarkTextCombo.setModel(wtcModel);
    }

    private void populateDocumentTypeSelection() {
        this.documentType = DocumentType.DOCX;
    }

    private void populateWatermarkTypeSelection() {
        this.watermarkType = WatermarkType.HORIZONTAL;
    }

    /**
     * Get the watermark text the user selects from the drop-down menu or a
     * custom text if custom watermark text is chosen
     *
     * @return
     */
    private String getWatermarkText() {
        WatermarkText wmText = (WatermarkText) watermarkTextCombo.getSelectedItem();
        if (wmText.equals(WatermarkText.CUSTOM)) {
            String cwtftxt = customWatermarkTextField.getText();
            if (cwtftxt.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Water mark text is required.", "Empty Watermark", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return customWatermarkTextField.getText().trim();
        }

        return wmText.getText();
    }

    private void addDocxDocumentWatermark(File sourceFile, File destinationFile, String watermarkText) {
        WordWatermark wordWatermark = new WordWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wordWatermark.addWatermark(sourceFile, destinationFile, watermarkType, watermarkText);
                    JOptionPane.showMessageDialog(CeytechMarkerGUI.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void removeDocxDocumentWatermark(File sourceFile, File destinationFile) {
        System.out.println("remove docx watermark");
        WordWatermark wordWatermark = new WordWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wordWatermark.removeWatermark(sourceFile, destinationFile);
                    JOptionPane.showMessageDialog(CeytechMarkerGUI.this, "Watermark removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void addXlsxDocumentWatermark(File sourceFile, File destinationFile, String watermarkText) {
        ExcelWatermark excelWatermark = new ExcelWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    excelWatermark.addWatermark(sourceFile, destinationFile, watermarkType, watermarkText);

                    JOptionPane.showMessageDialog(CeytechMarkerGUI.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void removeXlsxDocumentWatermark(File sourceFile, File destinationFile) {
        System.out.println("remove xlsx watermark");
        ExcelWatermark excelWatermark = new ExcelWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    excelWatermark.removeWatermark(sourceFile, destinationFile);
                    JOptionPane.showMessageDialog(CeytechMarkerGUI.this, "Watermark removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void addPDFDocumentWatermark(File sourceFile, File destinationFile, String watermarkText) {
        PDFWatermark pdfw = new PDFWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pdfw.addWatermark(sourceFile, destinationFile, watermarkType, watermarkText);
                    JOptionPane.showMessageDialog(CeytechMarkerGUI.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void removePDFDocumentWatermark(File sourceFile, File destinationFile)  {
        PDFWatermark pdfw = new PDFWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pdfw.removeWatermark(sourceFile, destinationFile);
                    JOptionPane.showMessageDialog(CeytechMarkerGUI.this, "Watermark removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        documentTypeGroup = new javax.swing.ButtonGroup();
        watermarkTypeGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        selectedSourceFileName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        watermarkTextCombo = new javax.swing.JComboBox<>();
        customWatermarkTextField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        addWatermarkDestinationFileName = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        removeWatermarkDestinationFileName = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DocumentWatermarker");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/watermark_app_icon_overlayed.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Document Watermarker");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Ceytech Document Watermarker - Version 1.0");

        jLabel6.setText("Select File : ");

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        selectedSourceFileName.setText("No File Selected");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedSourceFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(selectedSourceFileName))
        );

        jLabel7.setText("Copyrights Reserved 2020 - Ceytech System Solutions");

        jToggleButton5.setBackground(new java.awt.Color(36, 124, 215));
        documentTypeGroup.add(jToggleButton5);
        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-word-100.png"))); // NOI18N
        jToggleButton5.setMnemonic('d');
        jToggleButton5.setSelected(true);
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton5ItemStateChanged(evt);
            }
        });

        documentTypeGroup.add(jToggleButton6);
        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-xls-100.png"))); // NOI18N
        jToggleButton6.setMnemonic('x');
        jToggleButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton6ItemStateChanged(evt);
            }
        });

        documentTypeGroup.add(jToggleButton7);
        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-powerpoint-100.png"))); // NOI18N
        jToggleButton7.setMnemonic('t');
        jToggleButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton7ItemStateChanged(evt);
            }
        });

        documentTypeGroup.add(jToggleButton8);
        jToggleButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-pdf-100.png"))); // NOI18N
        jToggleButton8.setMnemonic('p');
        jToggleButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton8ItemStateChanged(evt);
            }
        });

        watermarkTypeGroup.add(jToggleButton1);
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/horizontal-100.png"))); // NOI18N
        jToggleButton1.setMnemonic('h');
        jToggleButton1.setSelected(true);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });

        watermarkTypeGroup.add(jToggleButton2);
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/vertical-100.png"))); // NOI18N
        jToggleButton2.setMnemonic('v');
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton2ItemStateChanged(evt);
            }
        });

        watermarkTypeGroup.add(jToggleButton3);
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_left-100.png"))); // NOI18N
        jToggleButton3.setMnemonic('l');
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton3ItemStateChanged(evt);
            }
        });

        watermarkTypeGroup.add(jToggleButton4);
        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_right-100.png"))); // NOI18N
        jToggleButton4.setMnemonic('r');
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton4ItemStateChanged(evt);
            }
        });

        jLabel4.setText("Watermark Text : ");

        watermarkTextCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                watermarkTextComboItemStateChanged(evt);
            }
        });

        customWatermarkTextField.setToolTipText("<html>Enter your custom watermark text here...<br />\n<strong>NOTE: Max 22 characters only.</strong>");

        jButton3.setText("Add Watermark");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("Save Watermarked File As : ");

        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        addWatermarkDestinationFileName.setText("No File Selected");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addWatermarkDestinationFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4)
                .addComponent(addWatermarkDestinationFileName))
        );

        jLabel10.setText("<html><strong>NOTE: Max custom text characters are 22.</strong>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(watermarkTextCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(customWatermarkTextField))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2)
                    .addComponent(jToggleButton3)
                    .addComponent(jToggleButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(watermarkTextCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customWatermarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create Watermark", jPanel3);

        jPanel5.setBackground(new java.awt.Color(69, 126, 226));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 42, 255)));

        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("<html><strong>IMPORTANT!</strong> <br />Only the watermark added by this application can be removed. If additional headers and footers are present in the document, they can be removed in the process of removal of the watermark. <br />Watermarks will not be identified and removed if the document is modified externally after watermarking.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Remove Watermark");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Save Removed File As : ");

        jButton5.setText("Browse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        removeWatermarkDestinationFileName.setText("No File Selected");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeWatermarkDestinationFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton5)
                .addComponent(removeWatermarkDestinationFileName))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Remove Watermark", jPanel4);

        jMenu4.setText("About");

        jMenuItem3.setText("Keyboard Shortcuts");
        jMenu4.add(jMenuItem3);

        jMenuItem6.setText("Request Support");
        jMenu4.add(jMenuItem6);
        jMenu4.add(jSeparator2);

        jMenuItem5.setText("License Details");
        jMenu4.add(jMenuItem5);

        jMenuItem4.setText("About Document Watermarker");
        jMenu4.add(jMenuItem4);

        jMenuItem7.setText("About Ceytech System Solutions");
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jSeparator1)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // ask for the open file
        FileDialog fileDialog = new FileDialog(this, "Open " + this.documentType.getTypeName() + " File");
        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (dir.getName().startsWith(".")) {
                    return false;
                } else if (name.endsWith(documentType.getExtension())) {
                    return true;
                }
                return false;
            }
        });
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setModal(true);
        fileDialog.setVisible(true);
        String fileName = fileDialog.getFile();
        if (fileName != null) {
            String directoryName = fileDialog.getDirectory();
            String filePath = directoryName.concat(fileName);

            // check if file exists
            if (new File(filePath).exists()) {
                this.sourceFilePath = filePath;
                selectedSourceFileName.setText(fileName);
            }

            fileDialog.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // call add watermark class for the selected type
        try {
            File sourceFile = new File(sourceFilePath);
            File destinationFile = new File(destinationFilePath);
            String watermarkText = getWatermarkText();

            switch (documentType) {
                case DOCX:
                    addDocxDocumentWatermark(sourceFile, destinationFile, watermarkText);
                    break;
                case XLSX:
                    addXlsxDocumentWatermark(sourceFile, destinationFile, watermarkText);
                    break;
                case PDF:
                    addPDFDocumentWatermark(sourceFile, destinationFile, watermarkText);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void watermarkTextComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_watermarkTextComboItemStateChanged
        // ISURU: fire the event only when the item is selected. otherwise triggers same event at SELECTED and DESELECTED states
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            
        }
    }//GEN-LAST:event_watermarkTextComboItemStateChanged

    private void jToggleButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton5ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.documentType = DocumentType.DOCX;
        }
    }//GEN-LAST:event_jToggleButton5ItemStateChanged

    private void jToggleButton6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton6ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.documentType = DocumentType.XLSX;
        }
    }//GEN-LAST:event_jToggleButton6ItemStateChanged

    private void jToggleButton7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton7ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.documentType = DocumentType.PPTX;
        }
    }//GEN-LAST:event_jToggleButton7ItemStateChanged

    private void jToggleButton8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton8ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.documentType = DocumentType.PDF;
        }
    }//GEN-LAST:event_jToggleButton8ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // select add watermark destination file
        FileDialog fileDialog = new FileDialog(this, "Select Save As File Name");
        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (dir.getName().startsWith(".")) {
                    return false;
                } else if (name.endsWith(documentType.getExtension())) {
                    return true;
                }
                return false;
            }
        });
        fileDialog.setMode(FileDialog.SAVE);
        fileDialog.setModal(true);
        fileDialog.setVisible(true);
        String fileName = fileDialog.getFile();
        if (fileName != null) {
            String directoryName = fileDialog.getDirectory();

            if (!fileName.toLowerCase().endsWith(this.documentType.getExtension())) {
                fileName = fileName.concat(this.documentType.getExtension());
            }

            String filePath = directoryName.concat(fileName);

            this.destinationFilePath = filePath;
            this.addWatermarkDestinationFileName.setText(fileName);

            fileDialog.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // remove watermark destination file name
        FileDialog fileDialog = new FileDialog(this, "Select Save As File Name");
        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (dir.getName().startsWith(".")) {
                    return false;
                } else if (name.endsWith(documentType.getExtension())) {
                    return true;
                }
                return false;
            }
        });
        fileDialog.setMode(FileDialog.SAVE);
        fileDialog.setModal(true);
        fileDialog.setVisible(true);
        String fileName = fileDialog.getFile();
        if (fileName != null) {
            String directoryName = fileDialog.getDirectory();
            String filePath = directoryName.concat(fileName);

            if (!fileName.toLowerCase().endsWith(this.documentType.getExtension())) {
                fileName = fileName.concat(this.documentType.getExtension());
            }

            // check if file exists
            this.destinationFilePath = filePath;
            this.removeWatermarkDestinationFileName.setText(fileName);

            fileDialog.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // call remove watermark class for the selected document type
        try {
            File sourceFile = new File(sourceFilePath);
            File destinationFile = new File(destinationFilePath);

            switch (documentType) {
                case DOCX:
                    removeDocxDocumentWatermark(sourceFile, destinationFile);
                    break;
                case XLSX:
                    removeXlsxDocumentWatermark(sourceFile, destinationFile);
                    break;
                case PDF:
                    removePDFDocumentWatermark(sourceFile, destinationFile);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.HORIZONTAL;
        }
    }//GEN-LAST:event_jToggleButton1ItemStateChanged

    private void jToggleButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton2ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.VERTICAL;
            System.out.println(this.watermarkType);
        }
    }//GEN-LAST:event_jToggleButton2ItemStateChanged

    private void jToggleButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton3ItemStateChanged
        // diagonal left
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_LEFT;
        }
    }//GEN-LAST:event_jToggleButton3ItemStateChanged

    private void jToggleButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton4ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_RIGHT;
        }
    }//GEN-LAST:event_jToggleButton4ItemStateChanged

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CeytechMarkerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CeytechMarkerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CeytechMarkerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CeytechMarkerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CeytechMarkerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addWatermarkDestinationFileName;
    private javax.swing.JTextField customWatermarkTextField;
    private javax.swing.ButtonGroup documentTypeGroup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JLabel removeWatermarkDestinationFileName;
    private javax.swing.JLabel selectedSourceFileName;
    private javax.swing.JComboBox<String> watermarkTextCombo;
    private javax.swing.ButtonGroup watermarkTypeGroup;
    // End of variables declaration//GEN-END:variables
}
