package connect4.connect4;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardDisplay
{
    public Pane Display(char[][] board)
    {
        Pane mainPane = new Pane();

        Tile aa = new Tile(String.valueOf(board[0][0]));
        Tile ba = new Tile(String.valueOf(board[0][1]));
        Tile ca = new Tile(String.valueOf(board[0][2]));
        Tile da = new Tile(String.valueOf(board[0][3]));
        Tile ea = new Tile(String.valueOf(board[0][4]));
        Tile fa = new Tile(String.valueOf(board[0][5]));
        Tile ga = new Tile(String.valueOf(board[0][6]));

        HBox a = new HBox(aa, ba, ca, da, ea, fa, ga);
        a.setSpacing(-50);

        Tile ab = new Tile(String.valueOf(board[1][0]));
        Tile bb = new Tile(String.valueOf(board[1][1]));
        Tile cb = new Tile(String.valueOf(board[1][2]));
        Tile db = new Tile(String.valueOf(board[1][3]));
        Tile eb = new Tile(String.valueOf(board[1][4]));
        Tile fb = new Tile(String.valueOf(board[1][5]));
        Tile gb = new Tile(String.valueOf(board[1][6]));

        HBox b = new HBox(ab, bb, cb, db, eb, fb, gb);
        b.setSpacing(-50);

        Tile ac = new Tile(String.valueOf(board[2][0]));
        Tile bc = new Tile(String.valueOf(board[2][1]));
        Tile cc = new Tile(String.valueOf(board[2][2]));
        Tile dc = new Tile(String.valueOf(board[2][3]));
        Tile ec = new Tile(String.valueOf(board[2][4]));
        Tile fc = new Tile(String.valueOf(board[2][5]));
        Tile gc = new Tile(String.valueOf(board[2][6]));

        HBox c = new HBox(ac, bc, cc, dc, ec, fc, gc);
        c.setSpacing(-50);

        Tile ad = new Tile(String.valueOf(board[3][0]));
        Tile bd = new Tile(String.valueOf(board[3][1]));
        Tile cd = new Tile(String.valueOf(board[3][2]));
        Tile dd = new Tile(String.valueOf(board[3][3]));
        Tile ed = new Tile(String.valueOf(board[3][4]));
        Tile fd = new Tile(String.valueOf(board[3][5]));
        Tile gd = new Tile(String.valueOf(board[3][6]));

        HBox d = new HBox(ad, bd, cd, dd, ed, fd, gd);
        d.setSpacing(-50);

        Tile ae = new Tile(String.valueOf(board[4][0]));
        Tile be = new Tile(String.valueOf(board[4][1]));
        Tile ce = new Tile(String.valueOf(board[4][2]));
        Tile de = new Tile(String.valueOf(board[4][3]));
        Tile ee = new Tile(String.valueOf(board[4][4]));
        Tile fe = new Tile(String.valueOf(board[4][5]));
        Tile ge = new Tile(String.valueOf(board[4][6]));

        HBox e = new HBox(ae, be, ce, de, ee, fe, ge);
        e.setSpacing(-50);

        Tile af = new Tile(String.valueOf(board[5][0]));
        Tile bf = new Tile(String.valueOf(board[5][1]));
        Tile cf = new Tile(String.valueOf(board[5][2]));
        Tile df = new Tile(String.valueOf(board[5][3]));
        Tile ef = new Tile(String.valueOf(board[5][4]));
        Tile ff = new Tile(String.valueOf(board[5][5]));
        Tile gf = new Tile(String.valueOf(board[5][6]));

        HBox f = new HBox(af, bf, cf, df, ef, ff, gf);
        f.setSpacing(-50);

        VBox allRows = new VBox(a, b, c, d, e, f);
        allRows.setSpacing(-50);
        allRows.setAlignment(Pos.CENTER);

        mainPane.getChildren().addAll(allRows);

        return mainPane;
    }
}
