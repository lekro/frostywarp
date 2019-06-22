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

public enum Color {
    @SerializedName("black") BLACK,
    @SerializedName("dark_blue") DARK_BLUE,
    @SerializedName("dark_green") DARK_GREEN,
    @SerializedName("dark_aqua") DARK_AQUA,
    @SerializedName("dark_red") DARK_RED,
    @SerializedName("dark_purple") DARK_PURPLE,
    @SerializedName("gold") GOLD,
    @SerializedName("gray") GRAY,
    @SerializedName("dark_gray") DARK_GRAY,
    @SerializedName("blue") BLUE,
    @SerializedName("green") GREEN,
    @SerializedName("aqua") AQUA,
    @SerializedName("red") RED,
    @SerializedName("light_purple") LIGHT_PURPLE,
    @SerializedName("yellow") YELLOW,
    @SerializedName("white") WHITE;
}
