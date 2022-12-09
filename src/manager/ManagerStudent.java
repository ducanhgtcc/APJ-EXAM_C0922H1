package manager;

import io.IOStudent;
import model.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerStudent {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> students = new ArrayList<>();
    public final String REGEX_STRING = "[ny]";

    //Nhập thông tin sinh viên
    public Student infoStudent() {

        System.out.println("Nhập mã sinh viên: ");
        String code = scanner.nextLine();

        System.out.println("Nhập họ tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.println("Nhập tuổi sinh viên: ");
        int age = checkLoiNhap();

        System.out.println("Nhập giới tính sinh viên: ");
        String gender = scanner.nextLine();

        System.out.println("Nhập địa chỉ sinh viên: ");
        String address = scanner.nextLine();

        System.out.println("Nhập điểm trung bình sinh viên: ");
        double GPA;

        while (true) {
            try {
                GPA = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai định dạng! Vui lòng chọn lại");
            }
        }

        Student student = new Student(code, name, age, gender, address, GPA);
        System.out.println(student);
        return student;
    }

    //Hiển thị thông tin sinh viên
    public void show() {
        System.out.println("-----------------------------------------------Danh sách sinh viên-----------------------------------------------");
        System.out.printf("| %-15s| %-25s| %-15s| %-15s| %-15s| %-15s", "Mã sinh viên", "Tên", "Tuổi", "Giới tính", "Địa chỉ", "Điểm trung bình");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("| %-15s| %-25s| %-15s| %-15s| %-15s| %-15s", student.getCode(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getGPA());
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }

    //Thêm thông tin sinh viên
    public void add() {
        Student student = infoStudent();
        students.add(student);
    }

    //Sửa thông tin sinh viên
    public void update() {
        while (true) {
            System.out.println("Nhập mã sinh viên bạn muốn sửa: ");
            String code = scanner.nextLine();
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getCode().equals(code)) {
                    students.set(i, infoStudent());
                } else {
                    System.out.println("Không tìm được sinh viên với mã sinh viên trên");
                }
            }
        }
    }

    //Xóa thông tin sinh viên
    public void delete() {
        System.out.println("Nhập mã sinh viên bạn muốn xóa:  ");
        String code = scanner.nextLine();
        System.out.println("Bạn có chắc chắn muốn xóa sinh viên (yes:y hoặc no:n): ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getCode().equalsIgnoreCase(code)) {
                    students.remove(i);
                    System.out.println("Xóa thành công");
                    break;
                }
            }
        } else {
            System.out.println("Hủy bỏ xóa!");
        }
    }

    //Sắp xếp sinh viên theo điểm trung bình
    public void sort() {
        int choice = 1;
        System.out.println("---- Sắp xếp sinh viên theo điểm trung bình ----");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Sắp xếp điểm trung bình tăng dần");
        System.out.println("2. Sắp xếp điểm trung bình giảm dần");
        System.out.println("3. Thoát");
        System.out.println("Chọn chức năng: ");
        do {
            if (choice > 3) System.out.println("Vui lòng nhập lại");
            choice = Integer.parseInt(scanner.nextLine());
        } while (choice > 3);

        switch (choice) {
            case 1 -> ascending();
            case 2 -> decrease();
            case 3 -> System.out.println("oke");
        }
    }

    //Sắp xếp điểm trung bình tăng dần
    public void ascending() {
        Student temp;
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getGPA() > students.get(j).getGPA()) {
                    temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
        show();
    }

    //Sắp xếp điểm trung bình giảm dần
    public void decrease() {

        Student temp;
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getGPA() < students.get(j).getGPA()) {
                    temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
        show();
    }

    //Đọc từ file
    public void ReadFromFile() {
        System.out.println("Đọc từ File sẽ mất dữ liệu hiện tại, bạn có muốn tiếp tục? (yes:y hoặc no:n): ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            students = IOStudent.read();
            System.out.println("Đọc file thành công, chọn chức năng xem danh sách để kiểm tra.");
        }
    }

    //Ghi từ file
    public void WriteToFile() {
        System.out.println("Ghi vào File sẽ mất dữ liệu đang lưu, bạn có muốn tiếp tục? (yes:y hoặc no:n): ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            IOStudent.write(students);
            System.out.println("Ghi file thành công, chạy lại chương trình và chọn chức năng đọc file để kiểm tra.");
        }
    }

    //Check lỗi nhập tuổi
    public int checkLoiNhap() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai định dạng! Vui lòng chọn lại");
            }
        }
        return choice;
    }

    public String validateString(String regex) {
        while (true) {
            String name = scanner.nextLine();
            if (name.matches(regex)) {
                return name;
            }
            System.err.println("Sai định dạng, vui lòng nhập lại.");
        }
    }
}
