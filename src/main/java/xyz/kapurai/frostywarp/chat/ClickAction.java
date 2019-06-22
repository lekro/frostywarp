/*
 * Copyright 2019 lekro (kapurai).
 *
 * This file is part of frostywarp.
 *
 * frostywarp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * frostywarp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with frostywarp.  If not, see <https://www.gnu.org/licenses/>.
 */


package xyz.kapurai.frostywarp.chat;

import com.google.gson.annotations.SerializedName;

public class ClickAction {

    public static enum Type {
        @SerializedName("open_url") OPEN_URL,
        @SerializedName("open_file") OPEN_FILE,
        @SerializedName("run_command") RUN_COMMAND,
        @SerializedName("suggest_command") SUGGEST_COMMAND,
        @SerializedName("change_page") CHANGE_PAGE;
    }

    private Type action;
    private String value;

    public ClickAction(Type type, String value) {
        this.action = type;
        this.value = value;
    }
    
}
