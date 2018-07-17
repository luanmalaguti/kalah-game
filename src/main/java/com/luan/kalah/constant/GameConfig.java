package com.luan.kalah.constant;


/**
 * Configuration class that defines all the game parameters like the
 * Number of stones per house {@link #STONES_PER_HOUSE}
 */
public class GameConfig {

    /**
     * Private constructor to hide the implicit one
     */
    private GameConfig(){

    }

    /**
     * Number of stones per house
     */
    public static final int STONES_PER_HOUSE = 6;

    /**
     * How manyh stones defines an empty house
     */
    public static final int EMPTY_HOUSE = 0;

    /**
     * Total number of houses - pits + kalahs
     */
    public static final int NUMBER_OF_HOUSES = 14;

    /**
     * Kalah house - player 1
     */
    public static final int KALAH_PLAYER_1 = 7;

    /**
     * Kalah house - player 2
     */
    public static final int KALAH_PLAYER_2 = 14;


    /**
     * Short description of the player 1
     */
    public static final String PLAYER_1 = "Player Kalah 7";

    /**
     * Short description of the player 1
     */
    public static final String PLAYER_2 = "Player Kalah 14";
}
