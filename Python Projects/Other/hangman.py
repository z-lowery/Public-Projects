# formatted string literals, one-dimentional arrays, .isalpha(), .lower(), .join()

import random

wordList = [ # list of words that the random word to guess could be
    "mountain", "elephant", "butterfly", "microscope", "ocean", "cactus",
    "galaxy", "rainbow", "pyramid", "volcano", "jigsaw", "lantern", "compass",
    "adventure", "mystery", "zeppelin", "bicycle", "bridge", "eclipse",
    "treasure", "astronaut", "whistle", "parachute", "chameleon", "labyrinth",
    "treetop", "fireplace", "typewriter", "avalanche", "castle", "harpoon",
    "crystal", "kangaroo", "chimney", "origami", "telescope", "wildflower",
    "satellite", "thunderstorm", "horizon", "meadow", "pegasus", "quicksand",
    "vortex", "blueprint", "universe", "zebra", "boomerang", "waterfall"
]

word = random.choice(wordList) # get a random word from word list
wordLength = len(word) # obtain the length of the word

# variable to track what letters have been guessed at any given point in the game.
displayedWord = ["_"] * wordLength # initialized with all underscores '_'

turns = 12
print(f'Welcome to Hangman! Your word has {wordLength} letters. Input letters one by one to try to find the word!')

allGuesses = ""
while (turns > 0):
    guess = input("Input your guess (type \"solve\" to solve): ").lower()
    
    # if solving
    if guess == "solve":
        turns -= 1
        solve = input("What word do you think it is? ").lower() 
        if solve == word: # correct guess
            print(f"CORRECT! You got the word \"{word}\" with {turns} guesses left! ")
            break
        else: # incorrect guess
            print(f"INCORRECT! You have {turns} guesses left and have guessed the following letters: {allGuesses}")
    
    # if not solving, validate the guess input 
    elif len(guess) != 1 or not guess.isalpha(): # forces user to input only one letter
            print(f"input only a single letter or type \"solve\" to solve the puzzle!")
            
    # handling repeat guesses
    elif guess in allGuesses:
            print(f"You already guessed {guess}! You have guessed the following letters: {allGuesses}")
    
    # if a valid guess        
    else:
        # append the guessed char to a string containing all previous guesses, separated by spaces
        allGuesses  += (guess + " ")
        
        # update var displayedWord if the guessed letter is in the word
        for index in range(wordLength):
            if word[index] == guess:
                displayedWord[index] = guess
        
        # display the progress made with guessing the word
        print(" ".join(displayedWord)) # combines all elements in displayedWord seperated by a space
            
        # check if the user has won
        turns -= 1 # deincrement turns
        if "_" not in displayedWord: # user wins when all letters are filled in
            print(f"You WIN! You got the word \"{word}\" with {turns} guesses left!")
            break
        else: # user did not win
            print(f"You have {turns} guesses left", end = "")
            if turns != 0:
                print(f" and have guessed the following letters: {allGuesses}")  
            else:
                print(f". The word was {word}")  