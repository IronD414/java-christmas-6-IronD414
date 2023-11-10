package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.model.Customer;
import christmas.view.InputView;
import christmas.view.OutputView;
import org.assertj.core.data.MapEntry;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new ChristmasController();
        christmasController.run();
    }
}
