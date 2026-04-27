package com.back;

public class Main {
    public static void main() {
        App app = new App();
        app.run();
//        testRq1();
    }
    private static void testRq1() {
        Rq rq = new Rq("목록?&searchKeywordType=content&searchKeyword=자바&page=2");
        String searchKeywordType = rq.getParam("searchKeywordType", "");
        String searchKeyword = rq.getParam("searchKeyword", "");
        int page = rq.getParamInt("page", -1);
        int id = rq.getParamInt("id", -1);

        System.out.println("actionName : " + rq.getActionName());
        System.out.println("param searchKeywordType : " + searchKeywordType);
        System.out.println("param searchKeyword : " + searchKeyword);
        System.out.println("param page : " + page);
        System.out.println("param id : " + id);
    }
}

// 컨트롤러(데이터 제공) - 서비스(데이터 가공) - 레포지터리(데이터 찾기)