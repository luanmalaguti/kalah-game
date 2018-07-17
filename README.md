# kalah-game
A Spring boot app application that runs REST endpoints to play a 6-stone Kalah game

The general rules of the game are explained on Wikipedia: https://en.wikipedia.org/wiki/Kalah

## Usage

In order to run the application you need Java 8 and Maven

Clone the repository from Github:

$ git clone git@github.com:luanmalaguti/kalah-game.git

Navigate into the cloned repository directory

$ cd kala-game

Build the project with Maven

$ mvn clean install

The application will be running on http://localhost:8080

## Creation of the game should be possible with the command:

```
curl --header "Content-Type: application/json" \--request POST \http://localhost:8080/games
```
## To make a move

```
curl --header "Content-Type: application/json" \--request PUT \http://localhost:<8080>/games/{gameId}/pits/{pitId}
```

`gameId`: unique identifier of a game

`pitId`: id of the pit selected to make a move. Pits are numbered from 1 to 14 where 7 and 14 are the kalah (or house)
of each player
