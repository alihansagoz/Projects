#include <iostream>
#include <sstream>
#include <utility>
#include "Employee.h"
#include "PermanentEmployee.h"
#include "TemporaryEmployee.h"

class CircularArrayLinkedList {

public:
    Employee data;
    CircularArrayLinkedList* next{};

    Employee* search(int employeenumber) { //check if the employee with the given number is exist if exist return
        CircularArrayLinkedList *ptr;
        ptr = this;

        if(ptr == nullptr){
            return nullptr;
        }
        while (ptr->next != this) {
            if (ptr->data.getEmployeeNumber() == employeenumber) {
                return &ptr->data;
            }
            ptr = ptr->next;
            if (ptr == nullptr){
                break;
            }
        }
        return nullptr;
    }

    void insert(CircularArrayLinkedList** head_ref, CircularArrayLinkedList* new_node)
    {
        CircularArrayLinkedList* current = *head_ref;
        if (current == nullptr)
        {
            new_node->next = new_node;
            *head_ref = new_node;
        }
        else if (current->data.getEmployeeNumber() >= new_node->data.getEmployeeNumber())
        {
            while(current->next != *head_ref)
                current = current->next;
            current->next = new_node;
            new_node->next = *head_ref;
            *head_ref = new_node;
        }
        else
        {
            while (current->next!= *head_ref && current->next->data.getEmployeeNumber() < new_node->data.getEmployeeNumber())
                current = current->next;
            new_node->next = current->next;
            current->next = new_node;
        }
    }

    void show(CircularArrayLinkedList* head){  //print the all temp employees in circular
        CircularArrayLinkedList* temp = head;
        cout << temp->data << endl<<endl;
        temp = temp->next;
        while (temp != head) {
            cout << temp->data << endl<<endl;
            temp = temp->next;
        }
        cout<<endl;
    }


    void Delete(CircularArrayLinkedList** head, int key){
        if (*head == nullptr)
            return;

        if((*head)->data.getEmployeeNumber()==key && (*head)->next==*head)
        {
            free(*head);
            *head=nullptr;
            return;
        }

        CircularArrayLinkedList *last=*head,*d;
        if((*head)->data.getEmployeeNumber()==key)
        {
            while(last->next!=*head)
                last=last->next;
            last->next=(*head)->next;
            free(*head);
            *head=last->next;
            return;
        }

        while(last->next!=*head&&last->next->data.getEmployeeNumber()!=key)
        {
            last=last->next;
        }

        if(last->next->data.getEmployeeNumber()==key)
        {
            d=last->next;
            last->next=d->next;
            free(d);
        }
    }
};

CircularArrayLinkedList* headcircular = nullptr;
CircularArrayLinkedList* new_nodecircular;

CircularArrayLinkedList* makeNodeCircular(Employee new_data){ //creates a node for the circularlinkedlist
    auto* new_node = new CircularArrayLinkedList();
    new_node->data = std::move(new_data);
    new_node->next = nullptr;
    return new_node;
}

class DoubleDynamicLinkedList {
public:
    Employee data;
    DoubleDynamicLinkedList* prev{}, *next{};


    Employee* search(int employeenumber){ //searches the doublelinkedlist if the employee with the given number is exist if exist return
        DoubleDynamicLinkedList *ptr;
        ptr = this;

        if(ptr == nullptr){
            return nullptr;
        }

        while (ptr!=nullptr){
            if(ptr->data.getEmployeeNumber() == employeenumber){
                return &ptr->data;
            }
            ptr = ptr -> next;
        }
        return nullptr;
    }

    void insert(DoubleDynamicLinkedList** head_ref,DoubleDynamicLinkedList* newNode){ //puts the new employee node in order with their appointment date
        DoubleDynamicLinkedList* current;
        if (*head_ref == nullptr)
            *head_ref = newNode;
        else if ((*head_ref)->data.getDateofAppointment() >= newNode->data.getDateofAppointment()) {
            if (this->search((*head_ref)->data.getEmployeeNumber())!= nullptr){
                newNode->next = *head_ref;
                newNode->next->prev = newNode;
                *head_ref = newNode;
            }
            else{
                return;
            }
        }
        else {
            current = *head_ref;
            while (current->next != nullptr && current->next->data.getDateofAppointment() < newNode->data.getDateofAppointment())
                current = current->next;
            newNode->next = current->next;
            if (current->next != nullptr)
                newNode->next->prev = newNode;
            current->next = newNode;
            newNode->prev = current;
        }
    }

