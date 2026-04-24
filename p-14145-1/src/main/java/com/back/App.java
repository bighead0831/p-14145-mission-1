package com.back;

import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);

    private WiseSaying[] wss = new WiseSaying[10000]; // 일단 10,000개 받을 수 있게 설정.
    private int wsLastId = -1; // ws배열에 아무것도 안 들어있으므로, 인덱스 -1로 초기화
    private int cntId = -1; // 꾸준히 오르기만 하는 등록번호 (독고다이~~)

    public void run() {
        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if(cmd.equals("종료")) break;
            else if(cmd.equals("등록")) register(cmd);
            else if(cmd.equals("목록")) list();
            else if(cmd.startsWith("삭제")) delete(cmd);
            else if(cmd.startsWith("수정")) modify(cmd);
        }
    }

    /* --- Action 메서드 모음 --- */

    private void register(String cmd) {
        WiseSaying ws = new WiseSaying();

        wsLastId++; // 등록하면, ws배열에 하나 추가
        ws.id = ++cntId+1;
        System.out.print("명언 : ");
        ws.content = sc.nextLine();
        System.out.print("작가 : ");
        ws.author = sc.nextLine();
        wss[wsLastId] = ws;

        System.out.println("%d번 명언이 등록되었습니다.".formatted(ws.id));
    }

    private void list() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for(int i=wsLastId; i>=0; i--) {
            System.out.printf("%d / %s / %s\n", wss[i].id, wss[i].content, wss[i].author);
        }
    }

    private void delete(String cmd) {
        int deleteId = isDelete(cmd);
        if(deleteId==-1) return;

        int deleteRealId = findId(deleteId); // 삭제할 id의 배열에서의 실제 인덱스
        if(deleteRealId==-1)
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", deleteId);
        else {
            deleteLogic(deleteRealId, deleteId);
        }
    }

    private void modify(String cmd) {
        int modifyId = isModify(cmd);
        if(modifyId==-1) return;

        int modifyRealId = findId(modifyId); // 수정할 id의 배열에서의 실제 인덱스
        if(modifyRealId==-1)
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", modifyId);
        else {
            modifyLogic(modifyRealId);
        }
    }

    /* --- Logic 메서드 모음 --- */

    private int findId(int id) {
        for(int i=0; i<=wsLastId; i++) { // deleteId와 같은 wss배열의 값의 id가 있는지 확인
            if(wss[i].id == id) {
                return i;
            }
        }
        return -1;
    }

    private void deleteLogic(int id, int deleteId) {
        for(int i=id; i<wsLastId; i++) {
            wss[i] = wss[i+1];
        }
        wss[wsLastId--] = null;

        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }

    private void modifyLogic(int id) {
        System.out.printf("명언(기존) : %s\n명언 : ", wss[id].content);
        wss[id].content = sc.nextLine();
        System.out.printf("작가(기존) : %s\n작가 : ", wss[id].author);
        wss[id].author = sc.nextLine();
    }

    private int isDelete(String cmd) {
        String[] cmdBits = cmd.split("="); // cmdBits: [삭제?id=][index]
        if(cmdBits.length < 2 || !cmdBits[0].equals("삭제?id")) {
            System.out.println("\'삭제\'명령을 정확히 작성해주시기 바랍니다.");
            return -1;
        }
        return Integer.parseInt(cmdBits[1]);
    }


    private int isModify(String cmd) {
        String[] cmdBits = cmd.split("="); // cmdBits: [삭제?id=][index]
        if(cmdBits.length < 2 || !cmdBits[0].equals("수정?id")) {
            System.out.println("\'수정\'명령을 정확히 작성해주시기 바랍니다.");
            return -1;
        }
        return Integer.parseInt(cmdBits[1]);
    }

}
