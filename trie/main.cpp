#include <bits/stdc++.h>

using namespace std;

const int ALPHABET_SIZE = 26;
std::ofstream outfile;
struct TrieNode {
    struct TrieNode* children[ALPHABET_SIZE]{};
    string meaining;
    bool isEndOfWord{};
};

struct TrieNode* getNode()
{
    auto* pNode = new TrieNode;

    pNode->isEndOfWord = false;

    for (auto & i : pNode->children)
        i = nullptr;

    return pNode;
}

string Search(struct TrieNode* root, const string& key)
{
    struct TrieNode* pCrawl = root;

    for (char i : key) {
        int index = i - 'a';
        if (!pCrawl->children[index])
            return "incorrect Dothraki word";
        pCrawl = pCrawl->children[index];
    }
    if (pCrawl != nullptr && pCrawl->isEndOfWord){
        return "The English equivalent is " + pCrawl->meaining;
    }
    if (pCrawl->meaining.empty()){
        return "not enough Dothraki word";
    }
    return "";
}
string Search2(struct TrieNode* root, const string& key)
{
    struct TrieNode* pCrawl = root;

    for (char i : key) {
        int index = i - 'a';
        if (!pCrawl->children[index])
            return "incorrect Dothraki word";
        pCrawl = pCrawl->children[index];
    }
    if (pCrawl != nullptr && pCrawl->isEndOfWord){
        return pCrawl->meaining;
    }
    if (pCrawl->meaining.empty()){
        return "not enough Dothraki word";
    }
    return "";
}

void insert(struct TrieNode* root, const string& key,string mean)
{
    struct TrieNode* pCrawl = root;
    Search(root,key);
    int counter =0;
    if (!Search(root,key).empty() && Search(root,key) != "incorrect Dothraki word" && Search(root,key) != "not enough Dothraki word"){
        if (Search2(root,key)==mean){
            outfile<<'"'<<key<<'"' <<" already exist"<<endl;
            return;
        }
        else{
            counter++;
            outfile<<'"'<<key<<'"'<<" was updated"<<endl;
        }
    }
    for (char i : key) {
        int index = i - 'a';
        if (!pCrawl->children[index]){
            pCrawl->children[index] = getNode();
        }
        pCrawl = pCrawl->children[index];
    }
    pCrawl->isEndOfWord = true;
    pCrawl->meaining = std::move(mean);
    if (counter==0){
        outfile<<'"'<<key<<'"'<<" was added"<<endl;
    }

}

bool isEmpty(TrieNode* root)
{
    for (auto & i : root->children)
        if (i)
            return false;
    return true;
}

TrieNode* remove(TrieNode* root, string key, int depth = 0)
{
    if (!root) {
        outfile <<'"'<< "incorrect Dothraki word"<<'"' << endl;
        return nullptr;
    }
    if (depth == key.size()) {

        if (root->isEndOfWord) {
            outfile << '"' << key << '"' << "deletion is successful" << endl;
            root->isEndOfWord = false;
        }
        else{
            outfile<<"not enough Dothraki word"<<endl;
        }
        if (isEmpty(root)) {
            delete (root);
            root = nullptr;
        }

        return root;
    }


    int index = key[depth] - 'a';
    root->children[index] =
            remove(root->children[index], key, depth + 1);

    if (isEmpty(root) && !root->isEndOfWord) {
        delete (root);
        root = nullptr;
    }


    return root;
}
int numberofChild(struct TrieNode* root){
    int counter = 0;
    for (int i = 0; i < ALPHABET_SIZE; ++i) {
        if (root->children[i])
        {
            counter ++;
        }
    }
    return counter;
}

void display(struct TrieNode* root, char str[], int level,int counter)
{
    if (root->isEndOfWord || numberofChild(root)>1)
    {
        for (int i = 0; i < counter-1; ++i) {
            outfile<<'\t';
        }
        if (numberofChild(root)>1){
            counter++;
            for (int j = 0; j < level; ++j) {
                if (j==0){
                    if (!root->isEndOfWord){
                        outfile<<'-';
                    }
                }
                if (!root->isEndOfWord){
                    outfile<<str[j];
                }
            }
            if (!root->isEndOfWord){
                outfile<<endl;
            }
        }
        if (root->isEndOfWord){
            if (numberofChild(root)==0){
                counter = 0;
            }
            outfile<<'-';
            str[level] = '\0';
            outfile<<str<<'('<<root->meaining<<')'<<endl;
        }
    }
    int i;
    for (i = 0; i < ALPHABET_SIZE; i++)
    {
        if (root->children[i])
        {
            str[level] = i + 'a';
            display(root->children[i], str, level + 1,counter);
        }
    }
}

int main(int argc, char** argv){
    outfile.open(argv[2]);
    struct TrieNode* root = getNode();
    std::ifstream file(argv[1]);
    std::string str;
    while (std::getline(file, str)) {
        if (str.substr(0,6) == "insert"){
            string str2 = str.substr(7);
            str2.pop_back();
            replace(str2.begin(), str2.end(), ',', ' ');
            istringstream iss(str2);
            vector<string> result;
            for(string s;iss>>s;)
                result.push_back(s);
            insert(root,result[0],result[1]);
        }
        else if (str.substr(0,6) == "search"){
            string str2 = str.substr(6);
            str2.pop_back();
            str2.erase(0,1);
            outfile<<Search(root,str2)<<endl;
        }
        else if(str.substr(0,4) == "list"){
            int level = 0;
            char strx[20];
            display(root, strx, level, 0);
        }
        else if(str.substr(0,6) == "delete"){
            string str2 = str.substr(7);
            str2.pop_back();
            remove(root,str2);
        }
    }
    return 0;
}