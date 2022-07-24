package org.goup10.hris.cli.base;

public abstract class BaseCommand implements HrisCommand {
    protected String name;

    public BaseCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
