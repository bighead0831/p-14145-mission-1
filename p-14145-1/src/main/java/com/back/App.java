package com.back;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    Scanner sc = new Scanner(System.in);

    private List<WiseSaying> wss = new ArrayList<>(); // 일단 10,000개 받을 수 있게 설정.
//    private int wsLastId = -1; // ws배열에 아무것도 안 들어있으므로, 인덱스 -1로 초기화
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

//        wsLastId++; // 등록하면, ws배열에 하나 추가
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying ws = new WiseSaying(++cntId+1, content, author);
        wss.add(ws);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(ws.getId()));
    }

    private void list() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for(WiseSaying ws : wss.reversed()) {
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getContent(), ws.getAuthor());
        }
    }

    private void delete(String cmd) {
        int deleteId = isDelete(cmd);
        if(deleteId==-1) return;

        if(findId(deleteId)==-1)
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", deleteId);
        else {
            deleteLogic(deleteId, deleteId);
        }
    }

    private void modify(String cmd) {
        int modifyId = isModify(cmd);
        if(modifyId==-1) return;

        if(findId(modifyId)==-1)
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", modifyId);
        else {
            modifyLogic(modifyId);
        }
    }

    /* --- Logic 메서드 모음 --- */

    private int findId(int id) {
        int cnt = 0;
        for(WiseSaying ws : wss) {
            if(ws.getId() == id)
                return cnt;
            cnt++;
        }
        return -1;
    }

    private void deleteLogic(int id, int deleteId) {
        wss.remove(findId(deleteId));
        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }

    private void modifyLogic(int id) {
        WiseSaying ws = wss.get(findId(id));
        System.out.printf("명언(기존) : %s\n명언 : ", ws.getContent());
        ws.setContent(sc.nextLine());
        System.out.printf("작가(기존) : %s\n작가 : ", ws.getAuthor());
        ws.setAuthor(sc.nextLine());
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
