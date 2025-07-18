package lv3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {

    CALCULATE(1, "계산하기"),
    VIEW(2, "이전 연산 결과 조회하기"),
    VIEW_WITH_VALUE(3, "이전 연산 결과 조회하기 (입력값을 기준으로 크거나, 작은 목록)"),
    REMOVE_FIRST(4, "가장 먼저 저장된 연산 결과 삭제하기"),
    EXIT(5, "종료하기"),
    ;

    private final int command;
    private final String description;

    Menu(int command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * 사용자가 선택할 수 있는 메뉴의 목록을 반환합니다.
     */
    public static List<String> getFormattedMenuItems() {
        return Arrays.stream(Menu.values())
                .map(menu -> (menu.getCommand()) + ". " + menu.getDescription())
                .collect(Collectors.toList());
    }

    /**
     * 사용자로부터 입력받은 번호에 해당하는 메뉴를 반환합니다.
     *
     * @param command 사용자가 입력한 번호
     * @return 일치하는 Menu 혹은 예외
     */
    public static Menu findMenu(int command) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.command == command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 번호입니다. 올바른 번호를 입력해주세요"));
    }

    public int getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
