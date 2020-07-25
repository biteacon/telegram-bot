package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.GraphqlResponse;
import com.biteacon.commands.Command;
import com.biteacon.entities.CommandRequest;
import com.biteacon.entities.CommandResponse;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.biteacon.services.TransformationService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.glxn.qrgen.javase.QRCode;

import java.io.File;
import java.net.http.HttpResponse;

public class AccountByAddressCommand implements Command {
    Gson gson = new Gson();

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            HttpResponse<?> response = SearchService.getInstance().getAccountByAddress(request.getRequestMessage());
            String responseBodyString = response.body().toString();
            GraphqlResponse account = gson.fromJson(responseBodyString, GraphqlResponse.class);
            String formattedAccount = TransformationService.getInstance().getFormattedAccount(account);
            if (isAccountCorrect(account, formattedAccount)) {
                File file = getQrCode(account.getData().getLikelibAccounts().get(0).getAddress());
                return new CommandResponse(file, formattedAccount);
            }
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isAccountCorrect(GraphqlResponse account, String formattedAccount) {
        return formattedAccount != null && account != null && account.getData() != null && account.getData().getLikelibAccounts() != null &&
                account.getData().getLikelibAccounts().size() > 0;
    }

    private File getQrCode(String address) {
        return QRCode.from(address).file();
    }
}
