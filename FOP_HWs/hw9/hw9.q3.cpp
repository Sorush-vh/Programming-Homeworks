#include <iostream>
#include <string.h>
#include <stdlib.h>
#include <bits/stdc++.h>
#include "grader.h"


using namespace std;


// class Patient{
// private:
//     string firstname;
//     string lastname;
//     int visit_time;
//     int visit_length;
// public:
//     Patient(string firstname, string lastname, int visit_time, int visit_length);
//     string get_firstname();
//     string get_lastname();
//     int get_visit_time();
//     int get_visit_length();
// };


// class Clinic{
// private:
//     vector <Patient> patients;
//     int maximum_patients;
// public:
//     void set_maximum_patients(int maximum_patients);
//     bool add_patient(string firstname, string lastname, int visit_time, int visit_length);
//     bool remove_patient(string lastname);
//     void checkup();
//     Patient get_patient(int index);
// };

int cmpfunc( Patient a , Patient b){
    if(a.get_visit_time()>b.get_visit_time()) return 0;
    else return 1;
}


Patient::Patient(string firstname, string lastname, int visit_time, int visit_length) {
Patient::firstname=firstname;
Patient::lastname=lastname;
Patient::visit_length=visit_length;
Patient::visit_time=visit_time;
}

string Patient::get_firstname() {
return Patient::firstname;
}

string Patient::get_lastname() {
return Patient::lastname;
}


int Patient::get_visit_time() {
return Patient::visit_time;
}

int Patient::get_visit_length() {
return Patient::visit_length;
}

void Clinic::set_maximum_patients(int maximum_patients) {
Clinic::maximum_patients=maximum_patients;
}



bool Clinic::add_patient(string firstname, string lastname, int visit_time, int visit_length) {
    int start,len;

    if(patients.size()>=maximum_patients) return false;
for (int i = 0; i < patients.size() ; i++)
{
    start=patients[i].get_visit_time();
    len=patients[i].get_visit_length();

    if(patients[i].get_lastname()==lastname) return false;
    if( start*60+len<=visit_time*60 || visit_time*60+visit_length<=start*60 );
    else return false;
}
    Patient temp (firstname,lastname,visit_time,visit_length);
   patients.push_back(temp);
return true;
}

bool Clinic::remove_patient(std::string lastname) {
for (int i = 0; i < patients.size(); i++)
{
    if(patients[i].get_lastname()==lastname) {
        patients.erase(patients.begin()+i);
        return true;
    }
}
return false;
}


void Clinic::checkup() {
sort(patients.begin(),patients.end(),cmpfunc);
 patients.erase(patients.begin());
}


Patient Clinic::get_patient(int index) {
sort(patients.begin(),patients.end(),cmpfunc);
    return patients[index];
}


// int main(){


// Clinic clinic = *new Clinic();
// clinic.set_maximum_patients(2);
// cout << clinic.add_patient("walter", "white", 0, 60) << endl;
// cout << clinic.get_patient(0).get_firstname() << endl;
// cout << clinic.add_patient("jesse", "pinkman", 0, 30) << endl;
// cout << clinic.add_patient("jesse", "pinkman", 2, 30) << endl;
// cout << clinic.get_patient(1).get_lastname() << endl;
// cout << clinic.add_patient("jesse2", "pinkman", 3, 1) << endl;
// clinic.checkup();
// cout << clinic.get_patient(0).get_lastname() << endl;

//     return 0;
// }