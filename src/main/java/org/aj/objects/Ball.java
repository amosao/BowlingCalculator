package org.aj.objects;

public class Ball {

    private Character charValue = null;
    private Integer intValue = null;

    public Ball( Character points ) throws Exception {
        this.charValue = points;
        if ( !this.getStrValue().equals( 'F' ) ) {

            // File format error
            System.err.println( "Only the Letter 'F' is accepted as a score." );
            System.exit(1);
        }
        this.intValue = 0;
    }

    public Ball( Integer points ) {
        this.intValue = points;
        if ( getIntValue() == 10 ) {
            this.charValue = 'X';
        } else this.charValue = Character.forDigit( this.intValue, Character.MAX_RADIX );
    }

    public Integer getIntValue() { return this.intValue; }

    public Character getStrValue() { return this.charValue; }

    public void setCharValue( Character character ) { this.charValue = character; }

}
