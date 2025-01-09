# for loops, range(), user input, print statements, variable initialization

import random

min = 0 # min number that guessing num can be
max = 100 # max number that guessing num can be
chances = 7 # num of chances the user will have to guess the number

print("You have " + str(chances) + " chances to guess the number I chose between " + str(min) + " and " + str(max))

num_to_guess = random.randrange(min,max+1) # random number between min and max (inclusive)

for x in range(1, chances+1): 
    guess = input("Input a guess: ")
    try:
        guess = int(guess)
    except ValueError:
        print("Incorrect input type. You must guess a number!")
        continue # skips all other lines in the loop
    
    if guess == num_to_guess:
        print("Congratulations! You guessed the correct number in " + str(x) + " guesses!")
        break
    elif guess > num_to_guess:
        print("Your guess is too high!", end = " ")
    else:
        print("Your guess is too low!", end = " ")
        
    if (chances - x) == 0:
        print("Womp, womp. You failed to guess the number " + str(num_to_guess) + " in time. Maybe next time?")
    else:
        print("You have " + str(chances - x) + " guesses left")
