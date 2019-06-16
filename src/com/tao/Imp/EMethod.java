package com.tao.Imp;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.*;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class EMethod {
    public static void newFile() {

    }

    public static void openFile(JFrame jFrame) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("文本文件","txt"));
        int returnValue = jFileChooser.showOpenDialog(jFrame.getContentPane());
    }

    public static void exitFile() {
        System.exit(0);
    }

    public static void shearText() {

    }

    public static void pasteText() {

    }

    public static void copyText() {

    }

}
