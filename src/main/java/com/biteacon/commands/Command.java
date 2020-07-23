package com.biteacon.commands;

import com.biteacon.entities.CommandResponse;

public interface Command {
    CommandResponse execute(String key);
}
