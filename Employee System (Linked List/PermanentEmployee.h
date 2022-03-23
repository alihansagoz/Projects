//
// Created by aliha on 25.11.2021.
//

#ifndef UNTITLED14_PERMANENTEMPLOYEE_H
#define UNTITLED14_PERMANENTEMPLOYEE_H
#include "Employee.h"

class PermanentEmployee : public Employee {
public:
    PermanentEmployee(int employeeNumber, int employeeType, const string &name, const string &surname,
                      const string &title, double salary, const Date &dateofBirth, const Date &dateofAppointment,
                      int lenghtofService);
    PermanentEmployee();
};
#endif //UNTITLED14_PERMANENTEMPLOYEE_H
