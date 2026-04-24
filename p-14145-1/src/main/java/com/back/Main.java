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
        int cntId = -1;

        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();
            if(cmd.equals("종료")) {
                break;
            } else if(cmd.equals("등록")) {
                WiseSaying ws = new WiseSaying();

                wsLastId++; // 등록하면, ws배열에 하나 추가
                ws.id = ++cntId+1;
                System.out.print("명언 : ");
                ws.content = sc.nextLine();
                System.out.print("작가 : ");
                ws.author = sc.nextLine();
                wss[wsLastId] = ws;

                System.out.printf("%d번 명언이 등록되었습니다.\n", ws.id);
            } else if(cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언\n----------------------");
                for(int i=wsLastId; i>=0; i--) {
                    System.out.printf("%d / %s / %s\n", wss[i].id, wss[i].content, wss[i].author);
                }
            } else if(cmd.startsWith("삭제")) {
                String[] cmdBits = cmd.split("="); // cmdBits: [삭제?id=][index]
                if(cmdBits.length < 2 || !cmdBits[0].equals("삭제?id")) {
                    System.out.println("\'삭제\'명령을 정확히 작성해주시기 바랍니다.");
                    continue;
                }

                int deleteId = Integer.parseInt(cmdBits[1]);

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
                    wss[wsLastId--] = null;

                    System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
                }
            } else if(cmd.startsWith("수정")) {
                String[] cmdBits = cmd.split("="); // cmdBits: [삭제?id=][index]
                if(cmdBits.length < 2 || !cmdBits[0].equals("수정?id")) {
                    System.out.println("\'수정\'명령을 정확히 작성해주시기 바랍니다.");
                    continue;
                }
                int modifyId = Integer.parseInt(cmdBits[1]);

                int modifyRealId = -1; // 수정할 id의 배열에서의 실제 인덱스

                for(int i=0; i<=wsLastId; i++) { // modifyId와 같은 wss배열의 값의 id가 있는지 확인
                    if(wss[i].id == modifyId) {
                        modifyRealId = i;
                        break;
                    }
                }

                if(modifyRealId==-1)
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", modifyId);
                else {
                    System.out.printf("명언(기존) : %s\n명언 : ", wss[modifyRealId].content);
                    wss[modifyRealId].content = sc.nextLine();
                    System.out.printf("작가(기존) : %s\n작가 : ", wss[modifyRealId].author);
                    wss[modifyRealId].author = sc.nextLine();
                }
            }
        }
    }
}
