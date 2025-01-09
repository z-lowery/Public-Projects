# custom file imports, functions, alignment operators, two-dimentional arrays, returning more than two values, user interfaces 
import importlib
import logic # import applicable functions from logic file

importlib.reload(logic) # reload logic file

grid = logic.start_game() # obtain starting grid w/ two filled tiles

while (True):
    # Print grid state
    logic.print_grid(grid)

    # Get user input
    cmd = input("> ").lower()
    if cmd == "w":
        grid, changed = logic.move_up(grid)
    
    elif cmd == "a":
        grid, changed = logic.move_left(grid)
        
    elif cmd == "s":
        grid, changed = logic.move_down(grid)
        
    elif cmd == "d":
        grid, changed = logic.move_right(grid)
    else:
        print("Invalid input!")
    
    game_over, empty_cell = logic.check_game_over(grid)
    if game_over:
        logic.print_grid(grid)
        print("GAME OVER")
        exit()
        
    if changed and empty_cell: # Add a new cell iif the grid changed last move and there is an empty cell in the grid
            grid = logic.add_new_tile(grid) # add new tile.