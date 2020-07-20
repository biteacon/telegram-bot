package com.biteacon;

import com.biteacon.constants.BotConfig;
import net.glxn.qrgen.javase.QRCode;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class ExplorerBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String response_message_text = CommandFilter.getInstance().matchCommands(message_text);

            // get QR file from text using defaults
            File file = QRCode.from(message_text).file();
            SendPhoto message = new SendPhoto().setChatId(chat_id).setPhoto(file).setCaption(response_message_text);
//            SendMessage message = new SendMessage() // Create a message object object
//                    .setChatId(chat_id)
//                    .setText(response_message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.EXPLORER_USER;
    }

    @Override
    public String getBotToken() {
        return BotConfig.EXPLORER_TOKEN;
    }
}
