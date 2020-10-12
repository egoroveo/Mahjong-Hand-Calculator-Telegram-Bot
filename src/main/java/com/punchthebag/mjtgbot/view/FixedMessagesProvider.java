package com.punchthebag.mjtgbot.view;

import org.springframework.stereotype.Service;

@Service
public class FixedMessagesProvider {
    public static final String START_MESSAGE = "Send me a hand or use the example \"124578m124589p22s\"";
    public static final String NOT_SUPPORTED_MESSAGE = "Commands are not supported. Please enter a hand";
    public static final String ERROR_MESSAGE = "Invalid hand. Please provide 14 tiles";

    public static final String INLINE_TITLE_CORRECT = "Analyze hand";
    public static final String INLINE_TITLE_ERROR = "Invalid hand";

    public static final String START_COMMAND = "/start";
    public static final String HELP_COMMAND = "/help";


    public String getMessageForCommand(String command) {
        if (START_COMMAND.equals(command) || HELP_COMMAND.equals(command)) {
            return START_MESSAGE;
        } else {
            return NOT_SUPPORTED_MESSAGE;
        }
    }

    public String generateErrorResponse() {
        return ERROR_MESSAGE;
    }

    public String generateTitle(boolean valid) {
        return valid ? INLINE_TITLE_CORRECT : INLINE_TITLE_ERROR;
    }
}
