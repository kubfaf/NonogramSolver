import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    static final String ROW_SEPARATOR = "SEPARATOR";

    Square[][] squares;
    ArrayList<int[]> infoRows;
    ArrayList<int[]> infoCols;


    public Grid() {


    }


    void solve() {




    }

    boolean isContradicting(int x){

        Square[] columnToTest = getGridColumn(x);
        int[] info = infoCols.get(x);

        int howManyStarts = 0;
        int howManyEnds = 0;

        System.out.println("columnToTest = " + Arrays.toString(columnToTest));

        //find the first one, then the next one ....
        //there must be at least one space between them
        //then check if the rest can fit

        int lastIndexChecked = 0;
        int firstIndexOfBlackSquare = -1;
        mainLoop:for (int i = 0; i < info.length; i++) {

            int howManyToFindInARow = info[i];
            firstIndexOfBlackSquare = -1;
            int lastIndexOfBlackSquare = -2;

            for (; lastIndexChecked < columnToTest.length; lastIndexChecked++) {

                //FIXME WHAT IF EVERYTHING IS WHITE

                //also check for blank squares. If they are blank it means that the row after is not generated yet.
                if (columnToTest[lastIndexChecked].status == SquareStatus.EMPTY){
                    //TODO check if the rest can still fit
                    //TODO maybe break the main for loop?
                    break mainLoop;
                }


                if(columnToTest[lastIndexChecked].status == SquareStatus.BLACK){
                    //FOUND THE FIRST ONE
                    firstIndexOfBlackSquare = lastIndexChecked;
                    howManyStarts++;
                    break;
                }
            }

            //FIXME what if the thing is not finished yet?
            //FIXME what if i am looking for 4 but i only find 2?

            //FIXME ALSO what if i find nothing? for example empty column?


            //find the last piece
            for (; lastIndexChecked < columnToTest.length; lastIndexChecked++) {
                //the next one has to be empty, or it has to be an edge (the end of an array)
                if((columnToTest[lastIndexChecked].status == SquareStatus.BLACK) && (lastIndexChecked+1 == columnToTest.length ||  columnToTest[lastIndexChecked + 1 ].status != SquareStatus.BLACK )){
                    //FOUND THE LAST ONE
                    lastIndexOfBlackSquare = lastIndexChecked;
                    howManyEnds++;
                    break;
                }
            }

            //can be smaller or equal, but mustn't be bigger
            // +1 (found the same index twice - the block is size 1. But lastIndexOfBlackSquare - firstIndexOfBlackSquare would say 0)
            if((lastIndexOfBlackSquare - firstIndexOfBlackSquare + 1 ) > howManyToFindInARow){
                //it is bigger
                return true;
            }
            //TODO what if it does not find anything? Will it say it found length 1?  Do i have to check for it or something?

            //then there has to be at least ONE FREE SPACE (unless the end of an array)
            //I have to find a space, but only if the last one in not at the end

            //if the last index is at the end, then it is fine
            //if NOT, then check if the next one on the right is empty or white (not black)
            if(((lastIndexOfBlackSquare + 1) == columnToTest.length) && (columnToTest[lastIndexOfBlackSquare+1].status != SquareStatus.BLACK)){
                return true;
            }
        }

        //TODO check if the remaining blocks can fit.
        //TODO IF the last block is not finished, it needs to be finished before counting the rest
        //TODO also i think i need to know how many of them are remaining.
        //TODO also i need to know if the last one is fully placed or not
        //TODO if they can't fit, then return false
        //TODO if they can fit, return true

        //if they are NOT equal, then move 'lastIndexChecked' to the imaginary end of the unfinished block.
        if(howManyStarts != howManyEnds){
            //TODO


        }


        return false;
    }


    Square[] getGridColumn(int column) {
        Square[] columns = new Square[infoCols.size()];
        for (int i = 0; i < squares.length; i++) {
            columns[i] = squares[column][i];
        }
        return columns;
    }

    Square[] getGridRows(int row) {
        Square[] rows = new Square[infoRows.size()];
        for (int i = 0; i < squares.length; i++) {
            rows[i] = squares[i][row];
        }
        return rows;
    }


    public static Grid generateGrid(List<String> lines) {
        ArrayList<int[]> infoRows = new ArrayList<>();
        ArrayList<int[]> infoCols = new ArrayList<>();
        int lineCounter = 0;
        for (; lineCounter < lines.size(); lineCounter++) {
            String oneLine = lines.get(lineCounter);
            if (oneLine.equals(ROW_SEPARATOR)) {
                break;
            }
            String[] numbersInString = oneLine.split(",");
            int[] numbers = new int[numbersInString.length];
            for (int j = 0; j < numbersInString.length; j++) {
                numbers[j] = Integer.parseInt(numbersInString[j]);
            }
            infoRows.add(numbers);
        }
        lineCounter++;
        for (; lineCounter < lines.size(); lineCounter++) {
            String oneLine = lines.get(lineCounter);
            String[] numbersInString = oneLine.split(",");
            int[] numbers = new int[numbersInString.length];
            for (int j = 0; j < numbersInString.length; j++) {
                numbers[j] = Integer.parseInt(numbersInString[j]);
            }
            infoCols.add(numbers);
        }
        Grid g = new Grid();
        g.infoCols = infoCols;
        g.infoRows = infoRows;
        g.squares = new Square[infoCols.size()][infoRows.size()];
        for (int i = 0; i < g.squares.length; i++) {
            for (int j = 0; j < g.squares[0].length; j++) {
                g.squares[i][j] = new Square();
            }
        }
        return g;
    }
}
