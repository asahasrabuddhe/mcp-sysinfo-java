# SysInfo Java MCP Server

A Model Context Protocol (MCP) server implementation in Java that provides system information tools for AI assistants. This server exposes process management capabilities through a Spring Boot application with Spring AI integration.

## Features

- **Process Listing**: List all running processes with pagination support
- **Process Inspection**: Detailed inspection of specific processes by PID
- **MCP Server**: Compatible with MCP clients and AI assistants

## Prerequisites

- Java 21 or higher
- Gradle 8.x (or use the included Gradle wrapper)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/asahasrabuddhe/mcp-sysinfo-java.git
   cd sysinfo-java
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

## Usage

### Running the Server

Start the MCP server:
```bash
./gradlew bootRun
```

The server will start and be ready to accept MCP tool requests.

### Available Tools

#### 1. List Processes
Lists all running processes with pagination support.

#### 2. Inspect Process
Inspect a specific process by its Process ID (PID).

## Configuration

The application can be configured through `src/main/resources/application.properties`:

```properties
spring.application.name=sysinfo-java
spring.main.bannerMode=off
logging.level.root=OFF
logging.pattern.console=%msg%n
```

### Building Distribution

```bash
./gradlew build
```

The built JAR will be available in `build/libs/`.

## Dependencies

- **Spring Boot 3.5.3**: Core framework
- **Spring AI MCP Server 1.0.0**: MCP server implementation
- **Jackson 2.17.1**: JSON processing
- **JUnit 5**: Testing framework

## Technical Details

### MCP Integration

This server implements the Model Context Protocol (MCP) using Spring AI's MCP server capabilities. Tools are exposed through the `@Tool` annotation and automatically registered with the MCP server.

### Process Management

The server uses Java's `ProcessHandle` API to interact with system processes, providing:
- Safe process enumeration
- Process metadata extraction
- Cross-platform compatibility

### Security Considerations

- The server only provides read-only access to process information
- No process control capabilities (start/stop/kill) are exposed
- Process information is limited to what's available through standard Java APIs
