package com.back;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명언) ");
            String input = sc.nextLine();
            if(input.equals("종료"))
                break;
        }

    }
}
