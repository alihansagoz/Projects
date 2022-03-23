#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;
int array1size;
int array2size;
int point;
[[maybe_unused]] int counter;

void Check(int **array,int value,int x,int y,const string& direction,vector<int> *pVector) {
    if (direction != "down" && x-1>=0) {
        if (array[x-1][y]==value){
            pVector->push_back(x - 1);
            pVector->push_back(y);
            Check(array, value,x-1, y, "up", pVector);
        }
    }
    if (direction != "left" && y+1 < array1size) {
        if (array[x][y+1]==value){
            pVector->push_back(x);
            pVector->push_back(y + 1);
            Check(array, value, x,y+1, "right", pVector);
        }
    }
    if (direction != "up" && x+1 < array1size){
        if (array[x+1][y]==value){
            pVector->push_back(x + 1);
            pVector->push_back(y);
            Check(array, value,x+1, y, "down", pVector);
        }
    }
    if (direction != "right" && y-1>=0){
        if (array[x][y-1]==value){
            pVector->push_back(x);
            pVector->push_back(y - 1);
            Check(array, value, x,y-1, "left", pVector);
        }
    }
}

void PrintTheBoard(int **array,int arraysize,std::ofstream *outfile){
    for (int i = 0; i < arraysize; ++i) {
        for (int j = 0; j < arraysize; ++j) {
            if (j==arraysize-1){
                *outfile << array[i][j];
            } else{
                *outfile << array[i][j] << " ";
            }
        }
        *outfile << "\n";
    }
}

void Putit(int **array,int value,int x,int y) {
    vector<int> vect;
    counter=0;
    array[x][y]=value;
    Check(array,value,x,y,"null",&vect);
    if (vect.size()>=4){
        counter=1;
        int x1=0;
        int y1=1;
        while (y1<vect.size()){
            array[vect[x1]][vect[y1]]=0;
            x1=x1+2;
            y1=y1+2;
        }
        Putit(array,value+1,x,y);
    }
}

void CheckTheValue(int **array,int value,int xCord,int yCord,int *pointx){
    if (xCord>=0 && xCord<array2size && yCord>=0 && yCord<array2size && array[xCord][yCord]==value) {
        *pointx+=value;
        array[xCord][yCord]=0;
    }
}

void PutTheBomb(int **array,int xCord,int yCord,int arraysizex,int *pointx){
    int value = array[xCord][yCord];
    for (int i = 0; i < arraysizex; ++i) {
        int directions[9][2] = {{xCord-i,yCord}, {xCord+i,yCord}, {xCord,yCord-i},{xCord,yCord+i},{xCord-i,yCord-i},{xCord-i,yCord+i},{xCord+i,yCord-i},{xCord+i,yCord+i},{xCord,yCord}};
        for (auto & direction : directions) {
            CheckTheValue(array,value,direction[0],direction[1],pointx);
        }
    }
}

int numberofNumbers(const std::string& line_2){
    istringstream ss(line_2);
    string part2;
    int numberofnumbers = 0;
    while (getline(ss, part2, ' ')) {
        numberofnumbers++;
    }
    return numberofnumbers;
}

int main (int argc, char** argv) {
    std::string line_;
    int **baloon;
    ifstream file_(argv[1]);
    while (getline(file_, line_)) {
        if (numberofNumbers(line_)==1){
            array1size = stoi(line_);
            baloon = new int *[array1size];
            for (int i = 0; i < array1size; i++)
                baloon[i] = new int[array1size];
            for (int i = 0; i < array1size; ++i) {
                for (int j = 0; j < array1size; ++j) {
                    baloon[i][j] = 0;
                }
            }
        }
        else{
            istringstream ss(line_);
            string part;
            int numbers[3] = {0, 0, 0};
            int x = 0;
            while (getline(ss, part, ' ')) {
                numbers[x] = stoi(part);
                x++;
            }
            Putit(baloon, numbers[0], numbers[1], numbers[2]);
        }
    }
    point = 0;
    std::string line_2;
    int **baloon2;
    ifstream file_2(argv[2]);
    int xCord=0;
    while (getline(file_2, line_2)) {
        istringstream ss(line_2);
        string part2;
        int numbers2[array2size];
        int numbers3[2];
        int x = 0;
        int x1=0;
        if (numberofNumbers(line_2)==1){
            array2size = stoi(line_2);
            baloon2 = new int *[array2size];
            for (int i = 0; i < array2size; i++) {
                baloon2[i] = new int[array2size];
            }
            for (int i = 0; i < array2size; ++i) {
                for (int j = 0; j < array2size; ++j) {
                    baloon2[i][j] = 0;
                }
            }
        }
        else if (numberofNumbers(line_2)>2){
            while (getline(ss, part2, ' ')) {
                numbers2[x] = stoi(part2);
                x++;
            }
            for (int i = 0; i < array2size; ++i) {
                baloon2[xCord][i] = numbers2[i];
            }
            xCord++;
        }
        else{
            while (getline(ss, part2, ' ')) {
                numbers3[x1] = stoi(part2);
                x1++;
            }
            PutTheBomb(baloon2,numbers3[0],numbers3[1],array2size,&point);
        }
    }
    std::ofstream outfile (argv[3]);
    outfile <<"PART 1:"<<endl;
    PrintTheBoard(baloon, array1size,&outfile);
    outfile <<endl;
    outfile <<"PART 2:"<<endl;
    PrintTheBoard(baloon2, array2size,&outfile);
    outfile <<"Final Point: "<< point <<"p";
}