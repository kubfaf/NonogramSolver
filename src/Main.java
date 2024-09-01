import java.io.File;
import java.util.List;

public class Main {

    static final String NAME_OF_FILE = "nonogram.txt";

    public static void main(String[] args) {


        //TODO some function called check rules (x,y)

        //TODO guessing timestamps?
        //TOOD maybe something like history of all he moves - leads to list of grids - DONT FLIPPING FORGET TO CLODE THE WHOLE THING

        //go through every row, then go through every column


        Grid grid = createGrid(NAME_OF_FILE);

        grid.solve();




        ImageCreator.createImage(grid);


    }

    static Grid createGrid(String nameOfFile) {
        List<String> lines = NonogramLoader.loadNonogram(new File(nameOfFile));
        return Grid.generateGrid(lines);
    }


}