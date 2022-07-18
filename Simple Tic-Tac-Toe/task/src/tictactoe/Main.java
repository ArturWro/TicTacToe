package tictactoe;

import java.util.Scanner;

public class Main {

    public static boolean checkRowCol(char c1, char c2, char c3){
        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
    }

    public static boolean checkRowsForWin(char [][] board){
        for (int i = 0; i < 3; i++){
            if (checkRowCol(board[i][0], board[i][1], board[i][2])){
                return true;
            }
        }
        return false;
    }

    public static boolean checkColForWin(char[][] board){
        for (int i = 0; i < 3; i++){
            if (checkRowCol(board[0][i], board[1][i], board[2][1])){
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonalForWin(char[][] board){
        boolean negativeDirection = checkRowCol(board[0][0], board[1][1], board[2][2]);
        boolean positiveDirection = checkRowCol(board[0][2], board[1][1], board[2][0]);

        if (negativeDirection || positiveDirection){
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkForWin(char[][] board){
        return (checkColForWin(board) || checkRowsForWin(board) || checkDiagonalForWin(board));
    }

    public static boolean boardFull(char[][] board){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public static char playerHasWon(char[][] board){
        for (int i = 0; i < 3; i++){
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ' ){
                return board[i][0];
            }
        }
        for (int j = 0; j < 3; j++){
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ' ){
                return board[0][j];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' '){
            return board[1][1];
        }

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != ' '){
            return board[1][1];
        }

        return ' ';
    }

    public static boolean hasOnlyDigit(String string){
        char tmp;
        for (int i = 0; i < string.length(); i++){
            tmp = string.charAt(i);
            if(!Character.isDigit(tmp)){
                return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board){
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static char[][] initializedBoard(char[][] board){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j]=' ';
            }
        }
        return board;
    }

    public static char changePlayer(char currentPlayerMark){
        if (currentPlayerMark == 'X'){
            return currentPlayerMark = 'O';
        } else
            return currentPlayerMark = 'X';
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        char[][] board = new char[3][3];
        board = initializedBoard(board);
        printBoard(board);
        char currentPlayerMark = 'X';

        boolean complete = true;
        boolean flag = true;

        do {
            System.out.println("Enter the coordinates: ");
            String userInput = scanner.nextLine();
            String intStr = userInput.replace(" ", "");
            if (!hasOnlyDigit(intStr)){
                System.out.println("You should enter numbers!");
                flag = true;
            } else {
                String[] temp = userInput.split(" ");
                int x = Integer.parseInt(temp[0]);
                int y = Integer.parseInt(temp[1]);

                if (x > 3 || x < 1 || y > 3 || y < 1){
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag =  true;
                } else {
                    if (board[3-y][x-1] == 'X' || board[3-y][x-1] == 'O'){
                        System.out.println("This cell is occupied! Choose another one!");
                        flag = true;
                    } else {
                        board[3-y][x-1] = currentPlayerMark;
                        flag = false;
                        currentPlayerMark = changePlayer(currentPlayerMark);
                        printBoard(board);
                        if (checkForWin(board)){
                            char winner = playerHasWon(board);
                            System.out.println(winner + " wins");
                            complete = false;
                        } else {
                            if (boardFull(board)){
                                System.out.println("Draw");
                                complete = false;
                            } else {
                                complete = true;
                            }
                        }
                    }
                }
            }

        } while (complete || flag);
    }
}
