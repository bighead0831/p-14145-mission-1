package com.back;

import com.back.standard.util.TestUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String input) {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray(); // 출력을 끄고
        Scanner scanner = TestUtil.genScanner(input+"\n종료"); // 명령을 선입력해서 넣어주기

        new App(scanner).run();

        String out = byteArrayOutputStream.toString().trim(); // 모든 출력을 out에 모아주기
        TestUtil.clearSetOutToByteArray();

        return out;
    }
}
