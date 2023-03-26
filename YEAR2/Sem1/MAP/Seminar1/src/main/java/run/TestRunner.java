package run;

import container.Strategy;
import model.MessageTask;
import runner.*;
import sort.SortingStrategy;
import sort.SortingTask;

import java.time.LocalDateTime;

public class TestRunner {
    public static MessageTask[] getMessages() {
        MessageTask taskLaborator = new MessageTask(
                "1","Seminar", "tema laborator",
                "Florentin", "Razvan", LocalDateTime.now());
        MessageTask taskTema = new MessageTask(
                "2","Laborator", "Solutie",
                "Razvan", "Florentin", LocalDateTime.now());
        MessageTask taskNota = new MessageTask(
                "3","Nota Lab", "10",
                "Florentin", "Razvan", LocalDateTime.now());
        return new MessageTask[]{
                taskLaborator, taskTema, taskNota
        };
    }

    public static void run() {
        /*StrategyTaskRunner runner = new StrategyTaskRunner(Strategy.LIFO);
        MessageTask[] messages = getMessages();
        runner.addTask(messages[0]);
        runner.addTask(messages[1]);
        runner.addTask(messages[2]);
        runner.executeAll();*/

        StrategyTaskRunner runner = new StrategyTaskRunner(Strategy.FIFO);
        DelayTaskRunner delay = new DelayTaskRunner(runner);
        PrinterTaskRunner printer = new PrinterTaskRunner(runner);

        MessageTask[] messages = getMessages();

        delay.addTask(messages[0]);
        delay.addTask(messages[1]);
        delay.addTask(messages[2]);
        delay.executeAll();


        printer.addTask(messages[0]);
        printer.addTask(messages[1]);
        printer.addTask(messages[2]);
        printer.executeAll();


        /*MessageTask[] messages = getMessages();
        for(MessageTask message : messages) {
            message.execute();
        }*/


        int[] arr={1,4,2,7,8,6,4,9,2,3,5,7,0};
        SortingStrategy method=SortingStrategy.QuickSort;
        SortingTask srt = new SortingTask("1", "1", arr, method);

        SortRunner2 sortRunner2 = new SortRunner2(runner);
        sortRunner2.addTask(srt);
        sortRunner2.executeAll();
    }
}