    void Delete(DoubleDynamicLinkedList** head_ref, int key){ //deletes a node in doublelinkedlist with the given employee number
        DoubleDynamicLinkedList* temp = *head_ref;
        DoubleDynamicLinkedList* prevx = nullptr;

        if (temp != nullptr && temp->data.getEmployeeNumber() == key)
        {
            *head_ref = temp->next;
            delete temp;
            return;
        }
        else
        {
            while (temp != nullptr && temp->data.getEmployeeNumber() != key)
            {
                prevx = temp;
                temp = temp->next;
            }
            if (temp == nullptr)
                return;
            prevx->next = temp->next;
            delete temp;
        }
    }

    void show(DoubleDynamicLinkedList* headx){ //shows the all permanent employee in doublelinkedlist
        while (headx != nullptr) {
            cout << headx->data <<endl<<endl;
            headx = headx->next;
        }
        cout<<endl;
    }

};


void searchSmaller(int employeenumber,int comparenumber,DoubleDynamicLinkedList* head_double){ //searches an employee with the smaller employee number if there is searches again with the new employee number and then prints them in order
    DoubleDynamicLinkedList* ptr;
    ptr = head_double;
    while (ptr!=nullptr){
        if (ptr->data.getEmployeeNumber()>comparenumber && ptr->data.getEmployeeNumber()<employeenumber){
            searchSmaller(ptr->data.getEmployeeNumber(),employeenumber,head_double);
            cout<<ptr->data<<endl;
        }
        ptr = ptr->next;
    }
}

void searchSmallerandCheckBirthDate(int number, int comparenumber, DoubleDynamicLinkedList *pList, Date date);

DoubleDynamicLinkedList* headdouble = nullptr; //creates an empty linkedlist pointer
DoubleDynamicLinkedList* new_nodedouble; //creates an empty node for double linkedlist

DoubleDynamicLinkedList* makeNodeDouble(Employee data){ //creates a node for the double linkedlist to put
    auto* newNode = new DoubleDynamicLinkedList();
    newNode->data = std::move(data);
    newNode->prev = newNode->next = nullptr;
    return newNode;
}

void changeTitleandSalary(Employee* employee, string title, double salary){ //changes the title and salary with the given employee number
    employee->setTitle(title);
    employee->setSalary(salary);
}

void sortWithEmployeeNumber(CircularArrayLinkedList* head_circular,DoubleDynamicLinkedList* head_double){ //prints all employees in order with employee number

    int comparenumber = -1;
    CircularArrayLinkedList* ptr;
    ptr = head_circular;

    do {
        if (ptr->data.getEmployeeNumber()>comparenumber){
            searchSmaller(ptr->data.getEmployeeNumber(),comparenumber,head_double);
        }
        cout<<ptr->data<<endl;
        comparenumber = ptr->data.getEmployeeNumber();
        ptr = ptr->next;
    }
    while (ptr != head_circular);

    DoubleDynamicLinkedList* ptrdouble;
    ptrdouble = head_double;

    while(ptrdouble != nullptr){
        if (ptrdouble->data.getEmployeeNumber()>comparenumber){
            searchSmaller(ptrdouble->data.getEmployeeNumber(),comparenumber,head_double);
            cout<<ptrdouble->data<<endl;
            comparenumber = ptrdouble->data.getEmployeeNumber();
        }
        ptrdouble = ptrdouble->next;
    }
}

void searchSmallerDate(Date appointmentDate,Date compareDate,CircularArrayLinkedList* head_circular){ //searches an employee with the smaller appointment date if there is searches again with the new appointment date and then prints them in order
    CircularArrayLinkedList* ptr;
    ptr = head_circular;
    do {
        if (ptr->data.getDateofAppointment()>compareDate && ptr->data.getDateofAppointment()<appointmentDate){
            searchSmallerDate(ptr->data.getDateofAppointment(),appointmentDate,head_circular);
            cout<<ptr->data<<endl;
        }
        else if(ptr->data.getDateofAppointment()>compareDate && ptr->data.getDateofAppointment()<=appointmentDate){
            searchSmallerDate(ptr->data.getDateofAppointment(),appointmentDate,head_circular);
            cout<<ptr->data<<endl;
        }
        ptr = ptr->next;
    }
    while (ptr!=head_circular);

}


