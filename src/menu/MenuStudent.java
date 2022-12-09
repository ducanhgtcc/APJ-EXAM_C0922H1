package menu;

import manager.ManagerStudent;

public class MenuStudent {
    ManagerStudent managerStudent = new ManagerStudent();

    public void menu() {
        int choice;
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách sinh viên");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng: ");

            choice = managerStudent.checkLoiNhap();

            switch (choice) {
                case 1 -> managerStudent.show();
                case 2 -> managerStudent.add();
                case 3 -> managerStudent.update();
                case 4 -> managerStudent.delete();
                case 5 -> managerStudent.sort();
                case 6 -> managerStudent.ReadFromFile();
                case 7 -> managerStudent.WriteToFile();
                case 8 -> System.exit(0);
                default -> System.out.println("Chọn lại!");
            }
        } while (true);
    }
}
