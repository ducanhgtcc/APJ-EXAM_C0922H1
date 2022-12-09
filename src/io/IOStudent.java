package io;

import model.Student;

import java.io.*;
import java.util.ArrayList;

public class IOStudent {
    static File file = new File("Student.csv");

    public static void write(ArrayList<Student> students) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student student : students) {
                bufferedWriter.write(student.in());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> read() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String str = bufferedReader.readLine();
            while (str != null) {
                String[] arr = str.split(", ");
                String code = arr[0];
                String name = arr[1];
                int age = Integer.parseInt(arr[2]);
                String gender = arr[3];
                String address = arr[4];
                double GPA = Double.parseDouble((arr[5]));


                Student student = new Student(code, name, age, gender, address, GPA);
                students.add(student);
                str = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
