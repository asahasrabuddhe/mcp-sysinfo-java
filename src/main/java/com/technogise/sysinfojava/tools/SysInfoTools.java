package com.technogise.sysinfojava.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SysInfoTools {

    @Tool(
            name = "list-processes",
            description = "Lists all the running processes with pagination support"
    )
    public String listProcesses(
            @ToolParam(description = "Index of first process to return") Integer cursor,
            @ToolParam(description = "Maximum number of processes to return") Integer limit
    ) {
        if (cursor == null) {
            cursor = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        var processList = ProcessHandle.allProcesses()
                .filter(ProcessHandle::isAlive)
                .map(process -> Map.of(
                        "pid", process.pid(),
                        "info", process.info().toString()
                )).
                toList();

        int end = Math.min(cursor + limit, processList.size());
        var paged = processList.subList(cursor, end);

        Integer nextCursor = end < processList.size() ? end : null;

        Map<String, Object> result = new HashMap<>();
        result.put("results", paged);
        if (nextCursor != null) {
            result.put("nextCursor", nextCursor);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    @Tool(description = "Inspect a specified process by pid")
    public String inspectProcess(
            @ToolParam(description = "PID of the process to be inspected") int pid
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(ProcessHandle.of(pid));
        } catch (JsonProcessingException e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
}
