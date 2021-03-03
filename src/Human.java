package game;

class Human {
    private final char DOT;

    Human(char ch) { DOT = ch; }

    void turn(int x, int y, Field field, AI ai) {
        if (field.isCellValid(x, y)) {
            if (!field.isGameOver()) field.setDot(x, y, DOT);
            if (!field.isGameOver()) ai.turn(field);
        }
    }
}
