package lv2;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {

    CALCULATE(1, "계산하기"),
    VIEW(2, "이전 연산 결과 조회하기"),
    EXIT(3, "종료하기"),
    ;

    private final int command;
    private final String description;

    Menu(int command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * 사용자가 선택할 수 있는 메뉴를 출력합니다.
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("---------------------------------------------");
        for (Menu menu : Menu.values()) {
            System.out.println(menu.getCommand() + ". " + menu.getDescription());
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
    public static Menu getMenu(int command) {
        Optional<Menu> selectedMenu = Arrays.stream(Menu.values())
                .filter(cur -> cur.command == command)
                .findFirst();
        return selectedMenu.orElse(null);
    }

    public int getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
