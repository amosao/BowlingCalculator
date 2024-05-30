package org.aj.classes;

import org.aj.objects.Ball;
import org.aj.objects.Frame;
import org.aj.objects.PlayerScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BowlingGame {

    private List<PlayerScore> game;

    public BowlingGame( List<PlayerScore> game ) {
        this.game = game;
        calculatePoints( game );
    }

    private void calculatePoints( List<PlayerScore> game ) {
        // Looping through the game's player score
        game.forEach( playerScore -> {

            // Looping through the player's frames
            for ( int i = 0; i < playerScore.getFrames().size(); i++ ) {
                Frame frame = playerScore.getFrames().get( i );

                // Variable holding frame's sum of points
                int sum = 0;

                // Condition to add the previous frame's score
                if ( frame.getFrame() != 1 ) {
                    sum += playerScore.getFrames().get( i - 1 ).getTotalScore();
                }

                // Processing frames [1-9]'s points
                if ( frame.getFrame() != 10 ) {
                    // Spare = true
                    if ( frame.isSpare() ) {
                        sum += 10 + playerScore.getFrames().get( i + 1 ).getFirstThrow().getIntValue();
                    }
                    // Strike = true
                    else if ( frame.isStrike() ) {
                        // Strike = true | (Next frame) Strike = true | (Next frame) Frame != 10
                        if ( playerScore.getFrames().get( i + 1 ).isStrike() && playerScore.getFrames().get( i + 1 ).getFrame() != 10 ) {
                            sum += 10 + 10 + playerScore.getFrames().get( i + 2 ).getFirstThrow().getIntValue();
                        }
                        // Strike = true | (Next frame) strike = false
                        else sum += 10 + playerScore.getFrames().get( i + 1 ).getFirstThrow().getIntValue()
                                    + playerScore.getFrames().get( i + 1 ).getSecondThrow().getIntValue();

                    }
                    // strike = false | spare = false
                    else sum += frame.getFirstThrow().getIntValue() + frame.getSecondThrow().getIntValue();

                }
                // Processing the last frame's points
                // Strike = true | Spare = false
                else if ( frame.isStrike() ) {
                    sum += 10 + frame.getSecondThrow().getIntValue() + frame.getLastThrow().getIntValue();
                }
                // Strike = false | Spare = true
                else if ( frame.isSpare() ) {
                    sum += 10 + frame.getLastThrow().getIntValue();
                }
                // Strike = false | Spare = false
                else sum += frame.getFirstThrow().getIntValue() + frame.getSecondThrow().getIntValue();

                // Storing the points to frame
                playerScore.getFrames().get( i ).setTotalScore( sum );

            }
        });
    }

    public String gameToString() {
        StringBuilder pointsTable = new StringBuilder();
        String tableRow = "+===========+===========+===========+===========+===========+===========+===========+===========+===========+===========+===========+\n";

        pointsTable.append( tableRow );
        pointsTable.append( "| Name      |     1     |     2     |     3     |     4     |     5     |     6     |     7     |     8     |     9     |     10    |\n" );
        pointsTable.append( tableRow );

        // Looping through the game's players
        this.game.forEach( playerScore -> {
            StringBuilder nameAndPoints = new StringBuilder();
            StringBuilder totals = new StringBuilder();

            nameAndPoints.append( "| " ).append( String.format( "%-10s", playerScore.getName() ) );
            totals.append( "|" ).append( String.format( "%-11s", "" ) );

            // Looping through the player's frames
            for ( int i = 0; i < playerScore.getFrames().size(); i++ ) {
                Frame frame = playerScore.getFrames().get( i );

                // Processing last frame
                if( playerScore.getFrames().size() == ( i + 1 ) ) {
                    nameAndPoints.append( String.format( "|%3s", frame.getFirstThrow().getStrValue() ) )
                            .append( String.format( "%1$1s %2$-3s", "", frame.getSecondThrow().getStrValue() ) )
                            .append( String.format( "%-3s|", frame.getLastThrow().getStrValue() ) );
                    totals.append( "|" ).append( String.format( "%1$3s %2$-7s|", "", frame.getTotalScore() ) );
                }
                // Processing frames 1-9
                else {
                    nameAndPoints.append( String.format( "|%3s", frame.getFirstThrow().getStrValue() ) )
                            .append( String.format( "%1$4s %2$-3s", "", frame.getSecondThrow().getStrValue() ) );
                    totals.append( "|" ).append( String.format( "%1$3s %2$-7s", "", frame.getTotalScore() ) );
                }
            }

            // Appending strings to StringBuilder
            pointsTable.append( nameAndPoints ).append( "\n" );
            pointsTable.append( totals ).append( "\n" );
            pointsTable.append( tableRow );
        } );

        return pointsTable.toString();
    }

    public static BowlingGame parse(Scanner file) throws Exception {
        HashMap<String, ArrayList<Ball>> playerScore = new HashMap<>();
        HashMap<String, ArrayList<Frame>> gameScore = new HashMap<>();
        List<PlayerScore> game = new ArrayList<>();

        while ( file.hasNextLine() ) {
            String name = file.next();

            if ( !playerScore.containsKey( name ) && !gameScore.containsKey( name ) ) {
                playerScore.put( name, new ArrayList<>() );
                gameScore.put( name, new ArrayList<>() );
            }

            String roll = file.next();
            boolean isNumber = roll.chars().allMatch( Character::isDigit );

            if ( isNumber ) {
                Integer intRoll = Integer.parseInt( roll );
                playerScore.get( name ).add( new Ball( intRoll ) );
            } else if ( roll.length() > 1 ) {

                // File format error
                System.err.println( "Fouls should be represented only by the letter 'F'." );
                System.exit(1);

            } else playerScore.get( name ).add( new Ball( roll.charAt(0) ) );

        }

        playerScore.forEach( ( name, frames ) -> {

            Integer frame = 1;

            for ( int i = 0; i < frames.size(); i++ ) {
                Ball ball = frames.get( i );

                if ( ball.getIntValue() == 10 && frame < 10 ) {
                    gameScore.get( name ).add( new Frame( frame, ball, new Ball( 0 ) ) );
                } else if ( frame == 10 ) {
                    gameScore.get( name ).add( new Frame( frame, ball, frames.get( i + 1 ), frames.get( i + 2 ) ) );
                    i += 2;
                } else {
                    gameScore.get( name ).add( new Frame( frame, ball, frames.get( i + 1 ) ) );
                    i++;
                }

                frame++;
            }
        });

        gameScore.forEach( ( name, scores ) -> {
            game.add( new PlayerScore( name, scores) );
        });

        return new BowlingGame( game );
    }

    public List<PlayerScore> getGame() {
        return game;
    }

}
