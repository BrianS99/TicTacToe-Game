/* Written by Brian Sun
   Date: August 22, 2020
   This is a functional Tic Tac Toe game that takes input from your keyboard numbers 1-9
   as a 3x3 game board to play against a computer.
 */

package TicTacToe;

import javax.swing.JFrame;

public class GUI {

    public static void main(String[] args) throws InterruptedException {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(0, 0, 700, 600);
        obj.setTitle("Tic Tac Toe");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        obj.add(gameplay);

        gameplay.runGame();
    }
}