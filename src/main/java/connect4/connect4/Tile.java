package connect4.connect4;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane
{
    private Rectangle background = new Rectangle();
    private Circle piece;
    private Circle highlight;

    public Tile(String type)
    {
        piece = new Circle();
        highlight = new Circle();
        // Find out which color the piece is and assign it
        if (type.equals("Y")) {
            piece.setFill(Color.GOLDENROD);
            highlight.setFill(Color.GOLD);
        }
        else if (type.equals("R")) {
            piece.setFill(Color.DARKRED);
            highlight.setFill(Color.RED);
        }
        else {
            piece.setFill(Color.DARKGREY);
            highlight.setFill(Color.DARKGREY);
        }
        // set highlight positioning
        highlight.setRadius(40);
        highlight.setTranslateX(50);
        highlight.setTranslateY(50);
        // set piece positioning
        piece.setRadius(50);
        piece.setTranslateX(50);
        piece.setTranslateY(50);

        // Set the fill of the background to grey
        background.setFill(Color.GREY);
        background.setWidth(100);
        background.setHeight(100);

        this.getChildren().addAll(background, piece, highlight);
    }


}
