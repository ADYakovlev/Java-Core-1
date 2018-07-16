package ru.yakovlev.javacore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 *@author Yakovlev Alexandr
 */

public class myWindow extends JFrame {

    myWindow(int x, int y, int w, int h, String name, boolean resizable) {
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\projects\\javacore\\lesson04\\src\\main\\java\\ru\\yakovlev\\javacore\\fafaed3.png");
        setIconImage(icon);
        setTitle(name);
        setResizable(resizable);
        setBounds(x, y, w, h);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JButton send = new JButton("Send");
        send.setBorderPainted(false);
        send.setPreferredSize(new Dimension(100,30));
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);
        add(textField, BorderLayout.NORTH);
        add(send, BorderLayout.SOUTH);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textField!=null){
                    textArea.append(textField.getText()+"\n");
                    textField.setText("");
                }

            }

        });

        textField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(textField!=null){
                        textArea.append(textField.getText()+"\n");
                        textField.setText("");
                    }
                }
            }

            public void keyReleased(KeyEvent e) {}
        });

        textArea.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {

            }
        });

    }
}
