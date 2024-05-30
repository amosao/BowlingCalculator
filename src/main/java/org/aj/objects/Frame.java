package org.aj.objects;

public class Frame {
    private Integer frame;

    final Ball firstThrow;

    final Ball secondThrow;

    final Ball lastThrow;

    private Integer totalScore = 0;

    private boolean isSpare = false;

    private boolean isStrike = false;

    public Frame(Integer frame, Ball first, Ball second ) {
        setFrame( frame );
        this.firstThrow = first;
        this.secondThrow = second;
        this.lastThrow = null;

        if ( firstThrow.getIntValue() == 10 ) {
            setStrike( true );
        } else if ( firstThrow.getIntValue() + secondThrow.getIntValue() == 10 || secondThrow.getIntValue() == 10 ) {
            setSpare( true );
            secondThrow.setCharValue( '/' );
        } else if ( firstThrow.getIntValue() + secondThrow.getIntValue() > 10 ) {
            System.err.println( "The maximum number of pins that can be knocked down in the frame is 10." );
            System.exit(1);
        }
    }

    public Frame(Integer frame, Ball first, Ball second, Ball last ) {
        setFrame( frame );
        this.firstThrow = first;
        this.secondThrow = second;
        this.lastThrow = last;

        if ( firstThrow.getIntValue() == 10 ) {
            setStrike( true );
        } else if ( firstThrow.getIntValue() + secondThrow.getIntValue() == 10 ) {

            setSpare( true );
            secondThrow.setCharValue( '/' );

        } else if ( firstThrow.getIntValue() + secondThrow.getIntValue() + last.getIntValue() > 30 ) {
            System.err.println( "The maximum number of pins that can be knocked down in the last frame is 30." );
            System.exit(1);
        }
    }

    public Integer getFrame() { return frame; }

    public void setFrame(Integer round) { this.frame = round; }

    public Ball getFirstThrow() { return firstThrow; }

    public Ball getSecondThrow() { return secondThrow; }

    public Ball getLastThrow() { return lastThrow; }

    public Integer getTotalScore() { return totalScore; }

    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }

    public boolean isSpare() { return isSpare; }

    public void setSpare(boolean spare) { isSpare = spare; }

    public boolean isStrike() { return isStrike; }

    public void setStrike(boolean strike) { isStrike = strike; }

}
