package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.util.InputUtil;

import java.util.List;

public class InputTest {
    private InputUtil inputUtil = InputUtil.of();
    @Test
    @DisplayName("자동차 이름 입력하기")
    public void 자동차_이름_입력하기() throws Exception {
        // given
        String names = "poni,hi,hello";

        // when
        String[] strings = inputUtil.splitString(names);

        // then
        Assertions.assertThat(strings.length).isEqualTo(3);
    }

    @Test
    @DisplayName("자동차 입력 시 공백문자를 포함한 경우")
    public void 자동차_입력_시_공백문자_예외() throws Exception {
        // given
        String names = "poni, hi, hello";

        // when
        List<String> nameList = List.of(inputUtil.splitString(names));
        // then
        Assertions.assertThatThrownBy(() -> nameList.forEach(inputUtil::validateCarName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백을 제외하고 입력해주세요.");
    }

    @Test
    @DisplayName("자동차 입력 시 1자이하 또는 5자이상 포함한 경우")
    public void 자동차_이름_문자갯수_확인() throws Exception {
        // given
        String name1 = "racingcar";
        String name2 = "";

        // when

        // then
        Assertions.assertThatThrownBy(() -> inputUtil.validateCarName(name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1-5자까지 입력 가능합니다.");
        Assertions.assertThatThrownBy(() -> inputUtil.validateCarName(name2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1-5자까지 입력 가능합니다.");
    }
    
    @Test
    @DisplayName("자동차 입력 시 문자열(영문자) 이외의 값을 넣은 경우")
    public void 자동차_이름_문자열_타입확인() throws Exception {
        // given
        String name1 = "자동차";
        String name2 = "!car";
        String name3 = "car12";

        
        // when
        
        
        // then
        Assertions.assertThatThrownBy(() -> inputUtil.validateCarName(name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문자(영문자)만 입력해주세요.");

        Assertions.assertThatThrownBy(() -> inputUtil.validateCarName(name2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문자(영문자)만 입력해주세요.");

        Assertions.assertThatThrownBy(() -> inputUtil.validateCarName(name3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문자(영문자)만 입력해주세요.");
    }

    @Test
    @DisplayName("횟수 입력 시, 1이하의 수를 입력한 경우")
    public void 횟수_입력_1이하_확인() throws Exception {
        // given
        String input = "0";

        // when
        int number = inputUtil.stringToInt(input);

        // then
        Assertions.assertThatThrownBy(() -> inputUtil.checkNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1이상의 숫자를 입력하세요.");

    }

    @Test
    @DisplayName("횟수 입력 시, 숫자 이외의 값을 입력한 경우")
    public void 횟수_입력_문자열_입력확인() throws Exception {
        // given
        String number = "rk12";

        // when

        // then
        Assertions.assertThatThrownBy(() -> inputUtil.stringToInt(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자만 입력해주세요.");

    }
}
