package com.back;

import java.util.Scanner;

class WiseSaying {
    int id;
    String content;
    String author;
}

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        WiseSaying[] wss = new WiseSaying[10000]; // 일단 10,000개 받을 수 있게 설정.
        int wsLastId = -1; // ws배열에 아무것도 안 들어있으므로, 인덱스 -1로 초기화

        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");
            String input = sc.nextLine();
            if(input.equals("종료"))
                break;
            else if(input.equals("등록")) {
                WiseSaying ws = new WiseSaying();

                wsLastId++; // 등록하면, ws배열에 하나 추가
                ws.id = wsLastId+1;
                System.out.print("명언 : ");
                ws.content = sc.nextLine();
                System.out.print("작가 : ");
                ws.author = sc.nextLine();
                wss[ws.id-1] = ws;
            }
        }

    }
}
