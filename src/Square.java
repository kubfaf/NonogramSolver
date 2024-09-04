enum SquareStatus {

    WHITE, BLACK, EMPTY
}


public class Square {

    SquareStatus status = SquareStatus.EMPTY;

    public Square() {

    }

    public Square(SquareStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status.name();
    }
}
