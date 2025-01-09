# logic for 2048 game

import random

# Initialize the grid
def start_game():
    # Initialize a 4x4 grid of '0's where every element in grid is one row 
    grid = []
    for i in range(4):
        grid.append([0] * 4)  # Add a row of "0"s for the 4x4 grid
    
    # Print game controls
    print(f"CONTROLS\n" +
          "============\n"
          "\'w\' to go up\n" + 
          "\'a\' to go left\n" + 
          "\'s\' to go down\n" + 
          "\'d\' to go right\n")
    
    grid = add_new_tile(grid)
    grid = add_new_tile(grid)
    
    return grid

# Check all elements in the grid. If there is NOT an empty element, game over
# returning a 1 means there is a left or right movement that can be made to merge cells
# returning a 2 means there is an up or down movement that can be made to merge calls
def check_game_over(grid):
    empty_cell = False # Determines if a new tile can be placed on the grid
    # If at least one empty cell, game not over
    for row in grid:
        for x in row:
            if x == 2048: # Win if there is a cell with a value of 2048
                print_grid(grid)
                print("YOU WIN!")
                exit(); 
            if x == 0:
                empty_cell = True
                break # Break out of first for loop
        if empty_cell: break # Break out of second for loop
    
    # If there are two cells that are adjacent (up, left, down, right), game not over
    # Check left & right 
    for row in grid: 
        for i in range(3): # col index
            if row[i] == row[i+1]:
                return False, empty_cell
            
    # Check up & down
    for i in range(3): # row index
        for j in range(4): # col index
            if grid[i][j] == grid[i+1][j]:
                return False, empty_cell           
    
    # Else, game over    
    return True, empty_cell

def add_new_tile(grid):
    row = random.randint(0,3)
    col = random.randint(0,3)
    while (grid[row][col] != 0):
        row = random.randint(0,3)
        col = random.randint(0,3)
    
    # 90% chance for tile to be a 2, 10% for a 4
    rand = random.randint(0,10)
    if rand != 9: 
        grid[row][col] = 2
    else: 
        grid[row][col] = 4
    return grid

# Compresses with assumption of a LEFT movement.
# If not a LEFT movement, we use the reverse() and
# transpose functions to manipulate the grid how we'd like
def compress(grid):
    # Initialize a new grid to replace the old one
    new_grid = []
    for i in range(4): # Add four rows of '0's for the 4x4 grid
        new_grid.append([0] * 4)  
        
    for i in range(4): # Loop through each row in the grid
        row = grid[i]
        index = 0
        for x in row: # Check every element in row
            if x != 0: # Check element is not 0
                new_grid[i][index] = x # Move the element to the new grid
                index += 1 # Increment position for the next non-zero element
            
    return new_grid

# Merge adjacent elements in the grid
def merge(grid):
    for row in grid: # For each row in grid
        for i in range(3): # Only check the first three elements of the row
            if row[i] == row[i+1] and row[i] != 0: # If element to the right is the same
                row[i] *= 2 # Double element size
                row[i+1] = 0
                
    return grid

# Transposes a given matrix (grid)
def transpose(grid):
    new_grid = [] # New empty grid to store transpose result
    for i in range(4): # Add a row of '0's for the 4x4 grid
        new_grid.append([0] * 4)
    
    for i in range(4):
        for j in range(4):
            new_grid[j][i] = grid[i][j]
            
    return new_grid

# Reverses the order of elements in every row of a given matrix (grid)
def reverse(grid):
    new_grid = [] # New empty grid to store transpose result
    for i in range(4): # Add a row of '0's for the 4x4 grid
        new_grid.append([0] * 4)
    
    index = 0
    for row in grid:
        for i in range(4):
            new_grid[index][3-i] = row[i]
        index += 1
        
    return new_grid

def move_up(grid):
    changed = False
        
    new_grid = transpose(grid)
    new_grid, changed = move_left(new_grid)
    new_grid = transpose(new_grid)
    
    if new_grid != grid:
        changed = True
        
    return new_grid, changed

def move_left(grid):
    changed = False    
        
    new_grid = compress(grid) # compress grid
    new_grid = merge(new_grid) # merge grid
    new_grid = compress(new_grid) # compress grid again
    
    if new_grid != grid:
        changed = True
        
    return new_grid, changed

def move_down(grid):
    changed = False    
    new_grid = grid # Create a new grid to replace the old one
    
    new_grid = transpose(new_grid)
    new_grid, changed = move_right(new_grid) # Left movement + reverse
    new_grid = transpose(new_grid)
        
    if new_grid != grid:
        changed = True
        
    return new_grid, changed

def move_right(grid):
    changed = False    
        
    new_grid = reverse(grid)
    new_grid, changed = move_left(new_grid)
    new_grid = reverse(new_grid)
        
    if new_grid != grid:
        changed = True
        
    return new_grid, changed

# prints the grid in the current state
def print_grid(grid):
    max_digits = 0
    # Get max number of digits present in the grid
    for row in grid:
        for cell in row:
            digit_num = len(str(cell)) # Get num of digits contained in the cell
            if digit_num > max_digits: # Update max_digits
                max_digits = digit_num
    
    # Start with a '+'
    # Print '-' a number of times equal to max_digits+2 x4, seperated by a '+'
    # End with a '+'
    horizontal_border = "+" + "+".join("-" * (max_digits + 2) for x in range(4)) + "+"
    print(horizontal_border)
    
    # Alternatively: print("| " + " | ".join(f"{cell:>{max_digits}}" if cell != 0 else f"{' ':>{max_digits}}" for cell in row) + " |")
    for row in grid:
        print("| ", end = "") # Starting cell border
        for cell in row:
            if cell != 0: # If cell is not 0, print out a cell right aligned to a 4 spaces
                print(f"{cell:>{max_digits}}" + " | ", end = "")
            else: # If cell is 0, than print out a space instead of the cell's value
                print(f"{" ":>{max_digits}}" + " | ", end = "")
        print()
        print(horizontal_border) # Print out a horizontal border to seperate each row

