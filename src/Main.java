import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final String NAME_OF_FILE = "nonogram.txt";

    public static void main(String[] args) {


        //TODO some function called check rules (x,y)

        //TOOD maybe something like history of all he moves - leads to a list of grids - DON'T FLIPPING FORGET TO CLONE THE WHOLE THING

        //go through every row, then go through every column


        Grid grid = createGrid(NAME_OF_FILE);


//        grid.infoCols.set(0, new int[]{0});

        int[] firstColInfo = grid.infoCols.get(0);
        Square[] gridCol = grid.getGridColumn(0);

        for (int i = 2; i < 3; i++) {
            gridCol[i].status = SquareStatus.BLACK;
        }
        for (int i = 0; i < 2; i++) {
            gridCol[i].status = SquareStatus.WHITE;
        }

        System.out.println("firstColInfo = " + Arrays.toString(firstColInfo));
        System.out.println("grid.isContradicting(x) = " + grid.isContradicting(0));

//        grid.solve();




        ImageCreator.createImage(grid);


    }

    static Grid createGrid(String nameOfFile) {
        List<String> lines = NonogramLoader.loadNonogram(new File(nameOfFile));
        return Grid.generateGrid(lines);
    }


}