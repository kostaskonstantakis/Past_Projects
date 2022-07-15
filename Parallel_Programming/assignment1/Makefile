all:
	gcc -g -fopenmp epidemicSimulator.c -pthread -o epidemicSimulator
	export OMP_NUM_THREADS=4
clean:
	rm -rf epidemicSimulator
