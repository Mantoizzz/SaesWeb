package com.aes.saesweb;


/**
 * @author Mantozi
 */
public class Constants {
    public final static int[] RoundConstants1 = {8, 0};

    public final static int[] RoundConstants2 = {3, 0};

    public final static int[][] SubstitutionBox = {
            {9, 4, 10, 11},
            {13, 1, 8, 5},
            {6, 2, 0, 3},
            {12, 14, 15, 7}
    };

    public final static int[][] ReverseSubstitutionBox = {
            {10, 5, 9, 11},
            {1, 7, 8, 15},
            {6, 0, 2, 3},
            {12, 4, 13, 14}
    };

    public final static int[][] ConfusionMatrix = {
            {1, 4},
            {4, 1}
    };

    public final static int[][] ReverseConfusionMatrix = {
            {9, 2},
            {2, 9}
    };
}
