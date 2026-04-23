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
            String cmd = sc.nextLine();
            if(cmd.equals("종료")) {
                break;
            } else if(cmd.equals("등록")) {
                WiseSaying ws = new WiseSaying();

                wsLastId++; // 등록하면, ws배열에 하나 추가
                ws.id = wsLastId+1;
                System.out.print("명언 : ");
                ws.content = sc.nextLine();
                System.out.print("작가 : ");
                ws.author = sc.nextLine();
                wss[ws.id-1] = ws;

                System.out.printf("%d번 명언이 등록되었습니다.\n", ws.id);
            } else if(cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언\n----------------------");
                for(int i=wsLastId; i>=0; i--) {
                    System.out.printf("%d / %s / %s\n", wss[i].id, wss[i].content, wss[i].author);
                }
            } else if(cmd.equals("삭제")) {
                System.out.print("?id=");
                int deleteId = sc.nextInt();

                int deleteRealId = -1; // 삭제할 id의 배열에서의 실제 인덱스
                for(int i=0; i<=wsLastId; i++) { // deleteId와 같은 wss배열의 값의 id가 있는지 확인
                    if(wss[i].id == deleteId) {
                        deleteRealId = i;
                        break;
                    }
                }

                if(deleteRealId==-1)
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", deleteId);
                else {
                    for(int i=deleteRealId; i<wsLastId; i++) {
                        wss[i] = wss[i+1];
                    }
                    wss[wsLastId] = null;
                    wsLastId--;

                    System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
                }
            }
        }
    }
}
