

<div style="text-align: justify">

<div style="text-align: center">

# BATTLESHIP CL

</div>

Lisenced under **MIT**, created by **Pedro Marín Sanchis**.
    
**READ LISENCE BEFORE USE.**

---
    
### DOWNLOAD
    
Head to the [releases](https://github.com/pedro09pm/BattleshipCL/releases) page of this repository, where you'll find all the different versions.

### HOW TO PLAY

First of all, before logging anything, remember to configure the loggers behaviour with the methods explained below. Remember that **the logger has to be initialized**. This can be done by using the **initialize()** method.

```java
Logger.initialize();
```

Simple Java Logger is a **static** class, it is **not** meant to be instanced.

#### · HEADER TEXT

You can change the header text for newly created log files using the **setHeaderText()** method.

<pre><code>Logger.setHeaderText("HEADER TEXT");</code></pre>

#### · LOGS

##### LOG TYPES

Logs can be of various types, which come with specific properties that change their apprearance in the final log file. All log types except LogType.REQUIRED have a boolean flag declared at class level which can be changed to decide if they are logged or not.

Default log types have the following apperance, note that LogType.HEADER has neither a date nor a type label.


```
Test
[2022-11-12_00-41-06]  [WARNING] : Test
[2022-11-12_00-41-06]     [INFO] : Test
[2022-11-12_00-41-06]    [DEBUG] : Test
[2022-11-12_00-41-06] [REQUIRED] : Test
```

##### DELETING OLD LOG FILES

Old log files will be deleted upon initialization (Set to **true** by default). To configure this behaviour use the **deleteOldLogsAtInit()** method.

```java
Logger.deleteOldLogsAtInit(false);
```

##### DEACTIVATING LOGGING FOR SPECIFIC TYPES:

Say you want to make it so LogType.DEBUG logs are not logged. Use the **changeLogTypeLogging()** method.

```java
Logger.changeLogTypeLogging(Logger.LogType.DEBUG, false);
```

##### CHANGING LOG FILE PATHS

The default path for logs is "logs/". They will look like so:

```
Log[2022-11-12_00-41-06].txt
Log[2022-11-12_04-37-16].txt
```

To change the default log path use the **changeLogPath()** method. Remember that **this can only be done before initializing**.

```java
Logger.changeLogPath("logArchive/");
```

---

### HOW TO MODIFY

#### · ADDING LOG TYPES

Inside Logger.java you will find the enum "LogType":

```java
public static enum LogType {

    // LogType(isShown, includesTime).

    HEADER(false, false),
    WARNING(true, true),
    INFO(true, true),
    DEBUG(true, true),
    REQUIRED(true, true);

    private final boolean isTagShown;
    private final boolean includesTime;

    private LogType (boolean isTagShown, boolean includesTime) {

        this.isTagShown = isTagShown;
        this.includesTime = includesTime;

    }

}
```

Adding new log types is simple, it can be done by adding an element to the list like so:

```java
public static enum LogType {

    // LogType(isShown, includesTime).

    HEADER(false, false),
    WARNING(true, true),
    INFO(true, true),
    DEBUG(true, true),
    REQUIRED(true, true),
    NEWTYPE(true, true); // <----- Your new log type!

    ...
```

Keep in mind that the last element of the list must end with ";" and the rest of the elements must be separated by commas. Spacing for formatting and such is calculated automatically at initialization.

</div>
