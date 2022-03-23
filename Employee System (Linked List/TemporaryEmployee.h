//
// Created by aliha on 25.11.2021.
//

#ifndef UNTITLED14_TEMPORARYEMPLOYEE_H
#define UNTITLED14_TEMPORARYEMPLOYEE_H
#include "Employee.h"

class TemporaryEmployee : public Employee {
public:
    TemporaryEmployee(int employeeNumber, int employeeType, const string &name, const string &surname,
                      const string &title, double salary, const Date &dateofBirth, const Date &dateofAppointment,
                      int lenghtofService);
    TemporaryEmployee();
};



#endif //UNTITLED14_TEMPORARYEMPLOYEE_H
