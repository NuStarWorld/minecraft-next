/*
 *    minecraft-next
 *    Copyright (C) 2025  ideal-state
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package team.idealstate.minecraft.next.spigot.example.configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import team.idealstate.sugar.validate.annotation.NotNull;
import team.idealstate.sugar.next.context.annotation.component.Configuration;
import team.idealstate.sugar.next.context.annotation.feature.Environment;

@Configuration(uri = "/example/config.yml", release = "embedded:/example/config.yml")
@Environment("development")
@Data
public class ExampleConfiguration {

    @NotNull
    private Byte oneByte;

    @NotNull
    private Short oneShort;

    @NotNull
    private Integer oneInt;

    @NotNull
    private Long oneLong;

    @NotNull
    private Float oneFloat;

    @NotNull
    private Double oneDouble;

    @NotNull
    private Character oneChar;

    @NotNull
    private Boolean oneBoolean;

    @NotNull
    private String oneString;

    @NotNull
    private ExampleEnum oneEnum;

    @NotNull
    private Object[] oneArray;

    @NotNull
    private List<Integer> oneList;

    @NotNull
    private Set<String> oneSet;

    @NotNull
    private Map<String, Object> oneMap;

    public enum ExampleEnum {
        FIRST,
        SECOND
    }
}