void searchSmallerDateYear(Date appointmentDate,Date compareDate,CircularArrayLinkedList* head_circular,int year){ //searches an employee with the smaller appointment date if there is searches again with the new appointment date and then prints them in order
    CircularArrayLinkedList* ptr;
    ptr = head_circular;
    do{
        if (ptr->data.getDateofAppointment()>compareDate && ptr->data.getDateofAppointment()<appointmentDate && ptr->data.getDateofAppointment().getYear() == year){
            searchSmallerDateYear(ptr->data.getDateofAppointment(),appointmentDate,head_circular,year);
            cout<<ptr->data<<endl;
            compareDate = ptr->data.getDateofAppointment();
        }
        else if (ptr->data.getDateofAppointment()>compareDate && ptr->data.getDateofAppointment()<=appointmentDate && ptr->data.getDateofAppointment().getYear() == year){
            cout<<ptr->data<<endl;
        }
        ptr = ptr->next;
    }
    while (ptr!=head_circular);

}

void searchBiggerDate(Date employeedate,Date compareDate,CircularArrayLinkedList* head_circular){ //searches an employee with the smaller employee number if there is searches again with the new employee number and then prints them in order
    CircularArrayLinkedList* ptr;
    ptr = head_circular;

    do{
        if (ptr->data.getDateofAppointment()<compareDate && ptr->data.getDateofAppointment()>employeedate){
            searchBiggerDate(ptr->data.getDateofAppointment(),employeedate,head_circular);
            cout<<ptr->data<<endl;
        }
        else if (ptr->data.getDateofAppointment()<compareDate && ptr->data.getDateofAppointment()>=employeedate){
            searchBiggerDate(ptr->data.getDateofAppointment(),employeedate,head_circular);
            cout<<ptr->data<<endl;
        }
        ptr = ptr->next;
    }
    while (ptr!=head_circular);
}


void sortWithAppointmenDate(CircularArrayLinkedList *head_circular, DoubleDynamicLinkedList *head_double) { //prints all employees in order with appointmen date
    Date compareDate = Date(-1,-1,-1);
    DoubleDynamicLinkedList* ptr;
    ptr = head_double;
    while (ptr != nullptr) {
        if (ptr->data.getDateofAppointment()>compareDate){
            searchSmallerDate(ptr->data.getDateofAppointment(),compareDate,head_circular);
        }
        cout<<ptr->data<<endl;
        compareDate = ptr->data.getDateofAppointment();
        ptr = ptr->next;
    }

    CircularArrayLinkedList* ptr_circular;
    ptr_circular = head_circular;

    if (ptr_circular->data.getDateofAppointment()>compareDate){
        searchSmallerDate(ptr_circular->data.getDateofAppointment(),compareDate,head_circular);
        cout<<ptr_circular->data<<endl;
        compareDate = ptr_circular->data.getDateofAppointment();
    }
    ptr_circular = ptr_circular->next;

    while(ptr_circular != head_circular){
        if (ptr_circular->data.getDateofAppointment()>compareDate){
            searchSmallerDate(ptr_circular->data.getDateofAppointment(),compareDate,head_circular);
            cout<<ptr_circular->data<<endl;
            compareDate = ptr_circular->data.getDateofAppointment();
        }
        ptr_circular = ptr_circular->next;
    }
}

void listingemployeesingivenyear(int year){ //prints the all employees appointed in given year
    DoubleDynamicLinkedList *ptr;
    ptr = headdouble;
    Date compareDate(-1,-1,-1);
    while (ptr!=nullptr){
        if(ptr->data.getDateofAppointment().getYear() == year){
            searchSmallerDateYear(ptr->data.getDateofAppointment(),compareDate,headcircular,year);
            cout<<ptr->data<<endl;
            compareDate = ptr->data.getDateofAppointment();
        }
        ptr = ptr -> next;
    }

    CircularArrayLinkedList *ptrcircular;
    ptrcircular= headcircular;

    if(ptrcircular->data.getDateofAppointment().getYear() == year){
        searchSmallerDateYear(ptrcircular->data.getDateofAppointment(),compareDate,headcircular,year);
        cout<<ptrcircular->data<<endl;
        compareDate = ptrcircular->data.getDateofAppointment();
    }
    ptrcircular = ptrcircular -> next;


    while (ptrcircular!=headcircular){
        if(ptrcircular->data.getDateofAppointment().getYear() == year){
            searchSmallerDateYear(ptrcircular->data.getDateofAppointment(),compareDate,headcircular,year);
            cout<<ptrcircular->data<<endl;
            compareDate = ptrcircular->data.getDateofAppointment();
        }
        ptrcircular = ptrcircular -> next;
    }

}

