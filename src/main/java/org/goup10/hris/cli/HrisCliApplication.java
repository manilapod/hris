package org.goup10.hris.cli;

import org.goup10.hris.cli.base.HrisExecutor;

import java.util.HashMap;
import java.util.Map;

public class HrisCliApplication {
    public static final String ENDPOINT = "endpoint";

    public static void main(String[] args) {
        Map<String, Object> context = new HashMap<>();
        context.put(HrisCliApplication.ENDPOINT, "http://localhost:8080");

        HrisExecutor executor = HrisExecutor.getInstance();
        executor.next(new RootComponent());
        executor.execute(context);
    }
}
