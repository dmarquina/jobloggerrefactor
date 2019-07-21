package joblogger;

import java.text.DateFormat;
import java.util.Date;

import static joblogger.util.Constants.EMPTY_CHARACTER;
import static joblogger.util.Constants.ERROR_TAG;
import static joblogger.util.Constants.MESSAGE_TAG;
import static joblogger.util.Constants.WARNING_TAG;

public class BuildLogServiceImpl implements BuildLogService {

  @Override
  public String buildFinalLogMessage(String messageText, boolean logError, boolean logWarning, boolean logMessage) {
    messageText = messageText.trim();
    String finalMessage = EMPTY_CHARACTER;
    if (logError) {
      finalMessage = ERROR_TAG + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
    }

    if (logWarning) {
      finalMessage = finalMessage + WARNING_TAG + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
    }

    if (logMessage) {
      finalMessage = finalMessage + MESSAGE_TAG + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
    }
    return finalMessage;
  }

  @Override
  public int buildDataBaseMessageType(boolean logError, boolean logWarning, boolean logMessage) {
    if (logMessage) {
      return 1;
    }

    if (logError) {
      return 2;
    }

    if (logWarning) {
      return 3;
    }
    return 0;
  }
}
