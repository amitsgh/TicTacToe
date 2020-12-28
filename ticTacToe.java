import java.util.*;

public class ticTacToe {

  static ArrayList<Integer> humanPositions = new ArrayList<Integer>();
  static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

  // make a game board
  static char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, 
                                { '-', '+', '-', '+', '-' }, 
                                { ' ', '|', ' ', '|', ' ' },
                                { '-', '+', '-', '+', '-' }, 
                                { ' ', '|', ' ', '|', ' ' } };
  public static void main(String[] args) {

    while (true){
      
      Scanner scan = new Scanner(System.in);
      // Choose the position
      System.out.println("Choose the position from 1 to 9:");
      int position = scan.nextInt();

      // for human if position is taken re-enter it
      while (humanPositions.contains(position) || computerPositions.contains(position)){
        System.out.println("Position taken! Please re-enter the position.");
        position = scan.nextInt();
      }
      // move by Humans
      playPiece(position, "human");

      // check for result if human wins
      String result = checkWinner();
      if (result.length() > 0){
        printGameBoard();
        System.out.println(result);
        break;
      }

      // random position by computer
      Random randomPosition = new Random();
      int machinePosition = randomPosition.nextInt(9) + 1;

      // for computer if position is taken re-enter it
      while (humanPositions.contains(machinePosition) || computerPositions.contains(machinePosition)){
        machinePosition = randomPosition.nextInt(9) +1;
      }
      // move by Computer
      playPiece(machinePosition, "computer");

      // print the game board
      printGameBoard();

      // check for result if computer wins
      result = checkWinner();
      if (result.length() > 0) {
        printGameBoard();
        System.out.println(result);
        break;
      }
      
      // close the scan to prevent leakage 
      scan.close();
    }
  }

  // Placing Pieces to their Positions
  public static void playPiece(int position, String user) {

    char symbol = ' ';

    //check for human or computer
    if (user.equals("human")){
      symbol = 'X';
      humanPositions.add(position);
    } 
    else {
      symbol = 'O';
      computerPositions.add(position);
    }

    //placing the piece into the position
    switch (position) {
      case 1:
        gameBoard[0][0] = symbol;
        break;
      case 2:
        gameBoard[0][2] = symbol;
        break;
      case 3:
        gameBoard[0][4] = symbol;
        break;
      case 4:
        gameBoard[2][0] = symbol;
        break;
      case 5:
        gameBoard[2][2] = symbol;
        break;
      case 6:
        gameBoard[2][4] = symbol;
        break;
      case 7:
        gameBoard[4][0] = symbol;
        break;
      case 8:
        gameBoard[4][2] = symbol;
        break;
      case 9:
        gameBoard[4][4] = symbol;
        break;
      default:
        break;
    }
  }

  // Checking The condition for Winning
  public static String checkWinner(){
    
    List<Integer> topRow = Arrays.asList(1, 2, 3);
    List<Integer> middleRow = Arrays.asList(4, 5, 6);
    List<Integer> bottomRow = Arrays.asList(7, 8, 9);
    List<Integer> leftColumn = Arrays.asList(1, 4, 7); 
    List<Integer> middleColumn = Arrays.asList(2, 5, 8);
    List<Integer> rightColumn = Arrays.asList(3, 6, 9);
    List<Integer> straightDiagonal = Arrays.asList(1, 5, 9);
    List<Integer> reverseDiagnoal = Arrays.asList(7, 5, 3);
    
    List<List<Integer>> winningConditions = new ArrayList<List<Integer>>();
    winningConditions.add(topRow);
    winningConditions.add(middleRow);
    winningConditions.add(bottomRow);
    winningConditions.add(leftColumn);
    winningConditions.add(middleColumn);
    winningConditions.add(rightColumn);
    winningConditions.add(straightDiagonal);
    winningConditions.add(reverseDiagnoal);

    for (List<Integer> l: winningConditions){
      if (humanPositions.containsAll(l)){
        return "Congralutions! You Won!!!";

      } 
      else if (computerPositions.containsAll(l)){
        return "Computer Won! Better Luck Next Time :(";
      } else if (humanPositions.size() + computerPositions.size() > 9){
        return "Draw!!";
      }
    }
    return "";
  }

  /*
      Print Game Board
       | | 
      -+-+-
       | | 
      -+-+-
       | |
   */
  public static void printGameBoard() {
    for (char[] row : gameBoard) {
      for (char col : row) {
        System.out.print(col);
      }
      System.out.println();
    }
  }
}
