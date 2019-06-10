package com.tao;

import java.util.StringTokenizer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.rtf.RTFEditorKit;

public class MyTextPane extends EditorOpener {

    protected StyleContext m_context;
    protected DefaultStyledDocument m_doc;
    private MutableAttributeSet keyAttr, normalAttr;
    private MutableAttributeSet inputAttributes =
            new RTFEditorKit().getInputAttributes();

    private String[] keyWord = { "null", "true", "false","boolean", "char", "byte", "short", "int",
            "long", "float", "double", "void","abstract", "break", "case", "catch", "class",
            "const", "continue", "default", "do", "else", "extends", "final",
            "finally", "for", "goto", "if", "implements", "import",
            "instanceof", "interface", "native", "new", "package", "private",
            "protected", "public", "return", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws", "transient",
            "try", "volatile", "while"};

    public MyTextPane() {
        super();
        text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                syntaxParse();
            }
        });

        m_context = new StyleContext();
        m_doc = new DefaultStyledDocument(m_context);
        text.setDocument(m_doc);


        //定义关键字显示属性
        keyAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(keyAttr, Color.green);

        //定义一般文本显示属性
        normalAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(normalAttr, Color.blue);
    }

    public void syntaxParse() {
        try {
            String s = null;
            Element root = m_doc.getDefaultRootElement();

            int cursorPos = text.getCaretPosition();
            int line = root.getElementIndex(cursorPos);

            Element para = root.getElement(line);
            int start = para.getStartOffset();
            int end = para.getEndOffset() - 1;
            s = m_doc.getText(start, end - start);

            int i = 0;
            int xStart = 0;

            //分析关键字---
            m_doc.setCharacterAttributes(start, s.length(), normalAttr, false);
            MyStringTokenizer st = new MyStringTokenizer(s);
            while (st.hasMoreTokens()) {
                s = st.nextToken();
                if (s == null) return;
                for (i = 0; i < keyWord.length; i++) {
                    if (s.equals(keyWord[i])) break;
                }
                if (i >= keyWord.length) continue;

                xStart = st.getCurrPosition();

                //设置关键字显示属性
                m_doc.setCharacterAttributes(start + xStart, s.length(),
                        keyAttr, false);
            }
            inputAttributes.addAttributes(normalAttr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingConsle.run(new MyTextPane(), 600, 600);
    }
}

/*在分析字符串的同时，记录每个token所在的位置
 *
 */
class MyStringTokenizer extends StringTokenizer {
    String sval = " ";
    String oldStr, str;
    int m_currPosition = 0, m_beginPosition = 0;

    MyStringTokenizer(String str) {
        super(str, " ");
        this.oldStr = str;
        this.str = str;
    }

    @Override
    public String nextToken() {
        try {
            String s = super.nextToken();
            int pos = -1;

            if (oldStr.equals(s)) {
                return s;
            }

            pos = str.indexOf(s + sval);
            if (pos == -1) {
                pos = str.indexOf(sval + s);
                if (pos == -1)
                    return null;
                else pos += 1;
            }

            int xBegin = pos + s.length();
            str = str.substring(xBegin);

            m_currPosition = m_beginPosition + pos;
            m_beginPosition = m_beginPosition + xBegin;
            return s;
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //返回token在字符串中的位置
    public int getCurrPosition() {
        return m_currPosition;
    }
}

