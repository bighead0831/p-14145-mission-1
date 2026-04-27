package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Scanner;

//역할 : 고객의 명령을 입력받고 적절한 응답을 표현
//이 단계에서는 스캐너 사용가능
//이 단계에서는 출력 사용가능
//역할 : 명언에 관련된 응대
public class WiseSayingController {
    private final Scanner sc;
    private final List<WiseSaying> wss = new ArrayList<>();
    private int cntId = -1; // 꾸준히 오르기만 하는 명언 등록번호 (독고다이~~)

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
    }

    /* --- Action 메서드 모음 --- */
    public void actionRegister() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying ws = new WiseSaying(++cntId+1, content, author);
        wss.add(ws);

        System.out.println(String.format("%d번 명언이 등록되었습니다.", ws.getId()));
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        for(WiseSaying ws : wss.reversed()) {
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getContent(), ws.getAuthor());
        }
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamInt("id", -1);
        if(id==-1) {
            System.out.println("id를 숫자로 입력해주시기 바랍니다.");
            return;
        }

        if(findById(id)==-1)
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        else {
            deleteLogic(id);
        }
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamInt("id", -1);
        if(id==-1) {
            System.out.println("id를 숫자로 입력해주시기 바랍니다.");
            return;
        }

        if(findById(id)==-1)
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        else {
            modifyLogic(id);
        }
    }

    public void actionHelp() {
        System.out.println("""
                = 명령어 목록 =
                1. 등록
                2. 목록
                3. 수정?id={번호}
                4. 삭제?id={번호}
                """);
    }

    /* --- Logic 메서드 모음 --- */
    private int findById(int id) { // 스트림으로 구현!
        return IntStream.range(0, wss.size())
                .filter(i -> wss.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    private void deleteLogic(int id) {
        wss.remove(findById(id));
        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    private void modifyLogic(int id) {
        WiseSaying ws = wss.get(findById(id));
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
