import java.util.ArrayList;
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

        //find the first one, then the next one ....
        //there must be at least one space between them
        //then check if the rest can fit

        int lastIndexChecked = 0;
        for (int i = 0; i < info.length; i++) {

            int howManyToFindInARow = info[i];
            int firstIndexOfBlackSquare = 0;
            int lastIndexOfBlackSquare = 0;

            for (; lastIndexChecked < columnToTest.length; lastIndexChecked++) {
                if(columnToTest[lastIndexChecked].status == SquareStatus.BLACK){
                    //FOUND THE FIRST ONE
                    firstIndexOfBlackSquare = lastIndexChecked;
                    break;
                }
            }

            //find the last piece
            for (int j = lastIndexChecked; j < columnToTest.length; j++) {
                //the next one has to be empty, or it has to be an edge (the end of an array)
                if((columnToTest[lastIndexChecked].status == SquareStatus.BLACK) && (lastIndexChecked == columnToTest.length-1 ||  columnToTest[lastIndexChecked + 1 ].status == SquareStatus.WHITE )){
                    //FOUND THE LAST ONE
                    lastIndexOfBlackSquare = lastIndexChecked;
                    break;
                }
            }





            //can be smaller or equal, but mustn't be bigger
            if()





        }









        return false;
    }

    boolean isConstraintSatisfied(int x, int y) {
        Square[] col = getGridColumn(x);
        Square[] row = getGridRows(y);

        int[] infoCol = infoCols.get(x);
        int[] infoRow = infoRows.get(y);

        for (int i = 0; i < infoCol.length; i++) {
            int howManyToFindInARow = infoCol[i];
            int j = 0;
            for (; j < col.length; j++) {
                Square colSquare = col[j];
                if (colSquare.status == SquareStatus.BLACK) {
                    break;
                }
            }
            int a = j + howManyToFindInARow;
            for (; j < a; j++) {
                Square colSquare = col[j];
                if (colSquare.status != SquareStatus.BLACK) {
                    return false;
                }
            }

            //dont forget about a space, if there is no space after it, then return false
            //BUT if it is on the end of the array, then don't check



        }


        return true;

    }


    void solveFirstFail() {


        for (int i = 0; i < infoRows.size(); i++) {
            Square[] myRow = getGridRows(i);
            int[] infoRow = infoRows.get(i);

            int[] fromLeft = new int[myRow.length];
            int[] fromRight = new int[myRow.length];

            //From left
            int position = 0;
            for (int j = 0; j < infoRow.length; j++) {
                int numberOfBlocksToPlace = infoRow[j];
                int k = 0;
                for (; k < numberOfBlocksToPlace; k++) {
                    fromLeft[k + position] = j + 1;
                }
                position = k + 1 + position;
            }


            //FIXME stupid
            //From RIGHT
            position = 0;
            for (int j = infoRow.length - 1; j >= 0; j--) {
                int numberOfBlocksToPlace = infoRow[j];
                int k = 0;
                for (; k < numberOfBlocksToPlace; k++) {
                    fromRight[k + position] = j + 1;
                }
                position = k + 1 + position;
            }

            //dumb fix cause lazy
            for (int j = 0; j < fromRight.length / 2; j++) {
                int tmp = fromRight[j];
                fromRight[j] = fromRight[fromRight.length - 1 - j];
                fromRight[fromRight.length - 1 - j] = tmp;
            }


            //Check if the values are the same, if they are the same PLACE A BLACK SQUARE
            for (int j = 0; j < fromLeft.length; j++) {
                if ((fromRight[j] == fromLeft[j]) && fromLeft[j] != 0) {
                    myRow[j].status = SquareStatus.BLACK;
                }
            }


        }


        for (int i = 0; i < infoCols.size(); i++) {
            Square[] myRow = getGridColumn(i);
            int[] infoRow = infoCols.get(i);

            int[] fromLeft = new int[myRow.length];
            int[] fromRight = new int[myRow.length];

            //From left
            int position = 0;
            for (int j = 0; j < infoRow.length; j++) {
                int numberOfBlocksToPlace = infoRow[j];
                int k = 0;
                for (; k < numberOfBlocksToPlace; k++) {
                    fromLeft[k + position] = j + 1;
                }
                position = k + 1 + position;
            }


            //FIXME stupid
            //From RIGHT
            position = 0;
            for (int j = infoRow.length - 1; j >= 0; j--) {
                int numberOfBlocksToPlace = infoRow[j];
                int k = 0;
                for (; k < numberOfBlocksToPlace; k++) {
                    fromRight[k + position] = j + 1;
                }
                position = k + 1 + position;
            }

            //dumb fix cause lazy
            for (int j = 0; j < fromRight.length / 2; j++) {
                int tmp = fromRight[j];
                fromRight[j] = fromRight[fromRight.length - 1 - j];
                fromRight[fromRight.length - 1 - j] = tmp;
            }


            //Check if the values are the same, if they are the same PLACE A BLACK SQUARE
            for (int j = 0; j < fromLeft.length; j++) {
                if ((fromRight[j] == fromLeft[j]) && fromLeft[j] != 0) {
                    myRow[j].status = SquareStatus.BLACK;
                }
            }


        }
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
