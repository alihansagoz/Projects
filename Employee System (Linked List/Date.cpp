//
// Created by aliha on 25.11.2021.
//

#include "Date.h"

Date::Date(int day, int month, int year) : day(day), month(month), year(year) {}

Date::Date() {}

int Date::getDay() const {
    return day;
}

void Date::setDay(int day) {
    Date::day = day;
}

int Date::getMonth() const {
    return month;
}

void Date::setMonth(int month) {
    Date::month = month;
}

int Date::getYear() const {
    return year;
}

void Date::setYear(int year) {
    Date::year = year;
}

bool Date::operator<(const Date &rhs) const {
    if (year < rhs.year)
        return true;
    if (rhs.year < year)
        return false;
    if (month < rhs.month)
        return true;
    if (rhs.month < month)
        return false;
    return day < rhs.day;
}

bool Date::operator>(const Date &rhs) const {
    return rhs < *this;
}

bool Date::operator<=(const Date &rhs) const {
    return !(rhs < *this);
}

bool Date::operator>=(const Date &rhs) const {
    return !(*this < rhs);
}

std::ostream &operator<<(std::ostream &os, const Date &date) {
    os << date.day << "-" << date.month << "-" << date.year;
    return os;
}
