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

public class HoverAction {

    public static enum Type {
        @SerializedName("show_text") SHOW_TEXT,
        @SerializedName("show_achievement") SHOW_ACHIEVEMENT,
        @SerializedName("show_item") SHOW_ITEM,
        @SerializedName("show_entity") SHOW_ENTITY;
    }

    private Type action;
    private Object value;

    public HoverAction(Type type, Object value) {
        this.action = type;
        this.value = value;
    }
    
}
