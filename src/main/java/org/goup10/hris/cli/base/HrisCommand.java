package org.goup10.hris.cli.base;

import java.util.Map;

public interface HrisCommand {
    String getName();
    void execute(Map<String, Object> context);
}
