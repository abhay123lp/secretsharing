/*
 * Frame.java
 */
package userInterface;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.math.BigInteger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import secretsharing.Joining;
import secretsharing.Sharing;
import secretsharingException.JoiningException;
import secretsharingException.SharingException;
import steganography.Embedding;
import steganography.Extraction;
import steganographyException.SteganographyException;

public class Frame extends javax.swing.JFrame {

    /** Creates new form Frame */
    public Frame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelImage = new javax.swing.JLabel();
        jLabelMessage = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton0 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemOpenImage = new javax.swing.JMenuItem();
        jMenuItemSaveImage = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuCode = new javax.swing.JMenu();
        jMenuItemWriteCode = new javax.swing.JMenuItem();
        jMenuItemReadCode = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton0.setText("0");
        jButton0.setFocusable(false);
        jButton0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton0.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton0MousePressed(evt);
            }
        });
        jToolBar1.add(jButton0);

        jButton1.setText("1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setText("2");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setText("3");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setText("4");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setText("5");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setText("6");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setText("7");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jMenuFile.setText("File");

        jMenuItemOpenImage.setText("Open Image");
        jMenuItemOpenImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItemOpenImageMousePressed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpenImage);

        jMenuItemSaveImage.setText("Save Image");
        jMenuItemSaveImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItemSaveImageMousePressed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveImage);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItemExitMousePressed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuCode.setText("Code");

        jMenuItemWriteCode.setText("Write Code");
        jMenuItemWriteCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItemWriteCodeMousePressed(evt);
            }
        });
        jMenuCode.add(jMenuItemWriteCode);

        jMenuItemReadCode.setText("Read Code");
        jMenuItemReadCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItemReadCodeMousePressed(evt);
            }
        });
        jMenuCode.add(jMenuItemReadCode);

        jMenuBar1.add(jMenuCode);

        jMenuHelp.setText("Help");

        jMenuItemAbout.setText("About");
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(624, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                .addComponent(jLabelMessage)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemOpenImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemOpenImageMousePressed
        FileDialog fd = new FileDialog(this, "Open Image");
        fd.setVisible(true);
        if (fd.getFile() == null) {
            JOptionPane.showMessageDialog(null, "Choose file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        filename = fd.getDirectory() + fd.getFile();

        Image img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ImageIcon icon = new ImageIcon(img);
        jLabelImage.setIcon(icon);
        try {
            embedding = new Embedding(filename);
        } catch (SteganographyException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jLabelMessage.setText("");
    }//GEN-LAST:event_jMenuItemOpenImageMousePressed

    private void jMenuItemExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemExitMousePressed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitMousePressed

    private void jMenuItemSaveImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemSaveImageMousePressed
        if (embedding == null) {
            JOptionPane.showMessageDialog(null, "Before save, open image please", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        FileDialog fd = new FileDialog(this, "Save Image", FileDialog.SAVE);
        fd.setVisible(true);
        String name = fd.getDirectory() + fd.getFile();
        if (!name.contains(".bmp")) {
            name += ".bmp";
        }
        FileOutputStream saveImage = null;
        try {
            saveImage = new FileOutputStream(name);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            saveImage.write(embedding.image.getHead());
            saveImage.write(embedding.image.getInfohead());
            saveImage.write(embedding.image.getRGB());
            saveImage.close();
            embedding.writeFileWithHash(name + hashFilename);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (SteganographyException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jLabelMessage.setText("");
    }//GEN-LAST:event_jMenuItemSaveImageMousePressed

    private void jMenuItemWriteCodeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemWriteCodeMousePressed
        WriteCodeDialog dlg = new WriteCodeDialog(this, true);
        dlg.setVisible(true);
        if ((dlg.message == null) || (dlg.shares == 0) || (dlg.threshold == 0) || (dlg.prime == null)) {
            JOptionPane.showMessageDialog(null, "Please enter correct data", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        BigInteger message = new BigInteger(dlg.message);
        BigInteger prime = new BigInteger(dlg.prime);
        Sharing sharing = new Sharing(message, prime);
        BigInteger args[] = new BigInteger[dlg.shares];
        args[0] = prime.nextProbablePrime();
        for (int i = 1; i < args.length; i++) {
            args[i] = args[i - 1].nextProbablePrime();
        }
        BigInteger values[] = new BigInteger[dlg.shares];
        try {
            values = sharing.getShares(args, dlg.threshold, dlg.shares);
        } catch (SharingException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            embedding.writeSharesToSubImages(args, values);
        } catch (SteganographyException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        jLabelMessage.setText("");
    }//GEN-LAST:event_jMenuItemWriteCodeMousePressed

    private void jMenuItemReadCodeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemReadCodeMousePressed

        ReadCodeDialog dlg = new ReadCodeDialog(this, true);
        dlg.setVisible(true);
        BigInteger prime = new BigInteger(dlg.prime);
        BigInteger message = null;
        Joining joining = new Joining(prime);
        try {
            Extraction extraction = new Extraction(filename, filename + hashFilename);
            extraction.getSharesFromSubimages(dlg.threshold);
            try {
                message = joining.getMessage(extraction.args, extraction.values, dlg.threshold);
            } catch (JoiningException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SteganographyException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        jLabelMessage.setText(message.toString());
    }//GEN-LAST:event_jMenuItemReadCodeMousePressed

    private void jButton0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton0MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 0);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton0MousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 1);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 2);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 3);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 4);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 5);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton5MousePressed

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 6);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton6MousePressed

    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed
        byte[] rgb = shiftBytes(embedding.image.getRGB(), 7);
        jLabelImage.setIcon(createIcon(rgb));
    }//GEN-LAST:event_jButton7MousePressed

    private ImageIcon createIcon(byte[] rgb) {
        Image img = null;
        int height = embedding.image.getHeight();
        int width = embedding.image.getWidth();
        int data[] = new int[height * width];
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                data[width * (height - j - 1) + i] =
                        (255 & 0xff) << 24 | (((int) rgb[index + 2] & 0xff) << 16) | (((int) rgb[index + 1] & 0xff) << 8) | (int) rgb[index] & 0xff;
                index += 3;
            }
            index += embedding.image.getPad();
        }
        img = createImage(new MemoryImageSource(width, height, data, 0, width));
        return new ImageIcon(img);
    }

    private byte[] shiftBytes(byte[] rgb, int shift) {
        byte[] rgbWithMask = new byte[rgb.length];
        for (int i = 0; i < rgb.length; i++) {
            rgbWithMask[i] = (byte) (rgb[i] << (7-shift));
        }
        return rgbWithMask;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    private String hashFilename = "_hashkeys";
    private Embedding embedding = null;
    private String filename = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCode;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemOpenImage;
    private javax.swing.JMenuItem jMenuItemReadCode;
    private javax.swing.JMenuItem jMenuItemSaveImage;
    private javax.swing.JMenuItem jMenuItemWriteCode;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
