//
// Created by aliha on 25.11.2021.
//

#ifndef UNTITLED14_DATE_H
#define UNTITLED14_DATE_H


#include <ostream>

class Date {


private:
    int day;
    int month;
    int year;

public:
    Date(int day, int month, int year);

    Date();

    bool operator<(const Date &rhs) const;

    bool operator>(const Date &rhs) const;

    bool operator<=(const Date &rhs) const;

    bool operator>=(const Date &rhs) const;

    friend std::ostream &operator<<(std::ostream &os, const Date &date);

    int getDay() const;

    void setDay(int day);

    int getMonth() const;

    void setMonth(int month);

    int getYear() const;

    void setYear(int year);
};

#endif //UNTITLED14_DATE_H
