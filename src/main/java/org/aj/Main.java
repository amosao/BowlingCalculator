package org.aj;

import org.aj.classes.BowlingGame;
import org.aj.objects.PlayerScore;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner inputFile;

    public static void main( String[] args ) throws Exception {

        if ( args.length == 0 ) {
            System.out.println( "File name not specified. " );
            System.exit(1);
        }

        try {
            File file = new File( args[0] );
            inputFile = new Scanner( file );
        } catch (IOException ioException) {
            System.err.println( "Cannot open file" );
            System.exit(1);
        }

        BowlingGame game = BowlingGame.parse( inputFile );

        System.out.println( game.gameToString() );

    }

}
