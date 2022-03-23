#ifndef UNTITLED14_EMPLOYEE_H
#define UNTITLED14_EMPLOYEE_H
#include "Date.h"
#include <iostream>
using namespace std;
class Employee {
public:
    Employee(int employeeNumber, int employeeType, const string &name, const string &surname, const string &title,
             double salary, const Date &dateofBirth, const Date &dateofAppointment, int lenghtofService);
    Employee();

    int getEmployeeNumber() const;

    void setEmployeeNumber(int employeeNumber);

    int getEmployeeType() const;

    void setEmployeeType(int employeeType);

    const string &getName() const;

    void setName(const string &name);

    const string &getSurname() const;

    void setSurname(const string &surname);

    const string &getTitle() const;

    double getSalary();

    void setSalary(double salary);

    const Date &getDateofBirth() const;

    void setDateofBirth(const Date &dateofBirth);

    const Date &getDateofAppointment() const;

    void setDateofAppointment(const Date &dateofAppointment);

    int getLenghtofService() const;

    void setLenghtofService(int lenghtofService);

    friend ostream &operator<<(ostream &os, const Employee &employee);

    void setTitle(string &title);

private:
    int employeeNumber;
    int employeeType;
    string name;
    string surname;
    string title;
    double salary;
    Date dateofBirth;
    Date dateofAppointment;
    int lenghtofService;
};


#endif //UNTITLED14_EMPLOYEE_H
