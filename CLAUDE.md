# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this
repository.

**Build Commands:**

```bash
./mvnw clean spring-javaformat:apply compile                    # Compile application
./mvnw spring-javaformat:apply test                             # Run all tests
```

## Implemented Features

## Development Requirements

### Prerequisites

- Java 21+

### Code Standards

- Use builder pattern if the number of arguments is more than two
- Write javadoc and comments in English
- Spring Java Format enforced via Maven plugin
- All code must pass formatting validation before commit
- Use Java 21 compatible features (avoid Java 22+ specific APIs)
- Use modern Java technics as much as possible like Java Records, Pattern Matching, Text Block etc ...
- Be sure to avoid circular references between classes and packages.

### Testing Strategy

- **Unit Tests**: JUnit 5 with AssertJ for service layer testing
- **Integration Tests**: `@SpringBootTest` + Testcontainers (PostgreSQL)
- **E2E Tests**: Playwright with Testcontainers for full user journey testing
- **Test Data Management**: Database cleanup after each test using `JdbcClient` to maintain test independence
- **Test Stability**: All tests must pass consistently; use specific Playwright selectors (e.g., `locator("button").getByText("Remove")`) to avoid ambiguity
- All tests must pass before completing tasks

### After Task completion

- Ensure all code is formatted using `./mvnw spring-javaformat:apply`
- Run full test suite with `./mvnw test`
- For every task, notify that the task is complete and ready for review by the following command:

```
osascript -e 'display notification "<Message Body>" with title "<Message Title>"'
```

# important-instruction-reminders
Do what has been asked; nothing more, nothing less.