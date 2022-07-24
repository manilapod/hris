package org.goup10.hris.cli.base;

import java.util.*;

public class HrisComponent extends BaseCommand {
    protected List<HrisCommand> commands;

    public HrisComponent(String name) {
        this(name, new ArrayList<>());
    }

    public HrisComponent(String name, List<HrisCommand> commands) {
        super(name);
        this.commands = commands;
    }

    public boolean add(HrisCommand command) {
        return this.commands.add(command);
    }

    public boolean remove(HrisCommand command) {
        return this.commands.remove(command);
    }

    public Collection<HrisCommand> getCommands() {
        return this.commands;
    }

    @Override
    public void execute(Map<String, Object> context) {
        String header = this.name + " supports following features:";
        this.printHeader(header);
        this.printOptions();
        this.printBar(header.length());

        System.out.print("What would you like to do? Please select an option number: ");
        int option = this.readOption();
        HrisCommand command = this.commands.get(option - 1);
        command.execute(context);

        HrisExecutor executor = HrisExecutor.getInstance();
        executor.fallThrough(this);
    }

    public void printHeader(String header) {
        this.printBar(header.length());
        System.out.println(header);
        this.printBar(header.length());
    }

    public void printBar(int length) {
        for (int i = 0; i < length; ++i) {
            System.out.print('-');
        }
        System.out.println();
    }

    public void printOptions() {
        int number = 0;
        for (HrisCommand command: this.commands) {
            System.out.println(++number + ". " + command.getName());
        }
    }

    public int readOption() {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option < 1) {
            System.out.print("Invalid input! Must choose a value greater than 0! Please re-enter the value: ");
            return this.readOption();
        }

        int size = this.commands.size();
        if (option > size) {
            System.out.print("Invalid input! Must choose a value less than " + size + "! Please re-enter the value: ");
            return this.readOption();
        }

        return option;
    }
}
