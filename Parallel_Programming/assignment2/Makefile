all:
	gcc -g -fopenmp game_of_life.c -o game_of_life
	export OMP_NUM_THREADS=4
	#gcc -g game_of_life.c -o game_of_life #for serial execution
clean:
	rm -rf game_of_life
	rm -rf output.txt
