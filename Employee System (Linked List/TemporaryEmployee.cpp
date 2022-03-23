//
// Created by aliha on 25.11.2021.
//

#include "TemporaryEmployee.h"

TemporaryEmployee::TemporaryEmployee(int employeeNumber, int employeeType, const string &name, const string &surname,
                                     const string &title, double salary, const Date &dateofBirth,
                                     const Date &dateofAppointment, int lenghtofService) : Employee(employeeNumber,
                                                                                                    employeeType, name,
                                                                                                    surname, title,
                                                                                                    salary, dateofBirth,
                                                                                                    dateofAppointment,
                                                                                                    lenghtofService) {}
TemporaryEmployee::TemporaryEmployee() = default;