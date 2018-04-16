# AlgorithmDesign
Different Exercises for the Algorithms Design Subject.

Right now There is this content:

## Dynamic Programming
Exercises to calculate something using dynamic programming:

- Combinatorial Number: Forward and backward methods
  - Calculated with Pascal's triangle:
   - combinatorial(n, k) = combinatorial(n-1, k-1) + combinatorial(n-1, k).

- Progression: Forward and backward methods
  - f(2n) = f(2n + 1)
  - f(2n + 1) = f(n) + f(n + 1)
  - f(1) = f(2) = 1
  
- Fibonacci number: Forward and backward methods

- Ackerman Function: Forward and backward methods

- Travelling salesperson: Forward and backward methods

- Dartboard: Backward method (Forward does not work correctly)
  - Similar to exchange problem, find the combination of scores to reach a certain number. If the number is unreachable find the combination of scores to reach the closest number. Find always the combination with less darts.

## Probabilistic programming
Exercises to calculate something using different probabilistic methods:
  - calculate areas below different functions.
