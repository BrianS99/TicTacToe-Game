package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Gameplay extends JPanel {
    private int playerScore = 0;
    private int comScore = 0;
    private int matchNumber = 0;
    private String gameOver = "";
    private ArrayList<Integer> comMoves = new ArrayList<>();
    private String[][] gameBoard = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "},
    };
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void paint(Graphics g) {

        // background
        g.setColor(Color.white);
        g.fillRect(0, 0, 700, 600);

        // borders
        g.setColor(Color.blue);
        g.fillRect(0, 0, 10, 600);
        g.fillRect(0, 0, 700, 10);
        g.fillRect(676, 0, 10, 600);
        g.fillRect(0, 555, 700, 10);

        // game board
        g.setColor(Color.BLACK);
        g.fillRect(290, 100, 10, 320);
        g.fillRect(400, 100, 10, 320);
        g.fillRect(190, 200, 320, 10);
        g.fillRect(190, 310, 320, 10);

        // drawing player Xs and Os
        Font font = new Font("Arial", Font.PLAIN, 70);
        g.setFont(font);
        g.drawString(gameBoard[0][0], 220, 180);
        g.drawString(gameBoard[0][1], 325, 180);
        g.drawString(gameBoard[0][2], 430, 180);
        g.drawString(gameBoard[1][0], 220, 285);
        g.drawString(gameBoard[1][1], 325, 285);
        g.drawString(gameBoard[1][2], 430, 285);
        g.drawString(gameBoard[2][0], 220, 390);
        g.drawString(gameBoard[2][1], 325, 390);
        g.drawString(gameBoard[2][2], 430, 390);

        // scoreboard
        Font font2 = new Font("Arial", Font.PLAIN, 40);
        g.setFont(font2);
        g.drawString("You: " + playerScore, 30, 110);
        g.drawString("COM: " + comScore, 520,110);

        // game over text
        g.drawString(gameOver, 10, 490);

        // helper image
        Image image = Toolkit.getDefaultToolkit().getImage("HelperImage.png");
        g.drawImage(image, 500, 400, 140, 140, this);


        g.dispose();
    }

    // the main game logic
    public void runGame() throws InterruptedException {

        while (true) {
            this.matchNumber++;
            boolean invalidSpot = false;
            comMoves.clear();
            int lastPlayerScore = this.playerScore;
            int lastComScore = this.comScore;

            while (true) {
                int spot;

                // if it's an odd number match
                if(this.matchNumber % 2 != 0) {
                    System.out.print("Enter a number (1-9) to play: ");
                    spot = scanner.nextInt();

                    if (!update(this.gameBoard, spot, 1)) {
                        continue;
                    }

                    repaint();

                    // tests if you win
                    if (gameOver(this.gameBoard, true)) {

                        if(this.playerScore != lastPlayerScore){
                            this.gameOver = " You Win!";
                        }
                        break;
                    }
                    Thread.sleep(1000);
                }

                if(!invalidSpot) {
                    do {
                        spot = getSpot(this.gameBoard);
                    } while (!update(this.gameBoard, spot, 2));

                    comMoves.add(spot);
                    repaint();

                    // tests if computer wins
                    if (gameOver(this.gameBoard, false)) {

                        if(this.comScore != lastComScore){
                            this.gameOver = " Computer Wins!";
                        }
                        break;
                    }
                }
                // resets the invalid spot test variable
                invalidSpot = false;

                if(this.matchNumber % 2 == 0) {
                    System.out.print("Enter a number (1-9) to play: ");
                    spot = scanner.nextInt();

                    if (!update(this.gameBoard, spot, 1)) {
                        invalidSpot = true;
                        continue;
                    }

                    repaint();

                    // tests if you win
                    if (gameOver(this.gameBoard, true)) {

                        if(this.playerScore != lastPlayerScore){
                            this.gameOver = " You Win!";
                        }
                        break;
                    }
                    Thread.sleep(1000);
                }
            }

            //game over code
            if(this.gameOver.isEmpty())
                this.gameOver = " DRAW :/";
            System.out.println("Game Over!");
            Thread.sleep(1500);
            resetBoard();
            repaint();
            this.gameOver = "";
        }
    }

    // clears board
    public void resetBoard() {

        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                this.gameBoard[row][col] = " ";
            }
        }
    }

    // takes user's input and updates the board, returns true if successful and false if not
    public boolean update(String[][] gameBoard, int spot, int user) {
        boolean valid = true;
        String symbol;

        if (user == 1) {
            symbol = "X";
        } else {
            symbol = "O";
        }

        // checks if spot is within range
        if (spot < 1 || spot > 9) {
            valid = false;
            System.out.println("Invalid!");
            return valid;
        }

        switch (spot) {
            case 1:
                if (!gameBoard[0][0].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[0][0] = symbol;
                break;
            case 2:
                if (!gameBoard[0][1].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[0][1] = symbol;
                break;
            case 3:
                if (!gameBoard[0][2].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[0][2] = symbol;
                break;
            case 4:
                if (!gameBoard[1][0].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[1][0] = symbol;
                break;
            case 5:
                if (!gameBoard[1][1].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[1][1] = symbol;
                break;
            case 6:
                if (!gameBoard[1][2].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[1][2] = symbol;
                break;
            case 7:
                if (!gameBoard[2][0].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[2][0] = symbol;
                break;
            case 8:
                if (!gameBoard[2][1].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[2][1] = symbol;
                break;
            case 9:
                if (!gameBoard[2][2].equals(" ")) {
                    valid = false;
                    break;
                }
                gameBoard[2][2] = symbol;
                break;
        }

        // only want to print if current player is human!
        if (!valid && user == 1)
            System.out.println("Invalid! ");

        return valid;
    }

    public boolean ticTacToe(String[][] gameBoard, boolean player1){

        //checks rows for tic tac toe
        for (int row = 0; row <= 2; row++) {
            if (gameBoard[row][0].equals(gameBoard[row][1]) && gameBoard[row][0].equals(gameBoard[row][2]) && !gameBoard[row][0].equals(" ")) {
                if(player1)
                    this.playerScore++;
                else
                    this.comScore++;
                return true;
            }
        }

        //checks columns for tic tac toe
        for (int col = 0; col <= 2; col++) {
            if (gameBoard[0][col].equals(gameBoard[1][col]) && gameBoard[0][col].equals(gameBoard[2][col]) && !gameBoard[0][col].equals(" ")) {
                if(player1)
                    this.playerScore++;
                else
                    this.comScore++;
                return true;
            }
        }

        //checks for diagonals
        if (gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2]) && !gameBoard[0][0].equals(" ")) {
            if(player1)
                this.playerScore++;
            else
                this.comScore++;
            return true;
        }

        if (gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals(gameBoard[2][0]) && !gameBoard[0][2].equals(" ")) {
            if(player1)
                this.playerScore++;
            else
                this.comScore++;
            return true;
        }

        //checks if full board
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                if (gameBoard[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return true;

    }

    // checks if a player has won
    public boolean gameOver(String[][] gameBoard, boolean player1) {


        if(ticTacToe(gameBoard, player1)){
            return true;
        }

        //checks if full board
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                if (gameBoard[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }

    // checks if the board is empty
    public boolean boardEmpty(String[][] gameBoard){

        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                if (!gameBoard[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // checks a row for obstructions, returns -1 if there is, or returns the value of the column that should be played in that row
    public int checkRow(String[][] gameBoard, int row){

        for(int i = 0; i <= 2; i++){
            if(gameBoard[row][i].equals("X")){
                return -1;
            }
        }
        if(gameBoard[row][0].equals("O")){
            return 2;
        } else {
            return 0;
        }
    }

    // checks a column for obstructions, returns -1 if there is, or returns the value of the row that should be played in that column
    public int checkCol(String[][] gameBoard, int col){
        for(int i = 0; i <= 2; i++){
            if(gameBoard[i][col].equals("X")){
                return -1;
            }
        }
        if(gameBoard[0][col].equals("O")){
            return 2;
        } else {
            return 0;
        }
    }

    // the AI of the computer that decides next step
    public int getSpot(String[][] gameBoard){

        /*For Tic-tac-toe, the rules, in the order of importance, are:
        Rule 1: If I have a winning move, take it.
        Rule 2: If the opponent has a winning move, block it.
        Rule 3: If I can create a fork (two winning ways) after this move, do it.
        Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
        Rule 5: Place in the position such as I may win in the most number of possible ways.*/

        // places in 1 of the 4 corners if com starts
        if(boardEmpty(gameBoard)){
            int rand1 = ThreadLocalRandom.current().nextInt(-100, 100);
            int rand2 = ThreadLocalRandom.current().nextInt(-100, 100);
            if(rand1 > 0 && rand2 > 0){
                return 1;
            } else if( rand1 > 1 && rand2 <= 0){
                return 3;
            } else if(rand1 <= 0 && rand2 > 0){
                return 7;
            } else {
                return 9;
            }
        }

        //CHECKS FOR WINS
        // check for win vertically
        for(int i = 0; i<3; i++){

            if(gameBoard[0][i].equals(gameBoard[1][i]) && gameBoard[0][i].equals("O")){

                if(gameBoard[2][i].equals(" ")){
                    return 7+i;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[1][i].equals(gameBoard[2][i]) && gameBoard[1][i].equals("O")){

                if(gameBoard[0][i].equals(" ")){
                    return 1+i;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[0][i].equals(gameBoard[2][i]) && gameBoard[0][i].equals("O")){

                if(gameBoard[1][i].equals(" ")){
                    return 4+i;
                }
            }
        }


        // check if you can take a win horizontally
        for(int i = 0; i<3; i++){

            if(gameBoard[i][0].equals(gameBoard[i][1]) && gameBoard[i][0].equals("O")){

                if(gameBoard[i][2].equals(" ")){
                    return (i+1)*3;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[i][2].equals(gameBoard[i][1]) && gameBoard[i][2].equals("O")){

                if(gameBoard[i][0].equals(" ")){
                    return ((i+1)*3) - 2;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[i][0].equals(gameBoard[i][2]) && gameBoard[i][0].equals("O")){

                if(gameBoard[i][1].equals(" ")){
                    return ((i+1)*3) - 1;
                }
            }
        }


        // check if you can take a win diagonally
        if(gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals("O")){

            if(gameBoard[2][2].equals(" ")){
                return 9;
            }
        }

        if(gameBoard[0][0].equals(gameBoard[2][2]) && gameBoard[0][0].equals("O")){

            if(gameBoard[1][1].equals(" ")){
                return 5;
            }
        }

        if(gameBoard[2][2].equals(gameBoard[1][1]) && gameBoard[1][1].equals("O")){

            if(gameBoard[0][0].equals(" ")){
                return 1;
            }
        }

        if(gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals("O")){

            if(gameBoard[2][0].equals(" ")){
                return 7;
            }
        }

        if(gameBoard[0][2].equals(gameBoard[2][0]) && gameBoard[0][2].equals("O")){

            if(gameBoard[1][1].equals(" ")){
                return 5;
            }
        }

        if(gameBoard[2][0].equals(gameBoard[1][1]) && gameBoard[2][0].equals("O")){

            if(gameBoard[0][2].equals(" ")){
                return 3;
            }
        }

        // BLOCKING
        // check for blocks vertically
        for(int i = 0; i<3; i++){

            if(gameBoard[0][i].equals(gameBoard[1][i]) && gameBoard[0][i].equals("X")){

                if(gameBoard[2][i].equals(" ")){
                    return 7+i;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[1][i].equals(gameBoard[2][i]) && gameBoard[1][i].equals("X")){

                if(gameBoard[0][i].equals(" ")){
                    return 1+i;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[0][i].equals(gameBoard[2][i]) && gameBoard[0][i].equals("X")){

                if(gameBoard[1][i].equals(" ")){
                    return 4+i;
                }
            }
        }


        // check for blocks horizontally
        for(int i = 0; i<3; i++){

            if(gameBoard[i][0].equals(gameBoard[i][1]) && gameBoard[i][0].equals("X")){

                if(gameBoard[i][2].equals(" ")){
                    return (i+1)*3;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[i][2].equals(gameBoard[i][1]) && gameBoard[i][2].equals("X")){

                if(gameBoard[i][0].equals(" ")){
                    return ((i+1)*3) - 2;
                }
            }
        }

        for(int i = 0; i<3; i++){

            if(gameBoard[i][0].equals(gameBoard[i][2]) && gameBoard[i][0].equals("X")){

                if(gameBoard[i][1].equals(" ")){
                    return ((i+1)*3) - 1;
                }
            }
        }

        // check for blocks diagonally
        if(gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals("X")){

            if(gameBoard[2][2].equals(" ")){
                return 9;
            }
        }

        if(gameBoard[0][0].equals(gameBoard[2][2]) && gameBoard[0][0].equals("X")){

            if(gameBoard[1][1].equals(" ")){
                return 5;
            }
        }

        if(gameBoard[2][2].equals(gameBoard[1][1]) && gameBoard[1][1].equals("X")){

            if(gameBoard[0][0].equals(" ")){
                return 1;
            }
        }

        if(gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals("X")){

            if(gameBoard[2][0].equals(" ")){
                return 7;
            }
        }

        if(gameBoard[0][2].equals(gameBoard[2][0]) && gameBoard[0][2].equals("X")){

            if(gameBoard[1][1].equals(" ")){
                return 5;
            }
        }

        if(gameBoard[2][0].equals(gameBoard[1][1]) && gameBoard[2][0].equals("X")){

            if(gameBoard[0][2].equals(" ")){
                return 3;
            }
        }

        // attempts to create forks
        if(comMoves.contains(1)){
            if(gameBoard[1][0].equals(" ") && gameBoard[2][0].equals(" ")){
                return 7;
            }
            if(gameBoard[0][1].equals(" ") && gameBoard[0][2].equals(" ")){
                return 3;
            }
        }

        if(comMoves.contains(3)){
            if(gameBoard[1][2].equals(" ") && gameBoard[2][2].equals(" ")){
                return 9;
            }
            if(gameBoard[0][1].equals(" ") && gameBoard[0][0].equals(" ")){
                return 1;
            }
        }

        if(comMoves.contains(7)){
            if(gameBoard[1][0].equals(" ") && gameBoard[0][0].equals(" ")){
                return 1;
            }
            if(gameBoard[2][1].equals(" ") && gameBoard[2][2].equals(" ")){
                return 9;
            }
        }

        if(comMoves.contains(9)){
            if(gameBoard[1][2].equals(" ") && gameBoard[0][2].equals(" ")){
                return 3;
            }
            if(gameBoard[2][1].equals(" ") && gameBoard[2][0].equals(" ")){
                return 7;
            }
        }

        // take the middle spot if it's empty
        if(gameBoard[1][1].equals(" ")){
            return 5;
        }

        // checks for any row that doesn't already have an obstruction from enemy
        for(int row = 0; row <= 2; row++){
            if(checkRow(gameBoard, row) != -1){
                switch(checkRow(gameBoard,row)){
                    case 0:
                        if(row == 0){
                            return 1;
                        } else if(row == 1) {
                            return 4;
                        } else {
                            return 7;
                        }
                    case 2:
                        if(row == 0){
                            return 3;
                        } else if(row == 1) {
                            return 6;
                        } else {
                            return 9;
                        }
                }
            }
        }

        // checks for columns that don't have obstructions
        for(int col = 0; col <= 2; col++){
            if(checkCol(gameBoard, col) != -1){
                switch(checkCol(gameBoard,col)){
                    case 0:
                        if(col == 0){
                            return 1;
                        } else if(col == 1) {
                            return 2;
                        } else {
                            return 3;
                        }
                    case 2:
                        if(col == 0){
                            return 7;
                        } else if(col == 1) {
                            return 8;
                        } else {
                            return 9;
                        }
                }
            }
        }

        //System.out.println("plz fix");
        return ThreadLocalRandom.current().nextInt(1,10);
    }
}