package com.ipiecoles.java.eval2x0;

import com.ipiecoles.java.java210.Sudoku;

public class main {

    public static void main(String[] args){
        Sudoku monSudoku = new Sudoku();
        System.out.print("Entrez vos valeurs et terminez par 'FIN'");
        String[] cooredonnees = monSudoku.demandeCoordonneesSudoku();
        monSudoku.remplitSudokuATrous(cooredonnees);
        monSudoku.ecrireSudoku(monSudoku.getSudokuAResoudre());
    }

}
