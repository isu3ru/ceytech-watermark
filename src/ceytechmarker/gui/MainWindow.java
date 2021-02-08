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
import ceytechmarker.markers.PowerPointWatermark;
import ceytechmarker.markers.WordWatermark;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author isuru
 */
public class MainWindow extends javax.swing.JFrame {

    private File sourceFile;
    private File destinationFile;
    private WatermarkType watermarkType;
    private DocumentType documentType;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        initCardContainer();
        populateWatermarkTextSelection();
    }

    /**
     * Populate the watermark texts selection combo
     */
    private void populateWatermarkTextSelection() {
        WatermarkText[] texts = WatermarkText.values();
        DefaultComboBoxModel wtcModel = new DefaultComboBoxModel<>(texts);
        wordWatermarkTextCombo.setModel(wtcModel);
        wordCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        excelWatermarkTextCombo.setModel(wtcModel);
        excelCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        pdfWatermarkTextCombo.setModel(wtcModel);
        pdfCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        powerpointWatermarkTextCombo.setModel(wtcModel);
        powerpointCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
    }

    /**
     * Init UI cards container - Start from the dashboard card
     */
    private void initCardContainer() {
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_dashboard");
    }

    private String getWatermarkText() {
        String watermarkText = null;
        switch (this.documentType) {
            case DOCX: {
                WatermarkText wmText = (WatermarkText) wordWatermarkTextCombo.getSelectedItem();
                if (wmText.equals(WatermarkText.CUSTOM)) {
                    watermarkText = wordCustomWatermarkText.getText();
                    if (watermarkText.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Water mark text is required.", "Empty Watermark", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    watermarkText = wordCustomWatermarkText.getText().trim();
                } else {
                    watermarkText = ((WatermarkText) wordWatermarkTextCombo.getSelectedItem()).getText();
                }
            }
            break;
            case XLSX: {
                WatermarkText wmText = (WatermarkText) excelWatermarkTextCombo.getSelectedItem();
                if (wmText.equals(WatermarkText.CUSTOM)) {
                    watermarkText = excelCustomWatermarkText.getText();
                    if (watermarkText.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Water mark text is required.", "Empty Watermark", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    watermarkText = excelCustomWatermarkText.getText().trim();
                } else {
                    watermarkText = ((WatermarkText) excelWatermarkTextCombo.getSelectedItem()).getText();
                }
            }
            break;
            case PDF: {
                WatermarkText wmText = (WatermarkText) pdfWatermarkTextCombo.getSelectedItem();
                if (wmText.equals(WatermarkText.CUSTOM)) {
                    watermarkText = pdfCustomWatermarkText.getText();
                    if (watermarkText.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Water mark text is required.", "Empty Watermark", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    watermarkText = pdfCustomWatermarkText.getText().trim();
                } else {
                    watermarkText = ((WatermarkText) pdfWatermarkTextCombo.getSelectedItem()).getText();
                }
            }
            break;
            case PPTX: {
                WatermarkText wmText = (WatermarkText) powerpointWatermarkTextCombo.getSelectedItem();
                if (wmText.equals(WatermarkText.CUSTOM)) {
                    watermarkText = powerpointCustomWatermarkText.getText();
                    if (watermarkText.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Water mark text is required.", "Empty Watermark", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    watermarkText = powerpointCustomWatermarkText.getText().trim();
                } else {
                    watermarkText = ((WatermarkText) powerpointWatermarkTextCombo.getSelectedItem()).getText();
                }
            }
            break;
        }

        return watermarkText;
    }

    private void addDocxDocumentWatermark(File sourceFile, File destinationFile, String watermarkText) {
        WordWatermark wordWatermark = new WordWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wordWatermark.addWatermark(sourceFile, destinationFile, watermarkType, watermarkText);
                    JOptionPane.showMessageDialog(MainWindow.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
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
                    JOptionPane.showMessageDialog(MainWindow.this, "Watermark removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
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

                    JOptionPane.showMessageDialog(MainWindow.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
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
                    JOptionPane.showMessageDialog(MainWindow.this, "Watermark removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
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
                    JOptionPane.showMessageDialog(MainWindow.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void addPowerpointDocumentWatermark(File sourceFile, File destinationFile, String watermarkText) {
        PowerPointWatermark pptw = new PowerPointWatermark();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pptw.addWatermark(sourceFile, destinationFile, watermarkType, watermarkText);
                    JOptionPane.showMessageDialog(MainWindow.this, "Watermark added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(destinationFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.run();
    }

    private void resetWordPanel() {
        // set UI components to default values
        word_selected_file_name.setText("No file selected");
        word_style_horizontal.setSelected(true);
        wordCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        populateWatermarkTextSelection();
        // set instance vars
        this.sourceFile = null;
        this.destinationFile = null;
        this.watermarkType = WatermarkType.HORIZONTAL;
        this.documentType = DocumentType.DOCX;
    }

    private void resetExcelPanel() {
        // set UI components to default values
        excel_selected_file_name.setText("No file selected");
        excel_style_horizontal.setSelected(true);
        excelCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        populateWatermarkTextSelection();
        // set instance vars
        this.sourceFile = null;
        this.destinationFile = null;
        this.watermarkType = WatermarkType.HORIZONTAL;
        this.documentType = DocumentType.XLSX;
    }

    private void resetPdfPanel() {
        // set UI components to default values
        pdf_selected_file_name.setText("No file selected");
        pdf_style_horizontal.setSelected(true);
        pdfCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        populateWatermarkTextSelection();
        // set instance vars
        this.sourceFile = null;
        this.destinationFile = null;
        this.watermarkType = WatermarkType.HORIZONTAL;
        this.documentType = DocumentType.PDF;
    }

    private void resetPowerPointPanel() {
        // set UI components to default values
        powerpoint_selected_file_name.setText("No file selected");
        powerpoint_style_horizontal.setSelected(true);
        powerpointCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
        populateWatermarkTextSelection();
        // set instance vars
        this.sourceFile = null;
        this.destinationFile = null;
        this.watermarkType = WatermarkType.HORIZONTAL;
        this.documentType = DocumentType.PPTX;
    }

    private File fixExtensionForFile(File file, DocumentType documentType) {
        String absolutePath = file.getAbsolutePath();
        String fileName = file.getName();
        if (fileName.trim().toLowerCase().endsWith(documentType.getExtension())) {
            return file;
        }

        return new File(absolutePath.concat(documentType.getExtension()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        word_rotations = new javax.swing.ButtonGroup();
        excel_rotations = new javax.swing.ButtonGroup();
        powerpoint_rotations = new javax.swing.ButtonGroup();
        pdf_rotations = new javax.swing.ButtonGroup();
        card_holder = new javax.swing.JPanel();
        panel_dashboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panel_word = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        word_select_file = new javax.swing.JButton();
        word_selected_file_name = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        word_style_horizontal = new javax.swing.JToggleButton();
        word_style_vertical = new javax.swing.JToggleButton();
        word_style_diagonal_left = new javax.swing.JToggleButton();
        word_style_diagonal_right = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        wordWatermarkTextCombo = new javax.swing.JComboBox<>();
        wordCustomWatermarkText = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        panel_excel = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        excel_select_file = new javax.swing.JButton();
        excel_selected_file_name = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        excel_style_horizontal = new javax.swing.JToggleButton();
        excel_style_vertical = new javax.swing.JToggleButton();
        excel_style_diagonal_left = new javax.swing.JToggleButton();
        excel_style_diagonal_right = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        excelWatermarkTextCombo = new javax.swing.JComboBox<>();
        excelCustomWatermarkText = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        panel_pdf = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        pdf_select_file = new javax.swing.JButton();
        pdf_selected_file_name = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pdf_style_horizontal = new javax.swing.JToggleButton();
        pdf_style_vertical = new javax.swing.JToggleButton();
        pdf_style_diagonal_left = new javax.swing.JToggleButton();
        pdf_style_diagonal_right = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        pdfWatermarkTextCombo = new javax.swing.JComboBox<>();
        pdfCustomWatermarkText = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        panel_powerpoint = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        powerpoint_select_file = new javax.swing.JButton();
        powerpoint_selected_file_name = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        powerpoint_style_horizontal = new javax.swing.JToggleButton();
        powerpoint_style_vertical = new javax.swing.JToggleButton();
        powerpoint_style_diagonal_left = new javax.swing.JToggleButton();
        powerpoint_style_diagonal_right = new javax.swing.JToggleButton();
        jLabel29 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        powerpointWatermarkTextCombo = new javax.swing.JComboBox<>();
        powerpointCustomWatermarkText = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        titlebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Document Watermark System");
        setResizable(false);

        card_holder.setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel2.setText("Select your document type");

        jButton1.setBackground(new java.awt.Color(28, 171, 226));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-word-100.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(142, 41, 115));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-xls-100.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(131, 14, 28));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-powerpoint-100.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(12, 90, 1));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-pdf-100.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Microsoft Word");

        jLabel19.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Microsoft Excel");

        jLabel23.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Microsoft PowerPoint");

        jLabel24.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Portable Documents (PDF)");

        javax.swing.GroupLayout panel_dashboardLayout = new javax.swing.GroupLayout(panel_dashboard);
        panel_dashboard.setLayout(panel_dashboardLayout);
        panel_dashboardLayout.setHorizontalGroup(
            panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashboardLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panel_dashboardLayout.createSequentialGroup()
                        .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        panel_dashboardLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jLabel17, jLabel19, jLabel23, jLabel24});

        panel_dashboardLayout.setVerticalGroup(
            panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashboardLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        panel_dashboardLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        card_holder.add(panel_dashboard, "panel_dashboard");

        jButton5.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-back-to-100-32.png"))); // NOI18N
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(253, 252, 202));

        jLabel3.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-word-100-32.png"))); // NOI18N
        jLabel3.setText("Select The File");

        word_select_file.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        word_select_file.setText("Browse");
        word_select_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                word_select_fileActionPerformed(evt);
            }
        });

        word_selected_file_name.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        word_selected_file_name.setForeground(new java.awt.Color(1, 1, 1));
        word_selected_file_name.setText("No file selected");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(word_select_file)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(word_selected_file_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(word_select_file)
                    .addComponent(word_selected_file_name))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(223, 253, 202));

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-plus-100-32.png"))); // NOI18N
        jLabel5.setText("Add Watermark");

        word_rotations.add(word_style_horizontal);
        word_style_horizontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/horizontal-100.png"))); // NOI18N
        word_style_horizontal.setSelected(true);
        word_style_horizontal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                word_style_horizontalItemStateChanged(evt);
            }
        });

        word_rotations.add(word_style_vertical);
        word_style_vertical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/vertical-100.png"))); // NOI18N
        word_style_vertical.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                word_style_verticalItemStateChanged(evt);
            }
        });

        word_rotations.add(word_style_diagonal_left);
        word_style_diagonal_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_left-100.png"))); // NOI18N
        word_style_diagonal_left.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                word_style_diagonal_leftItemStateChanged(evt);
            }
        });

        word_rotations.add(word_style_diagonal_right);
        word_style_diagonal_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_right-100.png"))); // NOI18N
        word_style_diagonal_right.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                word_style_diagonal_rightItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setText("1. Select Style");

        jButton7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-as-100-32.png"))); // NOI18N
        jButton7.setText("Save As");
        jButton7.setToolTipText("Save the watermarked version as a separate version as desired.");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        wordWatermarkTextCombo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        wordWatermarkTextCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        wordWatermarkTextCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wordWatermarkTextComboItemStateChanged(evt);
            }
        });

        wordCustomWatermarkText.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        wordCustomWatermarkText.setText("Type in your Custom Watermark Text here..");

        jLabel20.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(1, 1, 1));
        jLabel20.setText("2. Select Watermark Text");

        jButton13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-close-100-32.png"))); // NOI18N
        jButton13.setText("Save");
        jButton13.setToolTipText("Replace the original file with the watermarked version.");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(word_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(word_style_vertical)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(word_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(word_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(wordWatermarkTextCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wordCustomWatermarkText, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(wordWatermarkTextCombo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wordCustomWatermarkText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(word_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(word_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(word_style_vertical, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(word_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton7, wordCustomWatermarkText, wordWatermarkTextCombo});

        jPanel3.setBackground(new java.awt.Color(253, 202, 208));

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 1, 1));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-minus-100-32.png"))); // NOI18N
        jLabel7.setText("Remove Watermark");

        jButton8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-as-100-32.png"))); // NOI18N
        jButton8.setText("Save As");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-close-100-32.png"))); // NOI18N
        jButton14.setText("Save");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton8)
                    .addComponent(jButton14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-word-100-32.png"))); // NOI18N
        jLabel14.setText("Microsoft Word (DOCX)");

        javax.swing.GroupLayout panel_wordLayout = new javax.swing.GroupLayout(panel_word);
        panel_word.setLayout(panel_wordLayout);
        panel_wordLayout.setHorizontalGroup(
            panel_wordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_wordLayout.createSequentialGroup()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_wordLayout.setVerticalGroup(
            panel_wordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_wordLayout.createSequentialGroup()
                .addGroup(panel_wordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        card_holder.add(panel_word, "panel_word");

        jButton6.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-back-to-100-32.png"))); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(253, 252, 202));

        jLabel4.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-xls-100-32.png"))); // NOI18N
        jLabel4.setText("Select The File");

        excel_select_file.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        excel_select_file.setText("Browse");
        excel_select_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excel_select_fileActionPerformed(evt);
            }
        });

        excel_selected_file_name.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        excel_selected_file_name.setForeground(new java.awt.Color(1, 1, 1));
        excel_selected_file_name.setText("No file selected");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(excel_select_file)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(excel_selected_file_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(excel_select_file)
                    .addComponent(excel_selected_file_name))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(223, 253, 202));

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(1, 1, 1));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-plus-100-32.png"))); // NOI18N
        jLabel8.setText("Add Watermark");

        excel_rotations.add(excel_style_horizontal);
        excel_style_horizontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/horizontal-100.png"))); // NOI18N
        excel_style_horizontal.setSelected(true);
        excel_style_horizontal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                excel_style_horizontalItemStateChanged(evt);
            }
        });

        excel_rotations.add(excel_style_vertical);
        excel_style_vertical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/vertical-100.png"))); // NOI18N
        excel_style_vertical.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                excel_style_verticalItemStateChanged(evt);
            }
        });

        excel_rotations.add(excel_style_diagonal_left);
        excel_style_diagonal_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_left-100.png"))); // NOI18N
        excel_style_diagonal_left.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                excel_style_diagonal_leftItemStateChanged(evt);
            }
        });

        excel_rotations.add(excel_style_diagonal_right);
        excel_style_diagonal_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_right-100.png"))); // NOI18N
        excel_style_diagonal_right.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                excel_style_diagonal_rightItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(1, 1, 1));
        jLabel9.setText("1. Select Style");

        jButton9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-as-100-32.png"))); // NOI18N
        jButton9.setText("Save As");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        excelWatermarkTextCombo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        excelWatermarkTextCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        excelWatermarkTextCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                excelWatermarkTextComboItemStateChanged(evt);
            }
        });

        excelCustomWatermarkText.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        excelCustomWatermarkText.setText("Type in your Custom Watermark Text here..");

        jLabel21.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(1, 1, 1));
        jLabel21.setText("2. Select Watermark Text");

        jButton15.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-close-100-32.png"))); // NOI18N
        jButton15.setText("Save");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(excel_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(excel_style_vertical)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(excel_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(excel_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(excelWatermarkTextCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(excelCustomWatermarkText, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(excelWatermarkTextCombo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excelCustomWatermarkText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(excel_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(excel_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(excel_style_vertical, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(excel_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(253, 202, 208));

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(1, 1, 1));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-minus-100-32.png"))); // NOI18N
        jLabel10.setText("Remove Watermark");

        jButton10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-as-100-32.png"))); // NOI18N
        jButton10.setText("Save As");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-close-100-32.png"))); // NOI18N
        jButton18.setText("Save");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton10)
                    .addComponent(jButton18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-xls-100-32.png"))); // NOI18N
        jLabel16.setText("Microsoft Excel (XLSX)");

        javax.swing.GroupLayout panel_excelLayout = new javax.swing.GroupLayout(panel_excel);
        panel_excel.setLayout(panel_excelLayout);
        panel_excelLayout.setHorizontalGroup(
            panel_excelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_excelLayout.createSequentialGroup()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_excelLayout.setVerticalGroup(
            panel_excelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_excelLayout.createSequentialGroup()
                .addGroup(panel_excelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        card_holder.add(panel_excel, "panel_excel");

        jButton11.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-back-to-100-32.png"))); // NOI18N
        jButton11.setText("Back");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(253, 252, 202));

        jLabel11.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(1, 1, 1));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-pdf-100-32.png"))); // NOI18N
        jLabel11.setText("Select The File");

        pdf_select_file.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        pdf_select_file.setText("Browse");
        pdf_select_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf_select_fileActionPerformed(evt);
            }
        });

        pdf_selected_file_name.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        pdf_selected_file_name.setForeground(new java.awt.Color(1, 1, 1));
        pdf_selected_file_name.setText("No file selected");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pdf_select_file)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pdf_selected_file_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(pdf_select_file)
                    .addComponent(pdf_selected_file_name))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(223, 253, 202));

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(1, 1, 1));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-plus-100-32.png"))); // NOI18N
        jLabel12.setText("Add Watermark");

        pdf_rotations.add(pdf_style_horizontal);
        pdf_style_horizontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/horizontal-100.png"))); // NOI18N
        pdf_style_horizontal.setSelected(true);
        pdf_style_horizontal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pdf_style_horizontalItemStateChanged(evt);
            }
        });

        pdf_rotations.add(pdf_style_vertical);
        pdf_style_vertical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/vertical-100.png"))); // NOI18N
        pdf_style_vertical.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pdf_style_verticalItemStateChanged(evt);
            }
        });

        pdf_rotations.add(pdf_style_diagonal_left);
        pdf_style_diagonal_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_left-100.png"))); // NOI18N
        pdf_style_diagonal_left.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pdf_style_diagonal_leftItemStateChanged(evt);
            }
        });

        pdf_rotations.add(pdf_style_diagonal_right);
        pdf_style_diagonal_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_right-100.png"))); // NOI18N
        pdf_style_diagonal_right.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pdf_style_diagonal_rightItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(1, 1, 1));
        jLabel13.setText("1. Select Style");

        jButton12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-as-100-32.png"))); // NOI18N
        jButton12.setText("Save As");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        pdfWatermarkTextCombo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        pdfWatermarkTextCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pdfWatermarkTextCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pdfWatermarkTextComboItemStateChanged(evt);
            }
        });

        pdfCustomWatermarkText.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        pdfCustomWatermarkText.setText("Type in your Custom Watermark Text here..");

        jLabel22.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(1, 1, 1));
        jLabel22.setText("1. Select Watermark Text");

        jButton19.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-close-100-32.png"))); // NOI18N
        jButton19.setText("Save");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(pdf_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pdf_style_vertical)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pdf_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pdf_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(pdfWatermarkTextCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pdfCustomWatermarkText, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(pdfWatermarkTextCombo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pdfCustomWatermarkText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pdf_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pdf_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pdf_style_vertical, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pdf_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-pdf-100-32.png"))); // NOI18N
        jLabel18.setText("Portable Document Format (PDF)");

        javax.swing.GroupLayout panel_pdfLayout = new javax.swing.GroupLayout(panel_pdf);
        panel_pdf.setLayout(panel_pdfLayout);
        panel_pdfLayout.setHorizontalGroup(
            panel_pdfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_pdfLayout.createSequentialGroup()
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18))
        );
        panel_pdfLayout.setVerticalGroup(
            panel_pdfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_pdfLayout.createSequentialGroup()
                .addGroup(panel_pdfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        card_holder.add(panel_pdf, "panel_pdf");

        jButton16.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-back-to-100-32.png"))); // NOI18N
        jButton16.setText("Back");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(253, 252, 202));

        jLabel27.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(1, 1, 1));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-powerpoint-100-32.png"))); // NOI18N
        jLabel27.setText("Select The File");

        powerpoint_select_file.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        powerpoint_select_file.setText("Browse");
        powerpoint_select_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerpoint_select_fileActionPerformed(evt);
            }
        });

        powerpoint_selected_file_name.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        powerpoint_selected_file_name.setForeground(new java.awt.Color(1, 1, 1));
        powerpoint_selected_file_name.setText("No file selected");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(powerpoint_select_file)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(powerpoint_selected_file_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(powerpoint_select_file)
                    .addComponent(powerpoint_selected_file_name))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(223, 253, 202));

        jLabel28.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(1, 1, 1));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-plus-100-32.png"))); // NOI18N
        jLabel28.setText("Add Watermark");

        powerpoint_rotations.add(powerpoint_style_horizontal);
        powerpoint_style_horizontal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/horizontal-100.png"))); // NOI18N
        powerpoint_style_horizontal.setSelected(true);
        powerpoint_style_horizontal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                powerpoint_style_horizontalItemStateChanged(evt);
            }
        });

        powerpoint_rotations.add(powerpoint_style_vertical);
        powerpoint_style_vertical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/vertical-100.png"))); // NOI18N
        powerpoint_style_vertical.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                powerpoint_style_verticalItemStateChanged(evt);
            }
        });

        powerpoint_rotations.add(powerpoint_style_diagonal_left);
        powerpoint_style_diagonal_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_left-100.png"))); // NOI18N
        powerpoint_style_diagonal_left.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                powerpoint_style_diagonal_leftItemStateChanged(evt);
            }
        });

        powerpoint_rotations.add(powerpoint_style_diagonal_right);
        powerpoint_style_diagonal_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/diagonal_right-100.png"))); // NOI18N
        powerpoint_style_diagonal_right.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                powerpoint_style_diagonal_rightItemStateChanged(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(1, 1, 1));
        jLabel29.setText("1. Select Style");

        jButton17.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/icons8-save-close-100-32.png"))); // NOI18N
        jButton17.setText("Save Watermarked File As");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        powerpointWatermarkTextCombo.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        powerpointWatermarkTextCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        powerpointWatermarkTextCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                powerpointWatermarkTextComboItemStateChanged(evt);
            }
        });

        powerpointCustomWatermarkText.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        powerpointCustomWatermarkText.setText("Type in your Custom Watermark Text here..");

        jLabel30.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(1, 1, 1));
        jLabel30.setText("2. Select Watermark Text");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(powerpoint_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(powerpoint_style_vertical)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(powerpoint_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(powerpoint_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(powerpointWatermarkTextCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(powerpointCustomWatermarkText, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(powerpointWatermarkTextCombo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(powerpointCustomWatermarkText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(powerpoint_style_diagonal_right, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(powerpoint_style_diagonal_left, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(powerpoint_style_vertical, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(powerpoint_style_horizontal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/doc_icons/icons8-microsoft-powerpoint-100-32.png"))); // NOI18N
        jLabel32.setText("Microsoft PowerPoint (PPTX)");

        javax.swing.GroupLayout panel_powerpointLayout = new javax.swing.GroupLayout(panel_powerpoint);
        panel_powerpoint.setLayout(panel_powerpointLayout);
        panel_powerpointLayout.setHorizontalGroup(
            panel_powerpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_powerpointLayout.createSequentialGroup()
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32))
        );
        panel_powerpointLayout.setVerticalGroup(
            panel_powerpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_powerpointLayout.createSequentialGroup()
                .addGroup(panel_powerpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        card_holder.add(panel_powerpoint, "panel_powerpoint");

        titlebar.setBackground(new java.awt.Color(53, 56, 58));

        jLabel1.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ceytechmarker/assets/public_bank_logo-400x64.png"))); // NOI18N
        jLabel1.setText("Document Watermarking System");

        javax.swing.GroupLayout titlebarLayout = new javax.swing.GroupLayout(titlebar);
        titlebar.setLayout(titlebarLayout);
        titlebarLayout.setHorizontalGroup(
            titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlebarLayout.setVerticalGroup(
            titlebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel15.setText("<html> &copy; 2020 - Developed by Ceytech Systems Solution");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card_holder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(card_holder, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_word");
        resetWordPanel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_dashboard");
        this.documentType = null;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void word_style_horizontalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_word_style_horizontalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.HORIZONTAL;
        }
    }//GEN-LAST:event_word_style_horizontalItemStateChanged

    private void word_style_verticalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_word_style_verticalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.VERTICAL;
        }
    }//GEN-LAST:event_word_style_verticalItemStateChanged

    private void word_style_diagonal_leftItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_word_style_diagonal_leftItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_LEFT;
        }
    }//GEN-LAST:event_word_style_diagonal_leftItemStateChanged

    private void word_style_diagonal_rightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_word_style_diagonal_rightItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_RIGHT;
        }
    }//GEN-LAST:event_word_style_diagonal_rightItemStateChanged

    private void word_select_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_word_select_fileActionPerformed
        // open file
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Microsoft Word 2007 - 2013 Documents", "docx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile != null) {
                this.sourceFile = selectedFile;
                word_selected_file_name.setText(selectedFile.getName());
            }
        }
    }//GEN-LAST:event_word_select_fileActionPerformed

    private void wordWatermarkTextComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wordWatermarkTextComboItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            WatermarkText watermarkText = (WatermarkText) wordWatermarkTextCombo.getSelectedItem();
            if (watermarkText.equals(WatermarkText.CUSTOM)) {
                wordCustomWatermarkText.setText("");
                wordCustomWatermarkText.setEnabled(true);
            } else {
                wordCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
            }
        }
    }//GEN-LAST:event_wordWatermarkTextComboItemStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Word 2007 - 2013 Documents", "docx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            this.destinationFile = fixExtensionForFile(selectedFile, DocumentType.DOCX);
            // call word watermark function
            String watermarkText = getWatermarkText();

            addDocxDocumentWatermark(sourceFile, destinationFile, watermarkText);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the watermarked file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Word 2007 - 2013 Documents", "docx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            this.destinationFile = fixExtensionForFile(selectedFile, DocumentType.DOCX);
            // call remove word watermark function
            removeDocxDocumentWatermark(sourceFile, destinationFile);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // excel panel go back button
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_dashboard");
        this.documentType = null;
    }//GEN-LAST:event_jButton6ActionPerformed

    private void excel_select_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excel_select_fileActionPerformed
        // open excel file for watermarking
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Microsoft Excel 2007 - 2013 Documents", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile != null) {
                this.sourceFile = selectedFile;
                excel_selected_file_name.setText(selectedFile.getName());
            }
        }
    }//GEN-LAST:event_excel_select_fileActionPerformed

    private void excel_style_horizontalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_excel_style_horizontalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.HORIZONTAL;
        }
    }//GEN-LAST:event_excel_style_horizontalItemStateChanged

    private void excel_style_verticalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_excel_style_verticalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.VERTICAL;
        }
    }//GEN-LAST:event_excel_style_verticalItemStateChanged

    private void excel_style_diagonal_leftItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_excel_style_diagonal_leftItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_LEFT;
        }
    }//GEN-LAST:event_excel_style_diagonal_leftItemStateChanged

    private void excel_style_diagonal_rightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_excel_style_diagonal_rightItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_RIGHT;
        }
    }//GEN-LAST:event_excel_style_diagonal_rightItemStateChanged

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // add excel watermark here
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel 2007 - 2013 Documents", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            this.destinationFile = fixExtensionForFile(selectedFile, DocumentType.XLSX);
            // call word watermark function
            String watermarkText = getWatermarkText();

            addXlsxDocumentWatermark(sourceFile, destinationFile, watermarkText);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void excelWatermarkTextComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_excelWatermarkTextComboItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            WatermarkText watermarkText = (WatermarkText) excelWatermarkTextCombo.getSelectedItem();
            if (watermarkText.equals(WatermarkText.CUSTOM)) {
                excelCustomWatermarkText.setText("");
                excelCustomWatermarkText.setEnabled(true);
            } else {
                excelCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
            }
        }
    }//GEN-LAST:event_excelWatermarkTextComboItemStateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // remove excel watermark here
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the watermarked file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel 2007 - 2013 Documents", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            this.destinationFile = fixExtensionForFile(selectedFile, DocumentType.XLSX);
            // call remove excel watermark function
            removeXlsxDocumentWatermark(sourceFile, destinationFile);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // show excel panel
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_excel");
        resetExcelPanel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // pdf panel go back button
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_dashboard");
        this.documentType = null;
    }//GEN-LAST:event_jButton11ActionPerformed

    private void pdf_select_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf_select_fileActionPerformed
        // open pdf file for watermarking
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Portable Document Format (PDF) Files", "pdf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile != null) {
                this.sourceFile = selectedFile;
                pdf_selected_file_name.setText(selectedFile.getName());
            }
        }
    }//GEN-LAST:event_pdf_select_fileActionPerformed

    private void pdf_style_horizontalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pdf_style_horizontalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.HORIZONTAL;
        }
    }//GEN-LAST:event_pdf_style_horizontalItemStateChanged

    private void pdf_style_verticalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pdf_style_verticalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.VERTICAL;
        }
    }//GEN-LAST:event_pdf_style_verticalItemStateChanged

    private void pdf_style_diagonal_leftItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pdf_style_diagonal_leftItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_LEFT;
        }
    }//GEN-LAST:event_pdf_style_diagonal_leftItemStateChanged

    private void pdf_style_diagonal_rightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pdf_style_diagonal_rightItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_RIGHT;
        }
    }//GEN-LAST:event_pdf_style_diagonal_rightItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // save watermarked pdf as
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Portable Document Format (PDF) Files", "pdf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            this.destinationFile = fixExtensionForFile(selectedFile, DocumentType.PDF);
            // call pdf watermark function
            String watermarkText = getWatermarkText();

            addPDFDocumentWatermark(sourceFile, destinationFile, watermarkText);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void pdfWatermarkTextComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pdfWatermarkTextComboItemStateChanged
        // handle watermark text
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            WatermarkText watermarkText = (WatermarkText) pdfWatermarkTextCombo.getSelectedItem();
            if (watermarkText.equals(WatermarkText.CUSTOM)) {
                pdfCustomWatermarkText.setText("");
                pdfCustomWatermarkText.setEnabled(true);
            } else {
                pdfCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
            }
        }
    }//GEN-LAST:event_pdfWatermarkTextComboItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // show pdf panel
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_pdf");
        resetPdfPanel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // show the pptx card
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_powerpoint");
        resetPowerPointPanel();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // powerpoint panel go back button
        CardLayout cl = (CardLayout) card_holder.getLayout();
        cl.show(card_holder, "panel_dashboard");
        this.documentType = null;
    }//GEN-LAST:event_jButton16ActionPerformed

    private void powerpoint_select_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerpoint_select_fileActionPerformed
        // open pptx file for watermarking
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Microsoft PowerPoint 2007 - 2013 Files", "pptx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile != null) {
                this.sourceFile = selectedFile;
                powerpoint_selected_file_name.setText(selectedFile.getName());
            }
        }
    }//GEN-LAST:event_powerpoint_select_fileActionPerformed

    private void powerpoint_style_horizontalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_powerpoint_style_horizontalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.HORIZONTAL;
        }
    }//GEN-LAST:event_powerpoint_style_horizontalItemStateChanged

    private void powerpoint_style_verticalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_powerpoint_style_verticalItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.VERTICAL;
        }
    }//GEN-LAST:event_powerpoint_style_verticalItemStateChanged

    private void powerpoint_style_diagonal_leftItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_powerpoint_style_diagonal_leftItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_LEFT;
        }
    }//GEN-LAST:event_powerpoint_style_diagonal_leftItemStateChanged

    private void powerpoint_style_diagonal_rightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_powerpoint_style_diagonal_rightItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.watermarkType = WatermarkType.DIAGONAL_RIGHT;
        }
    }//GEN-LAST:event_powerpoint_style_diagonal_rightItemStateChanged

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // save watermarked powerpoint as
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft PowerPoint 2007 - 2013 Files", "pptx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            this.destinationFile = fixExtensionForFile(selectedFile, DocumentType.PPTX);
            // call powerpoint watermark function
            String watermarkText = getWatermarkText();

            addPowerpointDocumentWatermark(sourceFile, destinationFile, watermarkText);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void powerpointWatermarkTextComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_powerpointWatermarkTextComboItemStateChanged
        // handle watermark text
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            WatermarkText watermarkText = (WatermarkText) powerpointWatermarkTextCombo.getSelectedItem();
            if (watermarkText.equals(WatermarkText.CUSTOM)) {
                powerpointCustomWatermarkText.setText("");
                powerpointCustomWatermarkText.setEnabled(true);
            } else {
                powerpointCustomWatermarkText.setText("Type in your Custom Watermark Text here..");
            }
        }
    }//GEN-LAST:event_powerpointWatermarkTextComboItemStateChanged

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // word save action
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.destinationFile = fixExtensionForFile(this.sourceFile, DocumentType.DOCX);
        // call word watermark function
        String watermarkText = getWatermarkText();

        addDocxDocumentWatermark(this.sourceFile, this.destinationFile, watermarkText);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // remove and replace the same file
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the watermarked file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.destinationFile = fixExtensionForFile(this.sourceFile, DocumentType.DOCX);
        // call remove word watermark function
        removeDocxDocumentWatermark(this.sourceFile, this.destinationFile);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // add excel watermark here
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // set the source file as the destination file
        this.destinationFile = fixExtensionForFile(this.sourceFile, DocumentType.XLSX);
        // call word watermark function
        String watermarkText = getWatermarkText();

        addXlsxDocumentWatermark(this.sourceFile, this.destinationFile, watermarkText);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // remove excel watermark here
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the watermarked file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.destinationFile = fixExtensionForFile(this.sourceFile, DocumentType.XLSX);
        // call remove excel watermark function
        removeXlsxDocumentWatermark(this.sourceFile, this.destinationFile);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // save watermarked pdf as
        if (this.sourceFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the file", "No file selected", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.destinationFile = fixExtensionForFile(this.sourceFile, DocumentType.PDF);
        // call pdf watermark function
        String watermarkText = getWatermarkText();

        addPDFDocumentWatermark(this.sourceFile, this.destinationFile, watermarkText);
    }//GEN-LAST:event_jButton19ActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card_holder;
    private javax.swing.JTextField excelCustomWatermarkText;
    private javax.swing.JComboBox<String> excelWatermarkTextCombo;
    private javax.swing.ButtonGroup excel_rotations;
    private javax.swing.JButton excel_select_file;
    private javax.swing.JLabel excel_selected_file_name;
    private javax.swing.JToggleButton excel_style_diagonal_left;
    private javax.swing.JToggleButton excel_style_diagonal_right;
    private javax.swing.JToggleButton excel_style_horizontal;
    private javax.swing.JToggleButton excel_style_vertical;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel panel_dashboard;
    private javax.swing.JPanel panel_excel;
    private javax.swing.JPanel panel_pdf;
    private javax.swing.JPanel panel_powerpoint;
    private javax.swing.JPanel panel_word;
    private javax.swing.JTextField pdfCustomWatermarkText;
    private javax.swing.JComboBox<String> pdfWatermarkTextCombo;
    private javax.swing.ButtonGroup pdf_rotations;
    private javax.swing.JButton pdf_select_file;
    private javax.swing.JLabel pdf_selected_file_name;
    private javax.swing.JToggleButton pdf_style_diagonal_left;
    private javax.swing.JToggleButton pdf_style_diagonal_right;
    private javax.swing.JToggleButton pdf_style_horizontal;
    private javax.swing.JToggleButton pdf_style_vertical;
    private javax.swing.JTextField powerpointCustomWatermarkText;
    private javax.swing.JComboBox<String> powerpointWatermarkTextCombo;
    private javax.swing.ButtonGroup powerpoint_rotations;
    private javax.swing.JButton powerpoint_select_file;
    private javax.swing.JLabel powerpoint_selected_file_name;
    private javax.swing.JToggleButton powerpoint_style_diagonal_left;
    private javax.swing.JToggleButton powerpoint_style_diagonal_right;
    private javax.swing.JToggleButton powerpoint_style_horizontal;
    private javax.swing.JToggleButton powerpoint_style_vertical;
    private javax.swing.JPanel titlebar;
    private javax.swing.JTextField wordCustomWatermarkText;
    private javax.swing.JComboBox<String> wordWatermarkTextCombo;
    private javax.swing.ButtonGroup word_rotations;
    private javax.swing.JButton word_select_file;
    private javax.swing.JLabel word_selected_file_name;
    private javax.swing.JToggleButton word_style_diagonal_left;
    private javax.swing.JToggleButton word_style_diagonal_right;
    private javax.swing.JToggleButton word_style_horizontal;
    private javax.swing.JToggleButton word_style_vertical;
    // End of variables declaration//GEN-END:variables
}
