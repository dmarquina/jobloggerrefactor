package test;

import joblogger.BuildLogServiceImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuildLogServiceImplTest {

  private BuildLogServiceImpl buildLogService = new BuildLogServiceImpl();

  @Before
  private void prepareValues() {
  }

  @Test
  public void buildFinalLogMessageTest_FinalMessage_Empty_Expected() {
    String messageText = "Text Message";
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = false;

    String finalMessage =
        buildLogService.buildFinalLogMessage(messageText, logError, logWarning, logMessage);

    assertEquals(finalMessage, "");
  }

  @Test
  public void buildFinalLogMessageTest_LogError_Expected() {
    String messageText = "Internal Error: Contact with administrator.";
    boolean logError = true;
    boolean logWarning = false;
    boolean logMessage = false;

    String finalMessage =
        buildLogService.buildFinalLogMessage(messageText, logError, logWarning, logMessage);

    assertEquals(finalMessage,
                 "error 2019-05-23 15:08:03 UTC Internal Error: Contact with administrator.");
  }

  @Test
  public void buildFinalLogMessageTest_LogWarning_Expected() {
    String messageText = "Advertencia: Debe usar solo números.";
    boolean logError = false;
    boolean logWarning = true;
    boolean logMessage = false;

    String finalMessage =
        buildLogService.buildFinalLogMessage(messageText, logError, logWarning, logMessage);

    assertEquals(finalMessage,
                 "warning 2019-05-23 15:08:03 UTC Advertencia: Debe usar solo números.");
  }

  @Test
  public void buildFinalLogMessageTest_LogMessage_Expected() {
    String messageText = "Información: Log de consola creado con éxito.";
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = true;

    String finalMessage =
        buildLogService.buildFinalLogMessage(messageText, logError, logWarning, logMessage);

    assertEquals(finalMessage, "warning 2019-05-23 15:08:03 UTC Información: Log de consola creado"
        + " con éxito.");
  }

  @Test
  public void buildLogToDataBase_Zero_Expected() {
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = false;

    int logType = buildLogService.buildDataBaseMessageType(logError, logWarning, logMessage);

    assertEquals(logType, 0);
  }

  @Test
  public void buildLogToDataBase_LogMessage_Expected() {
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = true;

    int logType = buildLogService.buildDataBaseMessageType(logError, logWarning, logMessage);

    assertEquals(logType, 1);
  }

  @Test
  public void buildLogToDataBase_LogError_Expected() {
    boolean logError = true;
    boolean logWarning = false;
    boolean logMessage = false;

    int logType = buildLogService.buildDataBaseMessageType(logError, logWarning, logMessage);

    assertEquals(logType, 2);
  }

  @Test
  public void buildLogToDataBase_LogWarning_Expected() {
    boolean logError = false;
    boolean logWarning = true;
    boolean logMessage = false;

    int logType = buildLogService.buildDataBaseMessageType(logError, logWarning, logMessage);

    assertEquals(logType, 3);
  }
}
