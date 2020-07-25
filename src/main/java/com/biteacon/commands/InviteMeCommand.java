package com.biteacon.commands;

import com.biteacon.POJOs.GraphqlResponse;
import com.biteacon.POJOs.SupersetAccount;
import com.biteacon.constants.Messages;
import com.biteacon.entities.CommandRequest;
import com.biteacon.entities.CommandResponse;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.http.HttpResponse;

public class InviteMeCommand implements Command {
    Gson gson = new Gson();

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (request.getChatId() != 0) {
            try {
                HttpResponse<?> response = SearchService.getInstance().getUserById(request.getChatId());
                String responseBodyString = response.body().toString();
                GraphqlResponse user = gson.fromJson(responseBodyString, GraphqlResponse.class);

                String formattedInvite = "nothing..";
                SupersetAccount supersetAccount;
                if (isUserExists(user)) {
                    supersetAccount = user.getData().getBotUsers().get(0).getSupersetAccount();
                    if (supersetAccount != null) {
//                        todo: return supersetAccount to user "message you already.."
                        formattedInvite = "You have already got an account at biteacon.xyz\n<b>username:</b> <code>" +
                                supersetAccount.getUsername() + "</code>\n<b>password:</b> <code>" +
                                supersetAccount.getPassword() + "</code> (If you haven't changed it)";
                    } else {
//                        todo: create new superset связь
//                        response = SearchService.getInstance().getFreeSupersetAccount();
//                        responseBodyString = response.body().toString();
//                        user = gson.fromJson(responseBodyString, GraphqlResponse.class);
//
//                        if (isSupersetAccountCorrect(user)) {
//                            formattedInvite = "ndfadlfkdsf";
//                        } else {
//                            formattedInvite = "not found no one free account...";
//                        }
                    }
                } else {
//                    todo: get free superset user
                    response = SearchService.getInstance().getFreeSupersetAccount();
                    responseBodyString = response.body().toString();
                    user = gson.fromJson(responseBodyString, GraphqlResponse.class);

                    if (!isSupersetAccountCorrect(user)) {
                        formattedInvite = "not found no one free account...";
                    } else {
//                    todo: create new user
                        response = SearchService.getInstance().insertUser(
                                request.getChatId(),
                                user.getData().getGetFreeSupersetAccount().get(0).getUsername()
                        );
                        responseBodyString = response.body().toString();
                        GraphqlResponse insertResponse = gson.fromJson(responseBodyString, GraphqlResponse.class);
                        if (isInsertResponseCorrect(insertResponse)) {
                            supersetAccount = insertResponse.getData().getInsertBotUsers().getReturning().get(0).getSupersetAccount();
                            formattedInvite = "Congratulations! You have received an invitation to biteacon.xyz !\n<b>username:</b> <code>" +
                                    supersetAccount.getUsername() + "</code>\n<b>password:</b> <code>" + supersetAccount.getPassword() +
                                    "</code> (at login, change to a new one)";
                        }
                    }
                }

//                formattedInvite = TransformationService.getInstance().getFormattedBlock(user);
                return new CommandResponse(formattedInvite);
            } catch (SearchException | JsonSyntaxException | NullPointerException e) {
                e.printStackTrace();
            }
        }
//        return null;
        return new CommandResponse(Messages.INVITE_ME_COMMAND);
    }

    private boolean isSupersetAccountCorrect(GraphqlResponse user) {
        return user != null && user.getData() != null && user.getData().getGetFreeSupersetAccount() != null && 
                user.getData().getGetFreeSupersetAccount().size() > 0 &&
                user.getData().getGetFreeSupersetAccount().get(0) != null &&
                user.getData().getGetFreeSupersetAccount().get(0).getPassword() != null &&
                user.getData().getGetFreeSupersetAccount().get(0).getUsername() != null;
    }

    private boolean isInsertResponseCorrect(GraphqlResponse insertResponse) {
        return insertResponse != null && insertResponse.getData() != null && insertResponse.getData().getInsertBotUsers() != null &&
                insertResponse.getData().getInsertBotUsers().getReturning() != null && 
                insertResponse.getData().getInsertBotUsers().getReturning().size() > 0 && 
                insertResponse.getData().getInsertBotUsers().getReturning().get(0).getId() != null &&
                insertResponse.getData().getInsertBotUsers().getReturning().get(0).getSupersetAccount() != null &&
                insertResponse.getData().getInsertBotUsers().getReturning().get(0).getSupersetAccount().getPassword() != null &&
                insertResponse.getData().getInsertBotUsers().getReturning().get(0).getSupersetAccount().getUsername() != null;
    }

    private boolean isUserExists(GraphqlResponse user) {
        return user != null && user.getData() != null && user.getData().getBotUsers() != null && user.getData().getBotUsers().size() > 0;
    }
}