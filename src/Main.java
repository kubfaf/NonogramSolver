import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final String NAME_OF_FILE = "nonogram.txt";

    public static void main(String[] args) {

        //Create grid from a file
        Grid grid = createGrid(NAME_OF_FILE);


//        grid.infoCols.set(0, new int[]{0});

        int[] firstColInfo = grid.infoCols.get(0);
        Square[] gridCol = grid.getGridColumn(0);

        for (int i = 2; i < 5; i++) {
            gridCol[i].status = SquareStatus.BLACK;
        }
        for (int i = 0; i < 2; i++) {
            gridCol[i].status = SquareStatus.WHITE;
        }
        gridCol[5].status = SquareStatus.WHITE;
        gridCol[6].status = SquareStatus.WHITE;

        gridCol[7].status = SquareStatus.BLACK;

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