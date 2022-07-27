package connect4.connect4;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Scanner;

public class GameLogic
{
    private char[][] board = new char[6][7];
    protected boolean gameIsOver;
    private boolean turnIsOver;
    private boolean pieceWasAdded;
    private int input;
    private Button column1 = new Button();
    private Button column2 = new Button();
    private Button column3 = new Button();
    private Button column4 = new Button();
    private Button column5 = new Button();
    private Button column6 = new Button();
    private Button column7 = new Button();
    private Pane mainPane = new Pane();
    private Pane gameDisplay = new Pane();
    private int numberOfTurns = 0;
    private BoardDisplay boardDisplay = new BoardDisplay();
    private Text displayText = new Text("Click a spot, Player 1!");
    private Polygon polygon = new Polygon();
    private Button exit = new Button("Exit");
    private Button startOver = new Button("Start Over");
    private HBox topButtons = new HBox();
    private Line winLine = new Line(-10, -1000, 10, -1000);


    private void fillBoardWithEmpty()
    {
        // outer loop
        for (int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = 'E';
            }
        }
    }

    private void AddPiece(int location, int player)
    {
        // Initialize the piece color
        char piece = 'E';
        if (player == 1)
        {
            piece = 'R';
        }
        else
            piece = 'Y';
        // set the piece being added to false
        pieceWasAdded = false;
        int yValue = 0;
        while (!pieceWasAdded)
        {
            if (yValue == 5 && board[yValue][location - 1] != 'Y' && board[yValue][location - 1] != 'R')
            {
                board[yValue][location - 1] = piece;
                pieceWasAdded = true;
            }
            else if (board[yValue][location - 1] == 'E')
            {
                yValue++;
            }
            else
            {
                board[yValue - 1][location - 1] = piece;
                pieceWasAdded = true;
            }
        }
    }

    private void CheckForWinner()
    {
        for (int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == 'R')
                {
                    CheckSurroundingWinnings(i, j, 'R');
                }
                else if (board[i][j] == 'Y')
                {
                    CheckSurroundingWinnings(i, j, 'Y');
                }
            }
        }
        if (board[0][0] != 'E'&& board[0][0] != 'E'
                && board[0][1] != 'E'
                && board[0][2] != 'E'
                && board[0][3] != 'E'
                && board[0][4] != 'E'
                && board[0][5] != 'E'
                && board[0][6] != 'E')
        {
            displayText.setText("All spaces filled. It's a draw!");
            gameIsOver = true;
        }
    }

    private void MoveWinLine(int y1, int x1, int y2, int x2)
    {
        winLine.startXProperty().set(50 + (x1 * 100));
        winLine.startYProperty().set(50 + (y1 * 100));
        winLine.endXProperty().set(50 + (x2 * 100));
        winLine.endYProperty().set(50 + (y2 * 100));
    }

    private void CheckSurroundingWinnings(int i, int j, char team)
    {
        String color;
        if (team == 'R')
            color = "Red";
        else
            color = "Yellow";
        // check for up win
        if (i >= 3
                && (board[i][j] == team && board[i - 1][j] == team && board[i - 2][j] == team && board[i - 3][j] == team))
        {
            System.out.println("Up Win! Team " + color + " wins!");
            displayText.setText("Game Over. Team " + color + " wins!");
            MoveWinLine(i, j, i-3, j);
            gameIsOver = true;
        }
        // check for up right win
        else if ((i >= 3 && j <= 3)
                && (board[i][j] == team && board[i - 1][j + 1] == team && board[i - 2][j + 2] == team && board[i - 3][j + 3] == team))
        {
            System.out.println("Up Right Win! Team " + color + " wins!");
            displayText.setText("Game Over. Team " + color + " wins!");
            MoveWinLine(i, j, i-3, j+3);
            gameIsOver = true;
        }
        // check for right win
        else if ((j <= 2)
                && (board[i][j] == team && board[i][j + 1] == team && board[i][j + 2] == team && board[i][j + 3] == team))
        {
            System.out.println("Right win! Team " + color + " wins!");
            displayText.setText("Game Over. Team " + color + " wins!");
            MoveWinLine(i, j, i, j+3);
            gameIsOver = true;
        }
        // check for down right win
        else if ((j <= 3 && i <= 2)
                && (board[i][j] == team && board[i + 1][j + 1] == team && board[i + 2][j + 2] == team && board[i + 3][j + 3] == team))
        {
            System.out.println("Down right win! Team " + color + " wins!");
            displayText.setText("Game Over. Team " + color + " wins!");
            MoveWinLine(i, j, i+3, j+3);
            gameIsOver = true;
        }
    }

    public void UpdateBoardDisplay(BoardDisplay boardDisplay)
    {
        mainPane.getChildren().remove(gameDisplay);
        gameDisplay = boardDisplay.Display(board);

        mainPane.getChildren().add(gameDisplay);
        gameDisplay.toBack();
    }

    public void ButtonClickedAction(int column)
    {
        // First, figure out which player's turn it is
        int playerNumber = 0;
        int nextPlayerUp = 0;
        if (numberOfTurns % 2 == 1) {
            playerNumber = 2;
            nextPlayerUp = 1;
        }
        else {
            playerNumber = 1;
            nextPlayerUp = 2;
        }

        // Second, make sure the player didn't click a column that's already filled with game pieces
        if (board[0][column - 1] == 'E' && !gameIsOver) {
            System.out.println("Row " + column);
            AddPiece(column, playerNumber);
            UpdateBoardDisplay(boardDisplay);
            numberOfTurns++;

            CheckForWinner();
            if (gameIsOver) {
                polygon.setTranslateY(1000);
                fillBoardWithEmpty();
                numberOfTurns = 0;
            }
            else
                displayText.setText("Click a spot, Player " + nextPlayerUp + "!");
        }
        else if (board[0][column - 1] != 'E' && !gameIsOver)
            displayText.setText("Player " + playerNumber + ", please select an empty spot.");
        else
            System.out.println("Click Blocked");

    }

    public int RunGame(Stage stage)
    {
        mainPane.getChildren().add(winLine);
        winLine.setStrokeWidth(10);

        polygon.getPoints().addAll(new Double[]{
                0.0, 0.0,
                60.0, 0.0,
                30.0, 50.0 });
        mainPane.getChildren().add(polygon);
        polygon.setTranslateY(1000);

        VBox elements = new VBox();

        UpdateBoardDisplay(boardDisplay);

        column1.setPrefSize(100, 600);
        column1.setTranslateX(0);
        column1.setOnAction(e ->
        {
            ButtonClickedAction(1);
        });
        column1.setBackground(null);
        column1.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(0 + 20);
            }
        });
        column1.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        column2.setPrefSize(100, 600);
        column2.setTranslateX(100);
        column2.setOnAction(e ->
        {
            ButtonClickedAction(2);
        });
        column2.setBackground(null);
        column2.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(100 + 20);
            }
        });
        column2.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        column3.setPrefSize(100, 600);
        column3.setTranslateX(200);
        column3.setOnAction(e ->
        {
            ButtonClickedAction(3);
        });
        column3.setBackground(null);
        column3.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(200 + 20);
            }
        });
        column3.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        column4.setPrefSize(100, 600);
        column4.setTranslateX(300);
        column4.setOnAction(e ->
        {
            ButtonClickedAction(4);
        });
        column4.setBackground(null);
        column4.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(300 + 20);
            }
        });
        column4.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        column5.setPrefSize(100, 600);
        column5.setTranslateX(400);
        column5.setOnAction(e ->
        {
            ButtonClickedAction(5);
        });
        column5.setBackground(null);
        column5.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(400 + 20);
            }
        });
        column5.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        column6.setPrefSize(100, 600);
        column6.setTranslateX(500);
        column6.setOnAction(e ->
        {
            ButtonClickedAction(6);
        });
        column6.setBackground(null);
        column6.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(500 + 20);
            }
        });
        column6.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        column7.setPrefSize(100, 600);
        column7.setTranslateX(600);
        column7.setOnAction(e ->
        {
            ButtonClickedAction(7);
        });
        column7.setBackground(null);
        column7.setOnMouseEntered(e ->
        {
            if (!gameIsOver) {
                polygon.setTranslateY(30);
                polygon.setTranslateX(600 + 20);
            }
        });
        column7.setOnMouseExited(e ->
        {
            polygon.setTranslateY(1000);
        });

        mainPane.getChildren().addAll(column1, column2, column3, column4, column5, column6, column7);

        displayText.setFont(Font.font(40));

        startOver.setPrefWidth(350);
        startOver.setPrefHeight(50);
        startOver.setOnMouseClicked(e ->
        {
            gameIsOver = false;
            fillBoardWithEmpty();
            numberOfTurns = 0;
            UpdateBoardDisplay(boardDisplay);
            displayText.setText("Starting Over. Click a spot, Player 1!");
            winLine.setStartX(1000);
            winLine.setStartY(1000);
            winLine.setEndX(1001);
            winLine.setEndY(1001);
        });
        exit.setPrefWidth(350);
        exit.setPrefHeight(50);
        exit.setOnMouseClicked(e ->
        {
            Platform.exit();
        });
        topButtons.getChildren().addAll(exit, startOver);
        topButtons.setAlignment(Pos.TOP_CENTER);

        elements.getChildren().addAll(topButtons, displayText, mainPane);
        elements.setSpacing(11);
        elements.setAlignment(Pos.CENTER);
        Scene scene = new Scene(elements, 700, 700);
        stage.setTitle("Connect Four");
        stage.setScene(scene);
        stage.show();

        // initialize all the values
        gameIsOver = false;
        fillBoardWithEmpty();
        numberOfTurns = 0;
        turnIsOver = false;

        return 0;
    }
}