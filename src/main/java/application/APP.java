package application;

import controller.Controller;
import view.input.ConsoleInput;
import view.input.InputView;
import view.output.ConsoleOutput;
import view.output.OutputView;

import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new ConsoleInput(scanner);
        OutputView outputView = new ConsoleOutput();
        new Controller(inputView,outputView);
    }
}
