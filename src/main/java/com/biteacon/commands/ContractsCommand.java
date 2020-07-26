package com.biteacon.commands;

import com.biteacon.POJOs.GraphqlResponse;
import com.biteacon.entities.CommandRequest;
import com.biteacon.entities.CommandResponse;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.biteacon.services.TransformationService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ContractsCommand implements Command {
    Gson gson = new Gson();

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            HttpResponse<?> response = SearchService.getInstance().getContracts();
            String responseBodyString = response.body().toString();
            GraphqlResponse contracts = gson.fromJson(responseBodyString, GraphqlResponse.class);
            String formattedAccounts = TransformationService.getInstance().getFormattedContracts(contracts);
            if (formattedAccounts != null) {
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                keyboardButtonsRow1.add(new InlineKeyboardButton().setText("\u2B05").setCallbackData("CallFi4a"));
                keyboardButtonsRow1.add(new InlineKeyboardButton().setText("\u27A1").setCallbackData("CallFi4a"));
                rowList.add(keyboardButtonsRow1);
                InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
                keyboard.setKeyboard(rowList);
                return new CommandResponse(null, formattedAccounts, null);
            }
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
