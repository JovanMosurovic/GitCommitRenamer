# Git Commit Renamer

A plugin for IntelliJ IDEA that adds a "Rename current commit" action to the Git section in the main toolbar. 
This plugin simplifies Git workflow by providing a convenient way to rename the most recent commit directly from the IDE interface. <br/>
By integrating this functionality into the IDE, developers can maintain clean commit histories without needing to use terminal commands or remember Git's more complex rebasing operations.

> Through this project, I aimed to explore the JetBrains Plugin DevKit ecosystem and Gradle build system while creating a practical solution for a common Git workflow challenge.
>
> This plugin was developed as a solution for a JetBrains internship task that required creating a plugin for IntelliJ IDEA that adds a "Rename current commit"
action accessible from the Git section in the main toolbar.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Development Setup](#development-setup)
- [Troubleshooting](#troubleshooting)

## Features

- Adds a "Rename current commit" action to the Git section in the main toolbar
- Provides a simple dialog interface for entering the new commit message
- Displays notification feedback after the operation completes
- Works with any Git repository opened in the IDE

## Installation

1. Download the plugin ZIP file from the [releases page](https://github.com/JovanMosurovic/GitCommitRenamer/releases)
2. In IntelliJ IDEA:
   ```
   Settings → Plugins → ⚙️ → Install Plugin from Disk...
   ```
3. Select the downloaded ZIP file
4. Restart IntelliJ IDEA when prompted

## Usage

1. Open a Git repository in IntelliJ IDEA
2. Click on the "Rename current commit" action in the Git section of the main toolbar
3. Enter the new commit message in the dialog
4. Click "OK" to apply the change

## Development Setup

If you want to build the plugin from source, follow these instructions:

### Prerequisites

- IntelliJ IDEA (Community or Ultimate Edition)
- Java Development Kit (JDK) 11 or newer (Java 17 recommended)
- Git

### Setting Up the Project

1. Clone the repository:
   ```
   git clone https://github.com/JovanMosurovic/GitCommitRenamer.git
   ```

2. Open the project in IntelliJ IDEA:
   ```
   Open → [select cloned repository directory]
   ```
   Make sure to select the inner GitCommitRenamer directory that contains the Gradle files

3. Configure JDK:
   ```
   Project Structure → Project → Set SDK to Java 11+ (Java 17 recommended)
   ```

4. Configure Gradle:
   ```
   Settings → Build, Execution, Deployment → Build Tools → Gradle
   ```
   Make sure Gradle JVM is set to the same Java version as your Project SDK

5. Build the project:
   ```
   View → Tool Windows → Gradle → Run 'build' task
   ```

6. Run/Debug the plugin:
   ```
   Run → Edit Configurations → Add New Configuration → Gradle
   ```
   - Set the task to `runIde`
   - Ensure the working directory is set to the directory containing build.gradle.kts
   - Run the configuration

## Troubleshooting

### Java Version Issues

If you encounter an error like:
```
Dependency requires at least JVM runtime version 11. This build uses a Java 8 JVM.
```

Make sure:
1. You have JDK 11 or newer installed
2. Your Project SDK is set to JDK 11 or newer in Project Structure
3. Gradle is using the correct JDK:
   ```
   Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JVM
   ```

### Gradle Build Directory Issues

If you see an error like:
```
Directory 'path/to/project' does not contain a Gradle build.
```

Make sure:
1. You're in the correct directory containing the build.gradle.kts file
2. If running/debugging the plugin through IntelliJ:
   - Edit your run configuration
   - Verify the "Working directory" setting points to the directory containing build.gradle.kts

### Other Issues

- Try invalidating caches and restarting IntelliJ:
  ```
  File → Invalidate Caches / Restart
  ```
- Ensure all Gradle dependencies are resolving correctly
- Check the IntelliJ IDEA log for more detailed error information
