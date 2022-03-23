//
// Created by aliha on 25.11.2021.
//

#include "PermanentEmployee.h"

PermanentEmployee::PermanentEmployee(int employeeNumber, int employeeType, const string &name, const string &surname,
                                     const string &title, double salary, const Date &dateofBirth,
                                     const Date &dateofAppointment, int lenghtofService) : Employee(employeeNumber,
                                                                                                    employeeType, name,
                                                                                                    surname, title,
                                                                                                    salary, dateofBirth,
                                                                                                    dateofAppointment,
                                                                                                    lenghtofService) {}
PermanentEmployee::PermanentEmployee() = default;