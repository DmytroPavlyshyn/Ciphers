package com.company.lab2;

import java.awt.*;

public final class PolibiyKeyTable {

    public final static char[][] polibiyKeyTable =
                    {
                            {'m', 'e', 't', 'h', 'o', 'd'},
                            {'a', 'b', 'c', 'f', 'g', 'i'},
                            {'j', 'k', 'l', 'n', 'p', 'q'},
                            {'r', 's', 'u', 'v', 'w', 'x'},
                            {'y','z'}
                    };
    // key word: method




    public Point find(char beforeEncryption) {
        for (int i = 0; i < polibiyKeyTable.length; i++) {
            for (int j = 0; j < polibiyKeyTable[i].length; j++) {
                if (polibiyKeyTable[i][j] == beforeEncryption) return new Point(i, j);
            }
        }
        return null;
    }

    public char charToEncrypt(Point toEncrypt) {
        if (toEncrypt == null) {
            return ' ';
        }
        if (toEncrypt.x == 0 && toEncrypt.y == 0) return polibiyKeyTable[polibiyKeyTable.length - 1][toEncrypt.y];
        else if (toEncrypt.x == 0 && toEncrypt.y != 0) return polibiyKeyTable[polibiyKeyTable.length - 2][toEncrypt.y];
        else return polibiyKeyTable[toEncrypt.x - 1][toEncrypt.y];
    }

    public char charToDecrypt(Point toDecrypt) {
        if (toDecrypt == null) {
            return ' ';
        }
        if (toDecrypt.x == polibiyKeyTable.length - 1 && toDecrypt.y == 0) return polibiyKeyTable[0][toDecrypt.y];
        else if (toDecrypt.x == polibiyKeyTable.length - 2) return polibiyKeyTable[0][toDecrypt.y];
        else return polibiyKeyTable[toDecrypt.x + 1][toDecrypt.y];
    }
}
