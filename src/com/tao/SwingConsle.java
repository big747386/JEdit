package com.tao;

import javax.swing.*;

public class SwingConsle {
    public static void run(JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                f.setSize(width, height);
                f.setTitle("JEditor");
            }
        });
    }
}
