###Mancala-Game-Playing-Agent
 
A program to determine the next move for a player in the Mancala game using Greedy, Minimax, and Alpha-Beta pruning algorithm. The rules of the Mancala game can be found at https://en.wikipedia.org/wiki/Mancala .

###Introduction to Mancala

![Sample Board](/Images/Mancala.png)

Mancala is a two-player game from Africa in which players moves stones around a board (shown above), trying to capture as many as possible. In the board above, player 1 owns the bottom row of stones and player 2 owns the top row. There are also two special pits on the board, called Mancalas, in which each player accumulates his or her captured stones (player 1's Mancala is on the right and player 2's Mancala is on the left). 

On a player's turn, he or she chooses one of the pits on his or her side of the board (not the Mancala) and removes all of the stones from that pit. The player then places one stone in each pit, moving counterclockwise around the board, starting with the pit immediately next to the chosen pit, including his or her Mancala but NOT his or her opponents Mancala, until he or she has run out of stones. If the player's last stone ends in his or her own Mancala, the player gets another turn. If the player's last stone ends in an empty pit on his or her own side, the player captures all of the stones in the pit directly across the board from where the last stone was placed (the opponents stones are removed from the pit and placed in the player's Mancala) as well as the last stone placed (the one placed in the empty pit). The game ends when one player cannot move on his or her turn, at which time the other player captures all of the stones remaining on his or her side of the board. 

###Legal moves

![Initial State](/Images/InitialState.png)

Assume that the current board position is as shown in the image above and the current turn is Player-1’s. Blocks B2-B7 are Player-1’s pits, blocks A2-A7 are Player-2’s pits, B8 is Player-1’s Mancala and A1 is Player-2’s Mancala. We define a move for any player as to pick a pit (except Mancala) from which the stones are removed and placed in other pits as explained above. So for the current board position, Player-1’s legal moves are pits B2, B3, B4, B5, B6, or B7. The image below shows the board position if Player-1 picks B5 as his/her move. Any player cannot choose an empty pit as a move, i.e. empty pits are illegal moves. 

![State after first move](/Images/LegalMove1.png)

A player can make more than one move during his/her turn as per the rules. For example, Player-1 can pick pit B4 and then pick any other pit (except for B4 as it will be empty) for his turn for the starting board position shown above. 

###End game

If a player cannot make any valid move, i.e. all of his pits (except Mancala) are empty, the game ends and the remaining stones are moved to the other player’s Mancala. 

###Evaluation function

The goal of the game is to collect maximum number of stones by the end of the game and to win the game; you need to collect more stones than your opponent. So, the evaluation function for any legal move is computed as the difference between the numbers of stones in both players’ Mancala if that move is chosen. 
                         
                                      E(p) = #Stones_player - #Stones_opponent 

For example, for the current board position shown above, if Player-1 chooses pit B5 as his/her move, then the value of the evaluation function would E(B5) = (1-0) = 1.

###Tie breaking and Expand order

Ties between pits are broken by selecting the node that is first in the position order on the figure above. For example, if all legal moves for Player-1 (B2, B3, B4, B5, B5, B6, and B7) have the same evaluated values, the program pick B2 according to tie breaker rule. Same rule applies for Player-2. 

###Board size

The board size will be 2xN along with a mancala for each player, where N represents the number of pits for a player and 3≤N≤10. The board size for the mancala board shown above would be 2x6. The initial number of stones in each pit can be maximum 1000. 

###Input

A file input.txt that describes the current state of the game.  
(Task#) Greedy=1, MiniMax=2, Alpha-Beta=3   
(Your player: 1 or 2)  
(Cutting off depth)  
(Board state for player-2)  
(Board state for player-1)  
(#stones in player-2’s mancala)  
(#stones in player-1’s mancala)  

eg.  
2  
1  
2  
3 3 3  
3 3 3  
0  
0  

###Output

The program output one file named “next_state.txt” showing the next state of the board after the greedy move in the following format.  
3 4 4  
0 0 5  
0  
2  

* Line-1 represents the board state for player-2, i.e. the upper side of the board. Each number is separated by a single white space.
* Line-2 represents the board state for player-1, i.e. the upper side of the board. Each number is separated by a single white space.
* Line-3 gives you the number of stones in player-2’s mancala.
* Line-4 gives you the number of stones in player-1’s mancala.  

