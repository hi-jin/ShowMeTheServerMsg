package main;

import java.util.Scanner;

import main.FileIO;

public class Main {

	public static void main(String[] args) {
		System.out.println("웹에 원하는 메시지를 띄우는 프로그램입니다.");;
		int selection;
		Scanner in = new Scanner(System.in);
		System.out.println("현재 메시지");
		FileIO.showMsg();
		System.out.println("------------------------------------\n어떤 작업을 하시겠습니까?");
		System.out.println("0. 종료 // 1. 메시지 추가 // 2. 메시지 삭제 // 3. 메시지 전체 삭제 //");
		while((selection = in.nextInt()) != 0) {
			in.nextLine();
			switch(selection) {
			case 1:
				System.out.println("추가할 메시지를 입력하세요.");
				String msg = in.nextLine();
				FileIO.addMsg(msg);
				break;
			case 2:
				System.out.println("삭제할 메시지 번호를 입력하세요.");
				int index = in.nextInt();
				FileIO.removeMsg(index);
				break;
			case 3:
				FileIO.clearMsg();
				System.out.println("초기화 되었습니다.");
				break;
			default:
				System.out.println("0~3의 숫자만 입력하세요");
				break;
			}
			System.out.println("현재 메시지");
			FileIO.showMsg();
			System.out.println("------------------------------------\n어떤 작업을 하시겠습니까?");
			System.out.println("0. 종료 // 1. 메시지 추가 // 2. 메시지 삭제 // 3. 메시지 전체 삭제 //");
		}
		in.close();
		FileIO.writeMsg();
		System.out.println("적용되었습니다");
	}
}
