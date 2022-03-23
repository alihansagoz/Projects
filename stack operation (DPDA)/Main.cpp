#include <iostream>
#include <fstream>
#include <vector>
#include <stack>

using namespace std;

bool searchVector(string x, vector<string> vect){
    for (auto vectx: vect) {
        if (vectx==x){
            return true;
        }
    }
    return false;
}

basic_string<char> stacktostr(stack<string> s)
{
    string xd;
    stack<string> temp;
    while (!s.empty())
    {
        temp.push(s.top());
        s.pop();
    }
    while (!temp.empty())
    {
        basic_string<char> t = temp.top();
        xd+= t + ",";
        temp.pop();
        s.push(t);
    }
    return xd.substr(0,xd.size()-1);
}

vector<basic_string<char>> searchvect(string& state, vector<string> &vectinput, const vector<vector<string>>& vector, stack<string>& stackx,std::ofstream *outfile){
    for (auto vect : vector) {
        if (vect[0] == state && vect[1] == vectinput[0]){
            if (stackx.empty()){
                if (vect[2]=="e"){
                    if (vect[4]!="e"){
                        stackx.push(vect[4]);
                    }
                    outfile<<state<<","<<vectinput[0]<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                    vectinput.erase(vectinput.begin());
                    return vect;
                }
            }
            if (vect[2]==stackx.top()){
                if (vect[4]!="e"){
                    stackx.push(vect[4]);
                }
                if (vect[2]!="e"){
                    stackx.pop();
                }
                outfile<<state<<","<<vectinput[0]<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                vectinput.erase(vectinput.begin());
                return vect;
            }
        }
    }
    for (auto vect : vector) {
        if(vect[0] == state && vect[1] == "e" ){
            if (stackx.empty()){
                if (vect[2]=="e"){
                    if (vect[4]!="e"){
                        stackx.push(vect[4]);
                    }
                    outfile<<state<<","<<"e"<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                    return vect;
                }
            }
            if (vect[2]==stackx.top()){
                if (vect[4]!="e"){
                    stackx.push(vect[4]);
                }
                if (vect[2]!="e"){
                    stackx.pop();
                }

                outfile<<state<<","<<"e"<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                return vect;
            }
        }
    }
    for (auto vect : vector) {
        if (vect[0] == state && vect[1] == vectinput[0]){
            if (stackx.empty()){
                if (vect[2]=="e"){
                    if (vect[4]!="e"){
                        stackx.push(vect[4]);
                    }
                    outfile<<state<<","<<vectinput[0]<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                    vectinput.erase(vectinput.begin());
                    return vect;
                }
            }
            if (vect[2]=="e"){
                if (vect[4]!="e"){
                    stackx.push(vect[4]);
                }
                if (vect[2]!="e"){
                    stackx.pop();
                }
                outfile<<state<<","<<vectinput[0]<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                vectinput.erase(vectinput.begin());
                return vect;
            }
        }
    }
    for (auto vect : vector) {
        if (vect[0] == state && vect[1] == "e"){
            if (stackx.empty()){
                if (vect[2]=="e"){
                    if (vect[4]!="e"){
                        stackx.push(vect[4]);
                    }
                    outfile<<state<<","<<vectinput[0]<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                    return vect;
                }
            }
            if (vect[2]=="e"){
                if (vect[4]!="e"){
                    stackx.push(vect[4]);
                }
                if (vect[2]!="e"){
                    stackx.pop();
                }
                outfile<<state<<","<<"e"<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stackx)<<endl;
                return vect;
            }
        }
    }
    outfile<<"REJECT"<<endl<<endl;
    return {"xde"};
}

void split(string s, const string& delimiter,vector<string> *vect){
    size_t pos;
    string token;
    while ((pos = s.find(delimiter)) != std::string::npos) {
        token = s.substr(0, pos);
        vect->push_back(token);
        s.erase(0, pos + delimiter.length());
    }
    vect->push_back(s);
}

int main(int argc, char** argv) {
    std::ofstream outfile(argv[3]);
    string start;
    vector<string> vectends;
    ifstream file(argv[1]);
    string str;
    vector<string> vectstates;
    vector<string> vectinputA;
    vector<string> vectstackA;
    vector<vector<string>> vectrules;
    vector<vector<string>> vectinputs;
    vectinputA.emplace_back("e");
    vectstackA.emplace_back("e");

    while (getline(file, str)) {
        string first_two = str.substr(0, 2);
        if (first_two == "Q:"){
            string states = str.substr(2, str.find(" =>") -2);
            split(states,",",&vectstates);
            string conditions = str.substr(str.find(" =>")+4, str.size());
            start = conditions.substr(conditions.find('(')+1,conditions.find(')')-1);
            vector<string> endx;
            split(conditions,",",&endx);
            for (const auto& end : endx) {
                int x = end.size();
                if (end.substr(0,1) == "["){
                    vectends.push_back(end.substr(1,x-2));
                }
            }
        } else if(first_two == "A:"){
            string letters = str.substr(2, str.size());
            split(letters,",",&vectinputA);

        } else if(first_two == "Z:"){
            string letters = str.substr(2, str.size());
            split(letters,",",&vectstackA);
        } else if(first_two == "T:"){
            string elements = str.substr(2, str.size());
            vector<string> rules;
            split(elements,",",&rules);
            if (!(searchVector(rules[0],vectstates) &&  searchVector(rules[1],vectinputA) && searchVector(rules[2],vectstackA) && searchVector(rules[3],vectstates) && searchVector(rules[4],vectstackA))){
                outfile<<"Error [1]:DPDA description is invalid!"<<endl;
                return 0;
            }
            vectrules.push_back(rules);
        }
    }

    ifstream file_2(argv[2]);
    string str_2;

    while (getline(file_2, str_2)) {
        vector<string> vectinput;
        split(str_2,",",&vectinput);
        vectinputs.push_back(vectinput);
    }
    for (auto vectinput: vectinputs) {
        stack<string> stack;
        string go = start;
        int counter =0;
        if (vectinput[0].empty()){
            if (searchVector(start,vectends)){
                outfile<<"ACCEPT"<<endl<<endl;
                continue;
            } else{
                outfile<<"REJECT"<<endl<<endl;
                continue;
            }
        }
        do {
            vector<string> gostate = searchvect(go,vectinput,vectrules,stack) ;
            if (gostate[0]=="xde"){
                counter = 1;
                break;
            }
            go = gostate[3];

        }
        while(!vectinput.empty());
        if (counter==1){
            continue;
        }
        if (vectinput.empty()){
            for (auto vect : vectrules) {
                if(vect[0] == go && vect[1] == "e" && (vect[2]==stack.top() || vect[2] == "e" )){
                    if (vect[4]!="e"){
                        stack.push(vect[4]);
                    }
                    if (vect[2]!="e"){
                        stack.pop();
                    }
                    outfile<<go<<","<<"e"<<","<<vect[2]<<" => " <<vect[3]<<","<<vect[4] <<" [STACK]:"<<stacktostr(stack)<<endl;
                    go = vect[3];
                }
            }
        }
        if (searchVector(go,vectends) && (stack.empty() || (stack.size()==1) && stack.top()=="$")){
            outfile<<"ACCEPT"<<endl<<endl;
        }
        else{
            outfile<<"REJECT"<<endl<<endl;
        }
    }
}
