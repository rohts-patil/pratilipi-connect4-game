There are 3 rest apis.

1. To start the game
url:- POST https://connect4-rohit.herokuapp.com/start/
Response:- Game ready. Your id for the game is:- ae2621f8-45ca-4c57-aec8-97500e65e8be

2. To make Moves:-
 POST https://connect4-rohit.herokuapp.com/move/
JSON body:- {
                "uuid": "ae2621f8-45ca-4c57-aec8-97500e65e8be",
                "column": 4
            }

Response:- 0123456
           .......
           .......
           .......
           .......
           .......
           ....Y..

3. To make moves with invalid request
 POST https://connect4-rohit.herokuapp.com/move/
JSON body:- {
                "uuid": "ae2621f8-45ca-4c57-aec8-97500e65e8be",
                "column": 9
            }
Response :-
{
    "timeStamp": "2020-09-27T22:58:47.805+00:00",
    "message": "Column must be between 0 and 6",
    "details": "uri=/move/"
}

3. To get all moves by id
GET https://connect4-rohit.herokuapp.com/getAllMoves/{id}
so for example GET https://connect4-rohit.herokuapp.com/getAllMoves/ae2621f8-45ca-4c57-aec8-97500e65e8be
Response:- [ {
             "player" : "Y",
             "columnPlayed" : 4,
             "timeStamp" : "27-09-2020 22:08:10"
           }, {
             "player" : "R",
             "columnPlayed" : 0,
             "timeStamp" : "27-09-2020 22:08:18"
           }, {
             "player" : "Y",
             "columnPlayed" : 4,
             "timeStamp" : "27-09-2020 22:08:23"
           }, {
             "player" : "R",
             "columnPlayed" : 0,
             "timeStamp" : "27-09-2020 22:08:26"
           }, {
             "player" : "Y",
             "columnPlayed" : 4,
             "timeStamp" : "27-09-2020 22:08:29"
           }, {
             "player" : "R",
             "columnPlayed" : 0,
             "timeStamp" : "27-09-2020 22:08:33"
           }, {
             "player" : "Y",
             "columnPlayed" : 4,
             "timeStamp" : "27-09-2020 22:08:37"
           } ]
