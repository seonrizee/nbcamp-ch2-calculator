package lv3;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {

    CALCULATE("계산하기"),
    VIEW("이전 연산 결과 조회하기"),
    VIEW_WITH_VALUE("이전 연산 결과 조회하기 (입력값을 기준으로 크거나, 작은 목록)"),
    REMOVE_FIRST("가장 먼저 저장된 연산 결과 삭제하기"),
    EXIT("종료하기"),
    ;

    private final String description;

    Menu(String description) {
        this.description = description;
    }

    /**
     * 사용자가 선택할 수 있는 메뉴를 출력합니다.
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("---------------------------------------------");
        for (Menu menu : Menu.values()) {
            System.out.println((menu.ordinal() + 1) + ". " + menu.getDescription());
        }
        System.out.println("---------------------------------------------");
        System.out.print("원하는 기능의 번호를 입력해주세요: ");
    }

    /**
     * 사용자로부터 입력받은 번호에 해당하는 메뉴를 반환합니다.
     *
     * @param command
     * @return
     */
    public static Optional<Menu> getMenu(int command) {
        return Arrays.stream(Menu.values())
                .filter(cur -> cur.ordinal() + 1 == command)
                .findFirst();
    }

    public String getDescription() {
        return description;
    }
}
