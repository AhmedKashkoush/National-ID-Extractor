package nationalid;

import java.util.Scanner;

public class NationalID {

    public static void main(String[] args) {
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        String id = input.next();
        IDInfo info = new IDInfo();
        info.extractInfo(id);
        System.out.print("DOB: ");
        String date = info.getDate();
        System.out.println(date);
        System.out.print("Gov: ");
        String gov = info.getGov();
        System.out.println(gov);
        System.out.print("Gender: ");
        String gender = info.getGender();
        System.out.println(gender);
    }
    
}