void listingemployeesbornbefore(CircularArrayLinkedList* head_circular,DoubleDynamicLinkedList* head_double,const string& birthdate){ //lists the employees born before given date
    CircularArrayLinkedList *ptr_circular;
    ptr_circular = headcircular;

    Date birthdatex(stoi(birthdate.substr (0,2)),stoi(birthdate.substr (3,2)),stoi(birthdate.substr (6,4)));

    int comparenumber = -1;
    if (ptr_circular->data.getEmployeeNumber()>comparenumber && ptr_circular->data.getDateofBirth()<birthdatex){
        searchSmallerandCheckBirthDate(ptr_circular->data.getEmployeeNumber(),comparenumber,head_double,birthdatex);
        cout<<ptr_circular->data<<endl;
        comparenumber = ptr_circular->data.getEmployeeNumber();
    }

    ptr_circular = ptr_circular->next;
    while (ptr_circular != head_circular) {
        if (ptr_circular->data.getEmployeeNumber()>comparenumber && ptr_circular->data.getDateofBirth()<birthdatex ){
            searchSmallerandCheckBirthDate(ptr_circular->data.getEmployeeNumber(),comparenumber,head_double,birthdatex);
            cout<<ptr_circular->data<<endl;
            comparenumber = ptr_circular->data.getEmployeeNumber();
        }
        ptr_circular = ptr_circular->next;
    }

    DoubleDynamicLinkedList* ptrdouble;
    ptrdouble = head_double;

    while(ptrdouble != nullptr){
        if (ptrdouble->data.getEmployeeNumber()>comparenumber && ptrdouble->data.getDateofBirth()<birthdatex){
            searchSmallerandCheckBirthDate(ptrdouble->data.getEmployeeNumber(),comparenumber,head_double,birthdatex);
            cout<<ptrdouble->data<<endl;
            comparenumber = ptrdouble->data.getEmployeeNumber();
        }
        ptrdouble = ptrdouble->next;
    }
}

void searchSmallerandCheckBirthDate(int employeenumber, int comparenumber, DoubleDynamicLinkedList *head_double, Date birthdate) {
    DoubleDynamicLinkedList* ptr;
    ptr = head_double;
    while (ptr!=nullptr){
        if (ptr->data.getEmployeeNumber()>comparenumber && ptr->data.getEmployeeNumber()<employeenumber && ptr->data.getDateofBirth()<birthdate){
            searchSmallerandCheckBirthDate(ptr->data.getEmployeeNumber(),employeenumber,head_double,birthdate);
            cout<<ptr->data<<endl;
        }
        ptr = ptr->next;
    }
}



void searchSmallerandCheckBirthMonth(int employeenumber, int comparenumber, DoubleDynamicLinkedList *head_double, int month) {
    DoubleDynamicLinkedList* ptr;
    ptr = head_double;
    while (ptr!=nullptr){
        if (ptr->data.getEmployeeNumber()>comparenumber && ptr->data.getEmployeeNumber()<employeenumber && ptr->data.getDateofBirth().getMonth() == month){
            searchSmallerandCheckBirthMonth(ptr->data.getEmployeeNumber(),employeenumber,head_double,month);
            cout<<ptr->data<<endl;
        }
        ptr = ptr->next;
    }
}


