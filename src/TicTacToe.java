package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class TicTacToe extends JFrame {

    final String TITLE_OF_PROGRAM = "Крестики - нолики";
    final int WINDOW_SIZE = 330;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "Новая игра";
    final String BTN_EXIT = "Выход";

    Panel panel;
    Field field;
    Human human;
    AI ai;

    public static void main(String args[]) {
        new TicTacToe();
    }

    TicTacToe() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        field = new Field(FIELD_SIZE, CELL_SIZE);
        human = new Human(field.getHumanDot());
        ai = new AI(field.getAIDot());

        panel = new Panel();
        panel.setBackground(Color.white);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                human.turn(e.getX()/CELL_SIZE, e.getY()/CELL_SIZE, field, ai);
                panel.repaint();
                if (field.isGameOver())
                    JOptionPane.showMessageDialog(
                            TicTacToe.this, field.getGameOverMsg());
            }
        });
        JButton init = new JButton(BTN_INIT);
        init.addActionListener(e -> {
            field.init();
            panel.repaint();
        });
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout());
        panelBtn.add(init);
        panelBtn.add(exit);

        add(panelBtn, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    class Panel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint((Graphics2D) g);
        }
    }
}
