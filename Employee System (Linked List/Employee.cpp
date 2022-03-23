//
// Created by aliha on 25.11.2021.
//

#include "Employee.h"

Employee::Employee(int employeeNumber, int employeeType, const string &name, const string &surname, const string &title,
                   double salary, const Date &dateofBirth, const Date &dateofAppointment, int lenghtofService)
        : employeeNumber(employeeNumber), employeeType(employeeType), name(name), surname(surname), title(title),
          salary(salary), dateofBirth(dateofBirth), dateofAppointment(dateofAppointment),
          lenghtofService(lenghtofService) {}
Employee::Employee() {
}

int Employee::getEmployeeNumber() const {
    return employeeNumber;
}

void Employee::setEmployeeNumber(int employeeNumber) {
    Employee::employeeNumber = employeeNumber;
}

int Employee::getEmployeeType() const {
    return employeeType;
}

void Employee::setEmployeeType(int employeeType) {
    Employee::employeeType = employeeType;
}

const string &Employee::getName() const {
    return name;
}

void Employee::setName(const string &name) {
    Employee::name = name;
}

const string &Employee::getSurname() const {
    return surname;
}

void Employee::setSurname(const string &surname) {
    Employee::surname = surname;
}

const string &Employee::getTitle() const {
    return title;
}

void Employee::setTitle(string &title) {
    cout<<"new title : " <<title<<endl;
    Employee::title = title;
}

double Employee::getSalary() {
    return salary;
}

void Employee::setSalary(double salary) {
    cout<<"new salary : " <<salary<<endl;
    Employee::salary = salary;
}

const Date &Employee::getDateofBirth() const {
    return dateofBirth;
}

void Employee::setDateofBirth(const Date &dateofBirth) {
    Employee::dateofBirth = dateofBirth;
}

const Date &Employee::getDateofAppointment() const {
    return dateofAppointment;
}

void Employee::setDateofAppointment(const Date &dateofAppointment) {
    Employee::dateofAppointment = dateofAppointment;
}

int Employee::getLenghtofService() const {
    return lenghtofService;
}

void Employee::setLenghtofService(int lenghtofService) {
    Employee::lenghtofService = lenghtofService;
}


ostream &operator<<(ostream &os, const Employee &employee) {
    os << "Employee Number: " << employee.employeeNumber << " Employee Type: " << employee.employeeType << " Name: "
       << employee.name << " Surname: " << employee.surname << " Title: " << employee.title << " Salary: "
       << employee.salary << " Date of Birth: " << employee.dateofBirth << " Date of Appointment: "
       << employee.dateofAppointment << " Lenght of Service: " << employee.lenghtofService<<endl;
    return os;
}