void listingemployeesbornmonth(CircularArrayLinkedList* head_circular,DoubleDynamicLinkedList* head_double, int birthmonth){ //lists the employees born before given date
    CircularArrayLinkedList *ptr_circular;
    ptr_circular = headcircular;

    int comparenumber = -1;
    if (ptr_circular->data.getEmployeeNumber()>comparenumber && ptr_circular->data.getDateofBirth().getMonth() == birthmonth){
        searchSmallerandCheckBirthMonth(ptr_circular->data.getEmployeeNumber(),comparenumber,head_double,birthmonth);
        cout<<ptr_circular->data<<endl;
        comparenumber = ptr_circular->data.getEmployeeNumber();
    }

    ptr_circular = ptr_circular->next;
    while (ptr_circular != head_circular) {
        if (ptr_circular->data.getEmployeeNumber()>comparenumber && ptr_circular->data.getDateofBirth().getMonth() == birthmonth ){
            searchSmallerandCheckBirthMonth(ptr_circular->data.getEmployeeNumber(),comparenumber,head_double,birthmonth);
            cout<<ptr_circular->data<<endl;
            comparenumber = ptr_circular->data.getEmployeeNumber();
        }
        ptr_circular = ptr_circular->next;
    }

    DoubleDynamicLinkedList* ptrdouble;
    ptrdouble = head_double;

    while(ptrdouble != nullptr){
        if (ptrdouble->data.getEmployeeNumber()>comparenumber && ptrdouble->data.getDateofBirth().getMonth() == birthmonth){
            searchSmallerandCheckBirthMonth(ptrdouble->data.getEmployeeNumber(),comparenumber,head_double,birthmonth);
            cout<<ptrdouble->data<<endl;
            comparenumber = ptrdouble->data.getEmployeeNumber();
        }
        ptrdouble = ptrdouble->next;
    }
}


void searchtheTitle(CircularArrayLinkedList* head_circular,DoubleDynamicLinkedList* head_double,const string& title){ //searches the employees with the given title if exist more than one prints the newest employee
    DoubleDynamicLinkedList *ptr_double;
    ptr_double = headdouble;
    CircularArrayLinkedList *ptr_circular;
    ptr_circular = headcircular;
    Date compareDate;
    Employee emp;

    while (ptr_double != nullptr) {
        if (ptr_double->data.getTitle() == title && ptr_double->data.getDateofAppointment()>compareDate){
            compareDate = ptr_double->data.getDateofAppointment();
            emp = ptr_double->data;
        }
        ptr_double = ptr_double->next;
    }

    if (ptr_circular->data.getTitle() == title && ptr_circular->data.getDateofAppointment()>compareDate){
        compareDate = ptr_circular->data.getDateofAppointment();
        emp = ptr_circular->data;
    }
    ptr_circular = ptr_circular->next;

    while (ptr_circular != headcircular) {
        if (ptr_circular->data.getTitle() == title && ptr_circular->data.getDateofAppointment()>compareDate){
            compareDate = ptr_circular->data.getDateofAppointment();
            emp = ptr_circular->data;
        }
        ptr_circular = ptr_circular->next;
    }
    cout<<emp<<endl;
}

void ListingEACD(const string& certaindate) { //lists the employees appointed after certain date
    DoubleDynamicLinkedList *ptr;
    ptr = headdouble;
    Date certaindatex(stoi(certaindate.substr (0,2)),stoi(certaindate.substr (3,2)),stoi(certaindate.substr (6,4)));
    Date comparedate(99,9999,9999);
    while(ptr->next != nullptr){
        ptr = ptr->next;
    }
    while (ptr != nullptr){
        if (ptr->data.getDateofAppointment()>certaindatex){
            searchBiggerDate(ptr->data.getDateofAppointment(),comparedate,headcircular);
            cout<<ptr->data<<endl;
            comparedate = ptr->data.getDateofAppointment();
        }
        ptr = ptr->prev;
    }
}

void printandgetinfo(const string& newortransfered){ //prints and the get infos of employees for 1 and 2 operations
    int employeenumber;
    int employeetype;
    string name;
    string surname;
    string title;
    double salary;
    string birthdate;
    string appointmentdate;
    int lengthofservicedays;

    cout << "Please type the employee number" << endl;
    cin >> employeenumber;
    cout << "Type the employee type" << endl;
    cin >> employeetype;
    cout << "Type the name" << endl;
    std::getline(std::cin >> std::ws, name);
    cout << "Type the surname" << endl;
    std::getline(std::cin >> std::ws, surname);
    cout << "Type the title" << endl;
    std::getline(std::cin >> std::ws, title);
    cout << "Type salary coefficient" << endl;
    cin >> salary;
    cout << "Type birth date" << endl;
    cin >> birthdate;
    cout << "Type appointment date" << endl;
    cin >> appointmentdate;

    if (newortransfered=="transfered"){
        cout << "Type length of service days" << endl;
        cin >> lengthofservicedays;
    }
    else if(newortransfered =="new"){
        lengthofservicedays=0;
    }

    if (employeetype == 0) {
        TemporaryEmployee emp(employeenumber, employeetype, name, surname, title, salary, Date(stoi(birthdate.substr (0,2)),stoi(birthdate.substr (3,2)),stoi(birthdate.substr (6,4))),Date(stoi(appointmentdate.substr (0,2)),stoi(appointmentdate.substr (3,2)),stoi(appointmentdate.substr (6,4))), lengthofservicedays);
        new_nodecircular = makeNodeCircular(emp);
        headcircular->insert(&headcircular, new_nodecircular);
    }
    else {
        PermanentEmployee emp(employeenumber, employeetype, name, surname, title, salary, Date(stoi(birthdate.substr (0,2)),stoi(birthdate.substr (3,2)),stoi(birthdate.substr (6,4))),Date(stoi(appointmentdate.substr (0,2)),stoi(appointmentdate.substr (3,2)),stoi(appointmentdate.substr (6,4))), lengthofservicedays);
        new_nodedouble = makeNodeDouble(emp);
        headdouble->insert( &headdouble , new_nodedouble);
    }
}

