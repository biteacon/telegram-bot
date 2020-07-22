package com.biteacon;

import com.biteacon.constants.BotConfig;
import net.glxn.qrgen.javase.QRCode;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
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
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String responseMessageText = CommandOrchestrator.getInstance().matchCommands(messageText);

            // get QR file from text using defaults
            File file = QRCode.from(messageText).file();
            SendPhoto message = new SendPhoto().setChatId(chatId).setPhoto(file).setCaption(responseMessageText);
//            SendMessage message = new SendMessage() // Create a message object object
//                    .setChatId(chatId)
//                    .setText(responseMessageText);
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
