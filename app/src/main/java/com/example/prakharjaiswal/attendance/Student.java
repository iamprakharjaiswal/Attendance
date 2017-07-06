package com.example.prakharjaiswal.attendance;

/**
 * Created by Prakhar Jaiswal on 21-06-2017.
 */
public class Student {
        String studentId;
        String studentName;
        String studentClass;
        Days d=new Days();

        public Student(){

        }
        public Student(String studentId, String studentName, String studentClass,Days d){
            this.studentId = studentId;
            this.studentName = studentName;
            this.studentClass =studentClass;
            this.d.date = d.date;
            this.d.status = d.status;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getStudentClass() {
            return studentClass;
        }

        public Days getD() {
            return d;
        }
    }