void printTheMenuOperation(){ // prints the all menu operations in while loop
    int counter = 1;
    while(counter == 1){
        int operationnumber;
        cout<<"____ Employee Recording System ____"<<endl;
        cout<<"Please select for the following Menu Operation"<<endl;
        cout<<"1) Appointment of a new employee"<<endl;
        cout<<"2) Appointment of a transferred employee"<<endl;
        cout<<"3) Updating the title and salary coefficient of an employee"<<endl;
        cout<<"4) Deletion of a an employee"<<endl;
        cout<<"5) Listing the information of an employee"<<endl;
        cout<<"6) Listing employees ordered by employee number"<<endl;
        cout<<"7) Listing employees ordered by appointment date"<<endl;
        cout<<"8) Listing employees appointed after a certain date"<<endl;
        cout<<"9) Listing employees assigned in a given year"<<endl;
        cout<<"10) Listing employees born before a certain date"<<endl;
        cout<<"11) Listing employees born in a particular month"<<endl;
        cout<<"12) Listing the information of the last assigned employee with a given title"<<endl;
        cin >> operationnumber;
        switch (operationnumber) {
            default:

            case 1:
                printandgetinfo("new");
                break;
            case 2: {
                printandgetinfo("transfered");
                break;
            }
            case 3: {
                int employeenumber;
                string new_title;
                double new_salary;
                cout << "Type the employee number" << endl;
                cin >> employeenumber;
                cout << "Type the new title" << endl;
                cin >> new_title;
                cout << "Type the new salary coefficient" << endl;
                cin >> new_salary;
                if (headcircular->search(employeenumber) != nullptr){
                    changeTitleandSalary(headcircular->search(employeenumber), new_title,new_salary);
                } else{
                    changeTitleandSalary(headdouble->search(employeenumber), new_title,new_salary);
                }

                break;
            }
            case 4:{
                int employeenumber;
                cout << "Type the employee number" << endl;
                cin >> employeenumber;
                headcircular->Delete(&headcircular,employeenumber);
                headdouble->Delete(&headdouble,employeenumber);
                break;
            }
            case 5:{
                int employeenumber;
                cout << "Type the employee number" << endl;
                cin >> employeenumber;

                if (headcircular->search(employeenumber) != nullptr){
                    cout<<*headcircular->search(employeenumber)<<endl;

                } else{
                    cout<<*headdouble->search(employeenumber)<<endl;

                }
                break;
            }
            case 6 :{
                sortWithEmployeeNumber(headcircular,headdouble);
                break;
            }
            case 7 :{
                sortWithAppointmenDate(headcircular, headdouble);
                break;
            }
            case 8 :{
                string certaindate;
                cout << "Type a certain date" << endl;
                cin>>certaindate;
                ListingEACD(certaindate);
                break;
            }
            case 9:{
                int year;
                cout<<"Type a year"<<endl;
                cin>>year;
                listingemployeesingivenyear(year);
                break;
            }
            case 10:{
                string birthdate;
                cout<<"Type a certain date"<<endl;
                cin>>birthdate;
                listingemployeesbornbefore(headcircular,headdouble,birthdate);
                break;
            }
            case 11:{
                int month;
                cout<<"Type a certain month"<<endl;
                cin>>month;
                listingemployeesbornmonth(headcircular,headdouble,month);
                break;
            }
            case 12:{
                string title;
                cout<<"Type the title"<<endl;
                cin>>title;
                searchtheTitle(headcircular,headdouble,title);
            }
        }
    }
}

int main() {
    printTheMenuOperation();
}