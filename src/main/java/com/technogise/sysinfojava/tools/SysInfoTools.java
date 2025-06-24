package com.technogise.sysinfojava.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SysInfoTools {
    @Tool(description = "Lists all the processes running on the system")
    public String listProcesses() {
        var processList = ProcessHandle.allProcesses()
                .filter(ProcessHandle::isAlive)
                .map(process -> Map.of(
                        "pid", process.pid(),
                        "info", process.info().toString()
                )).
                toList();

        return processList.toString();
    }

    @Tool(description = "Inspect a specified process by pid")
    public String inspectProcess(
            @ToolParam(description = "PID of the process to be inspected") int pid
    ) {
        return ProcessHandle.of(pid).toString();
    }
}
