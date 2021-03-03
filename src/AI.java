package game;

import java.util.Random;


class AI {
    private final char DOT;
    private Random random;

    AI(char ch) {
        DOT = ch;
        random = new Random();
    }

    void turn(Field field) {
        int x, y;
        int rank = 0;
        int bestRank = 0;
        int bestI = -1, bestJ = -1;

        for (int i = 0; i < field.FIELD_SIZE; i++) {
            for (int j = 0; j < field.FIELD_SIZE; j++) {
                if (field.isCellValid(i, j)) {
                    if (i > 0 && field.map[i - 1][j] == DOT) {
                        ++rank;
                    }
                    if (i > 0 && j < field.FIELD_SIZE - 1 && field.map[i - 1][j + 1] == DOT) {
                        ++rank;
                    }
                    if (j < field.FIELD_SIZE - 1 && field.map[i][j + 1] == DOT) {
                        ++rank;
                    }
                    if (i < field.FIELD_SIZE - 1 && j < field.FIELD_SIZE - 1 && field.map[i + 1][j + 1] == DOT) {
                        ++rank;
                    }
                    if (i < field.FIELD_SIZE - 1 && field.map[i + 1][j] == DOT) {
                        ++rank;
                    }
                    if (i < field.FIELD_SIZE - 1 && j > 0 && field.map[i + 1][j - 1] == DOT) {
                        ++rank;
                    }
                    if (j > 0 && field.map[i][j - 1] == DOT) {
                        ++rank;
                    }
                    if (i > 0 && j > 0 && field.map[i - 1][j - 1] == DOT) {
                        ++rank; }
                }

                if (rank > bestRank) {
                    bestRank = rank;
                    bestI = i;
                    bestJ = j;
                }
                rank = 0;
            }
        }

        if (bestRank == 0) {
            do {
                x = random.nextInt(field.getSize());
                y = random.nextInt(field.getSize());
            } while (!field.isCellValid(x, y));
        }
        else {
            x = bestI;
            y = bestJ;
        }

        field.setDot(x, y, DOT);
    }
}
