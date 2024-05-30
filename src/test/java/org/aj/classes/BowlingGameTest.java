package org.aj.classes;

import org.aj.objects.Ball;
import org.aj.objects.Frame;
import org.aj.objects.PlayerScore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    // Simple pins input
    void parseAndPointCalculation_simpleInput() {
        List<PlayerScore> parsedFile = new ArrayList<>();
        parsedFile.add( new PlayerScore( "Jeff", buildSimpleFramesFirst() ) );
        parsedFile.add( new PlayerScore( "John", buildSimpleFramesSecond() ) );

        BowlingGame simpleGame = new BowlingGame( parsedFile );

        assertEquals( simpleGame.getGame().get(0).getFrames().get(9).getTotalScore(), 110 );
        assertEquals( simpleGame.getGame().get(1).getFrames().get(9).getTotalScore(), 116 );
    }

    @Test
    // Perfect pins input
    void parseAndPointCalculation_perfectScore() {
        List<PlayerScore> parsedFile = new ArrayList<>();
        parsedFile.add( new PlayerScore( "Jeff", buildPerfectFrames() ) );

        BowlingGame simpleGame = new BowlingGame( parsedFile );

        assertEquals( simpleGame.getGame().get(0).getFrames().get(9).getTotalScore(), 300 );
    }

    @Test
    // Zero pins input
    void parseAndPointCalculation_zeroScore() {
        List<PlayerScore> parsedFile = new ArrayList<>();
        parsedFile.add( new PlayerScore( "Jeff", buildZeroFrames() ) );

        BowlingGame simpleGame = new BowlingGame( parsedFile );

        assertEquals( simpleGame.getGame().get(0).getFrames().get(9).getTotalScore(), 0 );
    }

    private List<Frame> buildSimpleFramesFirst() {
        List<Frame> simpleFrames = new ArrayList<>();

        simpleFrames.add( new Frame( 1, new Ball( 4 ), new Ball( 0 ) ) );
        simpleFrames.add( new Frame( 2, new Ball( 6 ), new Ball( 3 ) ) );
        simpleFrames.add( new Frame( 3, new Ball( 3 ), new Ball( 5 ) ) );
        simpleFrames.add( new Frame( 4, new Ball( 10 ), new Ball( 0 ) ) );
        simpleFrames.add( new Frame( 5, new Ball( 0 ), new Ball( 10 ) ) );
        simpleFrames.add( new Frame( 6, new Ball( 7 ), new Ball( 1 ) ) );
        simpleFrames.add( new Frame( 7, new Ball( 3 ), new Ball( 6 ) ) );
        simpleFrames.add( new Frame( 8, new Ball( 6 ), new Ball( 1 ) ) );
        simpleFrames.add( new Frame( 9, new Ball( 5 ), new Ball( 5 ) ) );
        simpleFrames.add( new Frame( 10, new Ball( 6 ), new Ball( 4 ), new Ball( 2 ) ) );

        return simpleFrames;
    }

    private List<Frame> buildSimpleFramesSecond() {
        List<Frame> simpleFrames = new ArrayList<>();

        simpleFrames.add( new Frame( 1, new Ball( 10 ), new Ball( 0 ) ) );
        simpleFrames.add( new Frame( 2, new Ball( 6 ), new Ball( 4 ) ) );
        simpleFrames.add( new Frame( 3, new Ball( 6 ), new Ball( 2 ) ) );
        simpleFrames.add( new Frame( 4, new Ball( 10 ), new Ball( 0 ) ) );
        simpleFrames.add( new Frame( 5, new Ball( 8 ), new Ball( 2 ) ) );
        simpleFrames.add( new Frame( 6, new Ball( 1 ), new Ball( 7 ) ) );
        simpleFrames.add( new Frame( 7, new Ball( 3 ), new Ball( 2 ) ) );
        simpleFrames.add( new Frame( 8, new Ball( 1 ), new Ball( 4 ) ) );
        simpleFrames.add( new Frame( 9, new Ball( 3 ), new Ball( 2 ) ) );
        simpleFrames.add( new Frame( 10, new Ball( 10 ), new Ball( 4 ), new Ball( 4 ) ) );

        return simpleFrames;
    }

    private List<Frame> buildPerfectFrames() {
        List<Frame> perfectFrame = new ArrayList<>();

        perfectFrame.add( new Frame( 1, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 2, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 3, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 4, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 5, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 6, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 7, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 8, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 9, new Ball( 10 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 10, new Ball( 10 ), new Ball( 10 ), new Ball( 10 ) ) );

        return perfectFrame;
    }

    private List<Frame> buildZeroFrames() {
        List<Frame> perfectFrame = new ArrayList<>();

        perfectFrame.add( new Frame( 1, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 2, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 3, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 4, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 5, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 6, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 7, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 8, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 9, new Ball( 0 ), new Ball( 0 ) ) );
        perfectFrame.add( new Frame( 10, new Ball( 0 ), new Ball( 0 ), new Ball( 0 ) ) );

        return perfectFrame;
    }

}
